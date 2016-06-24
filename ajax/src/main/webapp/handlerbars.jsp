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
        <h3>HandlerBas</h3>
    </div>
    <button id="btn">Load Template</button>

    <div id="message" style="margin-top:20px;"></div>


</div>

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/handlebars-v4.0.5.js"></script>

<script type="text/x-handlebars-template" id="msgTemplate">
    <div class="panel panel-default">
        <div class="panel-heading">
            {{author}}
            {{#if isVip}}
            <span class="text-danger">VIP</span>
            {{else}}
            <span class="text-danger">♥</span>
            {{/if}}
        </div>
        <div class="panel-body">
            <ul>
                {{#each msg}}
                <li>{{this}}</li>
                {{/each}}
            </ul>
        </div>
    </div>
</script>

<script>
    $(function () {

        $("#btn").click(function () {
            var json = {"author": "李四", "msg": ["晚上请李总吃饭", "6月25日发合同", "7月1日去出差"], "isVip": true};

            var source = $("#msgTemplate").html();
            var template = Handlebars.compile(source);
            var html = template(json);

            $("#message").prepend(html);
        });

    });
</script>

</body>
</html>