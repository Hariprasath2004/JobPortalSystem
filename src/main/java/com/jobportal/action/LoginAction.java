package com.jobportal.action;

import com.jobportal.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private String email;
	private String password;

	@Override
	public String execute() {

		String sql = """
				SELECT user_id, full_name, email, role
				FROM users
				WHERE email = ?
				AND password = ?
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int userId = rs.getInt("user_id");
				String fullName = rs.getString("full_name");
				String userEmail = rs.getString("email");
				String role = rs.getString("role");

				System.out.println("Login successful");
				System.out.println("User: " + fullName);
				System.out.println("Role: " + role);

				HttpServletRequest request = ServletActionContext.getRequest();

				HttpSession session = request.getSession();

				session.setAttribute("userId", userId);
				session.setAttribute("fullName", fullName);
				session.setAttribute("email", userEmail);
				session.setAttribute("role", role);

				if (role.equals("JOB_SEEKER")) {
					return "seeker";
				}

				if (role.equals("EMPLOYER")) {
					return "recruiter";
				}

				addActionError("Invalid user role.");
				return ERROR;
			}

			// No matching user found
			addActionError("Invalid email or password.");
			return ERROR;

		} catch (Exception e) {

			e.printStackTrace();

			addActionError("Login failed: " + e.getMessage());

			return ERROR;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}