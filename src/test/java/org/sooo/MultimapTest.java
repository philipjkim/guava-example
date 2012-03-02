package org.sooo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class MultimapTest {

	List<String> words = Arrays.asList("all", "around", "be", "beyond",
			"believe", "do", "down", "equal", "for");

	@Test
	public void createDictionayViaTraditionalWay() {
		Map<String, List<String>> dictionary = new HashMap<String, List<String>>();
		for (String word : words) {
			String firstCharacter = word.substring(0, 1);
			List<String> wordsWithSameFirstCharacter = dictionary
					.get(firstCharacter);
			if (wordsWithSameFirstCharacter == null)
				dictionary.put(firstCharacter, Lists.newArrayList(word));
			else {
				wordsWithSameFirstCharacter.add(word);
				dictionary.put(firstCharacter, wordsWithSameFirstCharacter);
			}
		}
		System.out.println(dictionary);
	}

	@Test
	public void createDictionaryUsingMultimap() {
		Multimap<String, String> dictionary = ArrayListMultimap.create();
		for (String word : words)
			dictionary.put(word.substring(0, 1), word);
		System.out.println(dictionary);
	}
}
