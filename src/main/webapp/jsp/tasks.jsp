<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="UTF-8">
    <title>Mis tareas</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        h1 { margin-bottom: 20px; }
        form { margin-bottom: 20px; }
        ul { list-style: none; padding: 0; }
        li { margin-bottom: 10px; }
        .completed { text-decoration: line-through; color: gray; }
        button { margin-left: 10px; }
    </style>
</head>
<body>

<h1>Mis tareas</h1>
<a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a>

<!-- Mostrar error si existe -->
<c:if test="${not empty sessionScope.error}">
    <p style="color:red;">${sessionScope.error}</p>
    <c:remove var="error" scope="session"/>
</c:if>

<!-- Formulario crear tarea -->
<form method="post" action="${pageContext.request.contextPath}/tasks">
    <input type="hidden" name="action" value="create">
    <input type="text" name="title" placeholder="Nueva tarea" required>
    <button type="submit">Crear</button>
</form>

<!-- Lista de tareas -->
<ul>
    <c:choose>
        <c:when test="${not empty tasks}">
            <c:forEach var="task" items="${tasks}">
                <li>
                    <span class="${task.completed ? 'completed' : ''}">
                        ${task.title}
                    </span>

                    <!-- Completar -->
                    <form method="post" action="${pageContext.request.contextPath}/tasks" style="display:inline;">
                        <input type="hidden" name="action" value="complete">
                        <input type="hidden" name="taskId" value="${task.id}">
                        <button type="submit">Completar</button>
                    </form>

                    <!-- Eliminar -->
                    <form method="post" action="${pageContext.request.contextPath}/tasks" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="taskId" value="${task.id}">
                        <button type="submit">Eliminar</button>
                    </form>
                </li>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <li>No tienes tareas aún</li>
        </c:otherwise>
    </c:choose>
</ul>

</body>
</html>
