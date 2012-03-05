package org.sooo;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

import java.util.Collections;
import java.util.HashMap;
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
		assertThat(immutableMap.get("kr"), is("Korean"));
		assertThat(immutableMap.get("en"), is("English"));
		assertThat(immutableMap.get("jp"), is(nullValue()));

		// when
		map.put("jp", "Japanese");

		// then
		// ImmutableMap contains its own data.
		assertThat(immutableMap.get("jp"), is(nullValue()));

		// when
		immutableMap.put("jp", "Japanese");

		// then
		fail("UnsupportedOperationException expected");
	}

	@Test(expected = UnsupportedOperationException.class)
	public void tryToMutateClassicImmutableMap() {
		// given
		Map<String, String> map = new HashMap<>();
		map.put("kr", "Korean");
		map.put("en", "English");
		Map<String, String> immutableMap = Collections.unmodifiableMap(map);
		assertThat(immutableMap.get("kr"), is("Korean"));
		assertThat(immutableMap.get("en"), is("English"));

		// when
		map.put("jp", "Japanese");

		// then
		// Collections.unmodifiableMap() is just a view of original map.
		assertThat(immutableMap.get("jp"), is("Japanese"));

		// when
		immutableMap.put("jp", "Japanese");

		// then
		fail("UnsupportedOperationException expected");
	}
}
