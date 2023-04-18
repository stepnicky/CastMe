<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>${message}</h1>
  <%--@elvariable id="actor" type="pl.coderslab.castme.Actor.Actor"--%>
  <form:form method="post" modelAttribute="actor">
    <label>Agency:</label>
    <form:select path="agency" items="${agencies}" itemLabel="name" itemValue="id"/><br/>

    <label>Education:</label>
    <form:input path="education"/><br/>

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
    </select><br/>

    <label for="hairLength">Hair Length:</label>
    <select id="hairLength" name="hairLength">
      <option value="bald">Bald</option>
      <option value="short">Short</option>
      <option value="medium">Medium</option>
      <option value="long">Long</option>
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
    </select><br/>

    <label for="figure">Figure:</label>
    <select id="figure" name="figure">
      <option value="athletic">Athletic</option>
      <option value="overweight">Overweight</option>
      <option value="curvy">Curvy</option>
      <option value="slim">Slim</option>
      <option value="muscular">Muscular</option>
      <option value="other">Other</option>
    </select><br/>
    <label for="age">Age: </label>
    <input type="number" id="age" name="age"/><br/>
    <h2>Skills:</h2>
    <form:select path="skills" items="${skills}" itemValue="id" itemLabel="name"/><br/>
    <input type="text" name="newSkill" placeholder="Add new skill"/><br/>

    <input type="submit" value="Submit"/>
  </form:form>
</body>
</html>
