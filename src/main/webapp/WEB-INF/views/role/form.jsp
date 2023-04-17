<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 16.04.2023
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CastMe</title>
</head>
<body>
<h1>Add New Role</h1>
<form:form method="post" modelAttribute="role">
    <label>Title:</label>
    <form:input path="title"/><br/>

    <label>Description:</label>
    <form:textarea path="description"/><br/>

    <h2>Feature Set:</h2>

    <label for="gender">Gender:</label>
    <select id="gender" name="gender">
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        <option value="Other">Other</option>
    </select><br/>

    <label for="height">Height:</label>
    <input type="number" id="height" name="height"/><br/>

    <label for="weight">Weight:</label>
    <input type="number" id="weight" name="weight"/><br/>

    <label for="hairColor">Hair Color:</label>
    <input type="text" id="hairColor" name="hairColor"/><br/>

    <label for="hairLength">Hair Length:</label>
    <select id="hairLength" name="hairLength">
        <option value="bald">Bald</option>
        <option value="short">Short</option>
        <option value="medium">Medium</option>
        <option value="long">Long</option>
    </select><br/>

    <label for="eyeColor">Eye Color:</label>
    <input type="text" id="eyeColor" name="eyeColor"/><br/>

    <label for="figure">Figure:</label>
    <input type="text" id="figure" name="figure"/><br/>

    <label for="age">Date of Birth:</label>
    <input type="number" id="age" name="age"/><br/>

    <h2>Skills:</h2>
    <form:select path="skills" items="${skills}" itemValue="id" itemLabel="name"/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
