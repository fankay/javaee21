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
        <h3>Ajax读取XML</h3>
    </div>
    <button id="readBtn">Read</button>
    <div id="userBox">

    </div>
</div>

<script>
    (function(){

        function createXmlHttp() {
            var xmlHttp = null;
            if(window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } else {
                xmlHttp = new XMLHttpRequest();
            }
            return xmlHttp;
        }

        function createUserDiv(id,username,address) {
            /*
            <div>
                <h4>name <small>id</small></h4>
                <p>address</p>
            </div>
             */

            var div = document.createElement("div");
            var h4 = document.createElement("h4");
            var small = document.createElement("samll");
            var p = document.createElement("p");

            var idText = document.createTextNode(id);
            var addressText = document.createTextNode(address);
            var nameText = document.createTextNode(username);

            p.appendChild(addressText);
            small.appendChild(idText);
            h4.appendChild(nameText);
            h4.appendChild(small);

            div.appendChild(h4);
            div.appendChild(p);

            document.getElementById("userBox").appendChild(div);

        }

        document.getElementById("readBtn").onclick = function(){
            var xmlHttp = createXmlHttp();
            xmlHttp.open("get","/user.xml",true);
            xmlHttp.onreadystatechange = function(){
                if(xmlHttp.readyState == 4) {
                    var status = xmlHttp.status;
                    if(status == 200) {
                        //将userbox中内容清空
                        document.getElementById("userBox").innerHTML = "";
                        //获取服务端返回的xml数据
                        var xmlDoc = xmlHttp.responseXML;
                        var userArray = xmlDoc.getElementsByTagName("user");

                        for(var i = 0;i < userArray.length;i++) {
                            var user = userArray[i];
                            var id = user.getAttribute("id");
                            var username = user.getElementsByTagName("username")[0].childNodes[0].nodeValue;
                            var address = user.getElementsByTagName("address")[0].childNodes[0].nodeValue;
                            console.log("ID: " + id + " UserName:" + username + " Address:" + address);

                            createUserDiv(id,username,address);
                        }




                    } else {
                        alert("请求服务器异常:" + status);
                    }
                }
            };
            xmlHttp.send();
        };



    })();
</script>


</body>
</html>