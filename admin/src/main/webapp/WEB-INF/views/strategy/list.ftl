<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼骡窝窝后台管理系统</title>
    <#include "../common/header.ftl" >
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script>
        $(function () {
            //推荐按钮
            $(".btn-input").click(function () {
                var json = $(this).data('json');
                if(json){
                    //回显id隐藏域
                    $("input[name='id']").val(json.id);
                    $("input[name='title']").val(json.title);
                    $("input[name='subTitle']").val(json.subTitle);
                    $("select[name='state']").val(json.state);
                    $("select[name='place.id']").val(json.placeId);

                    $("#coverUrl").attr('src',json.coverUrl);
                    $("input[name='coverUrl']").val(json.coverUrl);

                }

                $("#inputModal").modal('show');
            })


            //提交表单
            $(".btn-submit").click(function () {
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

            
        })
    </script>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#assign currentMenu="strategy"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">大攻略管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/strategy/list.do" method="post">
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
                    <th>封面</th>
                    <th>攻略标题</th>
                    <th>副标题</th>
                    <th>所属地区</th>
                    <th>状态</th>
                </tr>

                </thead>
                <#list pageInfo.list as entity>
                <tr>
                    <td>${entity_index + 1}</td>
                    <td><img src="${entity.coverUrl}" width="100px"/></td>
                    <td>${entity.title}</td>
                    <td>${entity.subTitle!}</td>
                    <td>${entity.place.name}</td>
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
                <h4 class="modal-title">新增大攻略</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/strategy/saveOrUpdate.do" method="post" id="editForm" enctype="multipart/form-data">
                    <input type="hidden" name="id" >
                    <div class="form-group">
                        <label for="name" class="col-sm-4 control-label">标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="title" name="title" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-4 control-label">副标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="subTitle" name="subTitle" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">封面：</label>
                        <div class="col-sm-6">
                            <img id="coverUrl" width="100%"/>
                            <input type="file" class="form-control" name="file" >
                            <input type="hidden" class="form-control" name="coverUrl" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">所属地区：</label>
                        <div class="col-sm-6">
                            <select id="auditState" class="form-control" autocomplete="off" name="place.id" >
                                <#list regions as region>
                                    <option value="${region.id}">${region.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">状态：</label>
                        <div class="col-sm-6">
                            <select id="auditState" class="form-control" autocomplete="off" name="state" >
                                <option value="0">普通</option>
                                <option value="1">热门</option>
                                <option value="-1">禁用</option>
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