//使用jQuery发送get请求:jQuery.get(url, [data], [callback], [type])
$(function() {
	var humidity = 10;
	$("#check").click(function(){

        /*渲染按钮的点击事件*/
        Dialog.init('<input type="text" placeholder="城市名"  style="margin:8px 0;width:100%;padding:11px 8px;font-size:15px; border:1px solid #999;"/>', {
            title: '请输入查询城市名',
            button: {
                确定: function () {
                    if (this.querySelector('input').value.length >= 50) {
                        content = this.querySelector('input').value;
                        Dialog.init('你输入的内容不符合格式', 500);
                        Dialog.close(this);
                        return;
                    } else {
                        var cityName = this.querySelector('input').value;
                        var citycode=encodeURI(cityName);
                        console.debug(citycode);

                        //提交评论表单
                        $.ajax({
                            url:'http://v.juhe.cn/weather/index',
                            type:'GET',
                            data:'format=2&cityname='+citycode+'&key=8de9819651eea5d4f30bc0349975168f',
                            success:function(data) {
                                //清空已经添加的数据
                                $(".dynamic-area1 ul li p span").remove();

                                //当天天气情况
                                $("#today_temp").append("<span>"+data.result.today.temperature+"</span>");       //温度
                                $("#today_weather").append("<span>"+data.result.today.weather+"</span>");	     //天气
                                $("#today_desp").append("<span>"+data.result.today.travel_index+"</span>");      //描述
                                $("#today_wind").append("<span>"+data.result.today.wind+"</span>");              //风向
                                $("#today_advice").append("<span>"+data.result.today.dressing_advice+"</span>"); //建议

                                //当前天气情况
                                $("#current_time").append("<span>"+data.result.sk.time+"</span>");             //时间
                                $("#current_temp").append("<span>"+data.result.sk.temp+"</span>");	           //温度
                                $("#current_hum").append("<span>"+data.result.sk.humidity+"</span>");          //湿度
                                $("#current_wind").append("<span>"+data.result.sk.wind_strength+"</span>");	   //风速
                                $("#current_dir").append("<span>"+data.result.sk.wind_direction+"</span>");    //风向

                                console.log("future:"+data.result.future);
                                console.log("future:"+data.result.future[0].temperature);
                                console.log("future:"+data.result.future[0].weather);
                                console.log(data.result.future[0].week);
                                //未来七天
                                $("#day01").append("<span>"+data.result.future[0].week+","+data.result.future[0].temperature+","+data.result.future[0].weather+"</span>");
                                $("#day02").append("<span>"+data.result.future[1].week+","+data.result.future[1].temperature+","+data.result.future[1].weather+"</span>");
                                $("#day03").append("<span>"+data.result.future[2].week+","+data.result.future[2].temperature+","+data.result.future[2].weather+"</span>");
                                $("#day04").append("<span>"+data.result.future[3].week+","+data.result.future[3].temperature+","+data.result.future[3].weather+"</span>");
                                $("#day05").append("<span>"+data.result.future[4].week+","+data.result.future[4].temperature+","+data.result.future[4].weather+"</span>");
                                $("#day06").append("<span>"+data.result.future[5].week+","+data.result.future[5].temperature+","+data.result.future[5].weather+"</span>");
                                $("#day07").append("<span>"+data.result.future[6].week+","+data.result.future[6].temperature+","+data.result.future[6].weather+"</span>");

                                //语音提示
                                var msg = new SpeechSynthesisUtterance('亲爱的用户，您好，欢迎来到骡窝天气，你当前搜索的城市'+data.result.today.city);
                                window.speechSynthesis.speak(msg);
                                var msg = new SpeechSynthesisUtterance('当天的天气细节为:'+"温度"+data.result.today.temperature);
                                window.speechSynthesis.speak(msg);
                                var msg = new SpeechSynthesisUtterance('天气状况'+ data.result.today.weather);
                                window.speechSynthesis.speak(msg);
                                var msg = new SpeechSynthesisUtterance('天气描述'+ data.result.today.travel_index);
                                window.speechSynthesis.speak(msg);
                                var msg = new SpeechSynthesisUtterance('风向'+ data.result.today.wind);
                                window.speechSynthesis.speak(msg);
                                var msg = new SpeechSynthesisUtterance('建议'+ data.result.today.dressing_advice);
                                window.speechSynthesis.speak(msg);
                                var msg = new SpeechSynthesisUtterance('当天天气细节汇报完毕，下边是当前时刻的天气细节');
                                window.speechSynthesis.speak(msg);
                                var msg = new SpeechSynthesisUtterance('当前时刻'+ data.result.sk.time);
                                window.speechSynthesis.speak(msg);
                                var msg = new SpeechSynthesisUtterance('当前温度'+ data.result.sk.temp+"℃");
                                window.speechSynthesis.speak(msg);
                                var msg = new SpeechSynthesisUtterance('当前湿度'+ data.result.sk.humidity+'RH');
                                window.speechSynthesis.speak(msg);
                                var msg = new SpeechSynthesisUtterance('当前风速'+ data.result.sk.wind_strength);
                                window.speechSynthesis.speak(msg);
                                var msg = new SpeechSynthesisUtterance('当前风向'+ data.result.sk.wind_direction);
                                window.speechSynthesis.speak(msg);
                                var msg = new SpeechSynthesisUtterance('天气状况汇报完毕');
                                window.speechSynthesis.speak(msg);

                                //将拿到的城市名作为参数加入到url中
                                $("#check_a").prop("href","view.html?city="+citycode)
                                console.debug($("#check_a").prop("href"));
                            },
                            dataType: "jsonp"
                        });
                        Dialog.close(this);
                        return;
                    }
                    ;
                },
                点击关闭: function () {
                    Dialog.init('您取消了查询', 1000);
                    Dialog.close(this);
                }
            }
        });
	});
});