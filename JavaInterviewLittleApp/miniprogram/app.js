//app.js
let env = "online";
App({
  onLaunch: function () {
    this.globalData = {}
    if (wx.getSystemInfoSync().platform == 'devtools') {
      env = "local";
    }
    else {
      env = "online";
    }
  },

  catalogUrl:function(){
    if(env == 'local') {
      return 'https://localhost/article/catalog/get';
    }
    else {
      return 'https://www.keephealthy.top/article/catalog/get';
    }
  },

  articalUrl:function(){
    if (env == 'local') {
      return 'https://localhost/article/article/get';
    }
    else {
      return 'https://www.keephealthy.top/article/article/get';
    }
  },

  headerPicUrl: function () {
    if (env == 'local') {
      return 'https://localhost/article/article/get';
    }
    else {
      return 'https://www.keephealthy.top/article/article/get';
    }
  },

  searchUrl: function(){
    if (env == 'local') {
      return 'https://localhost/article/article/search';
    }
    else {
      return 'https://www.keephealthy.top/article/article/search';
    }
  },

  noticeUrl: function () {
    if (env == 'local') {
      return 'https://localhost/article/article/get';
    }
    else {
      return 'https://www.keephealthy.top/article/article/get';
    }
  }
})
