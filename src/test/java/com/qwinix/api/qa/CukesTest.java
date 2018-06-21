package com.qwinix.api.qa;


import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)


@CucumberOptions(features = "src/test/resources/features", tags = {"@Test_Create_user_login_api"})


public class CukesTest {

}