<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>

    <form action="/file/upload.do" method="post" enctype="multipart/form-data">
        <input type="text" name="name">
        <input type="file" name="photo">
        <input type="file" name="photo">
        <input type="file" name="photo">
        <button>Upload</button>
    </form>

    <p>
        <a href="/file/download.do">点此下载文件</a>
    </p>


</body>
</html>