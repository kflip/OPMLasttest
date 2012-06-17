package at.ac.tuwien.dse.resttest;

import static com.jayway.restassured.RestAssured.expect;

import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;

public class MatcherTest 
{
	private static final String HOSPITALURL = "http://opmfrontend41.cloudfoundry.com/hospital/";
	private static final String PATIENTURL = "http://opmfrontend41.cloudfoundry.com/patient/";
	private static final String MATCHERURL = "http://opmmatchernotifier41.cloudfoundry.com/matcher/";
	private static final String SLOTURL = "http://opmfrontend41.cloudfoundry.com/opslot/";
	private static final String NOTIFICATIONURL = "http://opmfrontend41.cloudfoundry.com/notification/";
	
	@Test
	public void TestReservation()
	{
		// Falls vorhanden Patienten löschen
		try
		{
			String id = "";
			do
			{
				// get an id
				String json = RestAssured.get(PATIENTURL +"index").asString();
				id = JsonPath.from(json).getString("patients[0].id");
				
				// delete
				expect().statusCode(200).when().delete(PATIENTURL + id);
			}while(!id.equals(""));

		}
		catch(Exception e)
		{
		}
		
		// Spitäler anlegen
		String message = "{\"name\":\"AKH Wien\",\"location\":[16.3738189,48.2081743]}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(HOSPITALURL +"index");
		
		message = "{\"name\":\"Krankenhaus Wiener Neustadt\",\"location\":[16.2483634,47.8154127]}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(HOSPITALURL +"index");
		
		message = "{\"name\":\"Krankenhaus Klagenfurt\",\"location\":[14.3172473,46.6258961]}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(HOSPITALURL + "index");
		
		message = "{\"firstName\":\"Maria\",\"lastName\":\"MÃ¼ller\",\"gender\":false,\"location\":[16.3698408,48.1988881]}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(PATIENTURL +"index");
		
		// ID des neu angelegten Patienten lesen
		String json = RestAssured.get(PATIENTURL +"index").asString();
		String id = JsonPath.from(json).getString("patients[0].id");
		
//		message = "{\"length\":1,\"startDate\":\"2012-06-17\",\"type\":{\"description\":\"Augen\"},\"doctor\":{\"name\":\"Dr. HochweiÃŸ\"}}";
//		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(SLOTURL +"index");
		
		message = "{\"distance\": 100, \"startTimeWindow\":\"2012-06-01\",\"endTimeWindow\":\"2012-06-30\",\"patientId\":\"" + id + "\"}";
		expect().statusCode(200).given().contentType("application/json; charset=UTF-8").body(message).when().post(MATCHERURL +"index");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		message = "{\"receiverId\":\"123\",\"receiverType\":\"Patient\",\"message\":{\"type\":\"Booked\",\"request\":null}}";
		json = RestAssured.get(NOTIFICATIONURL +"index").asString();
		System.out.println(json);
		
	}
}
