// pages/ucenter/service/service.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: [{
      title: '生活服务',
      img: 'https://image.weilanwl.com/color2.0/plugin/sylb2244.jpg',
      url: '/indexes/indexes'
    },
    // {
    //   title: '快递代取',
    //   img: 'https://image.weilanwl.com/color2.0/plugin/wdh2236.jpg',
    //   url: '/animation/animation'
    // },
    {
      title: '车次时刻表',
      img: 'https://image.weilanwl.com/color2.0/plugin/qpct2148.jpg',
      url: '/busTime/busTime'
    },
    // {
    //   title: '垂直导航',
    //   img: 'https://image.weilanwl.com/color2.0/plugin/qpczdh2307.jpg',
    //   url: '/verticalnav/verticalnav'
    // }
    ]
  },

  toChild(e) {
    wx.navigateTo({
      url: '/pages/ucenter/service' + e.currentTarget.dataset.url
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