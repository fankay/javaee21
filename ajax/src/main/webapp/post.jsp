<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <button id="getBtn">发出GET请求</button>
    <button id="btn">发出post请求</button>
    <script src="/static/js/ajax.js"></script>
    <script>
        (function(){

            function sayHello(arg1,arg2) {
                alert(arguments.length);
            }


            document.querySelector("#getBtn").onclick = function(){

                Ajax.getText("/ajax",function(result){
                    alert("Hi," + result);
                });

            };

            document.querySelector("#btn").onclick = function(){
                Ajax.postText("/ajax",{name:"java",address:"USA"},function(result){
                    alert("Post:" + result);
                })
            };



            /*document.querySelector("#btn").onclick = function(){
                var xmlHttp = createXmlHttp();
                xmlHttp.open("post","/ajax",true);
                xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                xmlHttp.send("name=张三&address=中国");
            };*/


        })();
    </script>
</body>
</html>