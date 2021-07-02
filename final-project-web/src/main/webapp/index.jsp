<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Please Login here:
</h1>
<br/>
<form method="post" action="login">
    Login:<input type="text" name="login">
    <br>
    Password:<input type="password" name="pass">
    <br>
    <input type="submit" value="Login">
</form>
<br/>
<form method="post" action="${abs_path}/pages/registration.jsp">
    <input type="submit" value="Registration">
</form>
<%--<a href="${abs_path}/pages/registration.jsp">Registration</a>--%>
<%--&lt;%&ndash;<c:redirect url="/pages/registration.jsp"/>&ndash;%&gt; автоматический переход на другую страинцу--%>
<br/>
<form method="post" action="studentsList">
    <input type="submit" value="All students list">
</form>
</body>
</html>