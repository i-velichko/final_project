<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 27.06.2021
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student info</title>
</head>
<body>
<%@include file="fragment/header.jsp"%>
Information about Student:
</br>
${user.firstName}
</br>
${user.lastName}
</br>
${user.email}
</br>
${user.gitLink}
</body>
</html>
