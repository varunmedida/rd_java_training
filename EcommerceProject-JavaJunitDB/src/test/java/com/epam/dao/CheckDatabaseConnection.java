package com.epam.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class CheckDatabaseConnection {

	@Test
	public void checkDatabaseConnection()  {
		assertNotNull(DatabaseConnection.getConnection());
	}

}
