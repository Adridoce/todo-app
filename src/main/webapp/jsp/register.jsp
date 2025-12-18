<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="includes/header.jsp" %>

<h2 class="mb-4">Registro</h2>

<c:if test="${not empty sessionScope.error}">
    <div class="alert alert-danger">
        ${sessionScope.error}
    </div>
    <c:remove var="error" scope="session"/>
</c:if>

<c:if test="${not empty sessionScope.message}">
    <div class="alert alert-success">
        ${sessionScope.message}
    </div>
    <c:remove var="message" scope="session"/>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/register" class="w-50">

    <div class="mb-3">
        <label class="form-label">Email</label>
        <input type="email" name="email" class="form-control" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Contraseña</label>
        <input type="password" name="password" class="form-control" required>
    </div>

    <button type="submit" class="btn btn-success">Registrarse</button>
    <a href="${pageContext.request.contextPath}/login" class="btn btn-link">
        Volver al login
    </a>
</form>

<%@ include file="includes/footer.jsp" %>
