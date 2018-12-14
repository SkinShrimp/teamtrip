<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼骡窝窝后台管理系统</title>
    <#include "../common/header.ftl" >

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
                var json = $(this).data("json");
                var authorId = json.authorId;
                console.log("authorId:"+authorId);
                //通知用户
                $.post("/notice/save.do",{"travel.id":id,"user.id":authorId,flag:state},function (data) {
                });
                //改变游记状态
                $.post('/travel/changeState.do',{id:id,state:state},function (data) {
                    window.location.reload();
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
            <#assign currentMenu="travel"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">待审核游记列表</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/travel/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <select id="auditState" class="form-control" autocomplete="off" name="state" >
                        <option value="-2">全部</option>
                        <option value="1">待审核</option>
                        <option value="-1">已拒绝</option>
                    </select>
                    <script>
                        $("#auditState").val(${(qo.state)!});
                    </script>
                    <button id="query" type="submit" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                </div>

            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>标题</th>
                    <th>封面</th>
                    <th>地点</th>
                    <th>作者</th>
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
                        <td>${entity.stateName}</td>
                        <td>
                            <a href="javascript:void(0);" data-state="2"  data-id="${entity.id}" class="changeStateBtn" data-json='${entity.json}'>发布</a>
                        </td>
                        <td>
                            <a href="javascript:void(0);" data-state="-1" data-id="${entity.id}" class="changeStateBtn" data-json='${entity.json}'>拒绝</a>
                        </td>
                        <td>
                            <a href="javascript:void(0);" class="lookBtn" data-id="${entity.id}">查看文章</a>
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


</body>
</html>