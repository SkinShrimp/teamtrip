(function (jQuery) {
    // 把定义变量函数之类作为局部

    // 定义默认值，在方法里面定义一个一系列默认值
    var defaults = {
        first:'首页',
        prev:'上一页',
        next:'下一页',
        last:'尾页',
        totalPages: 1, // 总页数
        visiblePages: 1, // 可见页码
        startPage: 1,// 当前页
        onPageClick: function (event, page){ }
    };

    // 给所有 jQuery 添加方法
    jQuery.prototype.myPagination = function (options) {
        // console.log(this); // this 代表 jQuery 对象
        options = $.extend(defaults, options); // 后面同样属性值，会覆盖前面

        // console.log(options);

        var minPages = Math.min(options.visiblePages, options.totalPages);

        var $ul = this; // ul 元素，给添加元素

        $ul.append('<li class="first"><a href="#">' + options.first + '</a></li>');
        $ul.append('<li class="prev"><a href="#">' + options.prev + '</a></li>');
        for(var i = 1; i <= minPages; i++){
            $ul.append('<li class="page"><a href="#">' + i +'</a></li>')
        }
        $ul.append('<li class="next"><a href="#">' + options.next + '</a></li>');
        $ul.append('<li class="last"><a href="#">' + options.last + '</a></li>');

        /**
         *  li 加class active
         *  根据当前页判断下，有些页面是点击不了 加了一个class disabled
         */

        $ul.on('click', 'li', function (event) { // 给所有 li 添加点击事件
            var page = 1;
            if($(this).hasClass('first')){
                page = 1;
            }else if($(this).hasClass('prev')){
                page = options.startPage - 1;
            }else if($(this).hasClass('next')){
                page = options.startPage + 1;
            }else if($(this).hasClass('last')){
                page = options.totalPages;
            }else{
                page = $(this).find('a').html();
            }
            options.onPageClick(event, page); // 执行使用插件传递过来函数
        });

    };





    /**
     给 Jquery 对象加一个方法 twbsPagination，接收一个参数

     若传了对应值，就会把默认值替换掉
     根据总页数，动态添加 li
     为 所有 li 添加点击事件 点了就会触发 我们传过去的函数 有这个样式 active disable 样式不可以点
     */



})(jQuery); // 定义一个函数并传参调用