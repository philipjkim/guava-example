package org.sooo;

import org.junit.Test;

import com.google.common.base.Objects;

public class ObjectsTest {

	private DummyCustomerFactory dummyCustomerFactory = new DummyCustomerFactory();
	private Customer c1;
	private Customer c2;

	@Test(expected = NullPointerException.class)
	public void useEqualsWithNull() {
		c1 = null;
		c2 = null;
		System.out.println(c1.equals(c2));
	}

	@Test
	public void useEqualOfObjectsWithNull() {
		c1 = null;
		c2 = null;
		System.out.println(Objects.equal(c1, c2));
	}

	@Test
	public void useEqualOfObjectsWithNotNullObjects() {
		c1 = dummyCustomerFactory.create();
		c2 = dummyCustomerFactory.create();
		System.out.println(Objects.equal(c1, c2));
	}
}
