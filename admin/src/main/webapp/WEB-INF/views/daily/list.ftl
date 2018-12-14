<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>骡窝窝日报管理</title>
    <#include "../common/header.ftl">
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/ckeditor/ckeditor.js"></script>
    <style>
        .modal-body img {
            width: 100%;
        }

        .table-font {
            font-size: 13px;
        }
    </style>

    <script type="text/javascript" src="../../../js/ckeditor/ckeditor.js"></script>

    <script>
        $(function () {
            //日报按钮
            $('.input-Btn').click(function () {

                //新增之前情况内容
                $('#editForm input,select').val("");
                $('#coverUrl').attr('src', ' ');
                ck.setData(" ");

                var json = $(this).data('json');
                if (json) {
                    //回显id 隐藏域
                    $('input[name="id"]').val(json.id);
                    //标题
                    $('input[name="title"]').val(json.title);
                    //副标题
                    $('input[name="subTitle"]').val(json.subTitle);
                    //封面
                    $('#coverUrl').attr('src', json.coverUrl);
                    $('input[name="coverUrl"]').val(json.coverUrl);
                    //状态
                    $('select[name="state"]').val(json.state);


                    //回显日报文章内容
                    $.get('/daily/getContentById.do',{id:json.id},function (data) {
                        //把内容放到富文本编辑器
                        ck.setData(data.content);
                    })
                }

                $('#inputModal').modal('show');

            });


            //提交表单
            // 使用JqueryForm方式异步提交,保存成功后刷新当前页面
            $('.btn-submit').click(function () {
                //获取富文本编辑器的内容,设置到表单组件中
                var data = ck.getData();
                $('#editor').html(data);

                $('#editForm').ajaxSubmit(function (data) {
                    if (data.success) {
                        //原来的alert太丑,现在使用Jquery.bootstrap的alert弹出消息框
                        $.messager.alert("保存成功,2秒后关闭");
                        //定时器,设置提交后2秒自动刷新
                        setTimeout(function () {
                            //刷新当前页面
                            window.location.reload();
                        }, 2000)
                    }
                })
            });


            //上传图片
            $('#imgBtn').click(function () {
                $('#fileInput').click()
            });

            //文本框change事件
            $("#fileInput").change(function () {

                if ($(this).val()) {
                    //上传图片
                    $('#fileForm').ajaxSubmit(function (data) {
                        //回显到img
                        $('#coverUrl').attr('src', data.url);
                        //回显到隐藏域
                        $('[name=coverUrl]').val(data.url);
                    })
                }
            })

        })
    </script>
</head>
<body>

<form id="fileForm" action="/daily/saveOrUpdate.do" method="post" enctype="multipart/form-data">
    <input type="file" name="file" id="fileInput" style="display: none">
</form>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl" >
    <div class="row">
        <div class="col-sm-3">
            <#assign currentMenu="daily"/>
            <#include "../common/menu.ftl" >
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">日报文章管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/daily/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">

                <div class="form-group">
                    <label for="keyword">关键字:</label>
                    <input type="text" class="form-control" name="keyword" value="${(qo.keyword)!}"
                           placeholder="请输入标题/副标题">
                </div>

                <div class="form-group">
                    <select id="auditState" class="form-control" autocomplete="off" name="state" >
                        <option value="-1">全部</option>
                        <option value="1" >普通</option>
                        <option value="2" >禁用</option>
                    </select>
                </div>

                <button id="query" type="submit" class="btn btn-success"><i class="icon-search"></i> 查询</button>

                <a href="javascript:" class="btn btn-success input-Btn">新增日报</a>
                <script>
                    $("#auditState").val(${(qo.state)!});
                </script>
            </form>
            <table class="table table-font table-striped table-hover">
                <tr>
                    <th>序号</th>
                    <th>标题</th>
                    <th>封面</th>
                    <th>副标题</th>
                    <th>发布时间</th>
                    <th>状态</th>
                    <th></th>
                </tr>
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${(entity_index + 1)!}</td>
                        <td>${(entity.title)!}</td>
                        <td>
                            <img src="${(entity.coverUrl)!}" width="100xp">
                        </td>
                        <td>${(entity.subTitle)!}</td>
                        <td>${(entity.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                        <td>${(entity.stateName)!}</td>
                        <td>
                            <a href="javascript:void(0);" data-json='${(entity.json)!}' class="input-Btn">修改</a>
                        </td>
                    </tr>
                </#list>
            </table>

            <#include "../common/page.ftl">
        </div>
    </div>
</div>


<!-- 模态框 -->
<div class="modal fade" id="inputModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增/编辑</h4>
            </div>
            <div class="modal-body">

            <#---->
                <form class="form-horizontal" action="/daily/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title" placeholder="标题">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">副标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="subTitle" placeholder="副标题">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-4 control-label">封面</label>
                        <div class="col-sm-6">
                            <img id="coverUrl" width="200px"/>
                            <input type="hidden" name="coverUrl">
                            <button type="button" id="imgBtn">选择图片</button>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-4 control-label">状态</label>
                        <div class="col-sm-6">
                            <select class="form-control" autocomplete="off" name="state">
                                <option value="">请选择</option>
                                <option value="1">普通</option>
                                <option value="2">禁用</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <textarea id="editor" name="content.content" class="ckeditor" cols="10" rows="10">
                      </textarea>
                        <script>
                            var ck = CKEDITOR.replace('editor');
                        </script>
                    </div>

                </form>

            <#---->

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary btn-submit">提交</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>