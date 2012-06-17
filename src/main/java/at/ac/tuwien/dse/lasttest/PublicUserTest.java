package at.ac.tuwien.dse.lasttest;


import static org.junit.Assert.*;
import junit.framework.TestCase;

import com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.matcher.RestAssuredMatchers.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PublicUserTest extends TestCase
{
	public PublicUserTest(String name)
	{
		super(name);
	}
	
	@Test
	public void TestCase1()
	{
		try 
		{
			ListAllHospitalsTestCase();
			Thread.sleep(1000);
			ListAllOpSlots();
			Thread.sleep(1000);
			for(int i = 0; i < 5; i++)
			{
				ListAllNotifications();
				Thread.sleep(1000);
			}
			
		} 
		catch (InterruptedException e) 
		{
			
		}
	}

	@Test
	public void ListAllHospitalsTestCase() 
	{
		com.jayway.restassured.RestAssured.expect().when().get("http://opmfrontend21.cloudfoundry.com/hospital/index/");
	}
	
	@Test
	public void ListAllOpSlots()
	{
		com.jayway.restassured.RestAssured.expect().when().get("http://opmfrontend21.cloudfoundry.com/opslot/index/");
	}

	@Test
	public void ListAllDoctors()
	{
		com.jayway.restassured.RestAssured.expect().when().get("http://opmfrontend21.cloudfoundry.com/doctor/index/");
	}
	
	@Test
	public void ListAllNotifications()
	{
		com.jayway.restassured.RestAssured.expect().when().get("http://opmfrontend21.cloudfoundry.com/notification/index/");
	}
	
	@Test
	public void ListAllPatients()
	{
		com.jayway.restassured.RestAssured.expect().log().all().when().get("http://opmfrontend21.cloudfoundry.com/patient/index/");
	}
}
