<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html xmlns:c="http://www.w3.org/1999/XSL/Transform">
    <head>
        <title>Login</title>
    </head>

    <body>

        <h2>Login</h2>

        <c:if test="${not empty sessionScope.error}">
            <p style="color:red">${sessionScope.error}</p>
            <c:remove var="error" scope="session"/>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/login">
            <input type="email" name="email" placeholder="Email" required />
            <br><br>
            <input type="password" name="password" placeholder="Contraseña" required />
            <br><br>
            <button type="submit">Entrar</button>
        </form>

    </body>
</html>