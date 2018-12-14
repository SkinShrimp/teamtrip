<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼骡窝窝后台管理系统</title>
    <#include "../common/header.ftl" >
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <style>
        .modal-body img{
            width: 100%;
        }
    </style>

    <script>
        $(function () {
            //查看文章按钮
            $(".lookBtn").click(function () {
                var id = $(this).data("id");
                //发送ajax请求查询游记内容
                $.get('/travel/getContentById.do',{id:id},function (data) {
                    $(".modal-body").html(data.content);
                })

                $("#contentModal").modal('show');
            })

            //发布/拒绝按钮
            $(".changeStateBtn").click(function () {
                var id = $(this).data("id");
                var state = $(this).data("state");
                var json = $(this).data('json');
                $.post('/travel/changeState.do', {id:id,state:state},function (data) {
                    window.location.reload();
                })
            })

            //推荐按钮
            $(".commendBtn").click(function () {
                var json = $(this).data('json');
                //回显游记id的隐藏域
                $("input[name='travel.id']").val(json.id);
                //标题
                $("input[name='title']").val(json.title);
                //封面
                $("#coverUrl").attr('src',json.coverUrl);//给人看
                $("input[name='coverUrl']").val(json.coverUrl);//提交表单

                $("#inputModal").modal('show');

                //通知用户
                $.post("/notice/save.do",{"travel.id":json.id,"user.id":json.authorId,flag:1},function (data) {
                });
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
            <#assign currentMenu="releaseList"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">已发布游记列表</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/travel/releaseList.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>标题</th>
                    <th>封面</th>
                    <th>地点</th>
                    <th>作者</th>
                    <th>发布时间</th>
                    <th>状态</th>
                </tr>

                </thead>
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index + 1}</td>
                        <td>${entity.title}</td>
                        <td><img src="${entity.coverUrl}" width="30px"/></td>
                        <td>${entity.place.name}</td>
                        <td>${entity.author.nickName}</td>
                        <td>${entity.releaseTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <td>${entity.stateName}</td>
                        <td>
                            <a href="javascript:void(0);" data-state="1" data-id="${entity.id}" data-json='${entity.json}' class="changeStateBtn">取消发布</a>
                        </td>
                        <td>
                            <a href="javascript:void(0);" class="lookBtn" data-id="${entity.id}">查看文章</a>
                        </td>
                        <td>
                            <a href="javascript:void(0);" class="commendBtn" data-json='${entity.json}' >推荐</a>
                        </td>
                    </tr>
                </#list>
            </table>
            <#include "../common/page.ftl" />
        </div>
    </div>
</div>


<#--查看文章内容模态框-->
<div class="modal fade" id="contentModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">文章内容</h4>
            </div>
            <div class="modal-body">
                    xxxxxxxxx
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<#--编辑模态框-->
<div class="modal fade" id="inputModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增游记推荐</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/travelCommend/saveOrUpdate.do" method="post" id="editForm" enctype="multipart/form-data">
                    <input type="hidden" name="travel.id" >
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
                            <img id="coverUrl"/>
                            <input type="file" class="form-control" name="file" >
                            <input type="hidden" class="form-control" name="coverUrl" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">推荐时间：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="schedule" name="schedule" onclick="WdatePicker()" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">推荐类型：</label>
                        <div class="col-sm-6">
                            <select id="auditState" class="form-control" autocomplete="off" name="type" >
                                <option value="1">每周推荐</option>
                                <option value="2">每月推荐</option>
                                <option value="3">攻略推荐</option>
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