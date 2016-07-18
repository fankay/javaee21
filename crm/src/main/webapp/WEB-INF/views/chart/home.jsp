<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛CRM | 统计</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/mainHeader.jsp"%>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="chart"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">统计区间</h3>
                </div>
                <div class="box-body">

                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <div class="info-box">
                        <!-- Apply any bg-* class to to the icon to color it -->
                        <span class="info-box-icon bg-aqua"><i class="fa fa-plus"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">本月新增客户数量</span>
                            <span class="info-box-number">${newCustomerCount}</span>
                        </div><!-- /.info-box-content -->
                    </div><!-- /.info-box -->
                </div>
                <div class="col-md-4">
                    <div class="info-box">
                        <!-- Apply any bg-* class to to the icon to color it -->
                        <span class="info-box-icon bg-green"><i class="fa fa-flag"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">本月完成交易次数</span>
                            <span class="info-box-number">${saleCount}</span>
                        </div><!-- /.info-box-content -->
                    </div><!-- /.info-box -->
                </div>
                <div class="col-md-4">
                    <div class="info-box">
                        <!-- Apply any bg-* class to to the icon to color it -->
                        <span class="info-box-icon bg-red"><i class="fa fa-money"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">本月交易额</span>
                            <span class="info-box-number">￥<fmt:formatNumber value="${saleMoney}"/> </span>
                        </div><!-- /.info-box-content -->
                    </div><!-- /.info-box -->
                </div>
            </div>

            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">销售机会统计</h3>
                </div>
                <div class="box-body">
                    <div id="pieChart" style="width: 100%;height: 300px"></div>
                </div>
            </div>

            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">员工业绩图</h3>
                </div>
                <div class="box-body">
                    <div id="barChart" style="width: 100%;height: 300px"></div>
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
<script src="/static/plugins/echart/echarts.min.js"></script>
<script>
    $(function(){
        //饼图
        /*var data = [
            {name:'初次接触',value:500},
            {name:'确定意向',value:400},
            {name:'提供合同',value:300},
            {name:'完成交易',value:200}
        ];*/

        var pieChart = echarts.init($("#pieChart")[0]);
        var pieOption = {
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series : [
                {
                    name: '销售机会',
                    type: 'pie',
                    data:[],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        pieChart.setOption(pieOption);
        $.get("/chart/progress/data",function(data){
            pieChart.setOption({
                series: [{
                    data: data
                }]
            });
        });
        //pieChart.setOption(pieOption);


        //柱状图
        var barChart = echarts.init($("#barChart")[0]);

        // 指定图表的配置项和数据
        var option = {
            tooltip: {},
            xAxis: {
                data: []
            },
            yAxis: {},
            series: [{
                name: '业绩',
                type: 'bar',
                data: []
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        barChart.setOption(option);
        $.get("/chart/user/money",function(data){
            barChart.setOption({
                xAxis: {
                    data: data.names
                },
                series: [{
                    data: data.values
                }]
            });
        });



    });
</script>
</body>
</html>
