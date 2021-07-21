<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<html>
<head>
    <title>All users list</title>
</head>
<body bgcolor="silver">
<%@include file="/fragment/header.jsp"%>

<form>
    <table border="2">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Login</th>
            <th>Git Link</th>
            <th>Show user info</th>
        </tr>
        <%--@elvariable id="users" type="java.util.List"--%>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.login}</td>
                <td>${user.gitLink}</td>
<%--                <td><a href="${abs_path}/userById?userId=${user.id}">Show info</a></td>--%>
                <td><a href="${abs_path}/controller?command=show_student_info&userId=${user.id}">show info</a></td>
<%--                <td><a href="${abs_path}/controller?command=show_student_info&userId=${user.id}">${user.id}</a></td>--%>
            </tr>
        </c:forEach>
    </table>
</form>
<br/>
</body>
</html>
