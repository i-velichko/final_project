<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="fragment/header.jsp"%>

<br/>
<br/>

<h1><fmt:message key="page.registration.write_data"/>
</h1>
<br/>
<form method="post" action="${abs_path}/controller">
    <fmt:message key="page.registration.first_name"/><input type="text" name="firstName">
    <br/>
    <fmt:message key="page.registration.last_name"/><input type="text" name="lastName">
    <br/>
    <fmt:message key="page.registration.email"/><input type="email" pattern="^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" name="email">
    <br/>
    <fmt:message key="page.registration.login"/><input type="text" pattern="^[\w@#$%^&+=]{3,25}$" name="login">
    <br/>
    <fmt:message key="page.registration.password"/><input type="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{3,45}$" name="password">
    <br/>
    <fmt:message key="page.registration.confirm_password"/><input type="password" name="confirmPassword">
    <br/>
    <input type="hidden" name="command" value="registration">
    <input type="submit" class="colorText" value=<fmt:message key="page.registration.registration"/>>
</form>
</body>
</html>
