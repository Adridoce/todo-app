<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="includes/header.jsp" %>

<h2 class="mb-4">Mis tareas</h2>

<c:if test="${not empty sessionScope.message}">
    <div class="alert alert-success">
        ${sessionScope.message}
    </div>
    <c:remove var="message" scope="session"/>
</c:if>

<c:if test="${empty tasks}">
    <div class="alert alert-info">
        No tienes tareas todavía.
    </div>
</c:if>

<c:if test="${not empty tasks}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Título</th>
            <th>Fecha creación</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${tasks}">
            <tr>
                <td>${task.title}</td>
                <td>${task.creationDate}</td>
                <td>
                    <c:choose>
                        <c:when test="${task.completed}">
                            <span class="badge bg-success">Completada</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-warning text-dark">Pendiente</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/tasks" class="d-inline">
                        <input type="hidden" name="action" value="complete"/>
                        <input type="hidden" name="taskId" value="${task.id}"/>
                        <button class="btn btn-sm btn-success">Completar</button>
                    </form>

                    <form method="post" action="${pageContext.request.contextPath}/tasks" class="d-inline"
                          onsubmit="return confirm('¿Seguro que quieres eliminar esta tarea?');">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="taskId" value="${task.id}"/>
                        <button class="btn btn-sm btn-danger">Eliminar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<hr>

<h4>Nueva tarea</h4>

<form method="post" action="${pageContext.request.contextPath}/tasks" class="w-50">
    <input type="hidden" name="action" value="create"/>

    <div class="mb-3">
        <input type="text" name="title" class="form-control"
               placeholder="Título de la tarea" required>
    </div>

    <button type="submit" class="btn btn-primary">Crear tarea</button>
</form>

<%@ include file="includes/footer.jsp" %>
