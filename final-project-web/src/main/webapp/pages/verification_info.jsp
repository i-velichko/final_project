<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 27.07.2021
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verification info</title>
</head>
<body>
<%@include file="/fragment/header.jsp" %>

<div class="container my-5">

    <section>

        <div class="card rounded mb-4">

            <div class="row">

                <div class="col-md-6">
                    <img class="img-fluid rounded rounded-left"
                         src="https://i.ibb.co/mcXV5bL/871030872.jpg"
                         alt="project image">
                </div>

                <div class="col-md-6 p-5 align-self-center">

                    <h5 class="font-weight-normal mb-3" data-mdb-toggle="animation"
                        data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-down"
                        data-mdb-animation-duration="1000">Project verification detail</h5>
                    <p class="text-muted mb-2" data-mdb-toggle="animation"
                       data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-up"
                       data-mdb-animation-duration="1000">
                        Student - ${verification.student.firstName} ${verification.student.lastName},
                        Trainer - ${verification.trainer.firstName} ${verification.trainer.lastName},
                        Examiner - ${verification.examiner.firstName} ${verification.examiner.lastName},
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
                               data-mdb-animation-duration="1000">Application date - ${verification.applicationDate},
                                Trainer verification date - ${verification.trainerVerificationDate},
                             Examiner verification date - ${verification.examinerVerificationDate}</p>
                        </li>


                        <li>
                            <p class="text-uppercase mb-2" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-down"
                               data-mdb-animation-duration="1000"><strong>Trainer score</strong></p>
                            <p class="text-muted mb-4" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-up"
                               data-mdb-animation-duration="1000">${verification.trainerScore}</p>
                            <c:if test="${sessionScope.user.role == 'TRAINER'}">
                                <p class="text-muted mb-4" data-mdb-toggle="animation"
                                   data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-up"
                                   data-mdb-animation-duration="1000">bla bla bla</p>
                            </c:if>
                        </li>

                        <li>
                            <p class="text-uppercase mb-2" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-down"
                               data-mdb-animation-duration="1000"><strong>Student characteristic</strong></p>
                            <p class="text-muted mb-4" data-mdb-toggle="animation"
                               data-mdb-animation-start="onLoad" data-mdb-animation="slide-in-up"
                               data-mdb-animation-duration="1000">${verification.trainerCharacteristic}</p>
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
