<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 15.04.2023
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <form method="POST" action="/login">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" class="form-control" id="email"  name="username" />
    </div>
    <div class="form-group">
      <label for="password">Password</label>
      <input type="password" class="form-control" id="password" name="password" />
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
  </form>
</body>
</html>
