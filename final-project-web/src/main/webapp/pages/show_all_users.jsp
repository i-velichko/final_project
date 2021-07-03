
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<html>
<head>
    <title>All users list</title>
</head>
<body bgcolor="silver">

                <table border="2">
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Login</th>
                        <th>Git Link</th>
                        <th>Show user info</th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                        <td>${user.getFirstName()}</td>
                        <td>${user.getLastName()}</td>
                        <td>${user.getLogin()}</td>
                        <td>${user.getGitLink()}</td>
                        <td><a href="${abs_path}/userById?userId=${user.getId()}">Show info</a></td>
                        </tr>
                    </c:forEach>
                </table>
</body>
</html>
