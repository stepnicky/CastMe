<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/top-nav.jsp"%>
    <section class="dashboard-section">
        <div class="row dashboard-nowrap">
            <%@include file="../commons/left-nav.jsp"%>
            <div class="m-4 p-3 width-medium text-color-darker">
                <div class="dashboard-content border-dashed p-3 m-4 view-height">

                    <form:form method="post" modelAttribute="role">
                        <div class="mt-4 ml-4 mr-4">
                            <div class="row border-bottom border-3">
                                <div class="col"><h3 class="color-header text-uppercase">${title}</h3></div>
                                <div class="col d-flex justify-content-end mb-2">
                                    <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Save</button>
                                </div>
                            </div>

                            <table class="table borderless">
                                <tbody>
                                    <tr class="d-flex">
                                        <th scope="row" class="col-2">Name</th>
                                        <td class="col-7">
                                            <form:input path="title" class="w-100 p-1" />
                                        </td>
                                    </tr>
                                    <tr class="d-flex">
                                        <th scope="row" class="col-2">Description</th>
                                        <td class="col-7">
                                            <form:textarea path="description" class="w-100 p-1" rows="5"/>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>

                            <div class="row d-flex">
                                <div class="col-5 border-bottom border-3">
                                    <h3 class="text-uppercase">
                                        Feature set
                                    </h3>
                                </div>
                                <div class="col-2"></div>
                                <div class="col-5 border-bottom border-3">
                                    <h3 class="text-uppercase">
                                        Skills
                                    </h3>
                                </div>
                            </div>
                            <div class="d-flex">
                                <div class="col-5 p-4">
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="gender">Gender: </label>
                                        </div>
                                        <div class="col-7">
                                            <select id="gender" name="gender">
                                                <option value="Male">Male</option>
                                                <option value="Female">Female</option>
                                                <option value="Other">Other</option>
                                                <option value="">No difference</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="height">Height: </label>
                                        </div>
                                        <div class="col-7">
                                            <select id="height" name="height">
                                                <option value="short">Short</option>
                                                <option value="medium">Medium</option>
                                                <option value="tall">Tall</option>
                                                <option value="">No difference</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="hairColor">Hair Color: </label>
                                        </div>
                                        <div class="col-7">
                                            <select id="hairColor" name="hairColor">
                                                <option value="blonde">Blonde</option>
                                                <option value="ginger">Ginger</option>
                                                <option value="brown">Brown</option>
                                                <option value="black">Black</option>
                                                <option value="grey">Grey</option>
                                                <option value="white">White</option>
                                                <option value="other">Other</option>
                                                <option value="">No difference</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="hairLength">Hair Length:</label>
                                        </div>
                                        <div class="col-7">
                                            <select id="hairLength" name="hairLength">
                                                <option value="short">Short</option>
                                                <option value="medium">Medium</option>
                                                <option value="long">Long</option>
                                                <option value="bald">Bald</option>
                                                <option value="">No difference</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="eyeColor">Eye Color: </label>
                                        </div>
                                        <div class="col-7">
                                            <select id="eyeColor" name="eyeColor">
                                                <option value="blue">Blue</option>
                                                <option value="brown">Brown</option>
                                                <option value="green">Green</option>
                                                <option value="yellow">Yellow</option>
                                                <option value="black">Black</option>
                                                <option value="grey">Grey</option>
                                                <option value="other">Other</option>
                                                <option value="">No difference</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="figure">Figure: </label>
                                        </div>
                                        <div class="col-7">
                                            <select id="figure" name="figure">
                                                <option value="athletic">Athletic</option>
                                                <option value="overweight">Overweight</option>
                                                <option value="curvy">Curvy</option>
                                                <option value="slim">Slim</option>
                                                <option value="muscular">Muscular</option>
                                                <option value="other">Other</option>
                                                <option value="">No difference</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="ageFrom">Age from: </label>
                                        </div>
                                        <div class="col-7">
                                            <input type="number" id="ageFrom" name="ageFrom" class="age-input"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="ageTo">to: </label>
                                        </div>
                                        <div class="col-7">
                                            <input type="number" id="ageTo" name="ageTo" class="age-input"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-2"></div>

                                <div class="col-5 p-4">
                                    <div class="form-group row">
                                        <form:checkboxes path="skills" items="${skills}" itemValue="id" itemLabel="name" class="skills"/>
                                    </div>
                                </div>
                            </div>
