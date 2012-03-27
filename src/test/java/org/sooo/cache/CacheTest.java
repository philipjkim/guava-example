package org.sooo.cache;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.primitives.Chars;

public class CacheTest {

	private final List<String> STRINGS = Arrays.asList("aaaaaaaaaaa",
			"bbbbbbbbbbb", "ccccccccccc");

	@Test
	public void populateCacheUsingCacheLoader() {
		// given
		LoadingCache<String, BigInteger> bigIntegers = CacheBuilder
				.newBuilder().maximumSize(10)
				.build(new CacheLoader<String, BigInteger>() {
					@Override
					public BigInteger load(String key) {
						return createUselessBigInteger(key);
					}
				});

		// when
		for (int i = 0; i < STRINGS.size(); i++) {
			printElapsedTime(bigIntegers, STRINGS.get(i));
		}
		for (int i = 0; i < STRINGS.size(); i++) {
			printElapsedTime(bigIntegers, STRINGS.get(i));
		}
	}

	private BigInteger createUselessBigInteger(String str) {
		BigInteger sum = new BigInteger("1");
		for (char character : Chars.asList(str.toCharArray()))
			sum = sum.multiply(new BigInteger(Integer.toString(character)));
		return sum.pow(1000);
	}

	private void printElapsedTime(LoadingCache<String, BigInteger> bigIntegers,
			String str) {
		long start = System.nanoTime();
		bigIntegers.getUnchecked(str);
		long elapsed = (System.nanoTime() - start) / 1000;
		System.out.printf("Elapsed for [%s]: %d ms\n", str, elapsed);
	}
}
