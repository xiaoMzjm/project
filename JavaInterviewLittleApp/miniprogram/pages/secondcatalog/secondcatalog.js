//index.js
const app = getApp()

Page({
  data: {
    code:'',
    list: ''
  },

  

  onLoad: function (options) {
    console.info("second options");
    console.info(options);
    this.setData({
      code: options.code
    });
  },

  onReady: function (e) {
    console.info("onReady,初始化二级目录");
    this.initCataLog();
  },

  initCataLog:function(){
    console.info(this.data);
    if(!this.data.code) {
      return;
    }
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
          return;
        }

        console.info(JSON.parse(serverResult.data.cataLog));
        that.setData({
          list: JSON.parse(serverResult.data.cataLog)
        });

      },
      fail: function (e) {
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
