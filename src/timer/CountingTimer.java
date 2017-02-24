package timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * User: gsunderam
 * Date: Jul 14, 2015
 */
public class CountingTimer {
	private static int count = 0;

	public static void main(String[] args) {
		Timer t = new Timer("TimerThread");
		t.schedule(new CountingTask(), 1 * 1000, 2 * 1000);
		
		System.out.println("Exiting main()");
	}
	
	private static final class CountingTask extends TimerTask {
		@Override
		public void run() {
			count++;
			System.out.println("This is later");

			if (count >= 10) System.exit(0);
		}
	}
}
