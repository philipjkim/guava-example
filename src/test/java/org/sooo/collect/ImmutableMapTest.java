package org.sooo.collect;

import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public class ImmutableMapTest {

	@Test
	public void createImmutableMapUsingCopyOf() {
		// given
		Map<Integer, String> intToWordMap = Maps.newHashMap();
		intToWordMap.put(1, "one");
		intToWordMap.put(2, "two");
		intToWordMap.put(3, "three");

		// when
		ImmutableMap<Integer, String> INT_TO_WORD = ImmutableMap
				.copyOf(intToWordMap);

		// then
		assertThat(INT_TO_WORD.get(1), is("one"));
		assertThat(INT_TO_WORD.get(2), is("two"));
		assertThat(INT_TO_WORD.get(3), is("three"));
	}

	@Test
	public void createImmutableMapUsingOf() {
		// when
		ImmutableMap<Integer, String> INT_TO_WORD = ImmutableMap.of(1, "one",
				2, "two", 3, "three");

		// then
		assertThat(INT_TO_WORD.get(1), is("one"));
		assertThat(INT_TO_WORD.get(2), is("two"));
		assertThat(INT_TO_WORD.get(3), is("three"));
	}

	@Test
	public void createImmutableMapUsingBuilder() {
		// when
		ImmutableMap<Integer, String> INT_TO_WORD = new ImmutableMap.Builder<Integer, String>()
				.put(1, "one").put(2, "two").put(3, "three").build();

		// then
		assertThat(INT_TO_WORD.get(1), is("one"));
		assertThat(INT_TO_WORD.get(2), is("two"));
		assertThat(INT_TO_WORD.get(3), is("three"));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void tryToMutateImmutableMap() {
		// given
		ImmutableMap<String, String> immutableMap = ImmutableMap.of("kr",
				"Korean", "en", "English");

		// when
		immutableMap.put("jp", "Japanese");

		// then
		fail("UnsupportedOperationException expected");
	}

	@Test(expected = UnsupportedOperationException.class)
	public void tryToMutateImmutableMapUsingCopyOf() {
		// given
		Map<String, String> map = Maps.newHashMap();
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
