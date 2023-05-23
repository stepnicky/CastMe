<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../commons/top-nav.jsp" %>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="../commons/left-nav.jsp" %>
        <div class="m-4 p-4 width-medium">
            <c:forEach items="${castings}" var="casting">
                <div class="m-4 p-4 border-dashed">
                    <h2 class="dashboard-content-title">
                        <span>${casting.title}</span>
                    </h2>
                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-3">Creation date: <br/> ${casting.createdOn}</th>
                            <th class="col-6"></th>
                            <th class="col-3">Deadline: <br/> ${casting.deadline}</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${casting.roles}" var="role">
                                        <tr class="d-flex">
                                            <td class="col-3"><strong>Role: ${role.title}</strong></td>
                                            <td class="col-6"></td>
                                            <td class="col-3"></td>
                                        </tr>
                                        <c:set var="actors" value="actors${role.id}"/>
                                        <tr class="d-flex">
                                            <td class="col-3">Actors:</td>
                                            <td class=".desc col-6">
                                                <c:forEach var="actor" items="${pageContext.request.getAttribute(actors)}">
                                                    ${actor.user.firstName} ${actor.user.lastName}
                                                </c:forEach>
                                            </td>
                                            <td class="col-3"></td>
                                        </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:forEach>

        </div>
    </div>
</section>
</body>
</html>
