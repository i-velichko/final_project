<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 27.06.2021
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <title>Student info</title>
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
<section class="vh-100" style="background-color: #f4f5f7;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-start h-100">
            <div class="col col-lg-6 mb-4 mb-lg-0">
                <div class="card mb-3" style="border-radius: .5rem;">
                    <div class="row g-0">
                        <div class="col-md-4 gradient-custom text-center text-white"
                             style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
                            <img
                                    <c:if test="${empty stringImage}">
                                        src="https://cdn.shopify.com/s/files/1/0089/7263/2160/products/YOUROWNPHOTO-min_1.jpg?v=1591290327"
                                    </c:if>
                                    src="${stringImage}"
                                    alt="..."
                                    class="img-fluid my-5"
                                    style="width: 170px;"
                            />
                            <h5>${user.firstName} ${user.lastName}</h5>
                            <i class="far fa-edit mb-5"></i>
                        </div>
                        <div class="col-md-8">
                            <div class="card-body p-4">
                                <h6>User information</h6>
                                <hr class="mt-0 mb-4">
                                <div class="row pt-1">
                                    <div class="col-6 mb-3">
                                        <h6>Email</h6>
                                        <p class="text-muted">${user.email}</p>
                                    </div>
                                </div>
                                <h6>Projects</h6>
                                <hr class="mt-0 mb-4">
                                <div class="row pt-1">
                                    <div class="col-6 mb-3">
                                        <h6>Role</h6>
                                        <p class="text-muted">${user.role}</p>
                                    </div>
                                    <div class="col-6 mb-3">
                                        <h6>Company</h6>
                                        <p class="text-muted">Epam Training Center</p>
                                    </div>
                                </div>
                                <form action="${abs_path}/controller?command=change_user_image&login=${user.login}" method="post"
                                      enctype="multipart/form-data">
                                    <div class="mb-3">
                                        <label for="imageId" class="form-label">change your photo here</label>
                                        <input class="form-control form-control-sm" name="image" id="imageId"
                                               type="file">
                                        <br/>
                                        <div class="col">
                                            <button type="submit"
                                                    class="btn btn-outline-warning btn-rounded"
                                                    data-mdb-ripple-color="dark">
                                                Upload
                                            </button>
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>

<style>
    .gradient-custom {
        /* fallback for old browsers */
        background: #f6d365;

        /* Chrome 10-25, Safari 5.1-6 */
        background: -webkit-linear-gradient(to right bottom, rgba(246, 211, 101, 1), rgba(253, 160, 133, 1));

        /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
        background: linear-gradient(to right bottom, rgba(246, 211, 101, 1), rgba(253, 160, 133, 1))
    }
</style>
</body>
</html>