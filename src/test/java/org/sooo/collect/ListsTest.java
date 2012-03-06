package org.sooo.collect;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class ListsTest {

	@Test
	public void createListBeforeJDK7() {
		List<String> list = new ArrayList<String>();
		list.add("alpha");
		list.add("beta");
		list.add("gamma");
		assertThat(list.size(), is(3));
	}

	@Test
	public void createListInJDK7() {
		List<String> list = new ArrayList<>();
		list.add("alpha");
		list.add("beta");
		list.add("gamma");
		assertThat(list.size(), is(3));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void createListUsingArrays() {
		List<String> list = Arrays.asList("alpha", "beta", "gamma");
		list.add("omega");
		fail("Arrays.asList should return an immutable list.");
	}

	@Test
	public void createListUsingLists() {
		List<String> list = Lists.newArrayList("alpha", "beta", "gamma");
		list.add("omega");
		assertThat(list.size(), is(4));
	}

	@Test
	public void charactersOfThenReverseThenTransform() {
		// given
		String string = "abracadabra";

		// when
		List<Character> characters = Lists.charactersOf(string);

		// then
		assertThat(characters.size(), is(11));
		assertThat(characters.get(1), is(new Character('b')));

		// when
		List<Character> reversed = Lists.reverse(characters);

		// then
		assertThat(reversed.size(), is(11));
		assertThat(reversed.get(1), is(new Character('r')));

		// when
		List<Character> capitalized = Lists.transform(reversed,
				new Function<Character, Character>() {
					@Override
					public Character apply(Character input) {
						return Character.toUpperCase(input);
					}
				});

		// then
		assertThat(capitalized.size(), is(11));
		assertThat(capitalized.get(1), is(new Character('R')));
	}
}
