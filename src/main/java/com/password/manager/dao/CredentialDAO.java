package com.password.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.password.manager.Credential;
import com.password.manager.DatabaseConnection;

public class CredentialDAO {

	private Connection connection;

	public CredentialDAO() {
		this.connection = DatabaseConnection.getConnection();
	}

	public void saveCredential(Integer userID, String website, String username, String password) throws SQLException {

		PreparedStatement statement = connection
				.prepareStatement("insert into password_store(user_id,url,username,password) values(?,?,?,?)");
		statement.setInt(1, userID);
		statement.setString(2, website);
		statement.setString(3, username);
		statement.setString(4, password);
		statement.executeUpdate();
	}

	public List<Credential> getAllCredentials(Integer userID) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from password_store where user_id=?");
		statement.setInt(1, userID);
		ResultSet rs = statement.executeQuery();
		ArrayList<Credential> credentialList = new ArrayList<>();
		if (userID == null)
			return credentialList;
		while (rs.next()) {
			Integer id = rs.getInt("id");
			String url = rs.getString("url");
			String username = rs.getString("username");
			String password = rs.getString("password");
			Credential cred = new Credential(id, url, username, password);
			credentialList.add(cred);
		}
		return credentialList;
	}
	
	public Credential getCredentialById(Integer id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from password_store where id=?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			String url = rs.getString("url");
			String username = rs.getString("username");
			String password = rs.getString("password");
			Credential cred = new Credential(id, url, username, password);
			return cred;
		}
		return null;
	}
	
	public boolean isBelongsTo(Integer userID, Integer id) {
		try {
			PreparedStatement query = connection
					.prepareStatement("select * from password_store where user_id=? and id=?");
			query.setInt(1, userID);
			query.setInt(2, id);
			ResultSet rs = query.executeQuery();
			return rs.next();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("SQLException during isBelongsTo check!");
		}
		return false;
	}

	public boolean deleteCredential(Integer userID, Integer id) {
		try {
			if (this.isBelongsTo(userID, id)) {
				PreparedStatement statement = connection
						.prepareStatement("delete from password_store where user_id=? and id=?");
				statement.setInt(1, userID);
				statement.setInt(2, id);
				statement.executeUpdate();
				return true;
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public void updateCredential(Integer id, String website, String username, String password, Integer userid) throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement("update password_store set url=?, username=?, password=? where id=? and user_id=?");
		statement.setString(1, website);
		statement.setString(2, username);
		statement.setString(3, password);
		statement.setInt(4, id);
		statement.setInt(5, userid);
		statement.executeUpdate();
	}
}
