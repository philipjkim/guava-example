package org.sooo.base;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.base.CaseFormat;

public class CaseFormatTest {

	@Test
	public void upperUnderscoreToLowerCamel() {
		// given
		String input = "THE_INPUT_CONSTANT";

		// when
		String converted = CaseFormat.UPPER_UNDERSCORE.to(
				CaseFormat.LOWER_CAMEL, input);

		// then
		assertThat(converted, is("theInputConstant"));
	}

	@Test
	public void upperUnderscoreToLowerHyphen() {
		// given
		String input = "THE_INPUT_CONSTANT";

		// when
		String converted = CaseFormat.UPPER_UNDERSCORE.to(
				CaseFormat.LOWER_HYPHEN, input);

		// then
		assertThat(converted, is("the-input-constant"));
	}

	@Test
	public void upperUnderscoreToLowerUnderscore() {
		// given
		String input = "THE_INPUT_CONSTANT";

		// when
		String converted = CaseFormat.UPPER_UNDERSCORE.to(
				CaseFormat.LOWER_UNDERSCORE, input);

		// then
		assertThat(converted, is("the_input_constant"));
	}

	@Test
	public void upperUnderscoreToUpperCamel() {
		// given
		String input = "THE_INPUT_CONSTANT";

		// when
		String converted = CaseFormat.UPPER_UNDERSCORE.to(
				CaseFormat.UPPER_CAMEL, input);

		// then
		assertThat(converted, is("TheInputConstant"));
	}
}
