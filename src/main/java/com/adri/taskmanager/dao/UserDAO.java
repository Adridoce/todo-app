package com.adri.taskmanager.dao;

import com.adri.taskmanager.model.User;

import java.sql.*;

public class UserDAO {

    public User createUser(User user) {
        String sql = "INSERT INTO users (email, password_hash) VALUES (?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPasswordHash());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if (keys.next()) {
                user.setId(keys.getInt(1));
            }
            return user;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el usuario", e);
        }
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()){

                if (rs.next()){
                    return new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password_hash")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar usuario", e);
        }
        return null;
    }
}
