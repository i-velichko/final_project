<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<html>
<head>
    <title>Trainer Personal Area</title>
</head>
<body bgcolor="silver">
<%@include file="/fragment/header.jsp"%>
<fmt:message key="page.trainer.welcome"/>

</br>
${user.firstName}
</br>
${user.lastName}
</body>

<br/>
<a href="${abs_path}/controller?command=show_all_users">
    <button type="button"><fmt:message key="page.admin.show_users"/></button>
</a>
</br>
<a href="${abs_path}/controller?command=show_all_verifications">
    <button type="button"><fmt:message key="page.admin.show_verifications"/></button>
</a>
</br>
<p><a href="trainer_info.jsp"><fmt:message key="page.trainer.personal_information"/></a></p>
</html>
