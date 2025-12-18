package com.adri.taskmanager.controller;

import com.adri.taskmanager.model.Task;
import com.adri.taskmanager.service.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    private final TaskService taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        List<Task> tasks = taskService.getTaskForUser(userId);
        request.setAttribute("tasks", tasks);

        request.getRequestDispatcher("/jsp/tasks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "create" -> handleCreate(request, userId);
                case "complete" -> handleComplete(request, userId);
                case "delete" -> handleDelete(request, userId);
                default -> throw new IllegalArgumentException("Acción no válida");
            }
        }catch (Exception e) {
            request.getSession().setAttribute("error", e.getMessage());
        }

        response.sendRedirect("tasks");
    }

    private void handleCreate(HttpServletRequest request, int userId){
        String title = request.getParameter("title");
        taskService.createTask(userId, title);
    }

    private void handleComplete(HttpServletRequest request, int userId) {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        taskService.completeTask(taskId, userId);
    }

    private void handleDelete(HttpServletRequest request, int userId) {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        taskService.deleteTask(taskId, userId);
    }
}
