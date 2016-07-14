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
    <title>凯盛CRM | 客户列表</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables/css/dataTables.bootstrap.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/mainHeader.jsp"%>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="customer"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="box box-primary">
                <div class="box-header with-border">
                    <div class="box-title">客户列表</div>
                    <div class="box-tools">
                        <button class="btn btn-xs btn-success" id="newBtn"><i class="fa fa-plus"></i> 新增客户</button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="customerTable">
                        <thead>
                            <tr>
                                <th style="width:20px;"></th>
                                <th>客户名称</th>
                                <th>联系电话</th>
                                <th>电子邮件</th>
                                <th>等级</th>
                                <th style="width: 80px">#</th>
                            </tr>
                        </thead>
                        <tbody></tbody>
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
                <h4 class="modal-title">新增客户</h4>
            </div>
            <div class="modal-body">
                <form id="newForm">
                    <div class="form-group">
                        <label>类型</label>
                        <div>
                            <label class="radio-inline">
                                <input type="radio" name="type" value="person" checked id="radioPerson"> 个人
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="type" value="company" id="radioCompany"> 公司
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>客户名称</label>
                        <input type="text" class="form-control" name="name">
                    </div>
                    <div class="form-group">
                        <label>联系电话</label>
                        <input type="text" class="form-control" name="tel" >
                    </div>
                    <div class="form-group">
                        <label>客户等级</label>
                        <select name="level" class="form-control">
                            <option value=""></option>
                            <option value="★">★</option>
                            <option value="★★">★★</option>
                            <option value="★★★">★★★</option>
                            <option value="★★★★">★★★★</option>
                            <option value="★★★★★">★★★★★</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>微信号</label>
                        <input type="text" class="form-control" name="weixin">
                    </div>
                    <div class="form-group">
                        <label>电子邮件</label>
                        <input type="text" class="form-control" name="email">
                    </div>
                    <div class="form-group">
                        <label>地址</label>
                        <input type="text" class="form-control" name="address">
                    </div>
                    <div class="form-group" id="companyList">
                        <label>所属公司</label>
                        <select name="companyid" class="form-control"></select>
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


