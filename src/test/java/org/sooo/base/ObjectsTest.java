package org.sooo.base;

import org.junit.Test;
import org.sooo.Customer;
import org.sooo.DummyCustomerFactory;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

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

	@Test
	public void useEqualOfObjectsWithMixedNullObjects() {
		c1 = null;
		c2 = dummyCustomerFactory.create();
		System.out.println(Objects.equal(c1, c2));
	}
	
	@Test
	public void useToStringHelper() {
		c1 = dummyCustomerFactory.create();
		ToStringHelper toStringHelper = Objects.toStringHelper(Customer.class);
		toStringHelper.add("firstName", c1.getFirstName());
		toStringHelper.add("lastName", c1.getLastName());
		toStringHelper.add("yearOfBirth", c1.getYearOfBirth());
		toStringHelper.add("vip", c1.isVip());
		System.out.println(toStringHelper.toString());
	}
}
