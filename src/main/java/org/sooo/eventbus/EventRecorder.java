package org.sooo.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Reference: <code><a href="http://bit.ly/ImpKvF">
 * http://bit.ly/ImpKvF</a></code>
 * 
 * @author sooo
 * 
 */
public class EventRecorder {

	@Subscribe
	public void handleEvent(String eventId) {
		System.out.println("Event occurred: id=" + eventId);
	}
}
