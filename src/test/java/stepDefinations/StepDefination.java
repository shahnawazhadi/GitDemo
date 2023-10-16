package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.cookie.IgnoreSpec;

import pojo.AddAdress;
import pojo.AddLocation;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefination extends Utils {
	
	ResponseSpecification res;
	RequestSpecification givreq;
	Response resspe;
	TestDataBuild data=new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload")
	

@Given("Add Place Payload {string} {string} {string}")
public void add_place_payload(String name, String language, String address) throws IOException {

		
		
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		
	  
	 
	  
	 givreq=given().spec(requestspecification()).body(data.addplacepayload(name,language,address));
}
	
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		//constructor will be called with value of resource which you pass
		APIResources resourceapi=APIResources.valueOf(resource);
		System.out.println(resourceapi.getResource());
		res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST")) {
		 resspe=givreq.when().post(resourceapi.getResource());
		}
		else if(method.equalsIgnoreCase("GET")) {
			
			resspe=givreq.when().get(resourceapi.getResource());
		}
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		//System.out.println(resspe.getStatusCode());
		assertEquals(resspe.getStatusCode(),200);
		
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expvalue) {
		//String ss=getJsonPath(resspe,keyvalue);
		
		//System.out.println(ss);
		
		assertEquals(getJsonPath(resspe,keyvalue),expvalue);
	  
	}
	

@Then("verify place_Id created maps to {string} using {string}")
public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
	 place_id=getJsonPath(resspe,"place_id");
	
	givreq=given().spec(requestspecification()).queryParam("place_id", place_id);
	
	user_calls_with_http_request(resource,"GET");
	String actnam=getJsonPath(resspe,"name");
	assertEquals(actnam,expectedname);
}

@Given("DeletePlace Payload")
public void delete_place_payload() throws IOException {
	givreq=given().spec(requestspecification()).body(data.deletePlacePayload(place_id));
}



}
