package org.sooo.base;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.google.common.base.Charsets;

public class CharsetsTest {

	private String string = "abcde";

	@Test
	public void dontDoThis() {
		try {
			string.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new AssertionError(e);
		}
	}

	@Test
	public void doThisInstead() {
		string.getBytes(Charsets.UTF_8);
	}
}
