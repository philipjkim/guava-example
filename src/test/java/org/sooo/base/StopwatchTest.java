package org.sooo.base;

import org.junit.Test;

import com.google.common.base.Stopwatch;

public class StopwatchTest {

	@Test
	public void getElapsedTimeForSomeTask() throws InterruptedException {
		Stopwatch stopwatch = new Stopwatch().start();
		Thread.sleep(100);
		stopwatch.stop();
		long millis = stopwatch.elapsedMillis();
		System.out.println("Elapsed millis: " + millis);
	}
}
