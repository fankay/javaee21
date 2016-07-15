<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛CRM | 销售机会 | ${sales.name}</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <style>
        .timeline>li>.timeline-item{
            box-shadow:none;
            -webkit-box-shadow:none;
        }
        .files li{
            padding: 5px;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/mainHeader.jsp"%>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="sales"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>　</h1>
            <ol class="breadcrumb">
                <li><a href="/sales"><i class="fa fa-dashboard"></i> 机会列表</a></li>
                <li class="active">${sales.name}</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">${sales.name}</h3>
                    <shiro:hasRole name="经理">
                        <div class="box-tools">
                            <button class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</button>
                        </div>
                    </shiro:hasRole>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tbody>
                            <tr>
                                <td style="width: 100px">关联客户</td>
                                <td style="width: 200px"><a href="/customer/${sales.custid}" target="_blank">${sales.custname}</a></td>
                                <td style="width: 100px">价值</td>
                                <td style="width: 200px">￥<fmt:formatNumber value="${sales.price}"/> </td>
                            </tr>
                            <tr>
                                <td>当前进度</td>
                                <td>${sales.progress} <a href="">修改</a></td>
                                <td>最后跟进时间</td>
                                <td>${empty sales.lasttime ? '无' : sales.lasttime}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>


            <div class="row">
                <div class="col-md-8">
                    <div class="box box-default">
                        <div class="box-header with-border">
                            <h3 class="box-title">跟进记录</h3>
                            <div class="box-tools">
                                <button class="btn btn-xs btn-success"><i class="fa fa-plus"></i> 新增记录</button>
                            </div>
                        </div>
                        <div class="box-body">
                            <ul class="timeline">
                                <c:forEach items="${salesLogList}" var="log">
                                    <li>
                                        <c:choose>
                                            <c:when test="${log.type == 'auto'}">
                                                <i class="fa fa-history bg-yellow"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fa fa-commenting bg-aqua"></i>
                                            </c:otherwise>
                                        </c:choose>

                                        <div class="timeline-item">
                                            <span class="time"><i class="fa fa-clock-o"></i> <span class="timeago" rel="${log.createtime}"></span></span>
                                            <h3 class="timeline-header no-border">
                                                ${log.context}
                                            </h3>
                                        </div>
                                    </li>
                                </c:forEach>
                                <li>
                                    <i class="fa fa-clock-o bg-gray"></i>
                                </li>
                            </ul>
                        </div>
                    </div>


                </div>
                <div class="col-md-4">
                    <div class="box box-default">
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-file-o"></i> 相关资料</h3>
                            <div class="box-tools">
                                <button class="btn btn-default btn-xs"><i class="fa fa-upload"></i></button>
                            </div>
                        </div>
                        <div class="box-body">
                            <ul class="list-unstyled files">
                                <li><a href="#">第一版合同.pdf</a></li>
                                <li><a href="#">材料预算.xls</a></li>
                                <li><a href="">会议纪要.docx</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="box box-default">
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-calendar-check-o"></i> 代办任务</h3>
                        </div>
                        <div class="box-body">
                            <h5>暂无代办任务</h5>
                        </div>
                    </div>

                </div>
            </div>


        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.0 -->
<script src="/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/moment/moment.min.js"></script>
<script>
    $(function(){
        //相对时间
        moment.locale('zh_cn');
        $(".timeago").text(moment($(".timeago").attr('rel')).fromNow());
    });
</script>
</body>
</html>
