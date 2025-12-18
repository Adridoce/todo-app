<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="includes/header.jsp" %>

<h2 class="mb-4" xmlns:c="http://www.w3.org/1999/XSL/Transform">Login</h2>

<c:if test="${not empty sessionScope.error}">
    <div class="alert alert-danger">
        ${sessionScope.error}
    </div>
    <c:remove var="error" scope="session"/>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/login" class="w-50">

    <div class="mb-3">
        <label class="form-label">Email</label>
        <input type="email" name="email" class="form-control" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Contraseña</label>
        <input type="password" name="password" class="form-control" required>
    </div>

    <button type="submit" class="btn btn-primary">Entrar</button>
    <a href="${pageContext.request.contextPath}/register" class="btn btn-link">
        Registrarse
    </a>
</form>

<%@ include file="includes/footer.jsp" %>
