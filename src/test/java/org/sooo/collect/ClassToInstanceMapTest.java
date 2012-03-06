package org.sooo.collect;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

public class ClassToInstanceMapTest {

	@Test
	public void putAndGetInstance() {
		// given
		ClassToInstanceMap<Exception> map = MutableClassToInstanceMap.create();

		// when
		map.putInstance(RuntimeException.class, new RuntimeException());
		map.putInstance(NullPointerException.class, new NullPointerException());

		// then
		assertThat(map.getInstance(RuntimeException.class),
				is(RuntimeException.class));
		assertThat(map.getInstance(NullPointerException.class),
				is(NullPointerException.class));
	}
}
