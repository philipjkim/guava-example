package org.sooo.collect;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import com.google.common.io.Files;

public class MultisetTest {

	private Iterable<String> words;

	@Before
	public void before() throws Exception {
		String text = Files.toString(new File("src/test/resources/sample.txt"),
				Charsets.UTF_8);
		words = Splitter.on(CharMatcher.anyOf(" .,?\n")).trimResults()
				.omitEmptyStrings().split(text);
	}

	@Test
	public void countWordViaTraditionalWay() {
		Map<String, Integer> counts = new HashMap<String, Integer>();
		for (String word : words) {
			Integer count = counts.get(word);
			if (count == null)
				counts.put(word, 1);
			else
				counts.put(word, count + 1);
		}

		for (String key : counts.keySet())
			System.out.println(key + "=" + counts.get(key));
	}

	@Test
	public void countWordUsingMultiset() {
		Multiset<String> wordsMultiset = HashMultiset.create();
		List<String> wordsList = Lists.newArrayList(words);
		wordsMultiset.addAll(wordsList);

		for (String word : wordsMultiset.elementSet())
			System.out.println(word + "=" + wordsMultiset.count(word));
	}

	@Test
	public void useTreeMultisetAsOrderedArray() {
		Multiset<Integer> treeMultiset = TreeMultiset.create(Arrays.asList(1,
				2, 3, 1, 1, -1, 2, 4, 5, 100));

		System.out.println(treeMultiset);
		System.out.println(Joiner.on(", ").join(treeMultiset));
	}
}
