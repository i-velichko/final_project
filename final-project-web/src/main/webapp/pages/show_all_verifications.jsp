<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 27.07.2021
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verification info</title>
    <%@include file="/fragment/header.jsp" %>
</head>
<body>
<form>
    <table class="table table-bordered">
        <tr>
            <th>ID</th>
            <th>Verification status</th>
            <th>Student</th>
            <th>Project title</th>
            <th>Trainer</th>
            <th>Examiner</th>
            <th>Application date</th>
            <th>Trainer Verification Date</th>
            <th>Trainer Score</th>
            <th>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search"
                     viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"></path>
                </svg>
                Characteristic
            </th>
            <th>Examiner Verification Date</th>
            <th>Final status</th>
            <th>Show verification info</th>
        </tr>
        <c:forEach items="${verifications}" var="verification">
            <tr>
                <td>${verification.id}</td>
                <td>${verification.verificationStatus}</td>
                <td>${verification.student.firstName} ${verification.student.lastName}</td>
                <td>${verification.title}</td>
                <td>${verification.trainer.firstName} ${verification.trainer.lastName}</td>
                <td>${verification.examiner.firstName} ${verification.examiner.lastName}</td>
                <td>${verification.applicationDate}</td>
                <td>${verification.trainerVerificationDate}</td>
                <td>${verification.trainerScore}</td>
                <td><p class="myTooltip">${verification.trainerCharacteristic}</p></td>
                <td>${verification.examinerVerificationDate}</td>
                <td>${verification.finalStatus}</td>
                <td><a href="${abs_path}/controller?command=show_verification_info&verificationId=${verification.id}">show info</a></td>
            </tr>
        </c:forEach>
    </table>
</form>

</body>
</html>
