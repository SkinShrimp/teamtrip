<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼骡窝窝后台管理系统</title>
    <#include "../common/header.ftl" >
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script>
        $(function () {
            //新增/编辑按钮
            $(".btn-input").click(function () {
                var json = $(this).data('json');
                if (json) {
                    //回显id隐藏域
                    $("input[name='id']").val(json.id);
                    $("input[name='name']").val(json.name);
                    $("input[name='sequence']").val(json.sequence);
                    $("select[name='state']").val(json.state + "");//变成字符串类型再回显
                    $("select[name='strategy.id']").val(json.strategyId);
                }

                $("#inputModal").modal('show');
            })


            //提交表单
            $(".btn-submit").click(function () {
                //将表单的提交方式修改为ajax异步提交
                $("#editForm").ajaxSubmit(function (data) {
                    if (data.success) {
                        $.messager.alert("温馨提示", "操作成功,2S之后关闭");
                        setTimeout(function () {
                            window.location.reload();
                        }, 2000);
                    }
                })
            });


        })
    </script>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#assign currentMenu="strategyCatalog"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">攻略分类管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/strategyCatalog/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label>所属攻略</label>
                    <select id="strategySelect" class="form-control" autocomplete="off" name="strategyId">
                        <#list strategies as strategy>
                            <option value="${strategy.id}">${strategy.title}</option>
                        </#list>
                    </select>
                    <script>
                        $("#strategySelect").val(${(qo.strategyId)!});
                    </script>
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
                    <th>分类名称</th>
                    <th>所属攻略</th>
                    <th>排序</th>
                    <th>状态</th>
                </tr>

                </thead>
                <#list pageInfo.list as entity>
                <tr>
                    <td>${entity_index + 1}</td>
                    <td>${entity.name}</td>
                    <td>${(entity.strategy.title)!}</td>
                    <td>${entity.sequence}</td>
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
                <h4 class="modal-title">新增攻略分类</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/strategyCatalog/saveOrUpdate.do" method="post" id="editForm"
                      enctype="multipart/form-data">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <label for="name" class="col-sm-4 control-label">分类名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-4 control-label">所属攻略：</label>
                        <div class="col-sm-6">
                            <select id="formStrategySelect" class="form-control" autocomplete="off" name="strategy.id">
                                <#list strategies as strategy>
                                    <option value="${strategy.id}">${strategy.title}</option>
                                </#list>
                            </select>
                            <script>
                                $("#formStrategySelect").val(${(qo.strategyId)!});
                            </script>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">序号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="sequence">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">状态：</label>
                        <div class="col-sm-6">
                            <select id="auditState" class="form-control" autocomplete="off" name="state">
                                <option value="true">启用</option>
                                <option value="false">禁用</option>
                            </select>
                        </div>
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