<%--@elvariable id="user" type="org.velichko.finalproject.logic.entity.User"--%>
<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 27.06.2021
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<html>
<head>
    <title>Student personal area</title>
</head>
<body>
<%@include file="/fragment/header.jsp" %>

<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row justify-content-center align-items-center h-100">
            <div class="col-12 col-lg-9 col-xl-7">
                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5"><fmt:message key="page.student.welcome"/>
                            </br>
                            </br>
                            ${user.firstName} ${user.lastName}
                            </br>
                            ${user.email}
                            </br>
                            </br>
                            <fmt:message key="page.student.to_start"/></h3>
                        </br>
                        <form name="" action="${abs_path}/controller?command=start_verification" method="post"
                              enctype="multipart/form-data">

                            <div class="row">
                                <div class="col-md-6 mb-4">

                                    <div class="form-outline">
                                        <input type="text" pattern="" id="git" class="form-control form-control-lg" required/>
                                        <label class="form-label" for="git"><fmt:message
                                                key="page.student.git"/></label>
                                    </div>

                                </div>
                                <div class="col-md-6 mb-4">

                                    <div class="form-outline">
                                        <input type="text" id="projectName" class="form-control form-control-lg" required/>
                                        <label class="form-label" for="projectName"><fmt:message
                                                key="page.student.project_name"/></label>
                                    </div>

                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-4 pb-2">
                                    <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
                                        <option selected>Choose your trainer here</option>
                                        <option value="1">One</option>
                                        <option value="2">Two</option>
                                        <option value="3">Three</option>
                                    </select>
                                    <label class="form-label select-label"> Your trainer</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-9 pe-5">
                                    <input class="form-control form-control-lg" id="imageId" name="image" type="file"/>
                                    <div class="small text-muted mt-2">Upload your photo.
                                        Max file size 1 MB
                                    </div>
                                </div>
                            </div>
                            <div class="mt-4 pt-2">
                                <input class="btn btn-primary btn-lg" type="submit"
                                       value="<fmt:message key="page.student.go_to_verification"/>"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<style>
    .gradient-custom {
        /* fallback for old browsers */
        background: #6a89f1;

        /* Chrome 10-25, Safari 5.1-6 */
        background: -webkit-linear-gradient(to bottom right, rgb(147, 232, 251), rgb(52, 80, 224));

        /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
        background: linear-gradient(to bottom right, rgb(147, 192, 251), rgb(34, 30, 154))
    }

    .card-registration .select-input.form-control[readonly]:not([disabled]) {
        font-size: 1rem;
        line-height: 2.15;
        padding-left: .75em;
        padding-right: .75em;
    }

    .card-registration .select-arrow {
        top: 13px;
    }
</style>

</body>
</html>
