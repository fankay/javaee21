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
        <button id="btn1">get json</button>
        <button id="btn2">ajax method</button>
        <img src="/static/img/loading.gif" id="loadImg" style="display: none">

    </div>
    <script src="/static/js/jquery-1.11.3.min.js"></script>
    <script>
        $(function(){

            $("#btn2").click(function(){
                var $this = $(this);
                $.ajax({
                    url:"/user.json", //请求路径
                    type:"get", //请求方式 get|post
                    data:{"name":"tom"}, //发送到服务器的数据
                    timeout:10000, //设置请求超时时间
                    success:function(data) { //成功时执行的回调方法
                        console.log("success function");
                        alert(":)");
                    },
                    error:function(){ //错误时执行的回调方法
                        alert("请求服务器异常");
                    },
                    complete:function(){ //无论成功和失败都执行的回调方法
                        console.log("complete function");
                        $this.text("ajax method").removeAttr("disabled");
                        $("#loadImg").hide();
                    },
                    beforeSend:function() { //在ajax发送之前执行的回调方法
                        console.log("before function");
                        $this.text("sending...").attr("disabled","disabled");
                        $("#loadImg").show();
                    }
                });


            });




            $("#btn1").click(function(){
                //两个方法都是以Get方式发出ajax异步请求
                //$.get 服务器的返回值可以是任意的结果(字符串、xml、json)
                //$.getJSON 服务器的返回值必须是JSON类型

                // get() post() getJSON() 返回 jqXHR对象中的
                // done()表示成功执行的回调函数
                // fail()表示错误执行的回调函数
                // always() 表示无论成功还是错误都会执行的回调函数
                $.getJSON("/user.json",function(data){
                            console.log(data);
                        })
                        .done(function(data){
                            console.log(data);
                        })
                        .fail(function(){
                            alert("请求服务器异常");
                        })
                        .always(function(){
                            console.log("always~~~~~~~~~~");
                        });



            });

        });
    </script>
</body>
</html>