<div class="modal fade" id="editModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑客户</h4>
            </div>
            <div class="modal-body">
                <form id="editForm">
                    <input type="hidden" name="userid" id="edit_userid">
                    <input type="hidden" name="id" id="edit_id">
                    <input type="hidden" name="type" id="edit_type">
                    <div class="form-group">
                        <label>客户名称</label>
                        <input type="text" class="form-control" name="name" id="edit_name">
                    </div>
                    <div class="form-group">
                        <label>联系电话</label>
                        <input type="text" class="form-control" name="tel" id="edit_tel">
                    </div>
                    <div class="form-group">
                        <label>客户等级</label>
                        <select name="level" class="form-control" id="edit_level">
                            <option value=""></option>
                            <option value="★">★</option>
                            <option value="★★">★★</option>
                            <option value="★★★">★★★</option>
                            <option value="★★★★">★★★★</option>
                            <option value="★★★★★">★★★★★</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>微信号</label>
                        <input type="text" class="form-control" name="weixin" id="edit_weixin">
                    </div>
                    <div class="form-group">
                        <label>电子邮件</label>
                        <input type="text" class="form-control" name="email" id="edit_email">
                    </div>
                    <div class="form-group">
                        <label>地址</label>
                        <input type="text" class="form-control" name="address" id="edit_address">
                    </div>
                    <div class="form-group" id="editCompanyList">
                        <label>所属公司</label>
                        <select name="companyid" class="form-control"></select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="editBtn">保存</button>
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
<script src="/static/plugins/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/moment/moment.min.js"></script>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script>
    $(function(){

        var dataTable = $("#customerTable").DataTable({
            serverSide:true,
            ajax:"/customer/load",
            ordering:false,
            "autoWidth": false,
            columns:[
                {"data":function(row){
                    if(row.type == 'company') {
                        return "<i class='fa fa-bank'></i>";
                    }
                    return "<i class='fa fa-user'></i>";
                }},
                {"data":function(row){
                    if(row.companyname) {
                        return '<a href="/customer/'+row.id+'">'+row.name+'</a>' + " - " + '<a href="/customer/'+row.companyid+'">'+row.companyname+'</a>';
                    }
                    return '<a href="/customer/'+row.id+'">'+row.name+'</a>';
                }},
                {"data":"tel"},
                {"data":"email"},
                {"data":function(row){
                    return "<span style='color:#ff7400'>"+row.level+"</span>"
                }},
                {"data":function(row){
                    return "<a href='javascript:;' rel='"+row.id+"' class='editLink'>编辑</a>  "<shiro:hasRole name="经理"> + "<a href='javascript:;' rel='"+row.id+"' class='delLink'>删除</a>　"</shiro:hasRole>;
                }}
            ],
            "language": { //定义中文
                "search": "客户名称或电话:",
                "zeroRecords": "没有匹配的数据",
                "lengthMenu": "显示 _MENU_ 条数据",
                "info": "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered": "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载中...",
                "processing": "处理中...",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            }
        });


        //新增客户
        $("#newForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                name:{
                    required:true
                },
                tel:{
                    required:true
                }
            },
            messages:{
                name:{
                    required:"请输入客户名称"
                },
                tel:{
                    required:"请输入联系电话"
                }
            },
            submitHandler:function(form){
                $.post("/customer/new",$(form).serialize()).done(function(data){
                    if("success" == data) {
                        $("#newModal").modal('hide');
                        dataTable.ajax.reload();
                    }
                }).fail(function(){
                    alert("服务器异常");
                });
            }
        });

        //新增客户
        $("#newBtn").click(function(){
            //重置表单
            $("#newForm")[0].reset();

            //使用Ajax加载最新的公司列表
            $.get("/customer/company.json").done(function(data){
                var $select = $("#companyList select");
                $select.html("");
                $select.append("<option></option>");
                if(data && data.length) {
                    for(var i = 0;i < data.length;i++) {
                        var company = data[i];
                        var option = "<option value='"+company.id+"'>"+company.name+"</option>";
                        $select.append(option);
                    }
                }
            }).fail(function(){
                alert("服务器异常");
            });


            $("#companyList").show();

            $("#newModal").modal({
                show:true,
                dropback:'static',
                keyboard:false
            });
        });
        $("#radioCompany").click(function(){
            if($(this)[0].checked) {
                $("#companyList").hide();
            }
        });
        $("#radioPerson").click(function(){
            if($(this)[0].checked) {
                $("#companyList").show();
            }
        });
        $("#saveBtn").click(function(){
            $("#newForm").submit();
        });

        <shiro:hasRole name="经理">
        //删除客户
        $(document).delegate(".delLink","click",function(){
            if(confirm("删除客户会自动删除关联数据，继续吗?")) {
                var id = $(this).attr("rel");
                $.get("/customer/del/"+id).done(function(data){
                    if("success" == data) {
                        dataTable.ajax.reload();
                    }
                }).fail(function(){
                    alert("服务器异常");
                });
            }
        });
        </shiro:hasRole>

        //编辑客户
        $("#editForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                name:{
                    required:true
                },
                tel:{
                    required:true
                }
            },
            messages:{
                name:{
                    required:"请输入客户名称"
                },
                tel:{
                    required:"请输入联系电话"
                }
            },
            submitHandler:function(form){
                $.post("/customer/edit",$(form).serialize()).done(function(data){
                    if("success" == data) {
                        $("#editModal").modal('hide');
                        dataTable.ajax.reload();
                    }
                }).fail(function(){
                    alert("服务器异常");
                });
            }
        });

        $(document).delegate(".editLink","click",function(){
            var id = $(this).attr("rel");
            var $select = $("#editCompanyList select");
            $select.html("");
            $select.append("<option></option>");

            //ajax请求服务端获取id对应的customer对象和公司列表
            $.get("/customer/edit/"+id+".json").done(function(data){

                if(data.state == "success") {
                    //1.获取公司列表动态添加select中的option
                    if(data.companyList && data.companyList.length) {
                        for(var i = 0;i < data.companyList.length;i++) {
                            var company = data.companyList[i];
                            var option = "<option value='"+company.id+"'>"+company.name+"</option>"
                            $select.append(option);
                        }
                    }


                    //2.将获取的customer对象填入表单
                    var customer = data.customer;

                    //判断customer是否是公司，如果是公司则隐藏所属公司列表
                    if(customer.type == 'company') {
                        $("#editCompanyList").hide();
                    } else {
                        $("#editCompanyList").show();
                    }

                    $("#edit_id").val(customer.id);
                    $("#edit_name").val(customer.name);
                    $("#edit_tel").val(customer.tel);
                    $("#edit_weixin").val(customer.weixin);
                    $("#edit_address").val(customer.address);
                    $("#edit_email").val(customer.email);
                    $("#edit_level").val(customer.level);
                    $("#edit_userid").val(customer.userid);
                    $("#edit_type").val(customer.type);
                    $select.val(customer.companyid);


                    $("#editModal").modal({
                        show: true,
                        backdrop: 'static',
                        keyboard: false
                    });
                } else {
                    alert(data.message);
                }
            }).fail(function(){
                alert("服务器异常");
            });
        });

        $("#editBtn").click(function(){
            $("#editForm").submit();
        });

    });
</script>
</body>
</html>
