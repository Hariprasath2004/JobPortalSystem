package com.jobportal.action;

import java.sql.Connection;

import com.jobportal.util.DBConnection;

public class DatabaseTestAction {

	public String execute() {

		try (Connection connection = DBConnection.getConnection()) {

			if (connection != null) {
				return "success";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "error";
	}
}