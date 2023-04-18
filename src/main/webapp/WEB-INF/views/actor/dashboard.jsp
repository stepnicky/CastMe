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
                            <c:forEach items="${actorRoles}" var="actorRole">
                                <c:if test="${role.id == actorRole.role.id}">

                                    <tr class="d-flex">
                                        <td class="col-3"><strong>Role: ${role.title}</strong></td>
                                        <td class="col-6"></td>
                                        <td class="col-3"></td>
                                    </tr>
                                    <tr class="d-flex">
                                        <td class="col-3">Description:</td>
                                        <td class=".desc col-6">${role.description}</td>
                                        <td class="col-3"></td>
                                    </tr>
                                    <tr class="d-flex">
                                        <td class="col-5">
                                            <i class="fas fa-heart" aria-hidden="true"></i>
                                            ${actorRole.status.equals("invited") ? "Hit like and take part" : "You like it"}
                                        </td>
                                        <td class="col-5"></td>
                                        <td class="col-2"></td>
                                    </tr>
                                    </c:if>
                            </c:forEach>
                        </c:forEach>
                        <tr class="d-flex">
                            <td class="col-4">Days until deadline: 8</td>
                            <td class="col-5"></td>
                            <td class="col-3">
                                <a class="btn btn-primary" href="/actor/casting/details/${casting.id}">
                                    Details
                                </a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </c:forEach>

        </div>
    </div>
</section>
</body>
</html>
