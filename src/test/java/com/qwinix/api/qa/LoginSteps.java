package com.qwinix.api.qa;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.jar.JarException;

import org.apache.commons.logging.Log;
import org.json.JSONException;
import org.json.JSONObject;

import com.jayway.restassured.response.Response;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;

public class LoginSteps {
	private Response response;

	@When("^I send valid email and pwd to login user with end point$")
	public void i_send_valid_email_and_pwd_to_login_user_with_end_point() throws Throwable {
		Map<String, Object> jsonAsMap = new HashMap<>();
		String email = "ksurendra@gmail.com";
		jsonAsMap.put("email", email);
		jsonAsMap.put("password", "qwinix123!");
		String json="{ \"email\": \"ksurendra@gmail.com\", \"password\": \"qwinix123!\"}";
		given().contentType("application/json").body(json).log().body()
		.baseUri("http://qblog-api.qwinix.net").basePath("login").when().post();
		Serenity.setSessionVariable("email").to(email);
	}

	@Then("^I should be login successfully$")
	public void i_should_be_login_successfully() throws Throwable {
	    then().statusCode(200);
	    System.out.println("success");
		String response_msg = then().extract().path("message");
		String token_id = then().extract().path("id_token");
		String actualmsg= "Logged In Successfully";
		assertThat("Login Succesfully", actualmsg.equals(response_msg));
		System.out.println("success validation");
		Serenity.setSessionVariable("token_id").to(token_id);
	}
	
	
	
	
	
	@When("^I send authorization tokens for user details with end point$")
	public void i_send_authorization_tokens_for_user_details_with_end_point() throws Throwable {
		String email = Serenity.sessionVariableCalled("email").toString();
		String token_id = Serenity.sessionVariableCalled("token_id").toString();
		given().header("Authorization", "Bearer "+token_id).header("email",email).contentType("application/json").log().
		body().baseUri("http://qblog-api.qwinix.net")
		.basePath("posts").when().get();
	}


	@Then("^the user details should be retrieved successfull$")
	public void the_user_details_should_be_retrieved_successfull() throws Throwable {
		then().statusCode(200);
		String message = then().extract().path("message");
		String expected_msg = "List of posts";
		System.out.println(message);
		assertThat("Get user details Succesfully",message.equals(expected_msg));
	}
	
	
	
	
	
	@When("^I send authorization tokens and create user with end point$")
	public void i_send_authorization_tokens_and_create_user_with_end_point() throws Throwable {
		String email = Serenity.sessionVariableCalled("email").toString();
		String token_id = Serenity.sessionVariableCalled("token_id").toString();
		String json= "{\"title\": \"my post\",\"description\": \"my description\",\"content\":\"my first content\"}";
		given().header("Authorization", "Bearer "+token_id).header("email",email).contentType("application/json")
		.body(json).log().body().baseUri("http://qblog-api.qwinix.net")
		.basePath("posts").when().post();
		 String id = then().extract().path("post.id").toString();
		 System.out.println(id);
		 Serenity.setSessionVariable("id").to(id);
		
	}

	@Then("^the user created successfully$")
	public void the_user_created_successfully() throws Throwable {
	   then().statusCode(200);
	   String expected_msg = "Post created successfully";
	   String actual_msg = then().extract().path("message");
	   assertThat("Login Succesfully", expected_msg.equals(actual_msg));
		System.out.println("user created succesfully");
	}
	
	
	
	
	
	
	
	@When("^I send authorization tokens and delete user with end point$")
	public void i_send_authorization_tokens_and_delete_user_with_end_point() throws Throwable {
		String id = Serenity.sessionVariableCalled("id").toString();
		String email = Serenity.sessionVariableCalled("email").toString();
		String token_id = Serenity.sessionVariableCalled("token_id").toString();
		String json= "{\"title\": \"my post\",\"description\": \"my description\",\"content\":\"my first content\"}";
		given().header("Authorization", "Bearer "+token_id).header("email",email).contentType("application/json")
		.body(json).log().body().baseUri("http://qblog-api.qwinix.net")
		.basePath("posts/"+id).when().delete();
	    
	}

	@Then("^the user deleted successfully$")
	public void the_user_deleted_successfully() throws Throwable {
		 then().statusCode(200);
		 String expected_msg = "Post deleted successfully";
		   String actual_msg = then().extract().path("message");
		   assertThat("Deleted Succesfully", expected_msg.equals(actual_msg));
			System.out.println("user deleted successfully");
	    
	}
}
