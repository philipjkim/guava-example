package org.sooo.base;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;

public class FunctionTest {

	@Test
	public void testEntryTransformer() {
		// given
		ListMultimap<String, String> firstNameToLastNames = ArrayListMultimap
				.create();
		firstNameToLastNames.put("John", "Lennon");
		firstNameToLastNames.put("Paul", "McCartney");
		firstNameToLastNames.put("George", "Harrison");
		firstNameToLastNames.put("John", "Lewis");
		firstNameToLastNames.put("George", "Winston");
		firstNameToLastNames.put("John", "Myung");

		// when
		ListMultimap<String, String> firstNameToFullNames = Multimaps
				.transformEntries(firstNameToLastNames,
						new Maps.EntryTransformer<String, String, String>() {
							@Override
							public String transformEntry(String firstName,
									String lastName) {
								return firstName + " " + lastName;
							}
						});

		// then
		assertTrue(firstNameToFullNames.get("John").contains("John Lennon"));
		assertTrue(firstNameToFullNames.get("John").contains("John Lewis"));
		assertTrue(firstNameToFullNames.get("John").contains("John Myung"));
		assertTrue(firstNameToFullNames.get("George").contains(
				"George Harrison"));
		assertTrue(firstNameToFullNames.get("George")
				.contains("George Winston"));
		assertTrue(firstNameToFullNames.get("Paul").contains("Paul McCartney"));
	}
}
