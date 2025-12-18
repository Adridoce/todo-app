<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <title>Task Manager</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/tasks">Task Manager</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/logout">Cerrar sesión</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
