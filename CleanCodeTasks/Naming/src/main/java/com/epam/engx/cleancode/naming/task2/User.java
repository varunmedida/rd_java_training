package com.epam.engx.cleancode.naming.task2;

import java.util.Arrays;
import java.util.Date;

public class User {

	private Date dateOfBirth;

	private String fullName;

	private boolean isAdmin;

	private User[] users;

	private int rating;

	public User(String fullName) {
		super();
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "User [dateOfBirth=" + dateOfBirth + ", fullName=" + fullName + ", isAdmin=" + isAdmin + ", users="
				+ Arrays.toString(users) + ", Rating=" + rating + "]";
	}

}
