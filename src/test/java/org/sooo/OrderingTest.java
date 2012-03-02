package org.sooo;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

public class OrderingTest {

	private List<String> words = Arrays.asList("I", "am", "learning", "how",
			"to", "use", "Guava");

	@Test
	public void orderStringByLength() {
		Ordering<String> byLengthOrdering = new Ordering<String>() {
			@Override
			public int compare(String left, String right) {
				return Ints.compare(left.length(), right.length());
			}
		};
		System.out.println(byLengthOrdering.sortedCopy(words));
		System.out.println(byLengthOrdering.reverse().sortedCopy(words));
	}

	@Test
	public void orderStringInANaturalWay() {
		System.out.println(Ordering.natural().sortedCopy(words));
		System.out.println(Ordering.natural().reverse().sortedCopy(words));
	}
}
