package com.adri.taskmanager.service;

import com.adri.taskmanager.dao.UserDAO;
import com.adri.taskmanager.model.User;
import com.adri.taskmanager.security.PasswordUtils;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public User createUser(String email, String password) {

        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("El email es obligatorio");
        if (userDAO.getUserByEmail(email) != null)
            throw new IllegalArgumentException("El email ya está registrado");
        if (password == null || password.trim().isEmpty())
            throw new IllegalArgumentException("La contraseña es obligatoria");
        if (password.length() < 6)
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 carácteres");

        String hashedPassword = PasswordUtils.hashPassword(password);

        User user = new User(email, hashedPassword);
        userDAO.createUser(user);
        return user;
    }
}
