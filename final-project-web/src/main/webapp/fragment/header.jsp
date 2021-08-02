<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<fmt:message key="locale.lang" var="curr_lang"/>






<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://www.markuptag.com/bootstrap/5/css/bootstrap.min.css">
    <!-- JavaScript Bundle with Popper -->
</head>
<body>

<%@include file="../fragment/common_imports.jspf" %>

<header>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
        <div class="container-fluid">
            <button
                    class="navbar-toggler"
                    type="button"
                    data-mdb-toggle="collapse"
                    data-mdb-target="#navbarExample01"
                    aria-controls="navbarExample01"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
            >
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarExample01">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="#"><form action="${abs_path}/controller" method="post">
                            <input type="hidden" name="command" value="change_locale">
                            <input type="hidden" name="refererCommand" value="${refererCommand}">
                            <input type="submit" class="btn btn-light" value="${curr_lang}">
                        </form></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><div id="logout">
                            <c:if test="${not empty sessionScope.user}">
                                <form action="${abs_path}/controller" method="post">
                                    <input type="hidden" name="command" value="logout">
                                    <input type="submit" class="btn btn-light" value=<fmt:message key="page.header.logout"/>>
                                </form>
                            </c:if>
                        </div></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Features</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Pricing</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">About</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Navbar -->

    <!-- Background image -->
    <div
            class="p-5 text-center bg-image"
            style="
      background-image: url('https://mdbcdn.b-cdn.net/img/new/slides/041.jpg');
      height: 400px;
      margin-top: 58px;
    "
    >
        <div class="mask" style="background-color: rgba(0, 0, 0, 0.6);">
            <div class="d-flex justify-content-center align-items-center h-100">
                <div class="text-white">
                    <h1 class="mb-3">Heading</h1>
                    <h4 class="mb-3">Subheading</h4>
                    <a class="btn btn-outline-light btn-lg" href="#!" role="button"
                    >Call to action</a
                    >
                </div>
            </div>
        </div>
    </div>
    <!-- Background image -->
</header>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>



