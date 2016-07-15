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
    <title>凯盛CRM | 销售机会</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/plugins/daterangepicker/daterangepicker-bs3.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/mainHeader.jsp"%>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="sales"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box box-default collapsed-box">
                <div class="box-header with-border">
                    <h3 class="box-title">搜索</h3>
                    <div class="box-tools">
                        <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"><i class="fa fa-plus"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <form action="" class="form-inline">
                        <input type="text" class="form-control" placeholder="机会名称">
                        <select name="level" class="form-control" class="form-control">
                            <option value="">当前进度</option>
                            <option value="">初次接触</option>
                            <option value="">确认意向</option>
                            <option value="">提供合同</option>
                            <option value="">完成交易</option>
                            <option value="">交易搁置</option>
                        </select>
                        <input type="text" id="rangepicker" class="form-control" placeholder="跟进时间">
                        <button class="btn btn-default"><i class="fa fa-search"></i> 搜索</button>
                    </form>
                </div>
            </div>
            <%--search box end--%>
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">机会列表</h3>
                    <div class="box-tools">
                        <button class="btn btn-success btn-xs" id="newBtn"><i class="fa fa-plus"></i> 新增机会</button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>机会名称</th>
                                <th>关联客户</th>
                                <th>价值</th>
                                <th>当前进度</th>
                                <th>最后跟进时间</th>
                                <th>所属员工</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><a href="/sales/1">购买SSD硬盘500块</a></td>
                                <td><a href="">FaceBook</a></td>
                                <td>￥50000</td>
                                <td>初次接触</td>
                                <td>2016-07-15</td>
                                <td>王若诗</td>
                            </tr>
                            <tr>
                                <td><a href="">OLED显示器10台</a></td>
                                <td><a href="">Google中国</a></td>
                                <td>￥70000</td>
                                <td><span class="label label-danger">交易搁置</span></td>
                                <td>2016-06-12</td>
                                <td>王若诗</td>
                            </tr>
                            <tr>
                                <td><a href="">DELL塔式服务器10台</a></td>
                                <td><a href="">Mark</a></td>
                                <td>￥100000</td>
                                <td>发送报价</td>
                                <td>2016-07-12</td>
                                <td>王若诗</td>
                            </tr>
                            <tr>
                                <td><a href="">OLED显示器100台</a></td>
                                <td><a href="">张丽茹</a></td>
                                <td>￥700000</td>
                                <td><span class="label label-success">完成交易</span></td>
                                <td>2016-06-12</td>
                                <td>王若诗</td>
                            </tr>
                        </tbody>
                    </table>
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
                <h4 class="modal-title">新增机会</h4>
            </div>
            <div class="modal-body">
                <form id="newForm">
                    <div class="form-group">
                        <label>机会名称</label>
                        <input type="text" class="form-control" name="name">
                    </div>
                    <div class="form-group">
                        <label>价值</label>
                        <input type="text" class="form-control" name="price">
                    </div>
                    <div class="form-group">
                        <label>关联客户</label>
                        <select name="level" class="form-control">
                            <option value="">FaceBook</option>
                            <option value="">Google中国</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>当前进度</label>
                        <select name="level" class="form-control">
                            <option value="">初次接触</option>
                            <option value="">确认意向</option>
                            <option value="">提供合同</option>
                            <option value="">完成交易</option>
                            <option value="">交易搁置</option>
                        </select>
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
<script src="/static/plugins/moment/moment.min.js"></script>
<script src="/static/plugins/daterangepicker/daterangepicker.js"></script>

<script>
    $(function(){

        //daterangepicker
        $("#rangepicker").daterangepicker({
            format: "YYYY-MM-DD",
            separator:"~",
            locale:{
                "applyLabel": "选择",
                "cancelLabel": "取消",
                "fromLabel": "从",
                "toLabel": "到",
                "customRangeLabel": "自定义",
                "weekLabel": "周",
                "daysOfWeek": [
                    "一",
                    "二",
                    "三",
                    "四",
                    "五",
                    "六",
                    "日"
                ],
                "monthNames": [
                    "一月",
                    "二月",
                    "三月",
                    "四月",
                    "五月",
                    "六月",
                    "七月",
                    "八月",
                    "九月",
                    "十月",
                    "十一月",
                    "十二月"
                ],
                "firstDay": 1
            },
            ranges: {
                '今天': [moment(), moment()],
                '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                '最近7天': [moment().subtract(6, 'days'), moment()],
                '最近30天': [moment().subtract(29, 'days'), moment()],
                '本月': [moment().startOf('month'), moment().endOf('month')],
                '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            }
        });
        $('#rangepicker').on('apply.daterangepicker', function(ev, picker) {
            console.log(picker.startDate.format('YYYY-MM-DD'));
            console.log(picker.endDate.format('YYYY-MM-DD'));
        });


        $("#newBtn").click(function(){

            $("#newModal").modal({
                show:true,
                backdrop:'static'
            });

        });

    });
</script>
</body>
</html>
