<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/top-nav.jsp"%>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="../commons/left-nav.jsp"%>
        <div class="m-4 p-3 width-medium">
            <div class="view-height dashboard-content border-dashed p-3 m-4">
                <div class="container w-25">
                    <form:form class="padding-small text-center" modelAttribute="agency">
                        <h1 class="text-color-darker">Create new agency</h1>
                        <div class="form-group">
                            <form:input path="name" class="form-control" placeholder="Agency name" />
                        </div>
                        <div class="form-group">
                            <form:input path="city" class="form-control" placeholder="City" />
                        </div>
                        <div class="form-group">
                            <form:input path="street" class="form-control" placeholder="Street" />
                        </div>
                        <div class="form-group">
                            <form:input path="streetNumber" class="form-control" placeholder="Street number" />
                        </div>
                        <div class="form-group">
                            <button class="btn btn-color" type="submit">Create</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
