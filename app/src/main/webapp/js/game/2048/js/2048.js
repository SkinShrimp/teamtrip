/**
 * Created by Dean on 2018/3/5.
 */
$(function(){
    /*初始化*/
    $(document).ready(function(){
        defaultSet();
        init();
    });

    // 给2048游戏板自适应高度
    $(window).resize(function() {
        defaultSet();
    });
    function defaultSet(){
        var mbw = $('.mainbox').width();       //并不是重要属性，偷懒用,哈哈
        $('.mainbox').height(mbw).css({"padding":(mbw/48)+"px"});/*,"margin":(-mbw/1.92)+"px 0 0 "+(-mbw/1.92)+"px"*/
        $('.scroebox').css({"width":(mbw/2.5)+"px"});
        $('.manubox').width(mbw*.9);
        // $('.scroebox1').css({"margin-left:":-(mbw/48)+"px"});
        $('.cell').css({"line-height":+$('.cell').height()+"px"});
    }

    //随机数字和随机块位置
    var randomNum,randomPos;
    var left ="left";                           //并不是重要属性，偷懒用,哈哈
    var up ="up";                               //并不是重要属性，偷懒用,哈哈
    var right ="right";                         //并不是重要属性，偷懒用,哈哈
    var down ="down";                           //并不是重要属性，偷懒用,哈哈

    //新游戏初始设置
    function init(){
        $('.cell').html('').removeClass('step');
        $('.score1').html('0');
        getRP();
        getRP();
        getClass();
    }
    $('.newGame').click(function(){init()});

    //随机生成2或者4在16格之一
    function getRP(direction){
        randomNum=Math.random()<0.67?2:4;
        randomPos=$('.cell').eq(parseInt((Math.random()*100)/6.25));
        if(randomPos.html()!=""){
            return getRP(direction);
        }else{
            randomPos.html(randomNum);
        }
    }

    //随机生成1个或2个数字
    function refreshPR(){
        if(checkGame()){
            alert('game over');
            return
        }else{
            setTimeout(function(){
                if(Math.random()>.67){
                    getRP();
                    setTimeout(function(){getRP()},5)
                }else{
                    getRP()
                }
                getClass();
            },50)
        }
    }

    //更新样式
    function getClass(){
        setTimeout(function(){
            for(var i=0;i<16;i++){
                if($('.cell').eq(i).html() >0){
                    $('.cell').eq(i).addClass('step')
                }
            }
        },10)
    }

    //滑动处理
    var mybody = $('.mainbox').get(0);
    var startX, startY, moveEndX, moveEndY, X, Y, isScrolling;
    mybody.addEventListener('touchstart', function(e) {
        e.preventDefault();
        startX = e.touches[0].pageX;
        startY = e.touches[0].pageY;
    });
    mybody.addEventListener('touchend', function(e) {
        e.preventDefault();
        moveEndX = e.changedTouches[0].pageX;
        moveEndY = e.changedTouches[0].pageY;
        X = moveEndX - startX;
        Y = moveEndY - startY;
        isScrolling = Math.abs(X) < Math.abs(Y) ? 1:0;    //isScrolling为1时，表示纵向滑动，0为横向滑动
        if ( isScrolling < 1 && X < 0 ) {
            cellsMove(left);
        }else if ( isScrolling > 0 && Y < 0 ) {
            cellsMove(up);
        }else if ( isScrolling < 1 && X > 0 ) {
            cellsMove(right);
        }else if ( isScrolling > 0 && Y > 0) {
            cellsMove(down);
        }
    });
    
    //判断键盘方向事件
    $(document).keydown(function (event){
        if(event.keyCode == 37){
            cellsMove(left);
        }else if(event.keyCode == 38){
            cellsMove(up);
        }else if(event.keyCode == 39){
            cellsMove(right);
        }else if(event.keyCode == 40){
            cellsMove(down);
        }
    });

    //统一移动结果动画
    function animateCells(fromtemp,temp,tmin,tadd,val){
        //fromtemp 移动开始块索引
        //temp 移动替换块索引
        //tmin，tadd 替换的样式
        //val 判断是否叠加和具体叠加值
        $('.mainbox>div').eq(fromtemp).removeClass(tadd).addClass(tmin);
        $('.mainbox>div').eq(temp).removeClass(tmin).addClass(tadd);
        if(val>0){
            $('.mainbox>div').eq(fromtemp).html(val*2);
            $('.mainbox>div').eq(temp).hide();
            setTimeout(function(){
                $('.mainbox>div').eq(temp).removeClass('step').html("").show();
            },10);
            $('.score1').html(-(-$('.score1').html()-val));
            $('.score1').after("<div class='fadescore scoreadd"+parseInt(Math.random()*9.999)+"'>+"+val+"</div>");
            if(-$('.score1').html()<-$('.score2').html()){
                $('.score2').html($('.score1').html()).after("<div class='fadescore scoreadd"+parseInt(Math.random()*9.999)+"'>+"+val+"</div>");
                setTimeout(function(){
                    $('.markdel2').prev().remove();
                },1000)
            }
            setTimeout(function(){
                $('.markdel1').prev().remove();
            },1000)

        }
    }

    //移动循环。。。。这个部分最复杂了=.=
    function cellsMove(direction){
    var markNum = 0,fromtemp,temp,l;
        //左移和上移都是从左上角开始循环
        if(direction == left || direction == up){
            for(var i=1;i<5;i++){
                markNum = 0;
                for(var j=1;j<5;j++){
                    l=j+1;
                    //左移具体循环判断
                    if(direction == left){
                        if($('.row'+i+'.col'+j).html()==""){
                            for(var k=j+1;k<5;k++){
                                if($('.row'+i+'.col'+k).html()!=""){
                                    l=j-1;
                                    if(markNum == $('.row'+i+'.col'+k).html()){
                                        fromtemp = $('.row'+i+'.col'+k).index();
                                        temp = $('.row'+i+'.col'+l).index();
                                        animateCells(fromtemp,temp,'col'+l,'col'+k,markNum);
                                        markNum = 0;
                                    }else{
                                        markNum = $('.row'+i+'.col'+k).html();
                                        fromtemp = $('.row'+i+'.col'+k).index();
                                        temp = $('.row'+i+'.col'+j).index();
                                        animateCells(fromtemp,temp,'col'+j,'col'+k,0);
                                        break;
                                    }
                                }
                            }
                        }else if($('.row'+i+'.col'+j).html() == $('.row'+i+'.col'+l).html()){
                            markNum = $('.row'+i+'.col'+j).html();
                            fromtemp = $('.row'+i+'.col'+l).index();
                            temp = $('.row'+i+'.col'+j).index();
                            animateCells(fromtemp,temp,'col'+j,'col'+l,markNum);
                            markNum = 0;
                        }else{
                            markNum = $('.row'+i+'.col'+j).html();
                        }
                    }
                    //上移具体循环判断
                    else{
                        if($('.row'+j+'.col'+i).html()==""){
                            for(var k=j+1;k<5;k++){
                                if($('.row'+k+'.col'+i).html()!=""){
                                    l=j-1;
                                    if(markNum == $('.row'+k+'.col'+i).html()){
                                        fromtemp = $('.row'+k+'.col'+i).index();
                                        temp = $('.row'+l+'.col'+i).index();
                                        animateCells(fromtemp,temp,'row'+l,'row'+k,markNum);
                                        markNum = 0;
                                    }else{
                                        markNum = $('.row'+k+'.col'+i).html();
                                        fromtemp = $('.row'+k+'.col'+i).index();
                                        temp = $('.row'+j+'.col'+i).index();
                                        animateCells(fromtemp,temp,'row'+j,'row'+k,0);
                                        break;
                                    }
                                }
                            }
                        }else if($('.row'+j+'.col'+i).html() == $('.row'+l+'.col'+i).html()){
                            markNum = $('.row'+j+'.col'+i).html();
                            fromtemp = $('.row'+l+'.col'+i).index();
                            temp = $('.row'+j+'.col'+i).index();
                            animateCells(fromtemp,temp,'row'+j,'row'+l,markNum);
                            markNum = 0;
                        }else{
                            markNum = $('.row'+j+'.col'+i).html();
                        }
                    }
                }
            }

        }
        //右移和下移都是从右下角开始循环
        else{
            for(var i=4;i>0;i--){
                markNum = 0;
                for(var j=4;j>0;j--){
                    l=j-1;
                    //具体右移循环判断
                    if(direction == right){
                        if($('.row'+i+'.col'+j).html()==""){
                            for(var k=j-1;k>0;k--){
                                if($('.row'+i+'.col'+k).html()!=""){
                                    l=j+1;
                                    if(markNum == $('.row'+i+'.col'+k).html()){
                                        fromtemp = $('.row'+i+'.col'+k).index();
                                        temp = $('.row'+i+'.col'+l).index();
                                        animateCells(fromtemp,temp,'col'+l,'col'+k,markNum);
                                        markNum = 0;
                                    }else{
                                        markNum = $('.row'+i+'.col'+k).html();
                                        fromtemp = $('.row'+i+'.col'+k).index();
                                        temp = $('.row'+i+'.col'+j).index();
                                        animateCells(fromtemp,temp,'col'+j,'col'+k,0);
                                        break;
                                    }
                                }
                            }
                        }else if($('.row'+i+'.col'+j).html() == $('.row'+i+'.col'+l).html()){
                            markNum = $('.row'+i+'.col'+j).html();
                            fromtemp = $('.row'+i+'.col'+l).index();
                            temp = $('.row'+i+'.col'+j).index();
                            animateCells(fromtemp,temp,'col'+j,'col'+l,markNum);
                            markNum = 0;
                        }else{
                            markNum = $('.row'+i+'.col'+j).html();
                        }
                    }
                    //具体下移循环判断
                    else{
                        if($('.row'+j+'.col'+i).html()==""){
                            for(var k=j-1;k>0;k--){
                                if($('.row'+k+'.col'+i).html()!=""){
                                    l=j+1;
                                    if(markNum == $('.row'+k+'.col'+i).html()){
                                        fromtemp = $('.row'+k+'.col'+i).index();
                                        temp = $('.row'+l+'.col'+i).index();
                                        animateCells(fromtemp,temp,'row'+l,'row'+k,markNum);
                                        markNum = 0;
                                    }else{
                                        markNum = $('.row'+k+'.col'+i).html();
                                        fromtemp = $('.row'+k+'.col'+i).index();
                                        temp = $('.row'+j+'.col'+i).index();
                                        animateCells(fromtemp,temp,'row'+j,'row'+k,0);
                                        break;
                                    }
                                }
                            }
                        }else if($('.row'+j+'.col'+i).html() == $('.row'+l+'.col'+i).html()){
                            markNum = $('.row'+j+'.col'+i).html();
                            fromtemp = $('.row'+l+'.col'+i).index();
                            temp = $('.row'+j+'.col'+i).index();
                            animateCells(fromtemp,temp,'row'+j,'row'+l,markNum);
                            markNum = 0;
                        }else{
                            markNum = $('.row'+j+'.col'+i).html();
                        }
                    }
                }
            }
        }
        setTimeout(function(){refreshPR()},50);
    }

    //检查是否gameover
    function checkGame(){
        for(var i=0;i<16;i++){
            if($('.cell').eq(i).html()==""){
                return false
            }
        }
        return true;
        // return init();
    }
});