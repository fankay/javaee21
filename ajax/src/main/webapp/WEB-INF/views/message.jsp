<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h4>轮询演示</h4>
        </div>
        <a href="javascript:;" id="loadData">
            <div class="alert alert-info" style="display:none "></div>
        </a>
        <div id="message">
            <c:forEach items="${messageList}" var="msg">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        ${msg.author}
                    </div>
                    <div class="panel-body">
                        ${msg.message}
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <script src="/static/js/jquery-1.11.3.min.js"></script>
    <script type="kaishengit/template" id="msgTemplate">
        <div class="panel panel-default">
            <div class="panel-heading" style="color: darkred">
                {{author}}
            </div>
            <div class="panel-body">
                {{msg}}
            </div>
        </div>
    </script>
    <script>
        $(function(){
            //当前显示的消息最大ID
            var maxId = ${maxId};
            //当前最新的数据
            var newData = null;

            //将最新的消息动态加载到页面中
            $("#loadData").click(function(){
                var $message = $("#message");
                if(newData) {
                    for(var i = newData.length-1;i >= 0;i--) {
                        var msg = newData[i];

                        var template = $("#msgTemplate").html(); // 字符串
                        template = template.replace("{{author}}",msg.author);
                        template = template.replace("{{msg}}",msg.message);

                        $message.prepend(template);
                    }

                    maxId = newData[0].id;
                    newData = null;
                    $(".alert").fadeOut();
                }
            });


            //从服务器中轮询数据
            setInterval(function(){
                $.post("/message",{"maxId":maxId},function(data){
                    if(data.length > 0) {
                        newData = data;
                        console.log("有" + data.length + "条新数据");
                        $(".alert").text("有" + data.length + "条新数据").fadeIn();
                    }
                });
            },10000);


        });
    </script>


</body>
</html>