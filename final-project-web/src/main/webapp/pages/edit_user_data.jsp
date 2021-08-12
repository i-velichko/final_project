<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 12.08.2021
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <title>Title</title>
    <ctg:is-student>
        <%@include file="/fragment/student_header.jsp" %>
    </ctg:is-student>

    <ctg:is-trainer>
        <%@include file="/fragment/trainer_header.jsp" %>
    </ctg:is-trainer>

    <ctg:is-examiner>
        <%@include file="/fragment/examiner_header.jsp" %>
    </ctg:is-examiner>

    <ctg:is-admin>
        <%@include file="/fragment/admin_header.jsp" %>
    </ctg:is-admin>

    <ctg:is-chief>
        <%@include file="/fragment/chief_header.jsp" %>
    </ctg:is-chief>
</head>
<body>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                <img class="rounded-circle mt-5" width="150px"
                     src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                <span class="font-weight-bold">
                    ${sessionScope.user.firstName}
                </span>
                <span class="text-black-50">${sessionScope.user.role}</span>
                <span>
                </span>
            </div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Profile Settings</h4>
                </div>
                <form action="${abs_path}/controller?command=edit_user_data" method="post">
                    <div class="row mt-2">
                        <div class="col-md-6">
                            <label class="labels">Firs Name:</label>
                            <label class="form-control">
                                <input type="text" pattern="^[\w@#$%^&+=]{2,30}$" name="nickname" value="${sessionScope.user.firstName}" required></label>
                            <strong>
                                <p class="text-danger">${errors.firstNameError}</p>
                            </strong>
                        </div>
                        <div class="col-md-6">
                            <label class="labels">Email:</label>
                            <label class="form-control">${sessionScope.user.lastName}</label>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-6">
                            <label class="labels">Your id:</label>
                            <label class="form-control">${sessionScope.user.gitLink}</label>
                        </div>
                        <div class="col-md-6">
                            <label class="labels">Status:</label>
                            <label class="form-control">${sessionScope.user.status}</label>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-6">
                            <label class="labels">Name:</label>
                            <label class="form-control">
                                <input type="text" name="name" value="${sessionScope.user.lastName}"></label>
                        </div>
                        <div class="col-md-6">
                            <label class="labels">Surename:</label>
                            <label class="form-control">
                                <input type="text" name="surename" value="${sessionScope.user.email}"></label>
                        </div>
                    </div>

                    <div class="mt-5 text-center">
                        <button class="btn btn-success profile-button" type="submit">Update</button>
                    </div>
                </form>
            </div>
        </div>


        <%--Buttons--%>
        <div class="col-md-4">


        </div>
    </div>
</div>
</body>
</html>
