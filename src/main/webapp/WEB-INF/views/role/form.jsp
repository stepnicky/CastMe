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
    <form:textarea path="description" cols="10" rows="25"/><br/>

    <h2>Feature Set:</h2>

    <label for="gender">Gender:</label>
    <select id="gender" name="gender">
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        <option value="Other">Other</option>
    </select><br/>

    <label for="height">Height:</label>
    <select id="height" name="height">
        <option value="short">Short</option>
        <option value="medium">Medium</option>
        <option value="tall">Tall</option>
        <option value="">No difference</option>
    </select><br/>

    <label for="hairColor">Hair Color:</label>
    <select id="hairColor" name="hairColor">
        <option value="blonde">Blonde</option>
        <option value="ginger">Ginger</option>
        <option value="brown">Brown</option>
        <option value="black">Black</option>
        <option value="grey">Grey</option>
        <option value="white">White</option>
        <option value="other">Other</option>
        <option value="">No difference</option>
    </select><br/>

    <label for="hairLength">Hair Length:</label>
    <select id="hairLength" name="hairLength">
        <option value="bald">Bald</option>
        <option value="short">Short</option>
        <option value="medium">Medium</option>
        <option value="long">Long</option>
        <option value="">No difference</option>
    </select><br/>

    <label for="eyeColor">Eye Color:</label>
    <select id="eyeColor" name="eyeColor">
        <option value="blue">Blue</option>
        <option value="brown">Brown</option>
        <option value="green">Green</option>
        <option value="yellow">Yellow</option>
        <option value="black">Black</option>
        <option value="grey">Grey</option>
        <option value="other">Other</option>
        <option value="">No difference</option>
    </select><br/>

    <label for="figure">Figure:</label>
    <select id="figure" name="figure">
        <option value="athletic">Athletic</option>
        <option value="overweight">Overweight</option>
        <option value="curvy">Curvy</option>
        <option value="slim">Slim</option>
        <option value="muscular">Muscular</option>
        <option value="other">Other</option>
        <option value="">No difference</option>
    </select><br/>

    <label for="ageFrom">Age from: </label>
    <input type="number" id="ageFrom" name="ageFrom"/><br/>
    <label for="ageTo">To: </label>
    <input type="number" id="ageTo" name="ageTo"/><br/>
    <h2>Skills:</h2>
    <form:select path="skills" items="${skills}" itemValue="id" itemLabel="name"/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
