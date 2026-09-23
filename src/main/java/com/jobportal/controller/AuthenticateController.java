package com.jobportal.controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.mindrot.jbcrypt.BCrypt;

import com.jobportal.dao.UserDAO;
import com.jobportal.model.User;
import com.jobportal.util.RedisConnection;

import redis.clients.jedis.Jedis;

public class AuthenticateController implements SessionAware {

	private String email;
	private String password;

	private Map<String, Object> session;

	private UserDAO userDAO = new UserDAO();

	// =====================================================
	// LOGIN
	// =====================================================

	public String login() {

		System.out.println("========== LOGIN ==========");

		System.out.println("Email received: " + email);

		User user = getUserFromRedis();

		// -------------------------------------------------
		// If user is not available in Redis,
		// get user from PostgreSQL
		// -------------------------------------------------

		if (user == null) {

			System.out.println("User not found in Redis");

			user = userDAO.findUserByEmail(email);

			if (user == null) {

				System.out.println("User not found in database");

				return "error";
			}

			// Store user details in Redis
			saveUserToRedis(user);
		}

		// -------------------------------------------------
		// Verify password
		// -------------------------------------------------

		boolean passwordMatched = BCrypt.checkpw(password, user.getPassword());

		if (!passwordMatched) {

			System.out.println("Invalid password");

			return "error";
		}

		// -------------------------------------------------
		// Store logged-in user in HTTP session
		// -------------------------------------------------

		session.put("loggedIn", true);

		session.put("userId", user.getUserId());

		session.put("fullName", user.getFullName());

		session.put("email", user.getEmail());

		session.put("role", user.getRole());

		System.out.println("Login successful");

		System.out.println("User: " + user.getFullName());

		System.out.println("Role: " + user.getRole());

		// -------------------------------------------------
		// Redirect based on role
		// -------------------------------------------------

		if ("JOB_SEEKER".equals(user.getRole())) {

			return "seeker";
		}

		if ("EMPLOYER".equals(user.getRole())) {

			return "recruiter";
		}

		System.out.println("Invalid user role");

		return "error";
	}

	// =====================================================
	// GET USER FROM REDIS
	// =====================================================

	private User getUserFromRedis() {

		String redisKey = "user:cache:" + email;

		try (Jedis jedis = RedisConnection.getConnection()) {

			if (!jedis.exists(redisKey)) {

				return null;
			}

			System.out.println("User found in Redis");

			User user = new User();

			user.setUserId(Integer.parseInt(jedis.hget(redisKey, "userId")));

			user.setFullName(jedis.hget(redisKey, "fullName"));

			user.setEmail(jedis.hget(redisKey, "email"));

			user.setRole(jedis.hget(redisKey, "role"));

			/*
			 * Password is intentionally NOT stored in Redis.
			 *
			 * Password verification is always performed using the password hash stored in
			 * PostgreSQL.
			 */

			return user;

		} catch (Exception e) {

			System.out.println("Redis read failed: " + e.getMessage());

			return null;
		}
	}

	// =====================================================
	// SAVE USER TO REDIS
	// =====================================================

	private void saveUserToRedis(User user) {

		String redisKey = "user:cache:" + user.getEmail();

		try (Jedis jedis = RedisConnection.getConnection()) {

			jedis.hset(redisKey, "userId", String.valueOf(user.getUserId()));

			jedis.hset(redisKey, "fullName", user.getFullName());

			jedis.hset(redisKey, "email", user.getEmail());

			jedis.hset(redisKey, "role", user.getRole());

			// Cache expires after 30 minutes
			jedis.expire(redisKey, 1800);

			System.out.println("User stored in Redis");

		} catch (Exception e) {

			System.out.println("Redis write failed: " + e.getMessage());
		}
	}

	// =====================================================
	// LOGOUT
	// =====================================================

	public String logout() {

		System.out.println("========== LOGOUT ==========");

		// Clear current HTTP session
		session.clear();

		System.out.println("Session cleared");

		return "success";
	}

	// =====================================================
	// STRUTS SESSION
	// =====================================================

	@Override
	public void setSession(Map<String, Object> session) {

		this.session = session;
	}

	// =====================================================
	// INPUT SETTERS
	// =====================================================

	public void setEmail(String email) {

		this.email = email;
	}

	public void setPassword(String password) {

		this.password = password;
	}
}