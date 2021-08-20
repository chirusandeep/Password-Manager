package com.password.manager;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
	
	private static Connection connection;
	
	private DatabaseConnection() {
	}

	public static Connection getConnection() {
		if (connection != null) return connection;
		try {
			String dbDriver = "oracle.jdbc.driver.OracleDriver";
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			Class.forName(dbDriver);
			String dbUsername = "password_manager";
			String dbPassword = "password_manager";
			connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			return connection;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
