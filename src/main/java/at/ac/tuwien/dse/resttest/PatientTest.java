package at.ac.tuwien.dse.resttest;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.containsString;

import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;

public class PatientTest {

	private static final String URL = "http://opmfrontend3.cloudfoundry.com/patient/";
	
	@Test
	public void get() {
		expect().body(containsString("patients")).when().get(URL +"index");
	}
	
	@Test
	public void post() {
		String message = "{\"firstName\":\"Franz\",\"lastName\":\"Meier\",\"gender\":true,\"location\":[1,1]}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
	}

	@Test
	public void delete() {
		// add
		String message = "{\"firstName\":\"Maria\",\"lastName\":\"Müller\",\"gender\":false,\"location\":[2,2]}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
		
		// get an id
		String json = RestAssured.get(URL +"index").asString();
		String id = JsonPath.from(json).getString("patients[0].id");
		
		// delete
		expect().statusCode(200).when().delete(URL + id);
	}
	
	@Test
	public void getSingle() {
		// add
		String message = "{\"firstName\":\"Martin\",\"lastName\":\"Moser\",\"gender\":true,\"location\":[3,3]}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
		
		// get an id
		String json = RestAssured.get(URL +"index").asString();
		String id = JsonPath.from(json).getString("patients[0].id");
		
		// delete
		expect().statusCode(200).when().get(URL + id);
	}

}
