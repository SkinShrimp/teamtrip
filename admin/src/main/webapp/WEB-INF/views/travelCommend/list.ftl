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
            $(".inputBtn").click(function () {
                var json = $(this).data('json');
                //回显id隐藏域
                $("input[name='id']").val(json.id);
                //标题
                $("input[name='title']").val(json.title);
                $("input[name='subTitle']").val(json.subTitle);
                //推荐类型
                $("select[name='type']").val(json.type);
                //封面
                $("#coverUrl").attr('src',json.coverUrl);//给人看
                $("input[name='coverUrl']").val(json.coverUrl);//提交表单

                console.log(json.schedule);
                //推荐时间安排
                $("input[name='schedule']").val(json.schedule);

                //设置a连接地址
                $(".showTravel").attr('href','/travel/releaseList.do?travelId='+json.travelId);

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
            <#assign currentMenu="travelCommend"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">游记推荐管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/travelCommend/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label>关键字</label>
                    <input class="form-control" type="text" name="keyword" value="">
                </div>
                <div class="form-group">
                    <select id="type" class="form-control" autocomplete="off" name="type" >
                        <option value="-1">全部</option>
                        <option value="1">每周推荐</option>
                        <option value="2">每月推荐</option>
                        <option value="3">攻略推荐</option>
                    </select>
                    <script>
                        $("#type").val(${(qo.type)!});
                    </script>
                    <button id="query" type="submit" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                </div>
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>封面</th>
                    <th>标题</th>
                    <th>副标题</th>
                    <th>推荐时间安排</th>
                    <th>推荐类型</th>
                </tr>

                </thead>
                <#list pageInfo.list as entity>
                <tr>
                    <td>${entity_index + 1}</td>
                    <td><img src="${entity.coverUrl}" width="30px"/></td>
                    <td>${entity.title}</td>
                    <td>${entity.subTitle!}</td>
                    <td>${entity.schedule?string('yyyy-MM-dd')}</td>
                    <td>${entity.typeName}</td>
                    <td>
                        <a href="javascript:void(0);" class="inputBtn" data-json='${entity.json}'>修改</a>
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
                <h4 class="modal-title">新增游记推荐</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/travelCommend/saveOrUpdate.do" method="post" id="editForm" enctype="multipart/form-data">
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
                    <div class="form-group" style="text-align: center">
                        <a  class="showTravel" target="_blank" >点击查看游记文章明细</a>
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