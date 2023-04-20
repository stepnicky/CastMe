<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/top-nav.jsp"%>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="../commons/left-nav.jsp"%>
        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <%--@elvariable id="casting" type="pl.coderslab.castme.Casting.Casting"--%>
                <form:form method="post" modelAttribute="casting">
                    <div class="row border-bottom border-3 p-1 m-1">
                        <div class="col noPadding">
                            <h3 class="color-header text-uppercase">${title}</h3>
                        </div>
                        <div class="col d-flex justify-content-end mb-2 noPadding">
                            <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Save</button>
                        </div>
                    </div>

                    <div class="schedules-content">

                        <div class="form-group row">
                            <label for="castingTitle" class="col-sm-2 label-size col-form-label">
                                Casting title
                            </label>
                            <div class="col-sm-10">
                                <form:input path="title" class="form-control" id="castingTitle" placeholder="Casting title"/>
                                <form:errors path="title" cssClass="error"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="castingDescription" class="col-sm-2 label-size col-form-label">
                                Casting description
                            </label>
                            <div class="col-sm-10">
                                <form:textarea path="description" class="form-control" rows="5" id="castingDescription"
                                          placeholder="Casting description"/>
                                <form:errors path="description" cssClass="error"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="castingDeadline" class="col-sm-2 label-size col-form-label">
                                Casting deadline
                            </label>
                            <div class="col-sm-3">
                                <form:input path="deadline" type="date" class="form-control" rows="5" id="castingDeadline"/>
                                <form:errors path="deadline" cssClass="error"/>
                            </div>
                        </div>

                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>
