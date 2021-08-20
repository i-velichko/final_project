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
                    <h4 class="text-right"><fmt:message key="page.edit.user.data.title"/></h4>
                </div>
                <form action="${abs_path}/controller?command=edit_user_data" method="post">
                    <div class="row mt-2">
                        <div class="col-md-6">
                            <label class="labels"><fmt:message key="common.data.first.name"/>:</label>
                            <label class="form-control">
                                <input type="text" pattern="^[\w@#$%^&+=]{2,30}$" name="firstName"
                                       value="${sessionScope.user.firstName}" required></label>
                            <strong>
                                <p class="text-danger">${errorEditData.nameError}</p>
                            </strong>
                        </div>
                        <div class="col-md-6">
                            <label class="labels"><fmt:message key="common.data.last.name"/>:</label>
                            <label class="form-control">
                                <input type="text" pattern="^[\w@#$%^&+=]{2,30}$" name="lastName"
                                       value="${sessionScope.user.lastName}" required></label>
                            <strong>
                                <p class="text-danger">${errorEditData.nameError}</p>
                            </strong>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-6">
                            <label class="labels"><fmt:message key="common.data.login"/>:</label>
                            <label class="form-control">${sessionScope.user.login}</label>
                        </div>
                        <div class="col-md-6">
                            <label class="labels"><fmt:message key="common.data.status"/>:</label>
                            <label class="form-control">${sessionScope.user.status}</label>
                        </div>
                    </div>
                    <div class="row mt-3">

                        <ctg:is-student>

                            <div class="col-md-6">
                                <label class="labels"><fmt:message key="common.data.git"/>:</label>
                                <label class="form-control">
                                    <c:if test="${empty correctEditData}">
                                    <input type="text" name="gitLink"
                                           pattern="(https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]+\.[^\s]{2,}|www\.[a-zA-Z0-9]+\.[^\s]{2,})"
                                           value="${sessionScope.user.gitLink}" required></label>
                                </c:if>
                                <c:if test="${not empty correctEditData}">
                                    <input type="text" name="gitLink"
                                           pattern="(https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]+\.[^\s]{2,}|www\.[a-zA-Z0-9]+\.[^\s]{2,})"
                                           value="${correctEditData.gitLink}" required></label>
                                </c:if>
                                <strong>
                                    <p class="text-danger">${errorEditData.gitLinkError}</p>
                                </strong>
                            </div>
                        </ctg:is-student>

                        <div class="col-md-6">
                            <label class="labels"><fmt:message key="common.data.email"/>:</label>
                            <label class="form-control">
                            <c:if test="${empty correctEditData}">
                                    <input type="text" name="email"
                                           pattern="^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$"
                                           value="${sessionScope.user.email}" required></label>
                            </c:if>
                            <c:if test="${not empty correctEditData}">
                                    <input type="text" name="email"
                                           pattern="^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$"
                                           value="${correctEditData.email}" required></label>
                            </c:if>
                            <strong>
                                <p class="text-danger">${errorEditData.emailError}</p>
                            </strong>

                        </div>
                    </div>

                    <div class="mt-5 text-center">
                        <button class="btn btn-success profile-button" type="submit"><fmt:message
                                key="page.edit.user.data.update"/></button>
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
