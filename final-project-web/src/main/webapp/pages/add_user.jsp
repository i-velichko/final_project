<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 19.08.2021
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
    <%@include file="/fragment/admin_header.jsp" %>
</head>
<body>
<section class="vh-100" >
    <form method="post" id="addUser" action="${abs_path}/controller?command=add_user_command">
        <div class="row">
            <div class="col-md-3 offset-md-4">
                <div class="login-form bg-light mt-4 p-4">

                    <!-- Login input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="form1Example1">Field</label>
                        <input type="text"
                               name="login"
                               value="${registrationValues.login}"
                               pattern="^[\w@#$%^&+=]{7,25}$"
                               id="form1Example1"
                               class="form-control" required/>
                        <strong>
                            <p class="text-danger">${errors.loginError}</p>
                        </strong>
                    </div>
                    <!-- Email input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="form1Example2">Field</label>
                        <input type="text"
                               name="email"
                               value="${registrationValues.email}"
                               id="form1Example2"
                               pattern="^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$"
                               class="form-control" required/>
                        <strong>
                            <p class="text-danger">${errors.emailError}</p>
                        </strong>
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="form1Example2">Field</label>
                        <input type="password"
                               name="password"
                               value="${registrationValues.password}"
                               pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{12,45}$"
                               id="form1Example6"
                               class="form-control" required/>
                        <strong>
                            <p class="text-danger">${errors.passwordError}</p>
                        </strong>
                    </div>

                    <!-- Nickname input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="form1Example2">Field</label>
                        <input type="text"
                               name="nickname"
                               pattern="^[\w@#$%^&+=]{2,30}$"
                               value="${registrationValues.nickname}"
                               id="form1Example3"
                               class="form-control" required/>
                        <strong>
                            <p class="text-danger">${errors.nicknameError}</p>
                        </strong>
                    </div>

                    <!-- Name input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="form1Example2">Field</label>
                        <input type="text"
                               name="name"
                               pattern="[a-zA-Zа-яА-Я]{3,35}"
                               value="${registrationValues.name}"
                               id="form1Example4"
                               class="form-control"/>
                        <strong>
                            <p class="text-danger">${errors.nameError}</p>
                        </strong>
                    </div>

                    <!-- Surename input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="form1Example2">Field</label>
                        <input type="text"
                               pattern="[a-zA-Zа-яА-Я]{3,35}"
                               name="surename"
                               value="${registrationValues.surename}"
                               id="form1Example8"
                               class="form-control"/>
                        <strong>
                            <p class="text-danger">${errors.surenameError}</p>
                        </strong>
                    </div>

                    <br>
                    <!-- Status input -->
                    <select class="form-select" name="instrumentStatus" id="fuck1" aria-label="Default select example">
                        <option selected>Role</option>
                        <option value="GUEST">GUEST</option>
                        <option value="CLIENT">CLIENT</option>
                        <option value="ADMIN">ADMIN</option>
                    </select>
                    <strong>
                        <p class="text-danger">${errors.userStatusError}</p>
                    </strong>
                    <br>
                    <br>

                    <!-- Submit button -->
                    <button type="submit" class="btn btn-warning">Field</button>
                </div>
            </div>
        </div>
    </form>
</section>
</body>
</html>
