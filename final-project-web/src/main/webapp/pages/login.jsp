<html>
<head>
    <title>Road to Epam</title>

    <%@include file="fragment/header.jsp"%>
</head>

<body>


<h1><fmt:message key="page.login.welcome"/></h1>
<br/>


<form method="post" action="${abs_path}/controller?command=login">
    <label for="login"><fmt:message key="page.login.login"/>
        <input type="text" name="login" id="login" required>
    </label><br/>
    <label for="password"><fmt:message key="page.login.password"/>
        <span><input type="password" name="password" id="password" required></span>
        <span style="color: #ff0000">${userNotFound}</span>
    </label><br/>
    <button type="submit"><fmt:message key="page.login.sign_in"/></button>
    <a href="${abs_path}/pages/registration.jsp">
        <button type="button"><fmt:message key="page.login.register"/></button>
    </a>
</form>
</body>
</html>

<%--<form method="post" action="${abs_path}/controller">--%>
<%--  <label><fmt:message key="page.login.login"/>:--%>
<%--    <input type="hidden" name="command" value="login">--%>
<%--    <input type="text" name="login" id="login" required>--%>
<%--  </label><br>--%>

<%--  <label><fmt:message key="page.login.password"/> :--%>
<%--    <span><input type="password" name="password" id="password" required></span>--%>
<%--    <span style="color: #ff0000">${userNotFound}</span>--%>
<%--  </label>--%>
<%--  <br>--%>
<%--  <input type="submit" value="Login">--%>
<%--</form>--%>
<%--<br/>--%>
<%--<form method="post" action="${abs_path}/pages/registration.jsp">--%>
<%--  <input type="submit" value="Registration">--%>
<%--</form>--%>
<%--<br/>--%>
<%--</body>--%>
<%--</html>--%>
