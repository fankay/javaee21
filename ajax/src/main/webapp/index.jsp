<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>

<button id="btn">发出Ajax请求</button>

<script>
    (function(){

        function createXmlHttp() {
            var xmlHttp = null;
            //ie < 9
            if(window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } else {
                //非IE浏览器 和 IE >= 9
                xmlHttp = new XMLHttpRequest();
            }
            return xmlHttp;
        }

        document.querySelector("#btn").onclick = function(){
            //1. 创建XMLHttpRequest对象
            var xmlHttp = createXmlHttp();
            //2. 设置请求的URL和方式
            xmlHttp.open("get","/ajax",true);
            //3. 发出Ajax请求
            xmlHttp.send();
        };




    })();
</script>


</body>
</html>