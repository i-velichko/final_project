<html>
<head>
    <title>Road to Epam</title>

    <%@include file="/fragment/header.jsp"%>
</head>

<body>


<h6 style="color: forestgreen">${registrationIsDone}
<br/>

<br/>
<form method="post" action="${abs_path}/controller?command=login">
    <label for="login"><fmt:message key="page.login.login"/>
        <input type="text" pattern="^[\w@#$%^&+=]{3,25}$" name="login" id="login" required>
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
