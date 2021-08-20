package com.password.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import com.password.manager.DatabaseConnection;

public class UserDAO {
	private Connection connection;

	public UserDAO() {
		this.connection = DatabaseConnection.getConnection();
	}

	public Integer isValidUser(String username, String password) {
		String digest = DigestUtils.sha1Hex(password);
		try {
			PreparedStatement statement = connection
					.prepareStatement("select * from users where username = ? and password = ?");
			statement.setString(1, username);
			statement.setString(2, digest);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			}
			return null;
		} catch (SQLException e) {
			System.out.println("Unable to execute query!");
			return null;
		}
	}

	public boolean isUserExists(String username) {

		try {
			PreparedStatement statement = connection.prepareStatement("select * from users where username = ?");
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println("Unable to execute query!");
			return false;
		}
	}

	public void saveUser(String fullname, String username, String password) throws SQLException {
		String digest = DigestUtils.sha1Hex(password);
		PreparedStatement statement = connection
				.prepareStatement("insert into users(fullname,username,password) values(?,?,?)");
		statement.setString(1, fullname);
		statement.setString(2, username);
		statement.setString(3, digest);
		statement.executeUpdate();

	}

}
