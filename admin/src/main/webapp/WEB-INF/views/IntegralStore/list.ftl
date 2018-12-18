<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼骡窝窝后台管理系统</title>
    <#include "../common/header.ftl" >
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/ckeditor/ckeditor.js"></script>
    <script>
        $(function () {
            //添加修改按钮
            $(".btn-input").click(function () {
                //清除表单数据
                $("#editForm input,select").val("");
                //清除富文本编辑器
                ck.setData("");

                var json = $(this).data('json');
                if(json) {
                    //回显id隐藏域
                    $("input[name='id']").val(json.id);
                    $("input[name='name']").val(json.name);
                    $("#picture").attr('src', json.picture);
                    $("input[name='picture']").val(json.picture);
                    $("input[name='money']").val(json.money);
                    $("input[name='sellcount']").val(json.sellcount);

                    ck.setData(json.introduce);
                    console.log(json.introduce);
                    $("#goodsType").val(json.goodsTypeId);

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

            //删除按钮
            $(".btn-delete").click(function () {
                var json = $(this).data('json');
                console.log(json);
                $.post("/goods/deleteById.do",{id:json.id},function () {
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
            <#assign currentMenu="goods"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">积分商城商品管理管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/goods/list.do" method="post">
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
                    <th>商品名称</th>
                    <th>商品分类</th>
                    <th>商品图片</th>
                    <th>商品价格</th>
                    <th>商品销量</th>
                    <th>操作</th>
                </tr>

                </thead>
                <#list pageInfo.list as entity>
                <tr>
                    <td>${entity_index + 1}</td>
                    <td>${entity.name}</td>
                    <td>${entity.type.goodsType}</td>
                    <td><img src="${entity.picture}" width="50px"/></td>
                    <td>${entity.money}</td>
                    <td>${entity.sellcount}</td>
                    <td>
                        <a href="javascript:void(0);" class="btn-input" data-json='${entity.json}'>修改</a>
                    </td>
                    <td>
                        <a href="javascript:void(0);" class="btn-delete" data-json='${entity.json}'>删除</a>
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
                <h4 class="modal-title">新增销售商品</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/goods/saveOrUpdate.do" method="post" id="editForm" enctype="multipart/form-data">
                    <input type="hidden" name="id" >
                    <div class="form-group">
                        <label  class="col-sm-4 control-label">商品名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"  name="name" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">商品图片：</label>
                        <div class="col-sm-6">
                        <img id="picture" width="100%"/>
                        <input type="file" class="form-control" name="file" >
                        <input type="hidden" class="form-control" name="picture" >
                    </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">商品类型：</label>
                        <div class="col-sm-6">
                            <select id="goodsType" class="form-control" autocomplete="off" name="type.id">
                                <#list goodsTypes as goodsType>
                                    <option value="${goodsType.id}">${goodsType.goodsType}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-4 control-label">商品价格：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"  name="money" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">商品销量：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="sellcount" >
                        </div>
                    </div>

                    <div class="form-group">
                        <textarea name="introduce" id="editor" rows="10" cols="80">
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
        </div>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>