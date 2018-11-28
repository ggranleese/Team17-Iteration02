package core;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
	static int interval;
	static Timer timer;
	
	public boolean countdown() {
	    int delay = 1000;
	    int period = 1000;
	    timer = new Timer();
	    interval = (120);
	    System.out.println(interval);
	    timer.scheduleAtFixedRate(new TimerTask() {

	        public void run() {
	            System.out.println(setInterval());

	        }
	    }, delay, period);
	    
	    return true;
	}

	private static final int setInterval() {
	    if (interval == 1)
	        timer.cancel();
	    return --interval;
	}
	}