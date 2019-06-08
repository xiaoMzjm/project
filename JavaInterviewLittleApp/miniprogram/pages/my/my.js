// pages/my/my.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 导航
    TabCur: 0,
    tabNav: ['浏览记录', '收藏文章'],
    // 用户
    userInfo:'',
    // 浏览记录
    readHistory:'',
    // 收藏记录
    shouCangList:''
  },

  /**
   * 点击导航
   */
  tabSelect(e) {
    console.log(e);
    this.setData({
      TabCur: e.currentTarget.dataset.id,
      scrollLeft: (e.currentTarget.dataset.id - 1) * 60
    })
  },

  /**
   * 获取用户信息
   */
  onGotUserInfo:function(e){
    console.info("my|onGotUserInfo|e=");
    console.info(e);
    this.setData({
      userInfo:e.detail.userInfo
    });
    wx.setStorage({
      key: "userInfo",
      data: e.detail.userInfo
    })
  },

  /**
   * 统一初始化函数
   */
  init: function () {
    this.initUserInfo();
    this.initHistory();
    this.initShouCang();
  },
  /**
   * 初始化用户信息
   */
  initUserInfo:function(){
    let that = this;
    wx.getStorage({
      key: 'userInfo',
      success: function (res) {
        that.setData({
          userInfo: res.data
        });
        console.info("my|initUserInfo|userInfo=");
        console.info(that.data.userInfo);
      },
    })
  },
  /**
   * 初始化浏览记录
   */
  initHistory:function(){
    let that = this;
    wx.getStorage({
      key: 'readHistory',
      success: function(res) {
        let readHistory = res.data;
        let newReadHistory = new Array();
        for (let i = readHistory.length -1 ; i >= 0 ; i--) {
          newReadHistory.push(readHistory[i]);
        }
        that.setData({
          'readHistory': newReadHistory
        });
      },
    })
  },
  /**
   * 初始化收藏记录
   */
  initShouCang:function(){
    let that = this;
    wx.getStorage({
      key: 'shouCangList',
      success: function (res) {
        let shouCangList = res.data;
        let newShouCangList = new Array();
        for (let i = shouCangList.length - 1; i >= 0; i--) {
          newShouCangList.push(shouCangList[i]);
        }
        that.setData({
          'shouCangList': newShouCangList
        });
      },
    })
  },


  


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.init();
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
    this.init();
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