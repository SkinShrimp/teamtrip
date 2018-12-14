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

<<<<<<< HEAD
<<<<<<< Updated upstream
=======

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
=======

//-------------------数结构的js实现--------------------//
var zNodes = [
    {id: 0, pId: -1, name: "Aaaa"},
    {id: 1, pId: 0, name: "A"},
    {id: 11, pId: 1, name: "A1"},
    {id: 12, pId: 1, name: "A2"},
    {id: 13, pId: 1, name: "A3"},
    {id: 2, pId: 0, name: "B"},
    {id: 21, pId: 2, name: "B1"},
    {id: 22, pId: 2, name: "B2"},
    {id: 23, pId: 2, name: "B3"},
    {id: 3, pId: 0, name: "C"},
    {id: 31, pId: 3, name: "C1"},
    {id: 32, pId: 3, name: "C2"},
    {id: 33, pId: 3, name: "C3"},
    {id: 34, pId: 31, name: "x"},
    {id: 35, pId: 31, name: "y"},
    {id: 36, pId: 31, name: "z"},
    {id: 37, pId: 36, name: "z1123"},
    {id: 38, pId: 37, name: "z123123123"}
>>>>>>> master
];

function treeMenu(a) {
    this.tree = a || [];
    this.groups = {};
};
treeMenu.prototype = {
<<<<<<< HEAD
    /*初始化树结构
    * param:pid 第一层从那个数字开始*/
=======
>>>>>>> master
    init: function (pid) {
        this.group();
        return this.getDom(this.groups[pid]);
    },
<<<<<<< HEAD
    /*功能：将所有父节点相同的数据放在同一个数组中*/
=======
>>>>>>> master
    group: function () {
        for (var i = 0; i < this.tree.length; i++) {
            if (this.groups[this.tree[i].pId]) {
                this.groups[this.tree[i].pId].push(this.tree[i]);
            } else {
                this.groups[this.tree[i].pId] = [];
                this.groups[this.tree[i].pId].push(this.tree[i]);
<<<<<<< HEAD

            }
        }
        console.log(this.groups);

=======
            }
        }
>>>>>>> master
    },
    getDom: function (a) {
        if (!a) {
            return ''
        }
        var html = '\n<ul >\n';
        for (var i = 0; i < a.length; i++) {
<<<<<<< HEAD
            html += '<li><a href="#">' + a[i].content + '</a>';
=======
            html += '<li><a href="#">' + a[i].name + '</a>';
>>>>>>> master
            html += this.getDom(this.groups[a[i].id]);
            html += '</li>\n';
        }
        ;
        html += '</ul>\n';
        return html;
    }
};
<<<<<<< HEAD
var html = new treeMenu(zNodes).init(0);
>>>>>>> Stashed changes
=======
var html = new treeMenu(zNodes).init(0);
>>>>>>> master
