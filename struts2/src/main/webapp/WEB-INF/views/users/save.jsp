<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h3>User Save</h3>
    <h3>${sessionScope.hi}</h3>

    <form action="/user/save.do" method="post">
        <input type="text" name="user.username">
        <input type="text" name="user.address">
        <button>Save</button>
    </form>

</body>
</html>