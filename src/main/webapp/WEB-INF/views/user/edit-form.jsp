<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/top-nav.jsp"%>
    <section class="dashboard-section">
        <div class="row dashboard-nowrap">
        <%@ include file="../commons/left-nav.jsp"%>
            <div class="m-4 p-3 width-medium">
                <div class="view-height dashboard-content border-dashed p-3 m-4">
                    <div class="container w-25">
                        <form:form class="padding-small text-center" method="post" modelAttribute="user">
                            <h1 class="text-color-darker">Update your personal information</h1>
                            <div class="form-group">
                                <form:input path="firstName" class="form-control" placeholder="First name"/>
                            </div>
                            <form:errors path="firstName" cssClass="error"/>
                            <div class="form-group">
                                <form:input path="lastName" class="form-control" placeholder="Last name"/>
                            </div>
                            <form:errors path="lastName" cssClass="error"/>
                            <div class="form-group">
                                <form:input path="email" class="form-control" placeholder="Email"/>
                            </div>
                            <form:errors path="email" cssClass="error"/>
                            <div class="form-group">
                                <form:input path="phoneNumber" class="form-control" placeholder="Phone number"/>
                            </div>
                            <form:errors path="phoneNumber" cssClass="error"/>
                            <form:hidden path="id" />
                            <form:hidden path="password" />
                            <form:hidden path="userRole" />
                            <button class="btn btn-color rounded-0" type="submit">Save</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
