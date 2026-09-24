package com.jobportal.controller;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.mindrot.jbcrypt.BCrypt;

import com.jobportal.dao.UserDAO;
import com.jobportal.model.User;
import com.jobportal.util.RedisConnection;

import redis.clients.jedis.Jedis;

public class AuthenticateController {

	private String email;
	private String password;

	private UserDAO userDAO = new UserDAO();

	// =====================================================
	// LOGIN
	// =====================================================

	public String login() {

		System.out.println("========== LOGIN ==========");

		System.out.println("Email received: " + email);

		User user = getUserFromRedis();

		// User not found in Redis
		if (user == null) {

			System.out.println("User not found in Redis");

			user = userDAO.findUserByEmail(email);

			if (user == null) {

				System.out.println("User not found in database");

				return "error";
			}

			saveUserToRedis(user);

		} else {

			System.out.println("User loaded from Redis");
		}

		// Verify password
		boolean passwordMatched = BCrypt.checkpw(password, user.getPassword());

		if (!passwordMatched) {

			System.out.println("Invalid password");

			return "error";
		}

		// Get HTTP session
		HttpSession session = ServletActionContext.getRequest().getSession();

		// Store logged-in user in session
		session.setAttribute("loggedIn", true);

		session.setAttribute("userId", user.getUserId());

		session.setAttribute("fullName", user.getFullName());

		session.setAttribute("email", user.getEmail());

		session.setAttribute("role", user.getRole());

		System.out.println("Login successful");

		System.out.println("User: " + user.getFullName());

		System.out.println("Role: " + user.getRole());

		// Redirect based on role
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

			User user = new User();

			user.setUserId(Integer.parseInt(jedis.hget(redisKey, "userId")));

			user.setFullName(jedis.hget(redisKey, "fullName"));

			user.setEmail(jedis.hget(redisKey, "email"));

			user.setRole(jedis.hget(redisKey, "role"));

			user.setPassword(jedis.hget(redisKey, "password"));

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

			jedis.hset(redisKey, "password", user.getPassword());

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

		HttpSession session = ServletActionContext.getRequest().getSession(false);

		if (session != null) {

			session.invalidate();
		}

		System.out.println("Logout successful");

		return "success";
	}

	// =====================================================
	// SETTERS
	// =====================================================

	public void setEmail(String email) {

		this.email = email;
	}

	public void setPassword(String password) {

		this.password = password;
	}
}