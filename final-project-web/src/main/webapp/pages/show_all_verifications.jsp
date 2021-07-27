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
    <%@include file="/fragment/header.jsp"%>
</head>
<body>
<form>
    <table border="2" class="table table-striped" style="width:100%">
        <tr>
            <th>ID</th>
            <th>Verification status</th>
            <th>Student Name</th>
            <th>Student Surname</th>
            <th>Trainer Name</th>
            <th>Trainer Surname</th>
            <th>Examiner Name</th>
            <th>Examiner Surname</th>
            <th>Application date</th>
            <th>Trainer Verification Date</th>
            <th>Trainer Score</th>
            <th>Characteristic</th>
            <th>Examiner Verification Date</th>
            <th>Final status</th>
        </tr>
        <c:forEach items="${verifications}" var="verification">
            <tr>
                <td>${verification.id}</td>
                <td>${verification.verificationStatus}</td>
                <td>${verification.student.firstName}</td>
                <td>${verification.student.lastName}</td>
                <td>${verification.trainer.firstName}</td>
                <td>${verification.trainer.lastName}</td>
                <td>${verification.examiner.firstName}</td>
                <td>${verification.examiner.lastName}</td>
                <td>${verification.applicationDate}</td>
                <td>${verification.trainerVerificationDate}</td>
                <td>${verification.trainerScore}</td>
                <td>${verification.trainerCharacteristic}</td>
                <td>${verification.examinerVerificationDate}</td>
                <td>${verification.finalStatus}</td>
            </tr>
        </c:forEach>
    </table>
</form>

</body>
</html>
