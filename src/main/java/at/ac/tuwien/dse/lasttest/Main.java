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
		int userCount = 100;				// Bei 100 Usern wurden im Testlauf 98 in der entsprechenden Zeit fertig
		long maxTimeInMillis = 14000; 		// 7 Testaufrufe mit 1 Sekunde Pause dazwischen.
		Test test = new PublicUserTest("TestCase1");
		Test timedTest = new TimedTest(test, maxTimeInMillis);
		Test loadTest = new LoadTest(timedTest, userCount);
		return loadTest;
	}

}
