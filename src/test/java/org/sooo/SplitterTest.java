package org.sooo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;

public class SplitterTest {

	@Test
	public void splitSimpleStringToGetIterable() {
		// given
		String str = "apple,orange,lemon,strawberry";

		// when
		Iterable<String> fruits = Splitter.on(",").split(str);

		// then
		assertThat(Iterables.size(fruits), is(4));
		assertThat(Iterables.contains(fruits, "apple"), is(true));
		assertThat(Iterables.contains(fruits, "orange"), is(true));
		assertThat(Iterables.contains(fruits, "lemon"), is(true));
		assertThat(Iterables.contains(fruits, "strawberry"), is(true));
		assertThat(Iterables.contains(fruits, "pear"), is(false));
	}

	@Test
	public void splitComplicatedStringToGetMap() {
		// given
		Customer customer = new DummyCustomerFactory().create();
		String jsonString = new Gson().toJson(customer);
		String inputString = jsonString.replaceAll("[{}]", "");

		// when
		Map<String, String> map = Splitter
				.on(",")
				.omitEmptyStrings()
				.trimResults()
				.withKeyValueSeparator(
						Splitter.on(":").trimResults(CharMatcher.is('\"')))
				.split(inputString);

		// then
		assertThat(map.get("firstName"), is("John"));
		assertThat(map.get("lastName"), is("Doe"));
		assertThat(map.get("emailAddress"), is("john.doe@email.com"));
		assertThat(map.get("mobileNumber"), is("010-1234-5678"));
		assertThat(map.get("yearOfBirth"), is("1950"));
		assertThat(map.get("vip"), is("false"));
	}
}
