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
                            <tr class="d-flex">
                                <td class="col-4">Number of roles: ${casting.roles.size()}</td>
                                <td class="col-1"></td>
                                <td class="col-7"></td>
                            </tr>
                            <c:set var="numOfSelftapes" value="numOfSelftapes${casting.id}"/>
                            <tr class="d-flex">
                                <td class="col-4">
                                    <i class="fas fa-check"></i>
                                    ${pageContext.request.getAttribute(numOfSelftapes)}
                                </td>
                                <td class="col-1"></td>
                                <td class="col-7"></td>
                            </tr>
                            <c:set var="numOfLikes" value="numOfLikes${casting.id}"/>
                            <tr class="d-flex">
                                <td class="col-4">
                                    <i class="fas fa-heart"></i>
                                    ${pageContext.request.getAttribute(numOfLikes)}
                                </td>
                                <td class="col-1"></td>
                                <td class="col-7"></td>
                            </tr>
                            <c:set var="daysTillDeadline" value="daysTillDeadline${casting.id}"/>
                            <tr class="d-flex">
                                <td class="col-4">
                                    Days until deadline:
                                    ${pageContext.request.getAttribute(daysTillDeadline)}
                                </td>
                                <td class="col-5"></td>
                                <td class="col-3"><a class="btn btn-primary" href="/director/casting/${casting.id}/details">Details</a></td>
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
