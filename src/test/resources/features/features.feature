@Get_users
Feature: Retrieve single user from List of users

		@Sanity
		Scenario: Get user by specifying ID
		Given I have API
		When I send a GET request to /api/users/ with ID 2 containing:
          """   
          """
		Then the response status should be 200
		And response json body contain "data.first_name" should be "first_name"
		And response json body contain "data.last_name" should be "last_name"
		And response json body contain "data.email" should be "email"