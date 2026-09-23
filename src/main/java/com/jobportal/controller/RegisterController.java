package com.jobportal.controller;

import com.jobportal.dao.UserDAO;
import com.jobportal.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterController {

    private User user = new User();

    public String register() {

        System.out.println("========== REGISTER ==========");

        System.out.println("Name  : " + user.getFullName());
        System.out.println("Email : " + user.getEmail());
        System.out.println("Role  : " + user.getRole());

        UserDAO userDAO = new UserDAO();

        // Check whether email already exists
        User existingUser = userDAO.findUserByEmail(user.getEmail());

        if (existingUser != null) {

            System.out.println("Email already exists");

            return "error";
        }

        // Hash password before storing
        String hashedPassword =
                BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        user.setPassword(hashedPassword);

        // Save user into database
        boolean registered = userDAO.registerUser(user);

        if (registered) {

            System.out.println("Registration successful");

            return "success";
        }

        System.out.println("Registration failed");

        return "error";
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }
}