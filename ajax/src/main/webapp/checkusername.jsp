<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>

    <input type="text" id="username"><span id="help_text"></span>

    <script>
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

        document.querySelector("#username").onblur = function(){

            var username = this.value;

            //1. 创建Ajax引擎
            var xmlHttp = createXmlHttp();
            //2. 设置请求方式和URL
            //xmlHttp.open("get","/checkusername?username="+encodeURIComponent(username)+"&_="+new Date().getTime());
            xmlHttp.open("get","/checkusername?username="+encodeURIComponent(username));
            //3. 设置回调函数
            xmlHttp.onreadystatechange = function(){
                //3.1 获取请求状态码[1,2,3,4]
                var readyState = xmlHttp.readyState;
                if(readyState == 4) {
                    //3.2 获取HTTP状态码
                    var httpState = xmlHttp.status;
                    if(httpState == 200) {
                        //3.3 处理服务器响应的数据
                        var result = xmlHttp.responseText;

                        if(result == "yes") {
                            document.querySelector("#help_text").innerText = "√";
                        } else {
                            document.querySelector("#help_text").innerText = "账号已被占用";
                        }


                    } else {
                        alert("服务器请求异常:" + httpState);
                    }
                }
            };
            //4. 发出请求
            xmlHttp.send();


        };


    </script>

</body>
</html>