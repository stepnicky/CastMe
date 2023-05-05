<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>CastMe</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
              rel="stylesheet">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    </head>

    <body>
        <header class="page-header">
            <nav class="navbar navbar-expand-lg justify-content-between">
                <a href="/" class="navbar-brand main-logo">
                    Cast<span>Me</span>
                </a>
                <ul class="nav nounderline text-uppercase">
                    <li class="nav-item ml-4">
                        <a class="nav-link color-header" href="/login">sign in</a>
                    </li>
                    <li class="nav-item ml-4">
                        <a class="nav-link color-header" href="/register">sign up</a>
                    </li>
                    <li class="nav-item ml-4">
                        <a class="nav-link" href="/about">about</a>
                    </li>
                    <li class="nav-item ml-4">
                        <a class="nav-link disabled" href="/contact">contact</a>
                    </li>
                </ul>
            </nav>
        </header>

        <section class="dashboard-section">
            <div class="container pt-4 pb-4">
                <div class="border-dashed view-height">
                    <div class="container w-25">
                        <%--@elvariable id="user" type="pl.coderslab.castme.user.User"--%>
                        <form:form class="padding-small text-center" method="post" modelAttribute="user">
                            <h1 class="text-color-darker">Sign up</h1>
                            <div class="form-group">
                                <form:select path="userRole"  class="form-control">
                                    <form:option value="">Your area of expertise</form:option>
                                    <form:options items="${userRoles}" itemValue="id" itemLabel="label"/>
                                </form:select>
                                <form:errors path="userRole" cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <form:input path="firstName" class="form-control" placeholder="First name"/>
                            </div>
                            <form:errors path="firstName" cssClass="error"/>
                            <div class="form-group">
                                <form:input path="lastName" class="form-control" placeholder="Last name"/>
                            </div>
                            <form:errors path="lastName" cssClass="error"/>
                            <div class="form-group">
                                <form:input path="email" class="form-control" placeholder="Email"/>
                            </div>
                            <form:errors path="email" cssClass="error"/>
                            <div class="form-group">
                                <form:input path="phoneNumber" class="form-control" placeholder="Phone number"/>
                            </div>
                            <form:errors path="phoneNumber" cssClass="error"/>
                            <div class="form-group">
                                <form:password path="password" class="form-control" placeholder="Password"/>
                            </div>
                            <form:errors path="password" cssClass="error"/>
                            <button class="btn btn-color" type="submit">Sign up</button>
                        </form:form>
                        <c:out value="${successMessage}"/>
                    </div>
                </div>
            </div>
        </section>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
                crossorigin="anonymous"></script>
    </body>
</html>

