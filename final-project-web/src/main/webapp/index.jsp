<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Road to Epam</title>
</head>
<body>
</body>
</html>
<с:if test="${not empty sessionScope.user}" >
    <c:redirect url="/controller?command=login"/>
</с:if>

<c:redirect url="${abs_path}/controller?command=to_main_page"/>