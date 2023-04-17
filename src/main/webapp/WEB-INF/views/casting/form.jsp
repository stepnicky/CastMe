<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 13.04.2023
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CastMe</title>
</head>
<body>
    <%--@elvariable id="casting" type="pl.coderslab.castme.Casting.Casting"--%>
    <form:form method="post" modelAttribute="casting">
        Title: <form:input path="title" placeholder="Title"/>
        <form:errors path="title"/>
        Description: <form:textarea path="description" placeholder="description"/>
        <form:errors path="description"/>
        Deadline: <form:input path="deadline" type="date"/>
        <form:errors path="deadline"/>
        <input type="submit" value="Add casting">
    </form:form>
</body>
</html>
