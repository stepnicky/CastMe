<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/top-nav.jsp"%>
    <section class="dashboard-section">
        <div class="row dashboard-nowrap">
            <%@include file="../commons/left-nav.jsp"%>
            <div class="m-4 p-3 width-medium text-color-darker">
                <div class="dashboard-content border-dashed p-3 m-4 view-height">

                    <form:form method="post" modelAttribute="role" enctype="multipart/form-data">
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
                                    <tr class="d-flex">
                                        <th scope="row" class="col-2">Attachments</th>
                                        <td class="col-7">
                                            <input type="file" name="attachments" multiple="multiple"/>
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
                                            <select id="gender" name="gender" >
                                                <option value="male" <c:if test="${'male' eq featureSet.gender}">selected</c:if>>
                                                    Male
                                                </option>
                                                <option value="female" <c:if test="${'female' eq featureSet.gender}">selected</c:if>>
                                                    Female
                                                </option>
                                                <option value="other" <c:if test="${'o2ther' eq featureSet.gender}">selected</c:if>>
                                                    Other
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="height">Height: </label>
                                        </div>
                                        <div class="col-7">
                                            <select id="height" name="height">
                                                <option value="short" <c:if test="${'short' eq featureSet.height}">selected</c:if>>
                                                    Short
                                                </option>
                                                <option value="medium" <c:if test="${'medium' eq featureSet.height}">selected</c:if>>
                                                    Medium
                                                </option>
                                                <option value="tall" <c:if test="${'tall' eq featureSet.height}">selected</c:if>>
                                                    Tall
                                                </option>
                                                <option value="" <c:if test="${'' eq featureSet.height}">selected</c:if>>
                                                    No difference
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="hairColor">Hair Color: </label>
                                        </div>
                                        <div class="col-7">
                                            <select id="hairColor" name="hairColor">
                                                <option value="blonde" <c:if test="${'blonde' eq featureSet.hairColor}">selected</c:if>>
                                                    Blonde
                                                </option>
                                                <option value="ginger" <c:if test="${'ginger' eq featureSet.hairColor}">selected</c:if>>
                                                    Ginger
                                                </option>
                                                <option value="brown" <c:if test="${'brown' eq featureSet.hairColor}">selected</c:if>>
                                                    Brown
                                                </option>
                                                <option value="black" <c:if test="${'black' eq featureSet.hairColor}">selected</c:if>>
                                                    Black
                                                </option>
                                                <option value="grey" <c:if test="${'grey' eq featureSet.hairColor}">selected</c:if>>
                                                    Grey
                                                </option>
                                                <option value="white" <c:if test="${'white' eq featureSet.hairColor}">selected</c:if>>
                                                    White
                                                </option>
                                                <option value="other" <c:if test="${'other' eq featureSet.hairColor}">selected</c:if>>
                                                    Other
                                                </option>
                                                <option value="" <c:if test="${'' eq featureSet.hairColor}">selected</c:if>>
                                                    No difference
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="hairLength">Hair Length:</label>
                                        </div>
                                        <div class="col-7">
                                            <select id="hairLength" name="hairLength">
                                                <option value="short" <c:if test="${'short' eq featureSet.hairLength}">selected</c:if>>
                                                    Short
                                                </option>
                                                <option value="medium" <c:if test="${'medium' eq featureSet.hairLength}">selected</c:if>>
                                                    Medium
                                                </option>
                                                <option value="long" <c:if test="${'long' eq featureSet.hairLength}">selected</c:if>>
                                                    Long
                                                </option>
                                                <option value="bald" <c:if test="${'bald' eq featureSet.hairLength}">selected</c:if>>
                                                    Bald
                                                </option>
                                                <option value="" <c:if test="${'' eq featureSet.hairLength}">selected</c:if>>
                                                    No difference
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="eyeColor">Eye Color: </label>
                                        </div>
                                        <div class="col-7">
                                            <select id="eyeColor" name="eyeColor">
                                                <option value="blue" <c:if test="${'blue' eq featureSet.eyeColor}">selected</c:if>>
                                                    Blue
                                                </option>
                                                <option value="brown" <c:if test="${'brown' eq featureSet.eyeColor}">selected</c:if>>
                                                    Brown
                                                </option>
                                                <option value="green" <c:if test="${'green' eq featureSet.eyeColor}">selected</c:if>>
                                                    Green
                                                </option>
                                                <option value="yellow" <c:if test="${'yellow' eq featureSet.eyeColor}">selected</c:if>>
                                                    Yellow
                                                </option>
                                                <option value="black" <c:if test="${'black' eq featureSet.eyeColor}">selected</c:if>>
                                                    Black
                                                </option>
                                                <option value="grey" <c:if test="${'grey' eq featureSet.eyeColor}">selected</c:if>>
                                                    Grey
                                                </option>
                                                <option value="other" <c:if test="${'other' eq featureSet.eyeColor}">selected</c:if>>
                                                    Other
                                                </option>
                                                <option value="" <c:if test="${'' eq featureSet.eyeColor}">selected</c:if>>
                                                    No difference
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="figure">Figure: </label>
                                        </div>
                                        <div class="col-7">
                                            <select id="figure" name="figure">
                                                <option value="athletic" <c:if test="${'athletic' eq featureSet.figure}">selected</c:if>>
                                                    Athletic
                                                </option>
                                                <option value="overweight" <c:if test="${'overweight' eq featureSet.figure}">selected</c:if>>
                                                    Overweight
                                                </option>
                                                <option value="curvy" <c:if test="${'curvy' eq featureSet.figure}">selected</c:if>>
                                                    Curvy
                                                </option>
                                                <option value="slim" <c:if test="${'slim' eq featureSet.figure}">selected</c:if>>
                                                    Slim
                                                </option>
                                                <option value="muscular" <c:if test="${'muscular' eq featureSet.figure}">selected</c:if>>
                                                    Muscular
                                                </option>
                                                <option value="other" <c:if test="${'other' eq featureSet.figure}">selected</c:if>>
                                                    Other
                                                </option>
                                                <option value="" <c:if test="${'' eq featureSet.figure}">selected</c:if>>
                                                    No difference
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="ageFrom">Age from: </label>
                                        </div>
                                        <div class="col-7">
                                            <input type="number" id="ageFrom" name="ageFrom"
                                                   class="age-input" value="${featureSet.ageFrom}"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-5">
                                            <label for="ageTo">to: </label>
                                        </div>
                                        <div class="col-7">
                                            <input type="number" id="ageTo" name="ageTo"
                                                   class="age-input" value="${featureSet.ageTo}"/>
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
                        </div>
                        <input type="hidden" name="featureSetId" value="${featureSet.id}"/>
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
