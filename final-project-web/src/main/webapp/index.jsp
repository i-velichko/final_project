<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
    <title>Road to Epam</title>
    <style>
        .colorText {
            background-color: #ef0606;
            color: #ffffff;
        }
    </style>
</head>
<body>
<h1><%= "Welcome to Epam. You can login or register here " %></h1>
<br/>
<form method="post" action="${abs_path}/controller">
    <input type="hidden" name="command" value="login">
    Login:<input type="text" name="login">
    <br>
    <div><span>Password:<input type="password" name="password"></span>
        <span style="color: red">${userNotFound}</span>
    </div>
    <br>
    <input type="submit" value="Login">
</form>
<br/>
<form method="post" action="${abs_path}/pages/registration.jsp">
    <input type="submit" value="Registration">
</form>
<br/>
<form method="post" action="${abs_path}/controller" >
    <input type="hidden" name="command" value="show_all_users">
    <input type="submit" class="colorText" value="show all users">
</form>
</body>
</html>

<%--<a href="${abs_path}/pages/registration.jsp">Registration</a>--%>
<%--&lt;%&ndash;<c:redirect url="/pages/registration.jsp"/>&ndash;%&gt; автоматический переход на другую страинцу--%>