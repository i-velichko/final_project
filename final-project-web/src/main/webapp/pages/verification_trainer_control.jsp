<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 04.08.2021
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verification trainer</title>
</head>
<body>
<%@include file="/fragment/header.jsp" %>


<div class="container">

    <section>

        <div class="card rounded mb-4">

            <div class="row">

                <div class="col-md-6">
                    <img class="img-fluid rounded rounded-left"
                         src="http://cr.openjdk.java.net/~jeff/Duke/png/Thumbsup1.png"
                         alt="project image">
                </div>

                <div class="col-md-6 p-5 align-self-center">

                    <h5 class="font-weight-normal mb-3" data-mdb-toggle="animation"
                        data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-down"
                        data-mdb-animation-duration="1000">Project verification detail</h5>
                    <p class="text-muted mb-2" data-mdb-toggle="animation"
                       data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-up"
                       data-mdb-animation-duration="800">
                    <div>Student - ${verification.student.firstName} ${verification.student.lastName}</div>
                    <div>Trainer - ${verification.trainer.firstName} ${verification.trainer.lastName}</div>
                    <div>Examiner - ${verification.examiner.firstName} ${verification.examiner.lastName}</div>
                    Verification status - ${verification.verificationStatus} </p>
                    <ul class="list-unstyled font-small mt-5 mb-0">

                        <li>
                            <p class="text-uppercase mb-2" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-down"
                               data-mdb-animation-duration="1000"><strong>Title</strong></p>
                            <p class="text-muted mb-4" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-up"
                               data-mdb-animation-duration="1000">${verification.title}</p>
                        </li>

                        <li>
                            <p class="text-uppercase mb-2" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-down"
                               data-mdb-animation-duration="1000"><strong>Dates</strong></p>
                            <p class="text-muted mb-4" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-up"
                               data-mdb-animation-duration="1000">
                            <div>Application date - ${verification.applicationDate}</div>
                            <div> Trainer verification date - ${verification.trainerVerificationDate}</div>
                            <c:if test="${sessionScope.user.role == 'TRAINER'}">
                                 <span>
                                <form action="${abs_path}/controller?command=change_trainer_verification_date&verificationId=${verification.id}"
                                      method="post">
                                    <label for="dateTime"><h6 style="color: #157347">Change date</h6></label>
                                    <input id="dateTime" type="datetime-local" name="dateTime">
                                    <input type="submit" value="submit">
                                </form>
                            </span>
                            </c:if>
                            <div> Examiner verification date - ${verification.examinerVerificationDate}</div>
                            </p>
                        </li>


                        <li>
                            <p class="text-uppercase mb-2" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-down"
                               data-mdb-animation-duration="1000"><strong>Trainer score</strong></p>
                            <p class="text-muted mb-4" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-up"
                               data-mdb-animation-duration="1000">${verification.trainerScore}</p>
                            <c:if test="${sessionScope.user.role == 'TRAINER'}">
                                <p class="text-uppercase mb-2">
                                <form class="form-inline"
                                      action="${abs_path}/controller?command=change_trainer_score&verificationId=${verification.id}"
                                      method="post">
                                    <label class="my-1 mr-2" for="inlineFormCustomSelectPref"><h6
                                            style="color: #157347">Change score</h6></label>
                                    <select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref"
                                            name="newScore">
                                        <option selected>scores</option>
                                        <c:forEach items="${scores}" var="score">
                                            <option value=${score}>${score}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="submit" value="submit">
                                </form>
                                </p>
                            </c:if>
                        </li>

                        <li>
                            <p class="text-uppercase mb-2" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-down"
                               data-mdb-animation-duration="1000"><strong>Student characteristic</strong></p>
                            <p class="text-muted mb-4" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-up"
                               data-mdb-animation-duration="1000">${verification.trainerCharacteristic}</p>
                            <c:if test="${sessionScope.user.role == 'TRAINER'}">
                                <div class="mb-3">
                                    <form action="${abs_path}/controller?command=change_trainer_characteristic&verificationId=${verification.id}"
                                          method="post">
                                        <label for="exampleFormControlTextarea1" class="form-label">
                                            <h6 style="color: #157347">Write characteristic
                                                for this student</h6>
                                        </label>
                                        <textarea class="form-control" name="characteristic"
                                                  id="exampleFormControlTextarea1"
                                                  rows="3"></textarea>
                                        <input type="submit" value="submit"></form>
                                </div>
                            </c:if>
                        </li>

                        <li>
                            <p class="text-uppercase mb-2" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-down"
                               data-mdb-animation-duration="1000"><strong>Final status</strong></p>
                            <p class="text-muted mb-4" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-up"
                               data-mdb-animation-duration="1000">${verification.finalStatus}</p>
                        </li>

                    </ul>

                </div>

            </div>

        </div>

    </section>

</div>

<style>
    .eyes {
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        width: 100%;
        text-align: center;
    }

    .eye {
        width: 240px;
        height: 120px;
        background: #fff;
        display: inline-block;
        margin: 40px;
        border-radius: 50%;
        position: relative;
        overflow: hidden;
    }

    .ball {
        width: 50px;
        height: 50px;
        background: #000;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        border-radius: 50%;
        border: 10px solid #333;
    }
</style>
</body>
</html>
