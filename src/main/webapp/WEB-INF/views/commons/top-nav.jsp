<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>CastMe</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
        rel="stylesheet">
  <link href="/css/style.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
  <script src="/js/notification.js" type="module" defer></script>
  <script src="/js/accountMenu.js" type="module" defer></script>
</head>
<body>
  <header class="page-header">
    <nav class="navbar navbar-expand-lg justify-content-between">
      <sec:authorize access="hasRole('CASTING_DIRECTOR')">
        <a href="/director" class="navbar-brand main-logo main-logo-smaller">
          Cast<span>Me</span>
        </a>
      </sec:authorize>
      <sec:authorize access="hasRole('ACTOR')">
        <a href="/actor" class="navbar-brand main-logo main-logo-smaller">
          Cast<span>Me</span>
        </a>
      </sec:authorize>
      <sec:authorize access="hasRole('AGENT')">
        <a href="/agent" class="navbar-brand main-logo main-logo-smaller">
          Cast<span>Me</span>
        </a>
      </sec:authorize>
      <div class="d-flex justify-content-around">
        <div class="bell mr-3">
          <i class="fas fa-regular fa-bell mt-2"></i>
          <c:if test="${notifications.size() > 0}">
            <span class="notif-num">
              <small>${notifications.size()}</small>
            </span>
          </c:if>
          <ul class="notification-menu d-none">
            <c:forEach items="${notifications}" var="notification">
              <li>
                ${notification}
              </li>
              <hr/>
            </c:forEach>
          </ul>
        </div>
        <div class="account circle-div text-center">
          <i class="fas fa-user icon-user"></i>
          <ul class="account-menu d-none">
            <li>
              <a href="/user/account">My account</a>
              <hr/>
            </li>
            <li>
              <a href="/logout">Log out</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
