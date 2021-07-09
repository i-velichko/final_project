<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 01.07.2021
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<html>
<head>
    <title>Trainer Personal Area</title>
</head>
<body bgcolor="silver">
<%@include file="../include/header.jsp"%>
Hi, trainer!

<br/>
</br>
${user.firstName}
</br>
${user.lastName}
</body>
<br/>
${abs_path}
<br/>
<br/>
<form method="post" action="${abs_path}/controller" >
    <input type="hidden" name="command" value="show_all_users">
    <input type="submit" class="colorText" value="show all users">
</form>
<p><a href="trainerInfo.jsp">Your personal information</a></p>
</html>
