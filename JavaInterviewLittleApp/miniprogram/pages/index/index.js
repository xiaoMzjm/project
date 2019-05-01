// pages/grid/grid.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    grids: '',
    headerPicUrl: '',
    inputShowed: false,//鼠标聚焦搜索框
    inputVal: "",
    searchCataLogVOList: '',
    noticeList: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function (options) {
    console.info("onReady，获取图片和一级目录");
    this.init();
  },

  init:function(){
    this.initPic();
    this.initCataLog();
    this.initNotice();
  },

  /**
   * 初始化图片轮播
   */
  initPic:function(){
    let that = this;
    wx.request({
      url: app.catalogUrl() + "/headerPicUrl",
      data: {},
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      method: 'GET',
      success: function (result) {
        let serverResult = result.data;
        if (!serverResult.success) {
          wx.showModal({
            title: "获取一级目录失败",
            content: serverResult.errorMsg,
            showCancel: false,
          });
          return;
        }
        console.info("index.js|initPic method|serverResult=");
        console.info(serverResult);
        that.setData({
          headerPicUrl: JSON.parse(serverResult.data.cataLog)
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

  /**
   * 初始化九宫格目录
   */
  initCataLog : function(){
    let that = this;
    wx.request({
      url: app.catalogUrl() + "/grid",
      data: {},
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      method: 'GET',
      success: function (result) {
        let serverResult = result.data;
        if (!serverResult.success) {
          wx.showModal({
            title: "获取一级目录失败",
            content: serverResult.errorMsg,
            showCancel: false,
          });
          return;
        }
        console.info("index.js|initCataLog method|serverResult=");
        console.info(serverResult);
        that.setData({
          grids: JSON.parse(serverResult.data.cataLog)
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

  /**
   * 初始化公告
   */
  initNotice : function(){
    let that = this;
    wx.request({
      url: app.noticeUrl(),
      data: {
        code:'indexNotice'
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      method: 'POST',
      success: function (result) {
        let serverResult = result.data;
        if (!serverResult.success) {
          wx.showModal({
            title: "获取公告失败",
            content: serverResult.errorMsg,
            showCancel: false,
          });
          return;
        }
        console.info("index.js|initNotice|serverResult=");
        console.info(serverResult);
        if (!serverResult.data || !serverResult.data.cataLog) {
          console.info("index.js|initNotice|没有公告");
          that.setData({
            noticeList: ''
          });
          return;
        }
        that.setData({
          noticeList: JSON.parse(serverResult.data.cataLog)
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

  /**
   * 搜索相关
   */
  showInput: function () {
    console.info("showInput，点击搜索框时触发的函数");
    this.setData({
      inputShowed: true
    });
  },
  hideInput: function () {
    console.info("hideInput，点击搜索框时取消时触发的函数");
    this.setData({
      inputVal: "",
      inputShowed: false
    });
  },
  clearInput: function () {
    console.info("clearInput");
    this.setData({
      inputVal: ""
    });
  },
  inputTyping: function (e) {
    this.setData({
      inputVal: ''
    });
    console.info("inputTyping，输入文字时触发的函数=");
    console.info(e);
    if (e.detail.value==''){
        return;
    }

    let that = this;
    wx.request({
      url: app.searchUrl(),
      data: {
        code: e.detail.value
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      method: 'POST',
      success: function (result) {
        let serverResult = result.data;
        if (!serverResult.success) {
          wx.showModal({
            title: "查询文章失败",
            content: serverResult.errorMsg,
            showCancel: false,
          });
          return;
        }
        console.info("index.js|initCataLog method|serverResult=");
        console.info(serverResult);
        that.setData({
          searchCataLogVOList: serverResult.data
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


    this.setData({
      inputVal: e.detail.value
    });
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    console.info("onShow，还原搜索栏");
    this.setData({
      inputVal: '',
      inputShowed: false
    });
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
    console.info("用户下拉刷新onUnload，重新刷新图片和目录");
    this.init();
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

  }
})