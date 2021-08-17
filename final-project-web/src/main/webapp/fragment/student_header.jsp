<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 12.08.2021
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 03.08.2021
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<fmt:message key="locale.lang" var="curr_lang"/>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
    </script>
</head>
<body>
<%@include file="../fragment/common_imports.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
</script>
<link rel="stylesheet" href="https://www.markuptag.com/bootstrap/5/css/bootstrap.min.css">


<header class="p-3 mb-3 border-bottom" style="background-color: #bcc3c9">

    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li>
                    <form action="${abs_path}/controller?command=change_locale" method="post">
                        <input type="hidden" name="command" value="change_locale">
                        <input type="hidden" name="refererCommand" value="${refererCommand}">
                        <input type="submit" class="btn btn-outline-light me-2" value="${curr_lang}">
                    </form>
                </li>
                <li><a href="${abs_path}/controller?command=to_main_page" class="nav-link px-2 link-secondary">Home</a>
                </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown" href="#" id="navbarDarkDropdownMenuLink" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            My Project Verification
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                            <li><a class="dropdown-item" href="${abs_path}/controller?command=redirect_student">View</a>
                            </li>
                        </ul>
                    </li>

                <li><a href="https://training.by/#!/Home?lang=ru" class="nav-link px-2 link-dark">The road to Epam
                    starts here</a></li>
            </ul>

            <c:if test="${not empty sessionScope.user}">
                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                        <use xlink:href="#bootstrap"></use>
                    </svg>
                </a>
                <div class="dropdown text-end">
                    <a href="#"
                       class="d-block link-dark text-decoration-none dropdown-toggle"
                       id="dropdownUser1"
                       data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <img src="https://www.pngitem.com/pimgs/m/22-220721_circled-user-male-type-user-colorful-icon-png.png"
                             alt="mdo"
                             width="52"
                             height="52"
                             class="rounded-circle">
                    </a>
                        ${user.firstName} ${user.lastName}
                    <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                        <li>
                            <form action="${abs_path}/controller?command=show_user_info&userId=${user.id}" method="post">
                                <input type="hidden" name="command" value="logout">
                                <input type="submit" class="dropdown-item" value="My profile">
                            </form>
                        </li>
                        <hr class="dropdown-divider">
                        <li>
                            <form action="${abs_path}/controller?command=to_edit_user_data_page" method="post">
                                <input type="hidden" name="command" value="logout">
                                <input type="submit" class="dropdown-item" value="Change my data">
                            </form>
                        </li>
                        <hr class="dropdown-divider">
                        <li>
                            <form action="${abs_path}/controller" method="post">
                                <input type="hidden" name="command" value="logout">
                                <input type="submit" class="dropdown-item" value=<fmt:message
                                        key="page.header.logout"/>>
                            </form>
                        </li>
                    </ul>
                </div>
            </c:if>

            <c:if test="${empty sessionScope.user}">
                <div class="text-end">
                    <form action="${abs_path}/controller?command=to_login_page" method="post">
                        <button type="submit" class="btn btn-outline-light me-2">
                            <fmt:message key="page.login.login"/>
                        </button>
                    </form>
                </div>
                <div class="text-xxl-end">
                    <form action="${abs_path}/controller?command=to_registration_page" method="post">
                        <button type="submit" class="btn btn-outline-light me-2">
                            <fmt:message key="page.login.register"/>
                        </button>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</header>
</body>
</html>

