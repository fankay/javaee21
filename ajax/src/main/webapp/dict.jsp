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
            <h3>电子词典</h3>
        </div>
        <div class="form-group">
            <input type="text" id="keyword" class="form-control" placeholder="请输入要查询的单词">
        </div>
        <p id="result"></p>

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

            document.querySelector("#keyword").onkeyup = function(event){
                if(event.keyCode == 13) {
                    var word = this.value;
                    var xmlHttp = createXmlHttp();
                    xmlHttp.open("get","/dict?q=" + encodeURIComponent(word),true);
                    xmlHttp.onreadystatechange = function(){
                        if(xmlHttp.readyState == 4) {
                            if(xmlHttp.status == 200) {
                                var xmlDoc = xmlHttp.responseXML;

                                var basic = xmlDoc.getElementsByTagName("basic")[0];
                                var explains = basic.getElementsByTagName("explains")[0];
                                var ex = explains.getElementsByTagName("ex")[0].childNodes[0].nodeValue;

                                document.querySelector("#result").innerText = ex;

                            }
                        }
                    };
                    xmlHttp.send();
                }
            };




        })();
    </script>

</body>
</html>