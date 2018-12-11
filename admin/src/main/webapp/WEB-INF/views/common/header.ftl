<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="/css/core.css" type="text/css" />
<script type="text/javascript" src="/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="/jquery/jquery.twbsPagination.min.js"></script>
<script type="text/javascript" src="/jquery/plugins/jquery.form.js"></script>
<script type="text/javascript" src="/jquery/plugins/jquery.bootstrap.min.js"></script>

<script>
    $(function () {
        //修改确认框中的按钮文本
        $.messager.model={
            ok:{text:"确定"},
            cancel:{text:"取消"}
        }

        $(".btn-delete").click(function () {
            //获取删除按钮上绑定的url
            var url = $(this).data("url");
            //弹出确认框
            $.messager.confirm("温馨提示","亲,您确定要删除当前数据吗?",function () {
                //发送删除的请求
                $.get(url,function (data) {
                    successAlert(data);
                })
            })
        })
    });


    function successAlert(data) {
        if(data.success){
            $.messager.alert("温馨提示","操作成功,2S之后关闭");
            setTimeout(function () {
                window.location.reload();
            },2000);
        }
    }
</script>
