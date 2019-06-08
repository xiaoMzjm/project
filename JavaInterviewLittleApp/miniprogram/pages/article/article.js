// pages/article/article.js
const app = getApp()


Page({

  /**
   * 页面的初始数据
   */
  data: {
    title:"",
    wemark: "",
    catalogVO: '',
    code:'',
    isShouCang:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.code) {
      this.setData({
        code: options.code
      });
      console.info('my|onLoad|ssssss=');
      wx.getStorage({
        key: 'readHistory',
        success: function(res) {
          console.info('my|onLoad|readHistory=');
          console.info(res.data);
          let newArray = new Array();
          for (let i = 0; i < res.data.length ; i++) {
            if (options.code != res.data[i]) {
              newArray.push(res.data[i]);
            }
          }
          newArray.push(options.code);
          wx.setStorage({
            key: "readHistory",
            data: newArray
          })
        },
        fail:function(){
          let readHistory = new Array();
          readHistory.push(options.code);
          wx.setStorage({
            key: "readHistory",
            data: readHistory
          })
        }
      })
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    wx.showLoading({
      title: '加载中',
    });
    this.init();
  },

  /**
   * 统一初始化入口
   */
  init:function(){
    this.initArticle();
    this.initShouCang();
  },

  /**
   * 获取文章
   */
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
   * 记录浏览记录
   */
  recordHistory:function(){
    if (this.data.code) {
      let code = this.data.code;
      console.info('my|onLoad|ssssss=');
      wx.getStorage({
        key: 'readHistory',
        success: function (res) {
          console.info('my|onLoad|readHistory=');
          console.info(res.data);
          let newArray = new Array();
          for (let i = 0; i < res.data.length; i++) {
            if (code != res.data[i]) {
              newArray.push(res.data[i]);
            }
          }
          newArray.push(code);
          wx.setStorage({
            key: "readHistory",
            data: newArray
          });
        },
        fail: function () {
          let readHistory = new Array();
          readHistory.push(code);
          wx.setStorage({
            key: "readHistory",
            data: readHistory
          })
        }
      })
    }
  },
  /**
   * 初始化收藏
   */
  initShouCang:function(){
    let that = this;
    let code = this.data.code;
    wx.getStorage({
      key: 'shouCangList',
      success: function (res) {
        let shouCangList = res.data;
        console.info("article|initShouCang|shouCangList=");
        console.info(shouCangList);
        for (let i = 0; i < shouCangList.length; i++) {
          if (shouCangList[i] == code) {
            that.setData({
              isShouCang:true
            });
          }
        }
      }
    })
  },
  /**
   * 收藏
   */
  onTabShouCang:function(){
    console.info("article|onTabShouCang|code="+this.data.code);
    let code = this.data.code;
    let shouCangList = '';
    let newShouCangList = new Array();
    let that = this;
    wx.getStorage({
      key: 'shouCangList',
      success: function(res) {
        shouCangList = res.data;
        console.info("=========");
        console.info(shouCangList);
        console.info(code);
        for (let i = 0; i < shouCangList.length ; i++) {
          if (shouCangList[i] != code) {
            newShouCangList.push(shouCangList[i]);
          }
        }
        
        if(that.data.isShouCang) {
          wx.showToast({
            title: '取消成功',
            icon: 'success',
            duration: 2000
          });
          that.setData({
            isShouCang: false
          });
        }
        else {
          newShouCangList.push(code);
          wx.showToast({
            title: '收藏成功',
            icon: 'success',
            duration: 2000
          });
          that.setData({
            isShouCang: true
          });
        }
        
        wx.setStorage({
          key: 'shouCangList',
          data: newShouCangList.length == 0 ? '' : newShouCangList,
        });
        
      },
      fail:function(){
        newShouCangList.push(code);
        console.info("article|onTabShouCang|newShouCangList=" + newShouCangList);
        wx.setStorage({
          key: 'shouCangList',
          data: newShouCangList,
        });
        wx.showToast({
          title: '收藏成功',
          icon: 'success',
          duration: 2000
        });
        that.setData({
          isShouCang: true
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