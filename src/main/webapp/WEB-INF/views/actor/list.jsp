<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/top-nav.jsp"%>
  <section class="dashboard-section">
    <div class="row dashboard-nowrap">
      <%@include file="../commons/left-nav.jsp" %>
      <div class="m-4 p-3 width-medium text-color-darker">
        <div class="dashboard-content border-dashed p-3 m-4 view-height">
          <div class="gallery">
            <ul>
              <c:forEach var="photo" items="${photos}">
                <li class="photo">
                  <a href="<c:url value="/director/actor/${photo.actor.id}"/>">
                    <img src="data:image/jpeg;base64,${photo.base64Image}"/>
                    <div class="actor-name">
                      <h5>${photo.actor.user.firstName} ${photo.actor.user.lastName}</h5>
                    </div>
                  </a>
                </li>
              </c:forEach>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </section>
</body>
</html>
