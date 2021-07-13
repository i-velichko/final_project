<%--@elvariable id="user" type="org.velichko.finalproject.logic.entity.User"--%>
<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 27.06.2021
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<html>
<head>
    <title>Student personal area</title>
</head>
<body bgcolor="silver">
<%@include file="fragment/header.jsp"%>
<fmt:message key="page.student.welcome"/>
</br>
${user.firstName}
</br>
${user.lastName}
</br>
${user.email}
</br>

<h3><fmt:message key="page.student.to_start"/></h3>
</br>

<form name="" action="student" method="post">
    <input type="hidden" name="login" value= "${user.login}">
    <fmt:message key="page.student.git"/> <input type="text" name="git">
    <fmt:message key="page.student.skills"/><input type="text" name="skills">
    <fmt:message key="page.student.project_name"/> <input type="text" name="projectName">
    <input type="submit" value=<fmt:message key="page.student.go_to_verification"/>>
</form>

</body>
</html>
