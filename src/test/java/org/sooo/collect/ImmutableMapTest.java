package org.sooo.collect;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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
}
