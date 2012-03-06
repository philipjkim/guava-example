package org.sooo.base;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;
import org.sooo.Customer;
import org.sooo.DummyCustomerFactory;

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
		assertThat(map.get("firstName"), is(customer.getFirstName()));
		assertThat(map.get("lastName"), is(customer.getLastName()));
		assertThat(map.get("emailAddress"), is(customer.getEmailAddress()));
		assertThat(map.get("mobileNumber"), is(customer.getMobileNumber()));
		assertThat(map.get("yearOfBirth"),
				is(Integer.toString(customer.getYearOfBirth())));
		assertThat(map.get("vip"), is(Boolean.toString(customer.isVip())));
	}
}
