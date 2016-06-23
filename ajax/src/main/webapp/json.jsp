<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>

<button id="btn">Get JSON</button>
<ul id="list">

</ul>


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

        document.querySelector("#btn").onclick = function(){
            var xmlHttp = createXmlHttp();
            xmlHttp.open("get","/user.json",true);
            xmlHttp.onreadystatechange = function(){
                if(xmlHttp.readyState == 4) {
                    if(xmlHttp.status == 200) {
                        //获取服务器端返回的字符串
                        var result = xmlHttp.responseText;
                        //将字符串转换为JSON ES5
                        var json = JSON.parse(result);

                        for(var i = 0;i < json.length;i++) {
                            var user = json[i];
                            var id = user.id;
                            var name = user.name;
                            var score = user.score;

                            var li = document.createElement("li");
                            var text = document.createTextNode(id + "->" + name);
                            li.appendChild(text);
                            document.getElementById("list").appendChild(li);
                        }



                    }
                }
            };
            xmlHttp.send();
        };





        /*var obj = {
            "name":"java",
            "type":"language"
        };
        alert(obj.name);
        alert(obj["name"]);

        var array = [
            {
                "name":[],
                "type":"language"
            },
            {
                "name":"tom",
                "type":"person"
            }
        ];

        for(var i = 0;i < array.length;i++) {
            var o = array[i];
            console.log(o.name + " -> " + o.type);
        }*/




    })();
</script>
</body>
</html>