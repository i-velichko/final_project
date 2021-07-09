<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<html>
<head>
    <title>Road to Epam</title>

  <%@include file="/include/header.jsp"%>
</head>

<body>


<h1><%= "Welcome to Epam. You can login or register here " %></h1>
<br/>

<form>
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label"></label>
    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1">
  </div>
  <div class="mb-3 form-check">
    <input type="checkbox" class="form-check-input" id="exampleCheck1">
    <label class="form-check-label" for="exampleCheck1">Check me out</label>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

<br/>

<form method="post" action="${abs_path}/controller">
  <label for="login"><fmt:message key="page.login.login"/>
    <input type="hidden" name="command" value="login">
    <input type="text" name="login" id="login" required>
  </label><br/>
  <label for="password"><fmt:message key="page.login.password"/>
    <span><input type="password" name="password" id="password" required></span>
    <span style="color: #ff0000">${userNotFound}</span>
  </label><br/>
  <button type="submit">Login</button>
  <a href="${abs_path}/pages/registration.jsp">
  <button type="button">Register</button>
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
