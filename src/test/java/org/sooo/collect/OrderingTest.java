package org.sooo.collect;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

public class OrderingTest {

	private List<String> words = Arrays.asList("I", "am", "learning", "how",
			"to", "use", "Guava");
	private List<Integer> numbers = Arrays.asList(1, 0, 3, -4, 55, -1, 777);

	@Test
	public void orderStringByLength() {
		Ordering<String> byLengthOrdering = new Ordering<String>() {
			@Override
			public int compare(String left, String right) {
				return Ints.compare(left.length(), right.length());
			}
		};
		System.out.println("String.byLength: "
				+ byLengthOrdering.sortedCopy(words));
		System.out.println("String.byLength.reversed: "
				+ byLengthOrdering.reverse().sortedCopy(words));
	}

	@Test
	public void orderStringInANaturalWay() {
		System.out.println("String.natural: "
				+ Ordering.natural().sortedCopy(words));
		System.out.println("String.natural: "
				+ Ordering.natural().reverse().sortedCopy(words));
	}

	@Test
	public void orderIntegerInANaturalWay() {
		System.out.println("Integer.natural: "
				+ Ordering.natural().sortedCopy(numbers));
		System.out.println("Integer.natural.greatestOf(3): "
				+ Ordering.natural().greatestOf(numbers, 3));
		System.out.println("Integer.natural.reversed: "
				+ Ordering.natural().reverse().sortedCopy(numbers));
		System.out.println("Integer.natural.reversed.greatestOf(3): "
				+ Ordering.natural().reverse().greatestOf(numbers, 3));
	}
}
