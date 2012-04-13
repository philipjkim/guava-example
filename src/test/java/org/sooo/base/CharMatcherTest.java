package org.sooo.base;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.base.CharMatcher;

public class CharMatcherTest {

	@Test
	public void removeControlCharacters() {
		// given
		String input = "a\tb\nc";

		// when
		String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(input);

		// then
		assertThat(noControl, is("abc"));
	}

	@Test
	public void retainOnlyDigits() {
		// given
		String input = "a1b2#3&4";

		// when
		String theDigits = CharMatcher.DIGIT.retainFrom(input);

		// then
		assertThat(theDigits, is("1234"));
	}

	@Test
	public void trimWhitespaceAtEndsAndReplaceWhitespaceIntoSingleSpace() {
		// given
		String input = " a    b   c 1       2  3 ";

		// when
		String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(input, ' ');

		// then
		assertThat(spaced, is("a b c 1 2 3"));
	}

	@Test
	public void replaceAllDigitsToAsterisk() {
		// given
		String input = "abc123def456";

		// when
		String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(input, "*");

		// then
		assertThat(noDigits, is("abc***def***"));
	}

	@Test
	public void retainOnlyDigitsAndLowerCases() {
		// given
		String input = "2 Koreans And 3 Americans!!";

		// when
		String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(
				CharMatcher.JAVA_LOWER_CASE).retainFrom(input);

		// then
		assertThat(lowerAndDigit, is("2oreansnd3mericans"));
	}

	@Test
	public void removeNonASCIIAndTabCharacters() {
		// given
		String input = "This string has Korean characters(한국어) and a tab\tcharacter.";

		// when
		String filtered = CharMatcher.ASCII.and(CharMatcher.isNot('\t'))
				.retainFrom(input);

		// then
		assertThat(filtered,
				is("This string has Korean characters() and a tabcharacter."));
	}

	@Test
	public void countTabCharacter() {
		// given
		String input = "\ta\tb\tc\td";

		// when
		int count = CharMatcher.is('\t').countIn(input);

		// then
		assertThat(count, is(4));
	}
}
