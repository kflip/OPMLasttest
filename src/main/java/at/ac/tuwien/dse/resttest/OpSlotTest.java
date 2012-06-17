package at.ac.tuwien.dse.resttest;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.containsString;

import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;

public class OpSlotTest {

	private static final String URL = "http://opmfrontend3.cloudfoundry.com/opslot/";
	
	@Test
	public void get() {
		expect().body(containsString("opSlots")).when().get(URL +"index");
	}
	
	@Test
	public void post() {
		// get type id
		// TODO
		
		// get patient id
		// TODO
		
		// get doctor id
		// TODO
		
		String message = "{\"length\":1,\"startDate\":\"2012-06-17\",\"type\":{\"description\":\"Augen\"},\"patient\":{\"firstName\":\"Beate\",\"lastName\":\"Bauer\",\"gender\":false,\"location\":[4,4]},\"doctor\":{\"name\":\"Dr. Hochweiß\"}}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
	}

	@Test
	public void delete() {
		// add
		String message = "{\"length\":1,\"startDate\":\"2012-06-17\",\"type\":{\"description\":\"Augen\"},\"patient\":{\"firstName\":\"Beate\",\"lastName\":\"Bauer\",\"gender\":false,\"location\":[4,4]},\"doctor\":{\"name\":\"Dr. Hochweiß\"}}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
		
		// get an id
		String json = RestAssured.get(URL +"index").asString();
		String id = JsonPath.from(json).getString("opSlots[0].id");
		System.out.println(URL + id);
		// delete
		expect().statusCode(200).when().delete(URL + id);
	}
	
	@Test
	public void getSingle() {
		// add
		String message = "{\"length\":1,\"startDate\":\"2012-06-17\",\"type\":{\"description\":\"Augen\"},\"patient\":{\"firstName\":\"Beate\",\"lastName\":\"Bauer\",\"gender\":false,\"location\":[4,4]},\"doctor\":{\"name\":\"Dr. Hochweiß\"}}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(URL +"index");
		
		// get an id
		String json = RestAssured.get(URL +"index").asString();
		String id = JsonPath.from(json).getString("opSlots[0].id");
		
		// delete
		expect().statusCode(200).when().get(URL + id);
	}

}
