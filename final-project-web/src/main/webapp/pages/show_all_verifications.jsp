<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 27.07.2021
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <title>Verification info</title>
    <ctg:is-trainer>
        <%@include file="/fragment/trainer_header.jsp" %>
    </ctg:is-trainer>

    <ctg:is-examiner>
        <%@include file="/fragment/examiner_header.jsp" %>
    </ctg:is-examiner>

    <ctg:is-chief>
        <%@include file="/fragment/chief_header.jsp" %>
    </ctg:is-chief>
</head>
<body>
<form>
    <table class="table table-striped table-bordered table-sm">
        <tr>

            <th><fmt:message key="page.show.all.verifications.id"/></th>
            <th><fmt:message key="page.show.all.verifications.status"/></th>
            <th><fmt:message key="page.show.all.verifications.student"/></th>
            <th><fmt:message key="page.show.all.verifications.title"/></th>
            <th><fmt:message key="page.show.all.verifications.trainer"/></th>
            <th><fmt:message key="page.show.all.verifications.examiner"/></th>
            <th><fmt:message key="page.show.all.verifications.application.date"/></th>
            <th><fmt:message key="page.show.all.verifications.trainer.check.date"/></th>
            <th><fmt:message key="page.show.all.verifications.trainer.score"/></th>
            <th>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search"
                     viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"></path>
                </svg>
                <fmt:message key="page.show.all.verifications.trainer.characterisitic"/>
            </th>
            <th><fmt:message key="page.show.all.verifications.examiner.check.date"/></th>
            <th><fmt:message key="page.show.all.verifications.final.status"/></th>
            <th><fmt:message key="page.show.all.verifications.show.info"/></th>
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
                <td><a href="${abs_path}/controller?command=show_verification_info&verificationId=${verification.id}">show
                    info</a></td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Page navigation area">
        <ul class="pagination justify-content-center">
            <li class="page-item ${pageable.isFirstPage() ? 'disabled': ''}">
                <a class="page-link"
                   href="${abs_path}/controller?command=show_all_verifications&page=${pageable.currentPage - 1}"
                   tabindex="-1">Previous</a>
            </li>
            <c:forEach var="i" begin="1" end="${pageable.pageCount()}">
                <li class="page-item ${pageable.currentPage eq i ? 'active': ''}">
                    <a class="page-link" href="${abs_path}/controller?command=show_all_verifications&page=${i}">${i}</a>
                </li>
            </c:forEach>
            <li class="page-item ${pageable.isLastPage() ? 'disabled': ''}">
                <a class="page-link"
                   href="${abs_path}/controller?command=show_all_verifications&page=${pageable.currentPage + 1}">Next</a>
            </li>
        </ul>
    </nav>
</form>

</body>
</html>
