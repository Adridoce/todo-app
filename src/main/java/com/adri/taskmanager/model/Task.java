package com.adri.taskmanager.model;

import java.time.LocalDateTime;

public class Task {

    private int id;
    private String title;
    private LocalDateTime creationDate;
    private boolean completed;
    private int userId;

    public Task() {
    }

    // Para crear tareas, siempre se asigna la fecha de creacion
    public Task(String title, int userId) {
        this.title = title;
        this.creationDate = LocalDateTime.now();
        this.completed = false;
        this.userId = userId;
    }

    // Admite fecha de creacion para cuando necesito obtener una task desde BD
    public Task(int id, String title, LocalDateTime creationDate, boolean completed, int userId) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.completed = completed;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
