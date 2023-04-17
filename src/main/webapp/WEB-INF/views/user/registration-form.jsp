<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 14.04.2023
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CastMe</title>
</head>
<body>
    <%--@elvariable id="user" type="pl.coderslab.castme.User.User"--%>
    <form:form method="post" modelAttribute="user">
        What type of account are you interested in?
        <form:select path="userRole" items="${userRoles}" itemValue="id" itemLabel="userRole"/>
        <form:input path="firstName" placeholder="First name"/>
        <form:errors path="firstName"/>
        <form:input path="lastName" placeholder="Last name"/>
        <form:errors path="lastName"/>
        <form:input path="email" type="email" placeholder="Email"/>
        <form:errors path="email"/>
        <form:input path="phoneNumber" placeholder="Phone number"/>
        <form:errors path="phoneNumber"/>
        <form:input path="password" type="password" placeholder="Password"/>
        <form:errors path="password"/>
        <input type="submit" value="Register"/>
    </form:form>
    <c:out value="${successMessage}"/>
</body>
</html>
