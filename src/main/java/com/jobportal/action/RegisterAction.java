package com.jobportal.action;

import com.jobportal.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.opensymphony.xwork2.ActionSupport;
import org.mindrot.jbcrypt.BCrypt;
public class RegisterAction extends ActionSupport {

	private String fullName;
	private String email;
	private String password;
	private String role;

	@Override
	public String execute() {

	    String sql = """
	            INSERT INTO users
	            (full_name, email, password, role)
	            VALUES (?, ?, ?, ?)
	            """;

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

	        ps.setString(1, fullName);
	        ps.setString(2, email);
	        ps.setString(3, hashedPassword);
	        ps.setString(4, role);

	        ps.executeUpdate();

	        return SUCCESS;

	    } catch (Exception e) {

	        e.printStackTrace();

	        addActionError("Registration failed: " + e.getMessage());

	        return ERROR;
	    }
	}
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}