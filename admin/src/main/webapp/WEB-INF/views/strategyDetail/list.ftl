<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼骡窝窝后台管理系统</title>
    <#include "../common/header.ftl" >
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/ckeditor/ckeditor.js"></script>
    <script>
        $(function () {
            //推荐按钮
            $(".btn-input").click(function () {
                //清除表单数据
                $("#editForm input,select").val("");
                //清除富文本编辑器
                ck.setData("");

                var json = $(this).data('json');
                if(json){
                    //回显id隐藏域
                    $("input[name='id']").val(json.id);
                    $("input[name='title']").val(json.title);
                    $("#coverUrl").attr('src',json.coverUrl);
                    $("input[name='coverUrl']").val(json.coverUrl);
                    $("select[name='state']").val(json.state);
                    $("input[name='sequence']").val(json.sequence);
                    //回显大攻略id
                    $("#strategySelect").val(json.strategyId);
                    $.get('/strategyCatalog/listByStrategyId.do',{strategyId:json.strategyId},function (data) {
                        var temp = "";
                        //循环拼接option
                        $.each(data,function (index, ele) {
                            temp += '<option value="'+ele.id+'">'+ele.name+'</option>';
                        })
                        //攻略分类下拉框
                        $("#catalogSelect").html(temp);
                        //回显攻略分类id
                        $("#catalogSelect").val(json.catalogId);
                    })

                    //回显攻略文章内容
                    $.get('/strategyDetail/getContentById.do',{id:json.id},function (data) {
                        //把内容放到富文本编辑器
                        ck.setData(data.content);
                        console.log(data.content);
                    })

                }

                $("#inputModal").modal('show');
            })


            //提交表单
            $(".btn-submit").click(function () {
                //获取富文本编辑器的内容,设置到表单组件中
                var data = ck.getData();
                $("#editor").html(data);

                //将表单的提交方式修改为ajax异步提交
                $("#editForm").ajaxSubmit(function (data) {
                    if(data.success){
                        $.messager.alert("温馨提示","操作成功,2S之后关闭");
                        setTimeout(function () {
                            window.location.reload();
                        },2000);
                    }
                })
            });

            //大攻略下拉框change事件
            $("#strategySelect").change(function () {
                //获取大攻略选中的值
                var strategyId = $(this).val();
                //发送ajax请求获取该攻略下的所有分类
                $.get('/strategyCatalog/listByStrategyId.do',{strategyId:strategyId},function (data) {
                    var temp = "";
                    //循环拼接option
                    $.each(data,function (index, ele) {
                        temp += '<option value="'+ele.id+'">'+ele.name+'</option>';
                    })
                    //攻略分类下拉框
                    $("#catalogSelect").html(temp);
                })
            })

            
        })
    </script>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#assign currentMenu="strategyDetail"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">攻略文章管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/strategyDetail/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label>关键字</label>
                    <input class="form-control" type="text" name="keyword" value="${(qo.keyword)!}">
                </div>
                <div class="form-group">
                    <button id="query" type="submit" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                </div>
                <a href="javascript:;" class="btn btn-success btn-input">添加</a>
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>标题</th>
                    <th>封面</th>
                    <th>发布时间</th>
                    <th>排序</th>
                    <th>攻略类别</th>
                    <th>状态</th>
                </tr>

                </thead>
                <#list pageInfo.list as entity>
                <tr>
                    <td>${entity_index + 1}</td>
                    <td>${entity.title}</td>
                    <td><img src="${entity.coverUrl}" width="50px"/></td>
                    <td>${(entity.releaseTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                    <td>${entity.sequence}</td>
                    <td>${entity.catalog.name}</td>
                    <td>${entity.stateName}</td>
                    <td>
                        <a href="javascript:void(0);" class="btn-input" data-json='${entity.json}'>修改</a>
                    </td>
                </tr>
                </#list>
            </table>
            <#include "../common/page.ftl" />
        </div>
    </div>
</div>


<#--编辑模态框-->
<div class="modal fade" id="inputModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增攻略文章</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/strategyDetail/saveOrUpdate.do" method="post" id="editForm" enctype="multipart/form-data">
                    <input type="hidden" name="id" >
                    <div class="form-group">
                        <label  class="col-sm-4 control-label">标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"  name="title" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">封面：</label>
                        <div class="col-sm-6">
                            <img id="coverUrl" width="100%"/>
                            <input type="file" class="form-control" name="file" >
                            <input type="hidden" class="form-control" name="coverUrl" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">所属攻略：</label>
                        <div class="col-sm-6">
                            <select id="strategySelect" class="form-control" autocomplete="off" >
                                <#list strategies as strategy>
                                    <option value="${strategy.id}">${strategy.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-4 control-label">攻略类别：</label>
                        <div class="col-sm-6">
                            <select id="catalogSelect" class="form-control" autocomplete="off" name="catalog.id">

                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">状态：</label>
                        <div class="col-sm-6">
                            <select id="auditState" class="form-control" autocomplete="off" name="state" >
                                <option value="0">草稿</option>
                                <option value="1">发布</option>
                                <option value="-1">禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">序号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="sequence" >
                        </div>
                    </div>
                    <div class="form-group">
                        <textarea name="strategyContent.content" id="editor" rows="10" cols="80">
                        </textarea>
                        <script>
                            var ck = CKEDITOR.replace( 'editor' );
                        </script>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary btn-submit">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>