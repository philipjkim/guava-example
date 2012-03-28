package org.sooo.collect;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Iterables;

public class IterablesTest {

	List<String> iterable1;
	List<String> iterable2;

	@Before
	public void setUp() {
		iterable1 = Arrays.asList("aa", "bb", "cc", "dd", "ee");
		iterable2 = Arrays.asList("aa", "cc", "ee", "gg", "ii");
	}

	@Test
	public void concatThenSize() {
		// when
		Iterable<String> concated = Iterables.concat(iterable1, iterable2);

		// then
		assertThat(Iterables.size(concated), is(10));
	}

	@Test
	public void frequency() {
		// given
		Iterable<String> concated = Iterables.concat(iterable1, iterable2);

		// when
		assertThat(Iterables.frequency(concated, "aa"), is(2));
		assertThat(Iterables.frequency(concated, "bb"), is(1));
		assertThat(Iterables.frequency(concated, "cc"), is(2));
		assertThat(Iterables.frequency(concated, "dd"), is(1));
		assertThat(Iterables.frequency(concated, "ee"), is(2));
		assertThat(Iterables.frequency(concated, "gg"), is(1));
		assertThat(Iterables.frequency(concated, "ii"), is(1));
	}

	@Test
	public void toArray() {
		// when
		String[] array = Iterables.toArray(iterable1, String.class);

		// then
		assertThat(array.length, is(5));
		assertThat(array[0], is("aa"));
		assertThat(array[1], is("bb"));
		assertThat(array[2], is("cc"));
		assertThat(array[3], is("dd"));
		assertThat(array[4], is("ee"));
	}
}
