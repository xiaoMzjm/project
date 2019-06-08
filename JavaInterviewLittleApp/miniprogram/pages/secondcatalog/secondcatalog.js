//index.js
const app = getApp()

Page({
  data: {
    code:'',
    list: ''
  },

  /**
   * 获取二级目录code
   */
  onLoad: function (options) {
    console.info("secondcatalog.js|onLoad|second options");
    console.info(options);
    if (options.code) {
      this.setData({
        code: options.code
      });
    }
  },

  onReady: function (e) {
    this.init();
  },

  /**
   * 统一初始化入口
   */
  init:function(){
    this.initCataLog();
  },

  /**
   *初始化目录
   */
  initCataLog:function(){
    console.info("secondcatalog.js|initCataLog|this.data=");
    console.info(this.data);
    if(!this.data.code) {
      return;
    }
    wx.showLoading({
      title: '加载中',
    });
    let that = this;
    wx.request({
      url: app.catalogUrl() + "/" + that.data.code,
      data: {},
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      method: 'GET',
      success: function (result) {
        let serverResult = result.data;
        if (!serverResult.success) {
          wx.showModal({
            title: "获取二级目录失败",
            content: serverResult.errorMsg,
            showCancel: false,
          });
          return;
        }
        console.info("获取二级目录，serverResult=");
        console.info(serverResult);
        if (!serverResult.data || !serverResult.data.cataLog) {
          wx.hideLoading();
          return;
        }

        console.info(JSON.parse(serverResult.data.cataLog));
        that.setData({
          list: JSON.parse(serverResult.data.cataLog)
        });
        wx.hideLoading();
      },
      fail: function (e) {
        wx.hideLoading();
        wx.showModal({
          title: "系统维护中，请稍后重试",
          content: '',
          showCancel: false,
        });
      }
    })
  },

  kindToggle: function (e) {
    var id = e.currentTarget.id, list = this.data.list;
    for (var i = 0, len = list.length; i < len; ++i) {
      if (list[i].id == id) {
        list[i].open = !list[i].open
      } else {
        list[i].open = false
      }
    }
    this.setData({
      list: list
    });
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    console.info("用户下拉刷新onUnload，重新刷新目录");
    this.initCataLog();
    wx.stopPullDownRefresh();
  },
})
