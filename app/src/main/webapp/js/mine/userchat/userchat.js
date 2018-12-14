/*更新视图,视图上调*/
function upView(html) {
    $('.message').append(html);
    $('body').animate({scrollTop: $('.message').outerHeight() - window.innerHeight}, 200)
}

function sj() {
    return parseInt(Math.random() * 10)
}

/*发送消息*/
function send(headUrl, str, time) {
    var html = "<div class='send'><div class=\"time\">" + time + "</div><div class='msg'><img src=" + headUrl + " />" +
        "<p><i class='msg_input'></i>" + str + "</p></div></div>";
    upView(html);
}

/*接受消息*/
/*function show(headUrl, str, time) {
    var html = "<div class='show'><div class=\"time\">" + time + "</div><div class='msg'><img src=" + headUrl + " />" +
        "<p><i class='msg_input'></i>" + str + "</p></div></div>";
    upView(html);
}*/

//格式化时间
function CurentTime(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hh = date.getHours();
    var mm = date.getMinutes();
    var clock = year + "-";
    if (month < 10)
        clock += "0";
    clock += month + "-";
    if (day < 10)
        clock += "0";
    clock += day + " ";
    if (hh < 10)
        clock += "0";
    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm;
    return clock;
}
//格式化时间
function CurentTimeWithSeconds(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hh = date.getHours();
    var mm = date.getMinutes();
    var ss = date.getSeconds();
    var clock = year + "-";
    if (month < 10)
        clock += "0";
    clock += month + "-";
    if (day < 10)
        clock += "0";
    clock += day + " ";
    if (hh < 10)
        clock += "0";
    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm + ":";
    if (ss < 10) clock += "0";
    clock += ss;
    return clock;
}

//跳转到底部
function end() {
    var c = window.document.body.scrollHeight;
    window.scroll(0, c);
}

$(function () {
    //底部发送按钮的启用与禁用
    $('.footer').on('keyup','input',function(){
        if($(this).val().length>0){
            $(this).next().css('background','#114F8E').prop('disabled',true);

        }else{
            $(this).next().css('background','#ddd').prop('disabled',false);
        }
    })

    //发送者id
    var senderId = JSON.parse(sessionStorage.getItem("user")).id;

    //接收者id
    var receiverId = getParams().id;
    console.log("receiverId:"+receiverId);

    //一进入该页面就把所有标记为已读
    $.ajax({
        url: "/userchats",
        type: "put",
        async:true,
        data:{"sender.id":receiverId,"receiver.id":senderId,status:1},
        success:function (data) {
        }
    });

    $.get("/users/"+receiverId,function (data) {
        $("#sendImg").attr("src",data.headImgUrl);
        $(".receiverName").html(data.nickName);
        $(".receiverData").click(function () {
            window.location.href="/userProfiles.html?id="+receiverId;
        });
    });

    //历史数据回显,通过发送者id和接受者id获取到两者之间的信息
    $.get("/userchats/"+senderId+"/"+receiverId+"/histories",{pageSize:200},function (data) {
        $.each(data.list,function (index,ele) {
            //通过结果中发送者的id判断哪个是当前用户,是当前用户,就显示在右边
            if (ele.sender.id == senderId){
                $(".message").append('<div class="show">\n' +
                    '      <div class="time">'+ ele.sendTime+'</div>\n' +
                    '      <div class="msg">\n' +
                    '        <img src="'+ele.sender.headImgUrl+'" width="40px" alt="" />\n' +
                    '        <p><i class="msg_input"></i>'+ele.message+'</p>\n' +
                    '      </div>\n' +
                    '    </div>');
            }else{
                $(".message").append('<div class="send">\n' +
                    '      <div class="time">'+ele.sendTime+'</div>\n' +
                    '      <div class="msg">\n' +
                    '        <img src="'+ele.sender.headImgUrl+'" width="40px" alt="" />\n' +
                    '        <p><i class="msg_input"></i>'+ele.message+'</p>\n' +
                    '      </div>\n' +
                    '    </div>');
            }
        });
        end()
    });

    //给发送按钮绑定点击事件
    $("#sendBtn").click(function () {
        var message = $("[name='message']").val();
        //如果发送内容为空，结束方法，提示内容为空
        if (message == ""){
            $(document).dialog({
                type : 'notice',
                content: '<span class="info-text">内容不能为空</span>',
                autoClose: 1000,
                position: 'bottom'
            });
            return;
        }
        //保存发送内容到数据库
        $.ajax({
            url :"/userchats",
            data:{"sender.id":senderId,"receiver.id":receiverId,"message":message},
            type:"POST",
            success:function (data) {
                if (data.success){
                    location.reload();
                }
            }
        });
        //发送完后清空信息框内容
        $("[name='message']").val("");
        $("[name='message']").next().css('background', '#ddd').prop('disabled', false);
    });


    //查看这个页面的时候设置这条信息状态为已读状态
    $.ajax({
        type: "PUT",
        url: "/userchats/"+senderId+"/"+receiverId
    });
    //设置个初始时间，有消息就重新更新此时间
    var newTime = CurentTimeWithSeconds(new Date());
    //每隔两秒检查一下是否有新消息，有的话添加进去
    function getNewMessage() {
        $.ajax({
            type: "GET",
            url: "/userchats/" + receiverId + "/" + senderId + "/" + newTime,
            async: false,
            success:function (data) {
                if (data.length > 0) {
                    $.each(data, function (index, ele) {
                        send(ele.sender.headImgUrl, ele.message, CurentTime(new Date(ele.sendTime)));
                    });
                    end();
                    //如果有新的消息,就刷新时间
                    newTime = CurentTimeWithSeconds(new Date());
                }
            }
        });
    }
    window.setInterval(getNewMessage, 2000);
});