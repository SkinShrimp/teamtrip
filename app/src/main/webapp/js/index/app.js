window.appHandler = {
    openProduct: function (id) {
        try {
            webkit.messageHandlers.openProduct.postMessage(id);
            return false
        } catch (err) {
            console.log('The native context does not exist yet');
        }
        try {
            window.jinmalvyouApp.toProduct(id);
            return false
        } catch (err) {
            console.log('The native context does not exist yet');
        }
    },
    openProductList: function (type) {
        try {
            webkit.messageHandlers.openProductList.postMessage(type);
            return false
        } catch (err) {
            console.log('The native context does not exist yet');
        }
        try {
            window.jinmalvyouApp.toProductList(type);
            return false
        } catch (err) {
            console.log('The native context does not exist yet');
        }
    },
    openProductSearch: function (keyword) {
        try {
            webkit.messageHandlers.openProductSearch.postMessage(keyword);
            return false
        } catch (err) {
            console.log('The native context does not exist yet');
        }
        try {
            window.jinmalvyouApp.toSearch(keyword);
            return false
        } catch (err) {
            console.log('The native context does not exist yet');
        }
    },
    isAppUserAgent: function () {
        return /JinmalvyouApp/.test(navigator.userAgent);
    },

    openPage: function (json) {
        try {
            webkit.messageHandlers.openPage.postMessage(json);
            return true;
        } catch (err) {
            console.log('The native context does not exist yet');
        }
        try {
            window.jinmalvyouApp.jumpUrl(json);
            return true;
        } catch (err) {
            console.log('The native context does not exist yet');
        }
        return false;
    },

    openUrl: function (path, params) {
        var query = '';
        for (var key in params) {
            if (params.hasOwnProperty(key)) {
                query += (key + '=' + encodeURI(params[key]) + '&')
            }
        }

        var url = path + '?' + query;
        this.openPage(JSON.stringify({type: 'web', url: url}))
    },


    openOrUrl: function (path, params) {
        var query = '';
        for (var key in params) {
            if (params.hasOwnProperty(key)) {
                query += (key + '=' + params[key] + '&')
            }
        }
        var url = path + '?' + query;
        this.openPage(JSON.stringify({type: 'web', url: url}))
    },


    openchooseBackUrl: function (path, params) {
        var query = '';
        for (var key in params) {
            if (params.hasOwnProperty(key)) {
                query += (key + '=' + params[key] + '&')
            }
        }
        var url = query != '' ? path + '?' + query : path;
        this.openPage(JSON.stringify({type: 'chooseBack', url: url}))
    },

    choosePlaceUrl: function (path, params) {
        var query = '';
        for (var key in params) {
            if (params.hasOwnProperty(key)) {
                query += (key + '=' + params[key] + '&')
            }
        }
        var url = query != '' ? path + '?' + query : path;
        this.openPage(JSON.stringify({type: 'choosePlace', url: url}))
    },

    share: function (title, image_url, text, href){
        var params = {type:"share"};
        var json = {};
        json.title = title;
        json.image = image_url;
        json.text = text;
        json.url = href;
        params.json = JSON.stringify(json);
        this.openPage(JSON.stringify(params));
    }
};