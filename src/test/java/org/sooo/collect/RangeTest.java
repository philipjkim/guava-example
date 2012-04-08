package org.sooo.collect;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Preconditions;
import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.Ranges;

public class RangeTest {

	@Test
	public void open() {
		System.out.println("## open ##");
		Range<Integer> range = Ranges.open(0, 10);
		System.out.println(range);
		printAllElements(range);
	}

	private void printAllElements(Range<Integer> range) {
		for (int i : range.asSet(DiscreteDomains.integers()))
			System.out.print(i + " ");
		System.out.println();
	}

	@Test
	public void closed() {
		System.out.println("## closed ##");
		Range<Integer> range = Ranges.closed(0, 10);
		System.out.println(range);
		printAllElements(range);
	}

	@Test
	public void closedOpen() {
		System.out.println("## closedOpen ##");
		Range<Integer> range = Ranges.closedOpen(0, 10);
		System.out.println(range);
		printAllElements(range);
	}

	@Test
	public void openClosed() {
		System.out.println("## openClosed ##");
		Range<Integer> range = Ranges.openClosed(0, 10);
		System.out.println(range);
		printAllElements(range);
	}

	@Test
	public void filterUsingLessThan() {
		List<Double> scores = Lists.newArrayList(3.0, 3.5, 4.0, 4.5, 5.0);
		double median = getMedian(scores);
		List<Double> belowMedian = Lists.newArrayList(Iterables.filter(scores,
				Ranges.lessThan(median)));
		System.out.println(belowMedian);
	}

	private double getMedian(List<Double> list) {
		Preconditions.checkNotNull(list);
		Preconditions.checkArgument(list.size() > 0);
		List<Double> copy = Lists.newArrayList(list);
		Collections.sort(copy);
		return copy.get(copy.size() / 2);
	}

	@Test
	public void isConnected() {
		assertThat(Ranges.closed(3, 5).isConnected(Ranges.open(5, 10)),
				is(true));
		assertThat(Ranges.closed(0, 9).isConnected(Ranges.closed(3, 4)),
				is(true));
		assertThat(Ranges.closed(0, 5).isConnected(Ranges.closed(3, 9)),
				is(true));
		assertThat(Ranges.open(3, 5).isConnected(Ranges.open(5, 10)), is(false));
		assertThat(Ranges.closed(1, 5).isConnected(Ranges.closed(6, 10)),
				is(false));
	}

	@Test
	public void intersection() {
		assertThat(Ranges.closed(3, 5).intersection(Ranges.open(5, 10)),
				is(Ranges.openClosed(5, 5)));
		assertThat(Ranges.closed(0, 9).intersection(Ranges.closed(3, 4)),
				is(Ranges.closed(3, 4)));
		assertThat(Ranges.closed(0, 5).intersection(Ranges.closed(3, 9)),
				is(Ranges.closed(3, 5)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void intersectionWhenNoIntersectionFound() {
		Ranges.open(3, 5).intersection(Ranges.open(5, 10));
	}

	@Test
	public void span() {
		assertThat(Ranges.closed(3, 5).span(Ranges.open(5, 10)),
				is(Ranges.closedOpen(3, 10)));
		assertThat(Ranges.closed(0, 9).span(Ranges.closed(3, 4)),
				is(Ranges.closed(0, 9)));
		assertThat(Ranges.closed(0, 5).span(Ranges.closed(3, 9)),
				is(Ranges.closed(0, 9)));
		assertThat(Ranges.open(3, 5).span(Ranges.open(5, 10)),
				is(Ranges.open(3, 10)));
		assertThat(Ranges.closed(1, 5).span(Ranges.closed(6, 10)),
				is(Ranges.closed(1, 10)));
	}
}
