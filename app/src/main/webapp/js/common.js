//获取url上的请求参数
function getParams() {
    //获取问号及问号后面的内容
    var url = window.location.search;//?id=17&name=1
    var params = new Object();
    if (url.indexOf("?") != -1) {
        //截取问号后面的内容,再使用&分割多个属性
        var arr = url.substr(1).split("&");//id=17&name=1
        for (var i = 0; i < arr.length; i++) {
            //使用=分割为keyvalue
            var keyValue = arr[i].split("=");
            params[keyValue[0]] = keyValue[1];
        }
    }
    return params;
}


function getHref(item, value) {
    var url = $(item).data("href");
    $(item).attr('href', url + value);
}


//-------------------树结构的js实现--------------------//
/*pId---> parentId(父节点的ID)*/
var zNodes = [
    {id: 0, pId: -1, comment: "Aaaa"},
    {id: 1, pId: 0, comment: "A"},
    {id: 11, pId: 1, comment: "A1"},
    {id: 12, pId: 1, comment: "A2"},
    {id: 13, pId: 1, comment: "A3"},
    {id: 2, pId: 0, comment: "B"},
    {id: 21, pId: 2, comment: "B1"},
    {id: 22, pId: 2, comment: "B2"},
    {id: 23, pId: 2, comment: "B3"},
    {id: 3, pId: 0, comment: "C"},
    {id: 31, pId: 3, comment: "C1"},
    {id: 32, pId: 3, comment: "C2"},
    {id: 33, pId: 3, comment: "C3"},
    {id: 34, pId: 31, comment: "x"},
    {id: 35, pId: 31, comment: "y"},
    {id: 36, pId: 31, comment: "z"},
    {id: 37, pId: 36, comment: "z1123"},
    {id: 38, pId: 37, comment: "z123123123"},
    {id: 39, pId: 38, comment: "lwww"},
];

function treeMenu(a) {
    this.tree = a || [];
    this.groups = {};
};
treeMenu.prototype = {
    /*初始化树结构
    * param:pid 第一层从那个数字开始*/
    init: function (pid) {
        this.group();
        return this.getDom(this.groups[pid]);
    },
    /*功能：将所有父节点相同的数据放在同一个数组中*/
    group: function () {
        for (var i = 0; i < this.tree.length; i++) {
            if (this.groups[this.tree[i].pId]) {
                this.groups[this.tree[i].pId].push(this.tree[i]);
            } else {
                this.groups[this.tree[i].pId] = [];
                this.groups[this.tree[i].pId].push(this.tree[i]);

            }
        }
        // console.log(this.groups);
    },
    getDom: function (a) {
        if (!a) {
            return ''
        }
        var html = '\n<ul style="list-style-type:none;">\n';
        for (var i = 0; i < a.length; i++) {
            html += '<li><a href="#">' + a[i].comment + '</a>';
            html += this.getDom(this.groups[a[i].id]);
            html += '</li>\n';
        }
        ;
        html += '</ul>\n';
        return html;
    }
};
// var html = new treeMenu(zNodes).init(0);
var html = new treeMenu(zNodes).init(0);


//----------------格式化时间---------------
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
/*
<ul class="clearfix" id="j-nav-li">
    <li><a href="/mdd/" class="i1"><i></i><br>找攻略</a></li>
<li><a href="/note/" class="i5"><i></i><br>看游记</a></li>
<li><a href="/wenda/" class="i7"><i></i><br>问达人</a></li>
<li><a href="/together/" class="i6"><i></i><br>结伴</a></li>
<li><a href="/hotel/" class="i2"><i></i><br>酒店</a></li>
<li><a href="/sales/" class="i3"><i></i><br>旅行商城</a></li>
<li><a href="/flight/" class="i4"><i></i><br>机票</a></li>
<li><a href="/localdeals/" class="i8"><i></i><br>当地玩乐</a></li>
</ul>*/
