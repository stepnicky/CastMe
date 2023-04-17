<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../commons/top-nav.jsp" %>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="../commons/left-nav.jsp" %>
        <div class="m-4 p-4 width-medium">
<%--            <div class="dashboard-header m-4">--%>
<%--                <div class="dashboard-menu">--%>
<%--                    <div class="menu-item border-dashed">--%>
<%--                        <a href="">--%>
<%--                            <i class="far fa-plus-square icon-plus-square"></i>--%>
<%--                            <span class="title">dodaj przepis</span>--%>
<%--                        </a>--%>
<%--                    </div>--%>
<%--                    <div class="menu-item border-dashed">--%>
<%--                        <a href="">--%>
<%--                            <i class="far fa-plus-square icon-plus-square"></i>--%>
<%--                            <span class="title">dodaj plan</span>--%>
<%--                        </a>--%>
<%--                    </div>--%>
<%--                    <div class="menu-item border-dashed">--%>
<%--                        <a href="">--%>
<%--                            <i class="far fa-plus-square icon-plus-square"></i>--%>
<%--                            <span class="title">dodaj przepis do planu</span>--%>
<%--                        </a>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="dashboard-alerts">--%>
<%--                    <div class="alert-item alert-info">--%>
<%--                        <i class="fas icon-circle fa-info-circle"></i>--%>
<%--                        <span class="font-weight-bold">Liczba przepisów: 1</span>--%>
<%--                    </div>--%>
<%--                    <div class="alert-item alert-light">--%>
<%--                        <i class="far icon-calendar fa-calendar-alt"></i>--%>
<%--                        <span class="font-weight-bold">Liczba planów: 1</span>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
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
                            <tr class="d-flex">
                                <td class="col-4">Number of selftapes: 7</td>
                                <td class="col-1"></td>
                                <td class="col-7"></td>
                            </tr>
                            <tr class="d-flex">
                                <td class="col-2"><i class="fas fa-heart" aria-hidden="true"></i> 34</td>
                                <td class="col-1"></td>
                                <td class="col-10"></td>
                            </tr>
                            <tr class="d-flex">
                                <td class="col-4">Days until deadline: 8</td>
                                <td class="col-5"></td>
                                <td class="col-3"><a class="btn btn-primary" href="/director/casting/details/${casting.id}">Details</a></td>
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
