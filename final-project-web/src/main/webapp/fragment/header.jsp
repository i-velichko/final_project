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

<div
        class="p-5 text-center bg-image"
        style="
      background-image: url('https://kartinkin.com/uploads/posts/2021-01/1610813097_9-p-aiti-fon-14.jpg');
      height: 400px;
    "
>
    <div class="mask" style="background-color: rgba(0, 0, 0, 0.6);">
        <div class="d-flex justify-content-center align-items-center h-100">
            <div class="text-white">
                <h1 class="mb-3">Road to Epam</h1>
            </div>
        </div>
    </div>
</div>

<div id="logout">
    <c:if test="${not empty sessionScope.user}">
        <form action="${abs_path}/controller" method="post">
            <input type="hidden" name="command" value="logout">
            <input type="submit" class="colorText" value=<fmt:message key="page.header.logout"/>>
        </form>
    </c:if>
</div>


<form action="${abs_path}/controller" method="post">
    <input type="hidden" name="command" value="change_locale">
    <input type="hidden" name="refererCommand" value="${refererCommand}">
    <input type="submit" class="btn btn-primary" value="${curr_lang}">
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>


