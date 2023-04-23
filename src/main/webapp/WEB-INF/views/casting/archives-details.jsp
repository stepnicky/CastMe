<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../commons/top-nav.jsp"%>
<section class="dashboard-section">
  <div class="row dashboard-nowrap">
    <%@include file="../commons/left-nav.jsp"%>
    <div class="m-4 p-3 width-medium ">
      <div class="dashboard-content border-dashed p-3 m-4">
        <div class="row border-bottom border-3 p-1 m-1">
          <div class="col noPadding">
            <h3 class="color-header text-uppercase">casting details</h3>
          </div>
          <div class="col d-flex justify-content-end mb-2 noPadding">
            <sec:authorize access="hasRole('CASTING_DIRECTOR')">
              <a href="/director/casting/archives"
                 class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">
                Back to list
              </a>
            </sec:authorize>
            <sec:authorize access="hasRole('ACTOR')">
              <a href="/actor/casting/archives"
                 class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">
                Back to list
              </a>
            </sec:authorize>
          </div>
        </div>

        <div class="schedules-content">
          <div class="schedules-content-header">
            <div class="form-group row">
              <span class="col-sm-2 label-size col-form-label">
                  Casting title
              </span>
              <div class="col-sm-10">
                <p class="schedules-text">${casting.title}</p>
              </div>
            </div>
            <div class="form-group row">
              <span class="col-sm-2 label-size col-form-label">
                  Casting description
              </span>
              <div class="col-sm-10">
                <p class="schedules-text">
                  ${casting.description}
                </p>
              </div>
            </div>
            <div class="form-group row">
              <span class="col-sm-2 label-size col-form-label">
                  Deadline
              </span>
              <div class="col-sm-10">
                <p class="schedules-text">
                  ${casting.deadline}
                </p>
              </div>
            </div>
          </div>

          <table class="table">
            <thead>
            <tr class="d-flex">
              <th class="col-2">Roles</th>
              <th class="col-7"></th>
              <th class="col-1"></th>
              <th class="col-2"></th>
            </tr>
            </thead>
            <tbody class="text-color-lighter">
            <c:forEach items="${roles}" var="role">
              <tr class="d-flex">
                <td class="col-2">${role.title}</td>
                <td class="col-6">${role.description}</td>
                <c:set var="numOfLikes" value="numOfLikes${role.id}"/>
                <td class="col-2">
                  <i class="fas fa-heart" aria-hidden="true"></i>
                    ${pageContext.request.getAttribute(numOfLikes)}
                </td>
                <td class="col-2 center">
                  <sec:authorize access="hasRole('CASTING_DIRECTOR')">
                    <a href="/director/role/${role.id}/delete"
                       class="delete btn btn-danger rounded-0 text-light m-1"
                       data-toggle="modal" data-target="#exampleModalCenter">
                      Delete
                    </a>
                    <a href="/director/role/${role.id}/details"
                       class="btn btn-info rounded-0 text-light m-1">
                      Details
                    </a>
                  </sec:authorize>
                  <sec:authorize access="hasRole('ACTOR')">
                  <a href="/actor/role/${role.id}/details"
                     class="btn btn-info rounded-0 text-light m-1">
                    Details
                  </a>
                  </sec:authorize>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</section>

<script src="/js/deleteConfirmation.js" type="module"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>
