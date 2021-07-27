<html>
<head>
    <title>Road to Epam</title>
    <%@include file="/fragment/header.jsp" %>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="login-form bg-light mt-4 p-4">
                <form action="${abs_path}/controller?command=login" method="post" class="row g-3">
                    <h4><fmt:message key="page.login.welcome"/></h4>
                    <div class="col-12">
                        <label><fmt:message key="page.login.login"/></label>
                        <input type="text" pattern="[\w@#$%^&+=]{7,25}$" name="login" class="form-control"
                               placeholder="<fmt:message key="page.login.login"/>" required>
                        <input type="hidden" name="command" value="login">
                    </div>
                    <div class="col-12">
                        <label><fmt:message key="page.login.password"/></label>
                        <input type="password" name="password" class="form-control"
                               placeholder="<fmt:message key="page.login.password"/>" required>
                    </div>
                    <div>
                        <span>
                            <strong>
                                <p class="text-danger">${userNotFound}</p>
                                <p class="text-success">${registrationIsDone}</p>
                                <p class="text-danger">${registrationFailed}</p>
                            </strong>
                        </span>
                    </div>
                    <div class="col-12">
                        <button type="submit"><fmt:message key="page.login.sign_in"/></button>
                        <a href="${abs_path}/pages/registration.jsp">
                            <button type="button"><fmt:message key="page.login.register"/></button>
                        </a>
                    </div>
                </form>
                <hr class="mt-4">
            </div>
        </div>
    </div>
</div>
</body>
</html>
