package org.sooo;

import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapMaker;

public class ImmutableMapTest {

	@Test(expected = UnsupportedOperationException.class)
	public void tryToMutateImmutableMap() {
		// given
		Map<String, String> map = new MapMaker().makeMap();
		map.put("kr", "Korean");
		map.put("en", "English");
		ImmutableMap<String, String> immutableMap = ImmutableMap.copyOf(map);

		// when
		immutableMap.put("jp", "Japanese");

		// then
		fail("UnsupportedOperationException expected");
	}
}
