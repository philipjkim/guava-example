package org.sooo;

public class DummyCustomerFactory {

	public Customer create() {
		return new Customer("John", "Doe", "john.doe@email.com",
				"010-1234-5678", 1950, false);
	}
}
