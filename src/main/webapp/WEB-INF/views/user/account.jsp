<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/top-nav.jsp" %>
    <section class="dashboard-section">
        <div class="row dashboard-nowrap">
            <%@ include file="../commons/left-nav.jsp" %>
            <div class="m-4 p-3 width-medium">
                <div class="dashboard-content border-dashed p-3 m-4 view-height">
                    <div class="row border-bottom border-3 p-1 m-1">
                        <div class="col noPadding">
                            <h3 class="color-header text-uppercase">user account</h3>
                        </div>
                        <div class="col d-flex justify-content-end mb-2 noPadding">
                            <a href="/user/account/edit" class="btn btn-warning rounded-0 pt-0 pb-0 pr-4 pl-4">Edit</a>
                        </div>
                    </div>

                    <div class="schedules-content">
                        <div class="form-group row">
                            <div class="col-sm-2 label-size col-form-label">
                                First name
                            </div>
                            <div class="col-sm-3">
                                ${user.firstName}
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-2 label-size col-form-label">
                                Last name
                            </div>
                            <div class="col-sm-3">
                                ${user.lastName}
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-2 label-size col-form-label">
                                Email address
                            </div>
                            <div class="col-sm-3">
                                ${user.email}
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-2 label-size col-form-label">
                                Phone number
                            </div>
                            <div class="col-sm-3">
                                ${user.phoneNumber}
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-2 label-size col-form-label">
                                Area of expertise
                            </div>
                            <div class="col-sm-3">
                                ${user.userRole.label}
                            </div>
                        </div>
                    </div>
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