<%--                            <div class="row d-flex">--%>
<%--                                <div class="col-5 p-4">--%>
<%--                                    <label for="gender">Gender:</label>--%>
<%--                                    <select id="gender" name="gender">--%>
<%--                                        <option value="Male">Male</option>--%>
<%--                                        <option value="Female">Female</option>--%>
<%--                                        <option value="Other">Other</option>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                                <div class="col-2"></div>--%>

<%--                                <div class="col-5 p-4">--%>
<%--                                    <form:select path="skills" items="${skills}" itemValue="id" itemLabel="name"/>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row d-flex">--%>
<%--                                <div class="col-5 p-4">--%>
<%--                                    <label for="height">Height:</label>--%>
<%--                                    <select id="height" name="height">--%>
<%--                                        <option value="short">Short</option>--%>
<%--                                        <option value="medium">Medium</option>--%>
<%--                                        <option value="tall">Tall</option>--%>
<%--                                        <option value="">No difference</option>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                                <div class="col-2"></div>--%>

<%--                                <div class="col-5 p-4">--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row d-flex">--%>
<%--                                <div class="col-5 p-4">--%>
<%--                                    <label for="hairColor">Hair Color:</label>--%>
<%--                                    <select id="hairColor" name="hairColor">--%>
<%--                                        <option value="blonde">Blonde</option>--%>
<%--                                        <option value="ginger">Ginger</option>--%>
<%--                                        <option value="brown">Brown</option>--%>
<%--                                        <option value="black">Black</option>--%>
<%--                                        <option value="grey">Grey</option>--%>
<%--                                        <option value="white">White</option>--%>
<%--                                        <option value="other">Other</option>--%>
<%--                                        <option value="">No difference</option>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                                <div class="col-2"></div>--%>

<%--                                <div class="col-5 p-4">--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row d-flex">--%>
<%--                                <div class="col-5 p-4">--%>
<%--                                    <label for="hairLength">Hair Length:</label>--%>
<%--                                    <select id="hairLength" name="hairLength">--%>
<%--                                        <option value="short">Short</option>--%>
<%--                                        <option value="medium">Medium</option>--%>
<%--                                        <option value="long">Long</option>--%>
<%--                                        <option value="bald">Bald</option>--%>
<%--                                        <option value="">No difference</option>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                                <div class="col-2"></div>--%>

<%--                                <div class="col-5 p-4">--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row d-flex">--%>
<%--                                <div class="col-5 p-4">--%>
<%--                                    <label for="eyeColor">Eye Color:</label>--%>
<%--                                    <select id="eyeColor" name="eyeColor">--%>
<%--                                        <option value="blue">Blue</option>--%>
<%--                                        <option value="brown">Brown</option>--%>
<%--                                        <option value="green">Green</option>--%>
<%--                                        <option value="yellow">Yellow</option>--%>
<%--                                        <option value="black">Black</option>--%>
<%--                                        <option value="grey">Grey</option>--%>
<%--                                        <option value="other">Other</option>--%>
<%--                                        <option value="">No difference</option>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                                <div class="col-2"></div>--%>

<%--                                <div class="col-5 p-4">--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row d-flex">--%>
<%--                                <div class="col-5 p-4">--%>
<%--                                    <label for="figure">Figure:</label>--%>
<%--                                    <select id="figure" name="figure">--%>
<%--                                        <option value="athletic">Athletic</option>--%>
<%--                                        <option value="overweight">Overweight</option>--%>
<%--                                        <option value="curvy">Curvy</option>--%>
<%--                                        <option value="slim">Slim</option>--%>
<%--                                        <option value="muscular">Muscular</option>--%>
<%--                                        <option value="other">Other</option>--%>
<%--                                        <option value="">No difference</option>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                                <div class="col-2"></div>--%>

<%--                                <div class="col-5 p-4">--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row d-flex">--%>
<%--                                <div class="col-5 p-4">--%>
<%--                                    <label for="ageFrom">Age from: </label>--%>
<%--                                    <input type="number" id="ageFrom" name="ageFrom"/>--%>
<%--                                </div>--%>
<%--                                <div class="col-5"><label for="ageTo">to: </label>--%>
<%--                                    <input type="number" id="ageTo" name="ageTo"/></div>--%>

<%--                                <div class="col-2 p-4">--%>

<%--                                </div>--%>
<%--                            </div>--%>
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
