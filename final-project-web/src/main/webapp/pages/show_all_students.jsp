
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="silver">
                <table class="table table-bordered table-hover table-responsive" width="100%">
                    <tr>
                        <th class="">#</th>
                        <th class="w-50"><fmt:message key="book.info.title"/></th>
                        <th class=""><fmt:message key="book.info.publish_year"/></th>
                        <th class=""><fmt:message key="book.info.total_amount"/></th>
                        <th class=""><fmt:message key="book.info.real_amount"/></th>
                        <th class="w-10" style="text-align: center"><fmt:message key="list.edit"/></th>
                        <th class="w-10" style="text-align: center"><fmt:message key="list.delete"/></th>
                    </tr>
                    <c:forEach var="user" items="${users}" varStatus="loop">
                        <td>${loop.index+1}</td>
                        <td>${user}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.gitLink}</td>
                        </tr>
                    </c:forEach>
                </table>
</body>
</html>
