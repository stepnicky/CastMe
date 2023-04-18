<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav flex-column long-bg">
  <li class="nav-item">
    <sec:authorize access="hasRole('CASTING_DIRECTOR')">
      <a class="nav-link" href="/director/casting/add">
        <span>Add new casting</span>
        <i class="fas fa-angle-right"></i>
      </a>
    </sec:authorize>
    <sec:authorize access="hasRole('ACTOR')">
      <a class="nav-link" href="/actor/profile">
        <span>My profile</span>
        <i class="fas fa-angle-right"></i>
      </a>
    </sec:authorize>
  </li>
  <li class="nav-item">
    <sec:authorize access="hasRole('CASTING_DIRECTOR')">
      <a class="nav-link" href="/director/actors/list">
        <span>Actors</span>
        <i class="fas fa-angle-right"></i>
      </a>
    </sec:authorize>
    <sec:authorize access="hasRole('ACTOR')">
      <a class="nav-link" href="/actor/agency">
        <span>My agency</span>
        <i class="fas fa-angle-right"></i>
      </a>
    </sec:authorize>
  </li>
  <li class="nav-item">
    <sec:authorize access="hasRole('CASTING_DIRECTOR')">
      <a class="nav-link" href="/director/agency/list">
        <span>Agencies</span>
        <i class="fas fa-angle-right"></i>
      </a>
    </sec:authorize>
    <sec:authorize access="hasRole('ACTOR')">
      <a class="nav-link" href="/actor/liked">
        <span>Liked</span>
        <i class="fas fa-angle-right"></i>
      </a>
    </sec:authorize>
  </li>
  <li class="nav-item">
    <sec:authorize access="hasRole('CASTING_DIRECTOR')">
      <a class="nav-link" href="/director/casting/list">
        <span>Castings</span>
        <i class="fas fa-angle-right"></i>
      </a>
    </sec:authorize>
    <sec:authorize access="hasRole('ACTOR')">
      <a class="nav-link" href="/actor/casting/list">
        <span>Casting list</span>
        <i class="fas fa-angle-right"></i>
      </a>
    </sec:authorize>
  </li>
  <li class="nav-item">
    <sec:authorize access="hasRole('CASTING_DIRECTOR')">
      <a class="nav-link disabled" href="/director/casting/archives">
        <span>Archives</span>
        <i class="fas fa-angle-right"></i>
      </a>
    </sec:authorize>
    <sec:authorize access="hasRole('ACTOR')">
      <a class="nav-link" href="/actor/casting/archives">
        <span>Archives</span>
        <i class="fas fa-angle-right"></i>
      </a>
    </sec:authorize>
  </li>
<%--  <li class="nav-item">--%>
<%--    <a class="nav-link" href="/super-admin-users.html">--%>
<%--      <span>Użytkownicy</span>--%>
<%--      <i class="fas fa-angle-right"></i>--%>
<%--    </a>--%>
<%--  </li>--%>
</ul>
