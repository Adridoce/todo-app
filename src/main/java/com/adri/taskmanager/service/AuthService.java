package com.adri.taskmanager.service;

import com.adri.taskmanager.dao.UserDAO;
import com.adri.taskmanager.model.User;

public class AuthService {

    private final UserDAO userDAO = new UserDAO();

    public User authenticate(String email, String password) {

        User user = userDAO.getUserByEmail(email);

        if (user == null) return null;
        if (!user.getPasswordHash().equals(password)) return null;

        return user;
    }
}
