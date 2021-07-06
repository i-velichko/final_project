<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.07.2021
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<div>
    <span>Content. Русский</span>
    <p>Size: ${requestScope.users.size()}</p>
    <p>Login: ${requestScope.users.get(1).login}</p>
    <p>Map: ${sessionScope.usersMap[1]}</p>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
