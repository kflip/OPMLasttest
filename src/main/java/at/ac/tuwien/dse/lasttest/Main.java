package at.ac.tuwien.dse.lasttest;

import com.clarkware.junitperf.LoadTest;
import com.clarkware.junitperf.TimedTest;

import junit.framework.Test;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		junit.textui.TestRunner.run(startTest());
	}
	
	public static Test startTest()
	{
		int userCount = 100;
		long maxTimeInMillis = 2000;
		Test test = new PublicUserTest("ListAllHospitalsTestCase");
		Test timedTest = new TimedTest(test, maxTimeInMillis);
		Test loadTest = new LoadTest(timedTest, userCount);
		return loadTest;
	}

}
