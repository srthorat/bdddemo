package stepdefs;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;

import org.slf4j.Logger;
import bdd.runner.RunnerTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static org.junit.Assert.fail;

public class StepDef {
	private static final Logger log = getLogger(StepDef.class);
	RequestSpecification _REQ_SPEC;
	Response _RESP;
	ValidatableResponse _VALIDATABLE_RESP;
	
	@Given("I have API")
	public void i_have_API() {		
   log.info("I have Base Rest API URL: " + RunnerTest.baseUrl);
   
	}	
	
	@When("^I send a (.+) request to (.+) with ID (.+) containing:$")
	    public void send_request_request_containing(String httpVerb, String url, int ID, String body) throws IOException {
	        log.info("I send a " + httpVerb + " request to url: \"Base_URL+" + url +ID+"\" containing body: " + body);
	        
	        
	         _REQ_SPEC = given().
	                	relaxedHTTPSValidation().
	                    contentType(ContentType.JSON).
	                    body(body);
	        
	        switch (httpVerb.toUpperCase()) {
	            case "GET":    
	            		_RESP = _REQ_SPEC.
	                        	when().
	                            	get(RunnerTest.baseUrl+url+ID);
	            		
	                     _VALIDATABLE_RESP = _RESP.
	                    		then();
	                break;
	            case "POST":
	            		_RESP = _REQ_SPEC.
	                        	when().
	                            	post(RunnerTest.baseUrl+url);
	            		
	            		_VALIDATABLE_RESP= _RESP.
	            					then();
	                break;
	            case "PUT":
	            		_RESP = _REQ_SPEC.
	                        	when().
	                            	put(RunnerTest.baseUrl+url);
	            		_VALIDATABLE_RESP = _RESP.
	            				then();
	                break;
	            case "DELETE":
	            		_RESP = _REQ_SPEC.
	            				when().
	            					put(RunnerTest.baseUrl+url);
	            		_VALIDATABLE_RESP = _RESP.
	            				then();
	                break;
	            default:
	                fail("http verb " + httpVerb + " not supported for " + RunnerTest.baseUrl+url);
	                break;
	                }
	        log.info("The Responce recived for "+ httpVerb +" request : "+ _RESP.asString());
	        }
	 	
	@Then("^the response status should be (.+)$")
		public void the_response_status_should_be(int responseStatus) {
		_VALIDATABLE_RESP.statusCode(responseStatus);
		log.info("The Responce status Received is : "+ responseStatus);
		}
	
	@Then("response json body contain {string} should be {string}")
	public void response_json_body_contain_status_as(String string, String string2) {
			
		switch (string2.toUpperCase()) {
		case "FIRST_NAME":
			_VALIDATABLE_RESP.body(string, equalTo(RunnerTest.first_name));
			log.info("Responce json body contain Frist name at \""+string+"\" as " + RunnerTest.first_name);
			break;
		case "LAST_NAME":
			_VALIDATABLE_RESP.body(string, equalTo(RunnerTest.last_name));
			log.info("Responce json body contain last name at \""+string+"\" as " + RunnerTest.last_name);
			break;
		case "EMAIL":
			_VALIDATABLE_RESP.body(string, equalTo(RunnerTest.email));
			log.info("Responce json body contain email id at \""+string+"\" as " + RunnerTest.email);
			break;
		default:
			fail("Parameter not supported validate in API Responce");
			break;
			
		}
}
}
