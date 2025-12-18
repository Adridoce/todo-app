package com.adri.taskmanager.service;

import com.adri.taskmanager.dao.UserDAO;
import com.adri.taskmanager.model.User;
import com.adri.taskmanager.security.PasswordUtils;

public class AuthService {

    private final UserDAO userDAO = new UserDAO();

    public User authenticate(String email, String password) {

        User user = userDAO.getUserByEmail(email);

        if (user == null)
            throw new IllegalArgumentException("Email o contraseña incorrectos");
        if (!PasswordUtils.checkPassword(password, user.getPasswordHash()))
            throw new IllegalArgumentException("Email o contraseña incorrectos");

        return user;
    }
}
