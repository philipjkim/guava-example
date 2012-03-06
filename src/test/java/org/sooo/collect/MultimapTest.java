package org.sooo.collect;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

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

	@Test
	public void createDictionaryUsingFunctional() {
		Multimap<String, String> dictionary = Multimaps.index(words,
				new Function<String, String>() {
					@Override
					public String apply(String input) {
						return input.substring(0, 1);
					}
				});
		System.out.println(dictionary);
	}
}
