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
    <form method="post" id="addUser" action="${abs_path}/controller?command=add_new_user_command">
        <div class="row">
            <div class="col-md-3 offset-md-4">
                <div class="login-form bg-light mt-4 p-4">
                    <strong>
                        <p class="text-success">${addUserIsDone}</p>
                        <p class="text-warning">${registrationFailed}</p>
                    </strong>
                    <div class="form-outline mb-4">
                        <label class="form-label" for="form1Example1"><fmt:message key="common.data.first.name"/></label>
                        <input type="text"
                               name="firstName"
                               value="${correctRegistrationData.firstName}"
                               pattern="[a-zA-Zа-яА-ЯЁё]{3,30}"
                               id="form1Example1"
                               class="form-control" required/>
                        <strong>
                            <p class="text-danger">${errorRegistrationData.nameError}</p>
                        </strong>
                    </div>

                    <div class="form-outline mb-4">
                        <label class="form-label" for="form1Example2"><fmt:message key="common.data.last.name"/></label>
                        <input type="text"
                               name="lastName"
                               value="${correctRegistrationData.lastName}"
                               pattern="[a-zA-Zа-яА-ЯЁё]{3,30}"
                               id="form1Example2"
                               class="form-control" required/>
                        <strong>
                            <p class="text-danger">${errorRegistrationData.nameError}</p>
                        </strong>
                    </div>

                    <div class="form-outline mb-4">
                        <label class="form-label" for="form1Example2"><fmt:message key="common.data.login"/></label>
                        <input type="text"
                               name="login"
                               value="${correctRegistrationData.login}"
                               pattern="^[\w@#$%^&+=]{7,25}$"
                               id="form1Example6"
                               class="form-control" required/>
                        <strong>
                            <p class="text-danger">${errorRegistrationData.loginError}</p>
                        </strong>
                    </div>

                    <div class="form-outline mb-4">
                        <label class="form-label" for="form1Example2"><fmt:message key="page.registration.password"/></label>
                        <input type="password"
                               name="password"
                               pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{3,45}$"
                               value="${correctRegistrationData.password}"
                               id="form1Example3"
                               class="form-control" required/>
                        <strong>
                            <p class="text-danger">${errorRegistrationData.passwordError}</p>
                        </strong>
                    </div>

                    <div class="form-outline mb-4">
                        <label class="form-label" for="form1Example2"><fmt:message key="common.data.email"/></label>
                        <input type="text"
                               name="email"
                               pattern="^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$"
                               value="${correctRegistrationData.email}"
                               id="form1Example4"
                               class="form-control"/>
                        <strong>
                            <p class="text-danger">${errorRegistrationData.emailError}</p>
                        </strong>
                    </div>


                    <br>
                    <select class="form-select" name="role" id="fuck1" aria-label="Default select example">
                        <option selected><fmt:message key="common.data.role"/></option>
                        <option value="ADMIN">ADMIN</option>
                        <option value="TRAINER">TRAINER</option>
                        <option value="EXAMINER">EXAMINER</option>
                        <option value="CHIEF">CHIEF</option>
                        <option value="STUDENT">STUDENT</option>
                    </select>
                    <strong>
                        <p class="text-danger">${errors.userStatusError}</p>
                    </strong>
                    <br>
                    <br>

                    <button type="submit" class="btn btn-warning"><fmt:message key="page.admin.header.add.user"/></button>
                </div>
            </div>
        </div>
    </form>
</section>
</body>
</html>
