@Login_api
Feature: This feature is to test the Login scenarios

@Test_Create_user_login_api
Scenario: This scenario is to create user login service
	When I send valid email and pwd to login user with end point
	Then I should be login successfully
	
@Test_get_User_Details_api
Scenario: This scenario is get the user details
	When I send authorization tokens for user details with end point
	Then the user details should be retrieved successfull
	
@Test_Create_User_api
Scenario: This scenario is Create the user
	When I send authorization tokens and create user with end point
	Then the user created successfully
	
@Test_Delete_User_api
Scenario: This scenario is Delete the existing user
	When I send authorization tokens and delete user with end point
	Then the user deleted successfully	