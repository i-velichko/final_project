<html>
<head>
    <title>Road to Epam</title>
    <%@include file="/fragment/header.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://www.markuptag.com/bootstrap/5/css/bootstrap.min.css">

</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="login-form bg-light mt-4 p-4">
                <form action="${abs_path}/controller?command=login" method="post" class="row g-3">
                    <h4><fmt:message key="page.head.login"/></h4>
                    <div class="col-12">
                        <label><fmt:message key="page.login.login"/></label>
                        <input type="text" name="login" class="form-control"
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
                                <p class="text-danger">${loginError}</p>
                            </strong>
                        </span>
                    </div>
                    <div class="col-12">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="rememberMe">
                            <label class="form-check-label" for="rememberMe"><fmt:message
                                    key="page.head.remember_me"/></label>
                        </div>
                    </div>
                    <div class="col-12">
                        <input type="hidden" name="command" value="login">
                        <button type="submit"><fmt:message key="page.login.sign_in.button"/></button>
                    </div>
                </form>
                <hr class="mt-4">
                <div class="col-12">
                    <p class="text-center mb-0">
                        <fmt:message key="page.head.registration_text"/>
                        <a href="${abs_path}/pages/registration.jsp">
                            <fmt:message key="page.login.register"/>
                        </a>
                    </p>

                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://www.markuptag.com/bootstrap/5/js/bootstrap.bundle.min.js"></script>


<h6 style="color: forestgreen">${registrationIsDone}</h6>
<h5 style="color: red">${registrationFailed}</h5>
<br/>

<br/>
<form method="post" action="${abs_path}/controller?command=login">
    <label for="login"><fmt:message key="page.login.login"/>
        <input type="text" pattern="^[\w@#$%^&+=]{7,25}$" name="login" id="login" required>
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
