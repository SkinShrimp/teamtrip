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
    {id: 0, pId: -1, content: "Aaaa"},
    {id: 1, pId: 0, content: "A"},
    {id: 11, pId: 1, content: "A1"},
    {id: 12, pId: 1, content: "A2"},
    {id: 13, pId: 1, content: "A3"},
    {id: 2, pId: 0, content: "B"},
    {id: 21, pId: 2, content: "B1"},
    {id: 22, pId: 2, content: "B2"},
    {id: 23, pId: 2, content: "B3"},
    {id: 3, pId: 0, content: "C"},
    {id: 31, pId: 3, content: "C1"},
    {id: 32, pId: 3, content: "C2"},
    {id: 33, pId: 3, content: "C3"},
    {id: 34, pId: 31, content: "x"},
    {id: 35, pId: 31, content: "y"},
    {id: 36, pId: 31, content: "z"},
    {id: 37, pId: 36, content: "z1123"},
    {id: 38, pId: 37, content: "z123123123"},
    {id: 39, pId: 38, content: "lwww"},
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
        console.log(this.groups);

    },
    getDom: function (a) {
        if (!a) {
            return ''
        }
        var html = '\n<ul >\n';
        for (var i = 0; i < a.length; i++) {
            html += '<li><a href="#">' + a[i].content + '</a>';
            html += this.getDom(this.groups[a[i].id]);
            html += '</li>\n';
        }
        ;
        html += '</ul>\n';
        return html;
    }
};
var html = new treeMenu(zNodes).init(0);