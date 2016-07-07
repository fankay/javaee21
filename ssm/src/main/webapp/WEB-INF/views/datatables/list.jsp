<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/js/datatables/css/dataTables.bootstrap.min.css">
    <%--<link rel="stylesheet" href="/static/js/datatables/css/jquery.dataTables.min.css">--%>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h3>DataTables 演示</h3>
        </div>

        <div class="well well-sm">
            <form method="get" class="form-inline">
                <div class="form-group">
                    <input type="text" placeholder="书籍名称" name="bookname" id="search_bookName" value="${bookname}" class="form-control">
                </div>
                <div class="form-group">
                    <select name="type" class="form-control" id="search_type">
                        <option value="">请选择类型</option>
                        <c:forEach items="${types}" var="type">
                            <option value="${type.id}" ${typeid == type.id ? 'selected' : '' }>${type.booktype}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <select name="pub" class="form-control" id="search_pub">
                        <option value="">请选择出版社</option>
                        <c:forEach items="${pubs}" var="pub">
                            <option value="${pub.id}" ${pubid == pub.id ? 'selected' : ''} >${pub.pubname}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="button" id="searchBtn" class="btn btn-default">搜索</button>
            </form>
        </div>

        <a href="javascript:;" id="newBookBtn" class="btn btn-primary"  style="margin-bottom:20px;">添加新书籍</a>
        <table id="dataTable" class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>名称</th>
                <th>作者</th>
                <th>价格</th>
                <th>数量</th>
                <th>出版社</th>
                <th>分类</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="newBookModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">添加新书籍</h4>
                </div>
                <div class="modal-body">
                    <form id="saveForm">
                        <div class="form-group">
                            <label>书籍名称</label>
                            <input type="text" name="bookname" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>书籍作者</label>
                            <input type="text" name="bookauthor" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>书籍价格</label>
                            <input type="text" name="bookprice" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>书籍数量</label>
                            <input type="text" name="booknum" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>分类</label>
                            <select class="form-control" name="typeid">
                                <c:forEach items="${types}" var="type">
                                    <option value="${type.id}">${type.booktype}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>出版社</label>
                            <select class="form-control" name="pubid">
                                <c:forEach items="${pubs}" var="pub">
                                    <option value="${pub.id}">${pub.pubname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="editBookModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">编辑书籍</h4>
                </div>
                <div class="modal-body">
                    <form id="editForm">
                        <input type="hidden" name="id" id="edit_bookid">
                        <div class="form-group">
                            <label>书籍名称</label>
                            <input type="text" name="bookname" class="form-control" id="edit_bookname">
                        </div>
                        <div class="form-group">
                            <label>书籍作者</label>
                            <input type="text" name="bookauthor" class="form-control" id="edit_bookauthor">
                        </div>
                        <div class="form-group">
                            <label>书籍价格</label>
                            <input type="text" name="bookprice" class="form-control" id="edit_bookprice">
                        </div>
                        <div class="form-group">
                            <label>书籍数量</label>
                            <input type="text" name="booknum" class="form-control" id="edit_booknum">
                        </div>
                        <div class="form-group">
                            <label>分类</label>
                            <select class="form-control" name="typeid" id="edit_booktypeid">
                                <c:forEach items="${types}" var="type">
                                    <option value="${type.id}">${type.booktype}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>出版社</label>
                            <select class="form-control" name="pubid" id="edit_bookpubid">
                                <c:forEach items="${pubs}" var="pub">
                                    <option value="${pub.id}">${pub.pubname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="editBtn">保存</button>
                </div>
            </div>
        </div>
    </div>


<script src="http://cdn.bootcss.com/jquery/2.2.2/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/js/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script>
    $(function(){

        var dataTable = $("#dataTable").DataTable({
            "lengthMenu": [ 5,10, 25, 50, 75, 100 ], //定义每页显示的数量列表
            "serverSide": true, //表示所有的操作都在服务端进行
            "ajax" : {
                url:"/datatable/data.json",
                data:function(dataSouce){
                    dataSouce.bookname = $("#search_bookName").val();
                    dataSouce.typeid = $("#search_type").val();
                    dataSouce.pubid = $("#search_pub").val();
                }
            }, //服务端URL地址
            "order": [0,'desc'], //指定默认排序方式
            "searching": false,//禁止使用自带的搜索
            "columns" : [ //配置返回的JSON中[data]属性中数据key和表格列的对应关系
                {"data":"id","name":"id"},
                {"data":"bookname"},
                {"data":"bookauthor"},
                {"data":"bookprice","name":"bookprice"},
                {"data":"booknum","name":"booknum"},
                {"data":"publisher.pubname"},
                {"data":"bookType.booktype","name":"typeid"},
                {"data":function(row){
                    return "<a href='javascript:;' class='editLink' rel='"+row.id+"'>编辑</a> <a href='javascript:;' class='delLink' rel='"+row.id+"'>删除</a>";
                }}
            ],
            "columnDefs":[ //定义列的特征
                {targets: [0], visible: false},
                {targets:[1,2,5,7],orderable:false}
            ],
            "language":{ //定义中文
                "search": "请输入书籍名称:",
                "zeroRecords":    "没有匹配的数据",
                "lengthMenu":     "显示 _MENU_ 条数据",
                "info":           "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered":   "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载中...",
                "processing":     "处理中...",
                "paginate": {
                    "first":      "首页",
                    "last":       "末页",
                    "next":       "下一页",
                    "previous":   "上一页"
                },
            }
        });


        //添加新书籍
        $("#saveForm").validate({
            errorElement:"span",
            errorClass:"text-danger",
            rules:{
                bookname:{
                    required:true
                },
                bookauthor:{
                    required:true
                },
                bookprice:{
                    required:true,
                    number:true
                },
                booknum:{
                    required:true,
                    digits:true
                }
            },
            messages:{
                bookname:{
                    required:"请输入书籍名称"
                },
                bookauthor:{
                    required:"请输入作者"
                },
                bookprice:{
                    required:"请输入价格",
                    number:"请输入正确的价格"
                },
                booknum:{
                    required:"请输入数量",
                    digits:"请输入正确的数量"
                }
            },
            submitHandler:function(form){
                $.post("/datatable/new",$(form).serialize())
                        .done(function(data){
                            if(data == "success") {
                                $("#newBookModal").modal('hide');
                                dataTable.ajax.reload(); //让DataTables组建自动刷新
                            }
                        })
                        .fail(function(){
                            alert("请求服务器错误");
                        });
            }
        });

        $("#newBookBtn").click(function(){

            $("#saveForm")[0].reset(); //让表单进行重置

            $("#newBookModal").modal({
                show:true,
                backdrop:'static',
                keyboard:false
            });
        });
        $("#saveBtn").click(function(){
            $("#saveForm").submit();
        });

        //删除书籍(事件委托)可以给当前或未来出现的符合选择器的元素绑定事件
        $(document).delegate(".delLink","click",function(){
            var id = $(this).attr("rel");
            if(confirm("确定要删除吗?")) {
                $.get("/datatable/"+id+"/del").done(function(data){
                    if(data == "success") {
                        dataTable.ajax.reload();
                    }
                }).fail(function(){
                    alert("请求服务器异常");
                });
            }
        });

        /*$(".delLink").click(function(){
            alert("xxxx");
        });*/

        //编辑书籍
        $(document).delegate(".editLink","click",function(){
            var id = $(this).attr("rel");
            $.get("/datatable/"+id+".json").done(function(data){

                //将JSON数据显示在表单上
                $("#edit_bookid").val(data.id);
                $("#edit_bookname").val(data.bookname);
                $("#edit_bookauthor").val(data.bookauthor);
                $("#edit_bookprice").val(data.bookprice);
                $("#edit_booknum").val(data.booknum);
                $("#edit_bookpubid").val(data.pubid);
                $("#edit_booktypeid").val(data.typeid);

                $("#editBookModal").modal({
                    show:true,
                    drapback:'static',
                    keyboard:false
                });
            }).fail(function(){
                alert("服务器请求异常");
            });
        });
        $("#editBtn").click(function(){
            $("#editForm").submit();
        });
        $("#editForm").validate({
            errorElement:"span",
            errorClass:"text-danger",
            rules:{
                bookname:{
                    required:true
                },
                bookauthor:{
                    required:true
                },
                bookprice:{
                    required:true,
                    number:true
                },
                booknum:{
                    required:true,
                    digits:true
                }
            },
            messages:{
                bookname:{
                    required:"请输入书籍名称"
                },
                bookauthor:{
                    required:"请输入作者"
                },
                bookprice:{
                    required:"请输入价格",
                    number:"请输入正确的价格"
                },
                booknum:{
                    required:"请输入数量",
                    digits:"请输入正确的数量"
                }
            },
            submitHandler:function(form){
                $.post("/datatable/edit",$(form).serialize())
                        .done(function(data){
                            if(data == "success") {
                                $("#editBookModal").modal('hide');
                                dataTable.ajax.reload(); //让DataTables组建自动刷新
                            }
                        })
                        .fail(function(){
                            alert("请求服务器错误");
                        });
            }
        });

        //搜索按钮的点击
        $("#searchBtn").click(function(){
            dataTable.ajax.reload();
        });

    });
</script>
</body>
</html>