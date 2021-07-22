<html>
<head>
    <title>All users list</title>
    <%@include file="/fragment/header.jsp"%>
</head>
<body bgcolor="silver">


<form>
    <table border="2">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Login</th>
            <th>Git Link</th>
            <th>Show user info</th>
        </tr>
        <%--@elvariable id="users" type="java.util.List"--%>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td><div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            ${user.login}
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </div></td>
                <td>${user.gitLink}</td>
<%--                <td><a href="${abs_path}/userById?userId=${user.id}">Show info</a></td>--%>
                <td><a href="${abs_path}/controller?command=show_student_info&userId=${user.id}">show info</a></td>
<%--                <td><a href="${abs_path}/controller?command=show_student_info&userId=${user.id}">${user.id}</a></td>--%>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
