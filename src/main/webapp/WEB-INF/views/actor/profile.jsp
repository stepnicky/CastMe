<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ include file="../commons/top-nav.jsp"%>
    <section class="dashboard-section">
      <div class="row dashboard-nowrap">
        <%@ include file="../commons/left-nav.jsp"%>
        <div class="m-4 p-3 width-medium text-color-darker">
          <div class="dashboard-content border-dashed p-3 m-4 view-height">
            <div class="mt-4 ml-4 mr-4">
              <div class="row border-bottom border-3">
                <div class="col">
                  <h3 class="color-header text-uppercase">
                    ${actor.user.firstName} ${actor.user.lastName}
                  </h3>
                </div>
                <div class="col d-flex justify-content-end mb-2 noPadding">
                  <a href="/actor/my-profile/edit"
                     class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">
                    Edit profile
                  </a>
                </div>
              </div>
              <table class="table borderless">
                <tbody>
                  <tr class="d-flex">
                    <th scope="row" class="col-2">Agency</th>
                    <td class="col-7">
                      ${actor.agency.name}
                    </td>
                  </tr>
                  <tr class="d-flex">
                    <th scope="row" class="col-2">Education</th>
                    <td class="col-7">${actor.education}</td>
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
              <div class="row d-flex">
                <ul class="col-5 p-4 list-unstyled">
                  <li>Gender: ${actor.featureSet.gender}</li>
                  <li>Height: ${actor.featureSet.height}</li>
                  <li>Hair length: ${actor.featureSet.hairLength}</li>
                  <li>Hair color: ${actor.featureSet.hairColor}</li>
                  <li>Eye color: ${actor.featureSet.eyeColor}</li>
                  <li>Figure: ${actor.featureSet.figure}</li>
                  <li>Age: ${actor.featureSet.ageFrom} - ${actor.featureSet.ageTo}</li>
                </ul>
                <div class="col-2"></div>
                <ul class="col-5 p-4 list-unstyled">
                  <c:forEach var="skill" items="${actor.skills}">
                    <li>${skill.name}</li>
                  </c:forEach>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
  </body>
</html>

