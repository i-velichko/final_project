<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 27.06.2021
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="silver">
Hi Student:
</br>
${user.firstName}
</br>
${user.lastName}
</br>
${user.email}
</br>

<h3>To start verification fill in all fields and click "GO TO HELL"!</h3>
</br>

<form name="" action="student" method="post">
    <input type="hidden" name="login" value= "${user.login}">
    GIT: <input type="text" name="git">
    Skills: <input type="text" name="skills">
    Project Name: <input type="text" name="projectName">
    <input type="submit" value="GO TO HELL!">
</form>

</body>
</html>
