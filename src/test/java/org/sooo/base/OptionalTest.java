package org.sooo.base;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.sooo.Customer;
import org.sooo.DummyCustomerFactory;

import com.google.common.base.Optional;

public class OptionalTest {

	@Test
	public void useNull() {
		Customer customer = null;
		assertNull(customer);
	}

	@Test
	public void useFromNullableAndIsPresent() {
		Optional<Customer> customer = Optional.fromNullable(null);
		assertFalse(customer.isPresent());
	}

	@Test(expected = NullPointerException.class)
	public void useOfToFailFast() {
		Optional.of(null);
	}

	@Test(expected = IllegalStateException.class)
	public void useAbsentOptional() {
		Optional<Customer> customer = Optional.fromNullable(null);
		System.out.println(customer.get().getEmailAddress());
	}

	@Test
	public void useOrToUseDefaultObject() {
		Optional<Customer> customer = Optional.fromNullable(null);
		System.out.println(customer.or(new DummyCustomerFactory().create())
				.getEmailAddress());
	}
}
