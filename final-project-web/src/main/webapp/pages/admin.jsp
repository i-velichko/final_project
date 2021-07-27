<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 23.07.2021
  Time: 8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<%@include file="/fragment/header.jsp" %>
<a href="${abs_path}/controller?command=show_all_users">
    <button type="button"><fmt:message key="page.admin.show_users"/></button>
</a>
</body>
<body>
<a href="${abs_path}/controller?command=show_all_verifications">
    <button type="button"><fmt:message key="page.admin.show_verifications"/></button>
</a>
</body>
</html>
