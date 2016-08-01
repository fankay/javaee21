<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h3>User List</h3>
    <ul>
        <c:forEach items="${names}" var="name">
        <li>${name}</li>
        </c:forEach>
    </ul>
</body>
</html>