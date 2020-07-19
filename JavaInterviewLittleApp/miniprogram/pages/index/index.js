// pages/grid/grid.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 一级目录
    grids: '',
    headerPicUrl: '',
    inputShowed: false,//鼠标聚焦搜索框
    inputVal: "",
    searchCataLogVOList: '',
    noticeList: '',
    // 导航栏两个属性
    TabCur: 0,
    scrollLeft: 0,
    // 二级目录
    secondcataloglist:'',
    secondCatalogCode:'',
    // 手势
    touchstartx:'',
    touchstarty:'',
    touchendx:'',
    touchendy:'',
    xielv: 3.5
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
    this.init();
  },

  /**
   * 总初始化函数
   */
  init:function(){
    this.setData({
      TabCur:0
    });
    this.initPic();
    this.initCataLog();
    // this.initNotice();
    // this.initLastestArticle();
  },

  /**
   * 初始化图片轮播
   */
  initPic:function(){
    let that = this;
    console.info(app.catalogUrl() + "/headerPicUrl");
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
        console.info(e);
        wx.showModal({
          title: "系统维护中，请稍后重试",
          content: '',
          showCancel: false,
        });
      }
    })
  },

  /**
   * 初始一级目录
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
        let cataLog = JSON.parse(serverResult.data.cataLog);
        that.setData({
          grids: cataLog,
          secondCatalogCode: cataLog[0].code
        });
        // 加载二级目录
        that.initSecondCataLog();
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
   * 初始最新文章
   */
  initLastestArticle: function () {
    let that = this;
    wx.request({
      url: app.lastestArticalUrl(),
      data: {
        code: 'indexNotice'
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      method: 'POST',
      success: function (result) {
        let serverResult = result.data;
        if (!serverResult.success) {
          wx.showModal({
            title: "获取最新文章失败",
            content: serverResult.errorMsg,
            showCancel: false,
          });
          return;
        }
        console.info("index.js|initLastestArticle|serverResult=");
        console.info(serverResult);
        if (!serverResult.data) {
          console.info("index.js|initLastestArticle|没有最新文章");
          that.setData({
            noticeList: ''
          });
          return;
        }
        that.setData({
          noticeList: serverResult.data
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
   * 搜索框的事件函数
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
   * 导航栏点击事件
   */
  tabSelect:function(e){
    console.info("index.js|tabSelect|e=");
    console.info(e);
    this.setData({
      TabCur: e.currentTarget.dataset.id,
      secondCatalogCode: e.currentTarget.dataset.code
    })
    this.tabSelctById();
  },
  tabSelctById:function(){
    let id = this.data.TabCur;
    console.info("index.js|tabSelctById|id="+id);
    let secondCatalogCode = this.data.secondCatalogCode;
    this.setData({
      TabCur: id,
      scrollLeft: (id - 1) * 60
    })
    // 加载二级目录
    this.initSecondCataLog();
  },



  /**
   *初始化二级目录
  */
  initSecondCataLog: function () {
    console.info("index.js|initSecondCataLog|this.data=");
    console.info(this.data);
    if (!this.data.secondCatalogCode) {
      that.setData({
        secondcataloglist: ''
      });
      return;
    }
    wx.showLoading({
      title: '加载中',
    });
    let that = this;
    wx.request({
      url: app.catalogUrl() + "/" + that.data.secondCatalogCode,
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
        console.info("index.js|initSecondCataLog|获取二级目录，serverResult=");
        console.info(serverResult);
        if (!serverResult.data || !serverResult.data.cataLog) {
          wx.hideLoading();
          that.setData({
            secondcataloglist: ''
          });
          return;
        }

        console.info(JSON.parse(serverResult.data.cataLog));
        that.setData({
          secondcataloglist: JSON.parse(serverResult.data.cataLog)
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


  /**
   * 手势
   */
  touchstart:function(e){
    console.info("index.js|touchstart|e=");
    console.info(e);
    this.setData({
      touchstartx: e.changedTouches[0].pageX,
      touchstarty: e.changedTouches[0].pageY,
    });
  },
  touchend: function (e) {
    console.info("index.js|touchend|e=");
    console.info(e);
    console.info(this.data);
    let startx = this.data.touchstartx;
    let starty = this.data.touchstarty;
    let endx = e.changedTouches[0].pageX;
    let endy = e.changedTouches[0].pageY;
    let xchange = endx - startx;
    let ychange = endy - starty;
    console.info("x变化=" + xchange);
    console.info("y变化=" + ychange);
    if (Math.abs(ychange) * this.data.xielv < Math.abs(xchange)) {
      console.info("x变化率大于y的4倍，");
      if (xchange < 0) {
        console.info("从右往左划");
        this.onNextTab();
      }
      else if(xchange > 0){
        console.info("从左往右划");
        this.onPreTab();
      }
    }
  },
  onPreTab:function(){
    let tabid = this.data.TabCur;
    let code = this.calSecondCatalogCode('pre');
    if (tabid == 0) {
      return;
    }
    this.setData({
      TabCur: Number(tabid) - 1
    });
    this.setData({
      secondCatalogCode: code
    });
    this.tabSelctById();
  },
  onNextTab:function(){
    let tabid = this.data.TabCur;
    let maxid = this.data.grids.length - 1;
    let code = this.calSecondCatalogCode('next');
    if (maxid == tabid) {
      return;
    }
    this.setData({
      TabCur: Number(tabid) + 1
    });
    this.setData({
      secondCatalogCode: code
    });
    this.tabSelctById();
  },
  calSecondCatalogCode:function(preOrNext){
    let secondCatalogCode = this.data.secondCatalogCode;
    let grids = this.data.grids;
    let pre = secondCatalogCode;
    let next = secondCatalogCode;
    for (let i = 0; i < grids.length ; i++) {
      if (secondCatalogCode == grids[i].code) {
        if (grids.length > i+1) {
          console.info
          next = grids[i+1].code;
        }
        if(i > 0) {
          pre = grids[i - 1].code;
        }
      }
    }
    console.info("index.js|calSecondCatalogCode|pre="+pre + ',next=' + next);
    if(preOrNext == 'pre') {
      return pre;
    }
    else {
      return next;
    }
  },






  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    console.info("index.js|onShow|还原搜索栏");
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
    console.info("index.js|onPullDownRefresh|用户下拉刷新onUnload，重新刷新图片和目录");
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