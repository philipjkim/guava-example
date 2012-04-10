package org.sooo.eventbus;

import org.junit.Test;

import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Ranges;
import com.google.common.eventbus.EventBus;

public class EventBusTest {

	@Test
	public void registerAndMakeEvent() {
		EventBus eventBus = new EventBus();
		eventBus.register(new EventRecorder());
		for (int i : Ranges.closedOpen(0, 10).asSet(DiscreteDomains.integers()))
			eventBus.post(Integer.toString(i));
	}
}
