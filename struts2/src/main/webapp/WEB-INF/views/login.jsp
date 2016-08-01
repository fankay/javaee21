<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <form action="/login.do" method="post">
        <c:if test="${param.code == '10009'}">
            <div>账号或密码错误</div>
        </c:if>
        <c:if test="${param.code == '10002'}">
            <div>请登录后再继续</div>
        </c:if>
        <input type="text" name="username">
        <input type="text" name="password">
        <button>Login</button>

    </form>
</body>
</html>