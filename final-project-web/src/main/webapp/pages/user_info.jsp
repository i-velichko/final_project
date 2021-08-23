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
                            <h5>${userInfo.firstName} ${userInfo.lastName}</h5>
                            <i class="far fa-edit mb-5"></i>
                        </div>
                        <div class="col-md-8">
                            <div class="card-body p-4">
                                <h6><fmt:message
                                        key="page.user.info.title"/></h6>
                                <hr class="mt-0 mb-4">
                                <div class="row pt-1">
                                    <div class="col-6 mb-3">
                                        <h6><fmt:message
                                                key="common.data.email"/></h6>
                                        <p class="text-muted">${userInfo.email}</p>
                                    </div>
                                </div>
                                <div class="row pt-1">
                                    <div class="col-6 mb-3">
                                        <h6><fmt:message
                                                key="common.data.role"/></h6>
                                        <p class="text-muted">${userInfo.role}</p>
                                    </div>
                                    <div class="col-6 mb-3">
                                        <h6><fmt:message
                                                key="page.user.info.company"/></h6>
                                        <p class="text-muted"><fmt:message
                                                key="page.user.info.epam"/></p>
                                    </div>
                                </div>
                                <form action="${abs_path}/controller?command=change_user_image&login=${userInfo.login}" method="post"
                                      enctype="multipart/form-data">
                                    <div class="mb-3">
                                        <label for="imageId" class="form-label"><fmt:message
                                                key="page.user.info.change.photo"/></label>
                                        <input class="form-control form-control-sm" name="image" id="imageId"
                                               type="file">
                                        <br/>
                                        <div class="col">
                                            <button type="submit"
                                                    class="btn btn-outline-warning btn-rounded"
                                                    data-mdb-ripple-color="dark">
                                                <fmt:message
                                                        key="page.user.info.upload"/>
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
        background: #6571f6;

        /* Chrome 10-25, Safari 5.1-6 */
        background: -webkit-linear-gradient(to right bottom, rgb(101, 164, 246), rgb(125, 173, 245));

        /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
        background: linear-gradient(to right bottom, rgb(101, 147, 246), rgb(133, 157, 253))
    }
</style>
</body>
</html>
