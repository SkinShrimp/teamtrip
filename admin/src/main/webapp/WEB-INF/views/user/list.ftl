<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼骡窝窝后台管理系统</title>
    <#include "../common/header.ftl" >

  
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#assign currentMenu="user"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">平台用户列表</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/user/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label for="keyword">关键字:</label>
                    <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!}"
                           placeholder="请输入姓名/邮箱">
                </div>
                <input type="submit" id="btn_query" class="btn btn-default" value="查询">
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>邮箱</th>
                    <th>昵称</th>
                    <th>地区</th>
                    <th>头像</th>
                    <th>性别</th>
                    <th>签名</th>
                </tr>
                </thead>
                <#list pageInfo.list as entity>
                <tr>
                    <td>${entity_index + 1}</td>
                    <td>${entity.email}</td>
                    <td>${entity.nickName}</td>
                    <td>${entity.place!}</td>
                    <td><img src="${entity.headImgUrl}" width="30px"/></td>
                    <td>${entity.genderName}</td>
                    <td>${entity.sign!}</td>
                </tr>
                </#list>
            </table>
            <#include "../common/page.ftl" />
        </div>
    </div>
</div>
</body>
</html>