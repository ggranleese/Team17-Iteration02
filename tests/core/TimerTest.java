package core;

import junit.framework.TestCase;

public class TimerTest extends TestCase{

	public void test() {
		long startTime = System.nanoTime();
		Timer t = new Timer();
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);
		
		assertTrue(t > 10 && t < 11);
	}

}
