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
  <link href="/css/style.css" rel="stylesheet" type="text/css">
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
        <a class="nav-link disabled" href="/contact">contact us</a>
      </li>
    </ul>
  </nav>
</header>

<section class="dashboard-section">
  <div class="container pt-4 pb-4">
    <div class="border-dashed view-height">
      <div class="container w-25">
        <form class="padding-small text-center" method="post" action="/login">
          <h1 class="text-color-darker">Sign in</h1>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <div class="form-group">
            <input type="email" class="form-control" id="email" name="username" placeholder="email">
          </div>
          <div class="form-group">
            <input type="password" class="form-control" id="password" name="password" placeholder="password">
          </div>
          <button class="btn btn-color" type="submit">Sign in</button>
        </form>
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
