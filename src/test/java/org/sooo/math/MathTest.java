package org.sooo.math;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.math.RoundingMode;

import org.junit.Test;

import com.google.common.math.IntMath;

public class MathTest {

	@Test
	public void base2Logarithm() {
		for (int i = 1; i <= 1024; i = i * 2)
			System.out.printf("log2(%d)=%d\n", i,
					IntMath.log2(i, RoundingMode.FLOOR));
	}

	@Test
	public void base10Logarithm() {
		for (int i = 1; i <= 100000; i = i * 10)
			System.out.printf("log10(%d)=%d\n", i,
					IntMath.log10(i, RoundingMode.FLOOR));
	}

	@Test(expected = ArithmeticException.class)
	public void checkedCalculation() {
		IntMath.checkedPow(1000, 1000);
		fail("ArithmeticException expected");
	}

	@Test
	public void uncheckedCalculation() {
		double tooBigNumber = Math.pow(1000, 1000);
		assertThat(Double.toString(tooBigNumber), is("Infinity"));
	}

	@Test
	public void greatestCommonDivisor() {
		assertThat(IntMath.gcd(48, 72), is(24));
	}

	@Test
	public void modulusUsingNaiveWay() {
		assertThat(7 % 4, is(3));
		assertThat(-7 % 4, is(-3));
	}

	@Test
	public void modulusUsingGuava() {
		assertThat(IntMath.mod(7, 4), is(3));
		assertThat(IntMath.mod(-7, 4), is(1));
	}

	@Test
	public void factorial() {
		assertThat(IntMath.factorial(5), is(2 * 3 * 4 * 5));
	}

	@Test
	public void binomialCoefficient() {
		int n = 5;
		int k = 2;
		assertThat(
				IntMath.binomial(n, k),
				is(IntMath.factorial(n)
						/ (IntMath.factorial(k) * IntMath.factorial(n - k))));
	}
}
