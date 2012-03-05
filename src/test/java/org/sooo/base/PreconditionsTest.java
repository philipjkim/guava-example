package org.sooo.base;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class PreconditionsTest {

	@Test
	public void checkArgumentWhenCheckPassed() {
		int arg = 0;
		Preconditions.checkArgument(arg >= 0,
				"Argument was %s but expected nonnegative", arg);
	}

	@Test(expected = IllegalArgumentException.class)
	public void checkArgumentWhenCheckFailed() {
		int arg = -1;
		Preconditions.checkArgument(arg >= 0,
				"Argument was %s but expected nonnegative", arg);
	}

	@Test
	public void checkStateWhenCheckPassed() {
		boolean state = 2 > 1;
		Preconditions.checkState(state);
	}

	@Test(expected = IllegalStateException.class)
	public void checkStateWhenCheckFailed() {
		boolean state = 2 < 1;
		Preconditions.checkState(state);
	}

	@Test
	public void checkElementIndexWhenCheckPassed() {
		List<String> list = Lists.newArrayList("a", "b", "c", "d");
		Preconditions.checkElementIndex(2, list.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkElementIndexWhenCheckFailed() {
		List<String> list = Lists.newArrayList("a", "b", "c", "d");
		Preconditions.checkElementIndex(10, list.size());
	}
}
