package core;

import junit.framework.TestCase;
import model.GameTimer;

public class TimerTest extends TestCase{

	public void test() {
		GameTimer t = new GameTimer();
		long startTime = System.nanoTime();
		t.countdown();
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);
		
		assertTrue(duration > 0);
	}

}
