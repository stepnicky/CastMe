<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/top-nav.jsp"%>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="../commons/left-nav.jsp"%>
        <div class="m-4 p-3 width-medium">
            <div class="view-height dashboard-content border-dashed p-3 m-4">
                <div class="container">
                    <form:form class="padding-small text-center" modelAttribute="agent">
                        <h1 class="text-color-darker">Select agency from the list below or click 'Create new' to create new agency</h1>
                        <div class="row align-items-center justify-content-center form-group">
                            <div class="col-auto">
                                <form:select class="form-control" path="agency" items="${agencies}" itemValue="id" itemLabel="name"/>
                            </div>
                            <div class="col-auto">
                                <a href="/agent/agency/create" class="btn btn-primary">Create new</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-color" type="submit">Submit</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>

