package com.jobportal.dao;

import com.jobportal.model.User;
import com.jobportal.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	public User findUserByEmail(String email) {

		String sql = "SELECT user_id, full_name, email, password, role FROM users WHERE email = ?";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, email);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				User user = new User();

				user.setUserId(resultSet.getInt("user_id"));

				user.setFullName(resultSet.getString("full_name"));

				user.setEmail(resultSet.getString("email"));

				user.setPassword(resultSet.getString("password"));

				user.setRole(resultSet.getString("role"));

				return user;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	public boolean registerUser(User user) {

		String sql = "INSERT INTO users (full_name, email, password, role) VALUES (?, ?, ?, ?)";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, user.getFullName());

			statement.setString(2, user.getEmail());

			statement.setString(3, user.getPassword());

			statement.setString(4, user.getRole());

			int rows = statement.executeUpdate();

			return rows > 0;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}
}