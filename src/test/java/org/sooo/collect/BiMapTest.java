package org.sooo.collect;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class BiMapTest {

	private BiMap<String, Integer> nameToId;

	@Before
	public void setUp() {
		nameToId = HashBiMap.create();
		nameToId.put("Matthew", 1);
		nameToId.put("Mark", 2);
		nameToId.put("Luke", 3);
		nameToId.put("John", 4);
	}

	@Test
	public void inverseBiMap() {
		// when
		BiMap<Integer, String> idToName = nameToId.inverse();

		// then
		assertThat(idToName.get(1), is("Matthew"));
		assertThat(idToName.get(2), is("Mark"));
		assertThat(idToName.get(3), is("Luke"));
		assertThat(idToName.get(4), is("John"));
	}

	@Test
	public void getValuesOfBiMaptAsSet() {
		// when
		Set<Integer> ids = nameToId.values();

		// then
		Set<Integer> expectedSet = Sets.newHashSet(1, 2, 3, 4);
		SetView<Integer> setView = Sets.difference(ids, expectedSet);
		assertTrue(setView.isEmpty());
	}

	@Test(expected = IllegalArgumentException.class)
	public void tryToMapAKeyToAnAlreadyPresentValueUsingPut() {
		// when
		nameToId.put("Paul", 1);

		// then
		fail("IllegalArgumentException expected");
	}

	@Test
	public void tryToMapAKeyToAnAlreadyPresentValueUsingForcePut() {
		// when
		nameToId.forcePut("Paul", 1);

		// then
		assertThat(nameToId.get("Matthew"), is(nullValue()));
		assertThat(nameToId.get("Mark"), is(2));
		assertThat(nameToId.get("Luke"), is(3));
		assertThat(nameToId.get("John"), is(4));
		assertThat(nameToId.get("Paul"), is(1));
	}
}
