// pages/article/article.js
const app = getApp()


Page({

  /**
   * 页面的初始数据
   */
  data: {
    title:"",
    wemark: "",
    catalogVO: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      code: options.code
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    wx.showLoading({
      title: '加载中',
    });
    this.initArticle();
  },

  initArticle:function(){
    // 获取文章
    let that = this;
    let code = this.data.code;
    console.info("article.js|initArticle|code=" + code);
    wx.request({
      url: app.articalUrl(),
      data: {
        code: code
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      method: 'POST',
      success: function (result) {
        let serverResult = result.data;
        if (!serverResult.success) {
          wx.showModal({
            title: "获取文章失败",
            content: serverResult.errorMsg,
            showCancel: false,
          });
          return;
        }
        if (!serverResult.data || !serverResult.data.cataLog)  {
          return;
        }
        var md = serverResult.data.cataLog;
        console.info("md:");
        console.info(md);

        that.setData({
          wemark: md,
          catalogVO: serverResult.data
        });
        wx.hideLoading();
      },
      fail: function (e) {
        wx.hideLoading();
        wx.showModal({
          title: "网络异常",
          content: e.errMsg,
          showCancel: false,
        });
      }
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    console.info("用户下拉刷新onUnload，重新刷新目录");
    this.initArticle();
    wx.stopPullDownRefresh();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    //设置分享参数
    var that = this;
    var longitude = 114.0322103;
    var latitude = 22.5353646;
    return {
      title: 'Java面试小课堂',
      path: '/pages/article/article?code=' + this.data.code
    }
  }
})