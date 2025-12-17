package com.adri.taskmanager.dao;

import com.adri.taskmanager.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    public Task createTask(Task task) {
        String sql = "INSERT INTO tasks (title, creation_date, completed, user_id) VALUES (?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, task.getTitle());
            ps.setTimestamp(2, Timestamp.valueOf(task.getCreationDate()));
            ps.setBoolean(3, task.isCompleted());
            ps.setInt(4, task.getUserId());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                task.setId(keys.getInt(1));
            }
            return task;

        } catch (SQLException e) {
            throw new RuntimeException("Error creando tarea", e);
        }
    }

    public List<Task> getTasksByUserId(int userId) {

        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE user_id = ? ORDER BY creation_date DESC";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getTimestamp("creation_date").toLocalDateTime(),
                        rs.getBoolean("completed"),
                        rs.getInt("user_id")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo tareas", e);
        }
        return tasks;
    }

    public boolean setTaskAsCompleted(int taskId, int userId) {
        String sql = "UPDATE tasks SET completed = NOT completed WHERE id = ? AND user_id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, taskId);
            ps.setInt(2, userId);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Error al modificar la tarea", e);
        }
    }

    public boolean deleteTask(int taskId, int userId) {
        String sql = "DELETE FROM tasks WHERE id = ? AND user_id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, taskId);
            ps.setInt(2, userId);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la tarea", e);
        }
    }
}
