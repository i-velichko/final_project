<html>
<head>

    <title>All users list</title>
    <%@include file="/fragment/header.jsp" %>

</head>
<body>
<form>
    <table id="allUsers" class="table table-striped table-bordered table-sm" >
        <tr>
            <th class="th-sm">First Name</th>
            <th class="th-sm">Last Name</th>
            <th class="th-sm">Login</th>
            <th class="th-sm">Role</th>
            <th class="th-sm">Status</th>
            <th class="th-sm">Git Link</th>
            <th class="th-sm">Show user info</th>
        </tr>
        <%--@elvariable id="users" type="java.util.List"--%>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.login}</td>
                <td>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                                data-bs-toggle="dropdown" aria-expanded="false">
                                ${user.role}
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <li><a class="dropdown-item"
                                   href="${abs_path}/controller?command=change_user_role&userId=${user.id}&new_role=ADMIN">ADMIN</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${abs_path}/controller?command=change_user_role&userId=${user.id}&new_role=TRAINER">TRAINER</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${abs_path}/controller?command=change_user_role&userId=${user.id}&new_role=EXAMINER">EXAMINER</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${abs_path}/controller?command=change_user_role&userId=${user.id}&new_role=STUDENT">STUDENT</a>
                            </li>
                        </ul>
                    </div>
                </td>
                <td>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                                data-bs-toggle="dropdown" aria-expanded="false">
                                ${user.status}
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item"
                                   href="${abs_path}/controller?command=change_user_status&userId=${user.id}&new_status=ACTIVE">ACTIVE</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${abs_path}/controller?command=change_user_status&userId=${user.id}&new_status=BLOCKED">BLOCKED</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${abs_path}/controller?command=change_user_status&userId=${user.id}&new_status=DELETED">DELETED</a>
                            </li>
                        </ul>
                    </div>
                </td>
                <td>${user.gitLink}</td>
                <td><a href="${abs_path}/controller?command=show_student_info&userId=${user.id}">show info</a></td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
                <a class="page-link" href="#">Next</a>
            </li>
        </ul>
    </nav>
</form>

</body>
</html>
