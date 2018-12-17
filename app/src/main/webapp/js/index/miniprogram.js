var miniProgramOrWeb  = document.querySelector('.miniProgramOrWeb');
var miniProgramImg    = document.getElementById('miniProgramImg');
var miniProgramBottom = document.getElementById('miniProgramBottom');
var home_site_url     = document.getElementById('home_site_url');
var site_url_m        = document.getElementById('site_url_m');

var ios = document.querySelector('#isIos');

if (parseInt(ios) > 0) {
    wx.miniProgram.getEnv(function(res) {
        if (res.miniprogram === true) {
            ajax_js(site_url_m + '/Index/isMiniProgram?isMiniProgram=yes', function() {
                if (miniProgramBottom) {
                    if (miniProgramBottom.getElementsByTagName('a').length == 3) {
                        inMiniProgram();
                    }
                }
            });
            if (document.querySelector('.header').length) {
                document.querySelector('.header').style.display = 'none';
            }
        } else {
            ajax_js(site_url_m + '/Index/isMiniProgram?isMiniProgram=no', function() {
                notInMiniProgram();
            });
        }
    })
}

// 小程序里面修改状态栏
function inMiniProgram() {
    var html = '<img src="'+ home_site_url +'/Public/theme/default/app/images/icon_home.png" alt="" class="weui-tabbar__icon"> <p class="weui-tabbar__label">首页</p>'
    var miniProgramIndex = document.createElement('a');
    miniProgramIndex.setAttribute('id', 'goMiniProgram');
    miniProgramIndex.setAttribute('class', 'weui-tabbar__item');
    miniProgramIndex.setAttribute('onclick', 'goMiniProgram()');
    miniProgramIndex.innerHTML = html;
    miniProgramOrWeb.innerHTML = '商城';
    miniProgramImg.setAttribute('src', home_site_url + '/Public/theme/default/app/images/icon_shop_cur.png');
    miniProgramBottom.insertBefore(miniProgramIndex, miniProgramBottom.firstChild);

    document.querySelector('.kf_class').style.display = 'none';
}

// 不在小程序修改状态栏
function notInMiniProgram() {
    if (miniProgramBottom) {
        if (miniProgramBottom.getElementsByTagName('a').length == 4) {
            miniProgramOrWeb.innerHTML = '首页';
            miniProgramImg.setAttribute('src', home_site_url + '/Public/theme/default/app/images/icon_home_cur.png');
            miniProgramBottom.removeChild(miniProgramBottom.getElementsByTagName('a')[0]);
        }
    }

    if (document.getElementById('kf_btn_div') !== null && document.getElementById('kf_btn_div') != 'NULL') {

    } else {
        var html = '<img src="'+ home_site_url +'/Public/theme/default/app/images/onlinekf.png" style="width: 100%;">';
        var kf_html = document.createElement('div');
        kf_html.setAttribute('id', 'kf_btn_div');
        kf_html.setAttribute('class', 'kf_class');
        kf_html.setAttribute('onclick', 'jiaxinTogglerDiv()');
        kf_html.innerHTML = html;
        document.body.insertBefore(kf_html, document.body.lastChild);
    }
}

// 跳回到小程序
function goMiniProgram() {
    var url = "/pages/index/index";
    wx.miniProgram.navigateTo({
        url: url
    });
}

// 发送请求，仅支持get
function ajax_js(url, success)
{
    var oAjax = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
    oAjax.open("GET", url, true); // 把要读取的参数的传过来。
    oAjax.send();
    success(oAjax.responseText);
}

// 验证身份证是否有效
function IdentityCodeValid(code) {
    var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
    var tip = "";
    var pass= true;

    if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
        tip = "身份证号格式错误";
        pass = false;
    }

    else if(!city[code.substr(0,2)]){
        tip = "地址编码错误";
        pass = false;
    }
    else{
        //18位身份证需要验证最后一位校验位
        if(code.length == 18){
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
            //校验位
            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++)
            {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if(parity[sum % 11] != code[17]){
                tip = "校验位错误";
                pass =false;
            }
        }
    }
    return pass;
}
