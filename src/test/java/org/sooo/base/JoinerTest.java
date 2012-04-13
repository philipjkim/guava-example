package org.sooo.base;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

public class JoinerTest {

	@Test
	public void joinSkippingNulls() {
		Joiner joiner = Joiner.on("; ").skipNulls();
		String joined = joiner.join("Harry", null, "Ron", "Hermione");
		assertThat(joined, is("Harry; Ron; Hermione"));
	}

	@Test
	public void joinWithNulls() {
		Joiner joiner = Joiner.on("; ").useForNull("null");
		String joined = joiner.join("Harry", null, "Ron", "Hermione");
		assertThat(joined, is("Harry; null; Ron; Hermione"));
	}

	@Test(expected = NullPointerException.class)
	public void joinWithNullsWithoutUsingUseForNull() {
		Joiner joiner = Joiner.on("; ");
		joiner.join("Harry", null, "Ron", "Hermione");
		fail("NullPointerException expected");
	}

	@Test
	public void joinUsingJoinerObjectNotInstance() {
		String joined = Joiner.on(",").join(Arrays.asList(1, 5, 7));
		assertThat(joined, is("1,5,7"));
	}

	@Test
	public void createQueryStringUsingMapJoiner() {
		Map<String, String> input = Maps.newTreeMap();
		input.put("id", "id001");
		input.put("page", "2");
		input.put("svc", "mail");
		String result = Joiner.on("&").withKeyValueSeparator("=").join(input);
		assertThat(result, is("id=id001&page=2&svc=mail"));
	}
}
