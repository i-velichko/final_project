<html>
<head>
    <title>All users list</title>
    <%@include file="/fragment/header.jsp"%>
</head>
<body bgcolor="silver">


<form>
    <table border="2" class="table table-striped" style="width:100%">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Login</th>
            <th>Role</th>
            <th>Status</th>
            <th>Git Link</th>
            <th>Show user info</th>
        </tr>
        <%--@elvariable id="users" type="java.util.List"--%>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.login}</td>
                <td><div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                            ${user.role}
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=change_user_role&userId=${user.id}&new_role=ADMIN">ADMIN</a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=change_user_role&userId=${user.id}&new_role=TRAINER">TRAINER</a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=change_user_role&userId=${user.id}&new_role=EXAMINER">EXAMINER</a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=change_user_role&userId=${user.id}&new_role=STUDENT">STUDENT</a></li>
                    </ul>
                </div></td>
                <td><div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            ${user.status}
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=change_user_status&userId=${user.id}&new_status=ACTIVE">ACTIVE</a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=change_user_status&userId=${user.id}&new_status=BLOCKED">BLOCKED</a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=change_user_status&userId=${user.id}&new_status=DELETED">DELETED</a></li>
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
