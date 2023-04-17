<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 13.04.2023
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CastMe</title>
</head>
<body>
    <h1>Casting details</h1>
    <p>${casting.title}</p>
    <p>${casting.description}</p>
    <p>${casting.deadline}</p>
    <p>Roles: </p>
    <c:forEach var="role" items="${roles}">
        <p>${role.title}</p>
        <p>${role.description}</p>
    </c:forEach>
    <a href="/director/casting/${casting.id}/role/add">Add role to a casting</a>
</body>
</html>
