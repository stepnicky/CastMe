<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/top-nav.jsp"%>
  <section class="dashboard-section">
    <div class="row dashboard-nowrap">
      <%@include file="../commons/left-nav.jsp" %>
      <div class="m-4 p-3 width-medium text-color-darker">
        <div class="dashboard-content border-dashed p-3 m-4 view-height">
          <c:forEach items="${actors}" var="actor">
            <a href="<c:url value="/director/actor/${actor.id}"/>">
                ${actor.user.firstName} ${actor.user.lastName}
            </a>
          </c:forEach>
        </div>
      </div>
    </div>
  </section>
</body>
</html>
