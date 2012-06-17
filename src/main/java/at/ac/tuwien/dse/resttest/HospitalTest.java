package at.ac.tuwien.dse.resttest;

import junit.framework.TestCase;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.matcher.RestAssuredMatchers;
import com.jayway.restassured.path.json.JsonPath;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HospitalTest {
	
	private static final String URL = "http://opmfrontend3.cloudfoundry.com/hospital/";
	
	@Test
	public void get() {
		expect().body(containsString("hospitals")).when().get(URL +"index");
	}
	
	@Test
	public void post() {
		String message = "{\"name\":\"AKH Wien\",\"location\":[1,1]}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
	}
	
	@Test
	public void delete() {
		// add a hospital
		String message = "{\"name\":\"AKH Wien\",\"location\":[1,1]}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
		
		// get an id
		String json = RestAssured.get(URL +"index").asString();
		String id = JsonPath.from(json).getString("hospitals[0].id");
		
		// delete hospital
		expect().statusCode(200).when().delete(URL + id);
	}
	
	@Test
	public void getSingle() {
		// add a hospital
		String message = "{\"name\":\"AKH Wien\",\"location\":[1,1]}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
		
		// get an id
		String json = RestAssured.get(URL +"index").asString();
		String id = JsonPath.from(json).getString("hospitals[0].id");
		
		// delete hospital
		expect().statusCode(200).when().get(URL + id);
	}
}
