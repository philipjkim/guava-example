package org.sooo.cache;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.primitives.Chars;

public class CacheTest {

	private final String STRING = "asdkjfhlasjdhfjlkasdfalksfdjakl";

	@Test
	public void populateCacheUsingCacheLoader() {
		// given
		LoadingCache<String, BigInteger> bigIntegers = CacheBuilder
				.newBuilder().maximumSize(10)
				.build(new CacheLoader<String, BigInteger>() {
					@Override
					public BigInteger load(String key) throws Exception {
						return createUselessBigInteger(key);
					}
				});

		try {
			// when
			long start = System.nanoTime();
			bigIntegers.get(STRING);
			long firstDone = System.nanoTime();
			bigIntegers.get(STRING);
			long secondDone = System.nanoTime();

			// then
			System.out.printf("First elapsed : %d ms\n",
					(firstDone - start) / 1000);
			System.out.printf("Second elapsed : %d ms\n",
					(secondDone - firstDone) / 1000);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private BigInteger createUselessBigInteger(String str) {
		BigInteger sum = new BigInteger("1");
		for (char character : Chars.asList(str.toCharArray()))
			sum = sum.multiply(new BigInteger(Integer.toString(character)));
		return sum.pow(1000);
	}
}
