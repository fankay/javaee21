<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛CRM | 代办事项</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/plugins/fullcalendar/fullcalendar.min.css">
    <link rel="stylesheet" href="/static/plugins/colorpicker/bootstrap-colorpicker.css">
    <style>
        .todo-list>li .text {
            font-weight: normal;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/mainHeader.jsp"%>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="task"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-8">
                    <div class="box box-solid">
                        <div class="box-body">
                            <div id="calendar"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">我的待办事项</h3>
                            <div class="box-tools">
                                <button class="btn btn-success btn-xs" id="newBtn"><i class="fa fa-plus"></i> 新增</button>
                            </div>
                        </div>
                        <div class="box-body">
                            <ul class="todo-list">
                                <li>
                                    <input type="checkbox">
                                    <span class="text">给李忠打电话联系业务</span>
                                    <div class="tools">
                                        <i class="fa fa-edit"></i>
                                        <i class="fa fa-trash-o"></i>
                                    </div>
                                </li>
                                <li>
                                    <input type="checkbox">
                                    <span class="text">Make the theme responsive </span>
                                    <div class="tools">
                                        <i class="fa fa-edit"></i>
                                        <i class="fa fa-trash-o"></i>
                                    </div>
                                </li>
                                <li>
                                    <input type="checkbox">
                                    <span class="text">Check your messages and notifications</span>
                                    <div class="tools">
                                        <i class="fa fa-edit"></i>
                                        <i class="fa fa-trash-o"></i>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="box box-danger">
                        <div class="box-header with-border">
                            <h3 class="box-title">已经延期的事项</h3>
                        </div>
                        <div class="box-body">
                            <ul class="todo-list">
                                <li>
                                    <input type="checkbox">
                                    <span class="text">给李忠打电话联系业务</span>
                                    <div class="tools">
                                        <i class="fa fa-edit"></i>
                                        <i class="fa fa-trash-o"></i>
                                    </div>
                                </li>
                                <li>
                                    <input type="checkbox">
                                    <span class="text">Make the theme responsive </span>
                                    <div class="tools">
                                        <i class="fa fa-edit"></i>
                                        <i class="fa fa-trash-o"></i>
                                    </div>
                                </li>
                                <li>
                                    <input type="checkbox">
                                    <span class="text">Check your messages and notifications</span>
                                    <div class="tools">
                                        <i class="fa fa-edit"></i>
                                        <i class="fa fa-trash-o"></i>
                                    </div>
                                </li>
                            </ul>
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
<div class="modal fade" id="newModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增待办事项</h4>
            </div>
            <div class="modal-body">
                <form id="newForm" action="/task/new" method="post">
                    <div class="form-group">
                        <label>待办内容</label>
                        <input type="text" class="form-control" name="title">
                    </div>
                    <div class="form-group">
                        <label>开始日期</label>
                        <input type="text" class="form-control" name="start" id="start_time">
                    </div>
                    <div class="form-group">
                        <label>结束日期</label>
                        <input type="text" class="form-control" name="end" id="end_time">
                    </div>
                    <div class="form-group">
                        <label>提醒时间</label>
                        <div>
                            <select name="hour" style="width: 100px">
                                <option value=""></option>
                                <option value="0">0</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                                <option value="22">22</option>
                                <option value="23">23</option>
                            </select>
                            :
                            <select name="min" style="width: 100px">
                                <option value=""></option>
                                <option value="0">0</option>
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="15">15</option>
                                <option value="20">20</option>
                                <option value="25">25</option>
                                <option value="30">30</option>
                                <option value="35">35</option>
                                <option value="40">40</option>
                                <option value="45">45</option>
                                <option value="50">50</option>
                                <option value="55">55</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>显示颜色</label>
                        <input type="text" class="form-control" name="color" id="color" value="#61a5e8">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.0 -->
<script src="/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/moment/moment.min.js"></script>
<script src="/static/plugins/fullcalendar/fullcalendar.min.js"></script>
<script src="/static/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script>
    //新增
    $("#color").colorpicker({
        color:'#61a5e8'
    });
    $("#start_time,#end_time").datepicker({
        format: 'yyyy-mm-dd',
        autoclose:true,
        language:'zh-CN',
        startDate:moment().format("YYYY-MM-DD"),
        todayHighlight:true
    });

    $("#newBtn").click(function(){
        $("#newForm")[0].reset();
        $("#start_time").val(moment().format("YYYY-MM-DD"));
        $("#end_time").val(moment().format("YYYY-MM-DD"));

        $("#newModal").modal({
            show:true,
            backdrop:'static'
        });
    });

    $("#saveBtn").click(function(){

        $("#newForm").submit();
    });


    $("#calendar").fullCalendar({
        lang: 'zh-cn',
        dayClick: function(date, jsEvent, view) {
            alert('Clicked on: ' + date.format());
        },
        eventClick: function(calEvent, jsEvent, view) {
            //alert('Event: ' + calEvent.title);
            //alert("Start:" + calEvent.start);
            //alert("End:" + calEvent.end);
        },
        buttonText:{
            today:"今天"
        },
        events: "/task/load"
    });
</script>
</body>
</html>
