<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/fragment/header.jsp" %>

<br/>
<br/>

<h1><fmt:message key="page.registration.write_data"/></h1>
<br/>
<form method="post" action="${abs_path}/controller?command=registration">


    <div>
        <span><fmt:message key="page.registration.first_name"/><input type="text" name="firstName"
                                                                      value="${correctRegistrationData.firstName}"></span>
        <span style="color: red">${errorRegistrationData.get(firstNameError)}</span>

    </div>

    <div>
        <span><fmt:message key="page.registration.last_name"/><input type="text" name="lastName"
                                                                     value="${correctRegistrationData.lastName}"></span>
    </div>
    <div>
        <span>
            <fmt:message key="page.registration.email"/><input type="email"
                                                               pattern="^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$"
                                                               name="email"
                                                               value="${correctRegistrationData.email}">
             <span style="color: red">${errorRegistrationData.emailError}</span>
        </span>
    </div>
    <div>
         <span>
             <fmt:message key="page.registration.login"/><input type="text" pattern="^[\w@#$%^&+=]{7,25}$" name="login"
                                                                value="${correctRegistrationData.login}">
         </span>
        <span style="color: red">${errorRegistrationData.loginError}</span>
        <h7 style="color: forestgreen"><fmt:message key="page.registration.login.requirements"/></h7>
    </div>
    <div>
        <span>
            <fmt:message key="page.registration.password"/><input type="password"
                                                                  pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{3,45}$"
                                                                  name="password">
        </span>
        <h7 style="color: forestgreen"><fmt:message key="page.registration.password.requirements"/></h7>
    </div>
    <span><fmt:message key="page.registration.confirm_password"/><input type="password" name="confirmPassword"></span>
    <div>
    </div>
    <input type="submit" class="colorText" value=<fmt:message key="page.registration.registration"/>>
</form>
</body>
</html>
