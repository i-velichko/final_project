<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/fragment/header.jsp" %>


<section class="vh-100" style="background-color: #eee;">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-11">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4"><fmt:message
                                        key="page.registration.write_data"/></p>

                                <form class="mx-1 mx-md-4" method="post"
                                      action="${abs_path}/controller?command=registration">


                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" id="form3Example1c" name="firstName"
                                                   value="${correctRegistrationData.firstName}" class="form-control"/>
                                            <span style="color: red">${errorRegistrationData.firstNameError}</span>
                                            <label class="form-label" for="form3Example1c"><fmt:message
                                                    key="common.data.first.name"/></label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" id="form3Example2c" name="lastName"
                                                   value="${correctRegistrationData.lastName}" class="form-control"/>
                                            <span style="color: red">${errorRegistrationData.firstNameError}</span>
                                            <label class="form-label" for="form3Example1c"><fmt:message
                                                    key="common.data.last.name"/></label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="email" name="email" value="${correctRegistrationData.email}"
                                                   pattern="^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$"
                                                   id="form3Example3c" class="form-control"/>
                                            <label class="form-label" for="form3Example3c"><fmt:message
                                                    key="page.registration.email"/></label>
                                            <span style="color: red">${errorRegistrationData.emailError}</span>

                                        </div>
                                    </div>


                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" name="login" value="${correctRegistrationData.login}"
                                                   pattern="^[\w@#$%^&+=]{7,25}$" id="form3Example4c"
                                                   class="form-control"/>
                                            <label class="form-label" for="form3Example4c"><fmt:message
                                                    key="common.data.login"/></label>
                                            <span><h9 style="color: forestgreen"><fmt:message
                                                    key="page.registration.login.requirements"/></h9></span>
                                            <span style="color: red">${errorRegistrationData.loginError}</span>
                                        </div>
                                    </div>


                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" name="password"
                                                   pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{3,45}$"
                                                   id="form3Example5c" class="form-control"/>
                                            <label class="form-label" for="form3Example4c"><fmt:message
                                                    key="page.registration.password"/></label>
                                            <span><h9 style="color: forestgreen"><fmt:message
                                                    key="page.registration.password.requirements"/></h9></span>
                                        </div>
                                    </div>


                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" name="confirmPassword" id="form3Example4cd"
                                                   class="form-control"/>
                                            <label class="form-label" for="form3Example4cd"><fmt:message
                                                    key="page.registration.confirm_password"/></label>
                                        </div>
                                    </div>

                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <button type="submit" class="btn btn-primary btn-lg"><fmt:message
                                                key="page.registration.registration"/></button>
                                    </div>

                                </form>

                            </div>
                            <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                                <img src="https://mdbootstrap.com/img/Photos/new-templates/bootstrap-registration/draw1.png"
                                     class="img-fluid" alt="Sample image">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
