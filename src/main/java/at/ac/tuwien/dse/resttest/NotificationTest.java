package at.ac.tuwien.dse.resttest;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;

public class NotificationTest {

	private static final String URL = "http://opmfrontend3.cloudfoundry.com/notification/";
	
	private static final String MESSAGE = "{\"receiverId\":\"123\",\"receiverType\":\"Patient\",\"message\":{\"type\":\"Booked\",\"request\":null}}";
	
	@Test
	public void get() {
		expect().body(containsString("notification")).when().get(URL +"index");
	}
	
	@Test
	public void post() {
		String receiver = "{\"receiverId\":\"123\",\"receiverType\":\"Patient\"}";
		String request = "{\"distance\":1,\"patientId\":\"123\",\"startTimeWindow\":\"2012-06-17\",\"endTimeWindow\":\"2012-06-18\"}";
		request = "{}";
		String msg = "{\"type\":\"Booked\",\"request\":"+ request +"}";
		msg = "{}";
		String message = MESSAGE;
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
	}

	@Test
	public void delete() {
		// add
		String message = MESSAGE;
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
		
		// get an id
		String json = RestAssured.get(URL +"index").asString();
		String id = JsonPath.from(json).getString("notifications[0].id");
		
		// delete
		expect().statusCode(200).when().delete(URL + id);
	}
	
	@Test
	public void getSingle() {
		// add
		String message = MESSAGE;
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
		
		// get an id
		String json = RestAssured.get(URL +"index").asString();
		String id = JsonPath.from(json).getString("notifications[0].id");
		
		// delete
		expect().statusCode(200).when().get(URL + id);
	}

}
