package com.adri.taskmanager.service;

import com.adri.taskmanager.dao.TaskDAO;
import com.adri.taskmanager.model.Task;

import java.util.List;

public class TaskService {

    private final TaskDAO taskDAO = new TaskDAO();

    public List<Task> getTaskForUser(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("UserId inválido");
        }
        return taskDAO.getTasksByUserId(userId);
    }

    public Task createTask(int userId, String title) {
        if (userId <= 0) throw new IllegalArgumentException("UserId inválido");
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("El título no puede estar vacío");
        if (title.length() > 100)
            throw new IllegalArgumentException("El título no puede superar los 100 carácteres");

        Task task = new Task(title.trim(), userId);
        return taskDAO.createTask(task);
    }

    public void completeTask(int taskId, int userId) {
        boolean updated = taskDAO.setTaskAsCompleted(taskId, userId);
        if (!updated) throw new SecurityException("No puedes marcar esta tarea");
    }

    public void deleteTask(int taskId, int userId) {
        boolean deleted = taskDAO.deleteTask(taskId, userId);
        if (!deleted) throw new SecurityException("No puedes eliminar esta tarea");
    }

}
