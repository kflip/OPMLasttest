package at.ac.tuwien.dse.resttest;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.containsString;

import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;

public class DoctorTest {

	private static final String URL = "http://opmfrontend3.cloudfoundry.com/doctor/";
	
	@Test
	public void get() {
		expect().body(containsString("doctors")).when().get(URL +"index");
	}
	
	@Test
	public void post() {
		String message = "{\"name\":\"Dr. Hochweiß\"}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
	}

	@Test
	public void delete() {
		// add a hospital
		String message = "{\"name\":\"Dr. Gott\"}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
		
		// get an id
		String json = RestAssured.get(URL +"index").asString();
		String id = JsonPath.from(json).getString("doctors[0].id");
		
		// delete hospital
		expect().statusCode(200).when().delete(URL + id);
	}
	
	@Test
	public void getSingle() {
		// add a hospital
		String message = "{\"name\":\"Dr. Aufmesser\"}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
		
		// get an id
		String json = RestAssured.get(URL +"index").asString();
		String id = JsonPath.from(json).getString("doctors[0].id");
		
		// delete hospital
		expect().statusCode(200).when().get(URL + id);
	}
}
