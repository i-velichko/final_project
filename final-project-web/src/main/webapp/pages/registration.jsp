<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 29.06.2021
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Registration
</h1>
<br/>
<form method="post" action="registration">
    First Name:<input type="text" name="firstName">
    <br/>
    Last Name:<input type="text" name="lastName">
    <br/>
    E-mail:<input type="email" name="email">
    <br/>
    Login:<input type="text" name="login">
    <br/>
    Password:<input type="password" name="password">
    <br/>
    Confirm password:<input type="password" name="confirmPassword">
    <br/>
    <input type="submit" value="Submit">
</form>
</body>
</html>
