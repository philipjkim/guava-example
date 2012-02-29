package org.sooo;

public class Customer {

	private String firstName;
	private String lastName;
	private String emailAddress;
	private String mobileNumber;
	private int yearOfBirth;
	private boolean vip;

	public Customer(String firstName, String lastName, String emailAddress,
			String mobileNumber, int yearOfBirth, boolean vip) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.mobileNumber = mobileNumber;
		this.yearOfBirth = yearOfBirth;
		this.vip = vip;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public boolean isVip() {
		return vip;
	}
}
