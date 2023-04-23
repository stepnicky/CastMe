<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/top-nav.jsp"%>
  <section class="dashboard-section">
    <div class="row dashboard-nowrap">
      <%@ include file="../commons/left-nav.jsp"%>
      <div class="m-4 p-3 width-medium">
        <div class="dashboard-content border-dashed p-3 m-4 view-height">
          <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
              <h3 class="color-header text-uppercase">casting list</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
              <sec:authorize access="hasRole('CASTING_DIRECTOR')">
                <a href="/director/casting/add" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Add new casting</a>
              </sec:authorize>
            </div>
          </div>

          <div class="schedules-content">
            <table class="table border-bottom">
              <tbody class="text-color-lighter">
                <c:forEach var="casting" items="${castings}">
                  <tr class="d-flex">
                    <td class="col-3">${casting.title}</td>
                    <td class="col-7">${casting.description}</td>
                    <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                      <sec:authorize access="hasRole('CASTING_DIRECTOR')">
                        <a href="/director/casting/${casting.id}/edit" class="btn btn-warning rounded-0 text-light m-1">
                          Edit
                        </a>
                        <a href="/director/casting/${casting.id}/delete"
                           class="delete btn btn-danger rounded-0 text-light m-1"
                           data-toggle="modal" data-target="#exampleModalCenter">
                          Delete
                        </a>
                        <a href="/director/casting/${casting.id}/details" class="btn btn-info rounded-0 text-light m-1">
                          Details
                        </a>
                      </sec:authorize>
                      <sec:authorize access="hasRole('ACTOR')">
                        <a href="/actor/casting/${casting.id}/details" class="btn btn-info rounded-0 text-light m-1">
                          Details
                        </a>
                      </sec:authorize>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </section>

  <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLongTitle">Are you sure?</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          By clicking 'Yes' you will irreversibly delete this casting and all related data.
        </div>
        <div class="modal-footer">
          <a href="" type="button" class="confirm-delete btn btn-primary">Yes</a>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
        </div>
      </div>
    </div>
  </div>
  <script src="/js/deleteConfirmation.js" type="module"></script>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
          crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
          crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
          crossorigin="anonymous"></script>
</body>
</html>
