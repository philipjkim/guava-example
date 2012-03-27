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
		// when
		LoadingCache<String, BigInteger> bigIntegers = CacheBuilder
				.newBuilder().maximumSize(10)
				.build(new CacheLoader<String, BigInteger>() {
					@Override
					public BigInteger load(String key) {
						return createUselessBigInteger(key);
					}
				});

		// then
		for (int rep = 0; rep < 3; rep++) {
			for (int i = 0; i < STRINGS.size(); i++) {
				printElapsedTime(bigIntegers, STRINGS.get(i));
			}
			System.out.println("Hit rate so far: "
					+ bigIntegers.stats().hitRate());
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
