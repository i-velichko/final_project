<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 29.06.2021
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<html>
<head>
    <title>Title</title>
    <style>
        .colorText {
            background-color: #ef0606;
            color: #ffffff;
        }
    </style>
</head>
<body>
<h1>Registration
</h1>
<br/>
<form method="post" action="${abs_path}/controller">
    First Name:<input type="text" name="firstName">
    <br/>
    Last Name:<input type="text" name="lastName">
    <br/>
    E-mail:<input type="email" pattern="^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" name="email">
    <br/>
    Login:<input type="text" pattern="^[\w@#$%^&+=]{3,25}$" name="login">
    <br/>
    Password:<input type="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{3,45}$" name="password">
    <br/>
    Confirm password:<input type="password" name="confirmPassword">
    <br/>
    <input type="hidden" name="command" value="registration">
    <input type="submit" class="colorText" value="registration">
</form>
</body>
</html>
