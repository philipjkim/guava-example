package org.sooo.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Reference:
 * <code><a href="http://stackoverflow.com/questions/7748618/subscribing-to-an-event-bus">
 * stackoverflow</a></code>
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
