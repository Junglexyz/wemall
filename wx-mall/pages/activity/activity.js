// pages/activity/activity.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods:{
      cover: 'http://wemall-jungle.oss-cn-chengdu.aliyuncs.com/goods/20200528223657.jpg',
      title: '哇哈哈纯净水',
      sellPrice: 0.01,
      descible: '活动介绍'
    }
  },

  pay() {
    let that = this
    let openId = app.globalData.userInfo.openId
    let orderId = that.data.orderId
    let payMoney = that.data.payMoney
    let order = { payMoney: 0.1, orderId: 0, openid: openId }
    console.log(order)
    wx.showLoading({
      title: '发起支付',
    })
    util.wxRequest(app.globalData.url + 'wx/order/prepay', { order: order }, "post").then(res => {
      let data = res.data
      console.log(data)
      if (data.errno == 0) {
        console.log("支付过程开始");
        wx.hideLoading()
        const payParam = res.data.result;
        wx.requestPayment({
          'timeStamp': payParam.timeStamp,
          'nonceStr': payParam.nonceStr,
          'package': payParam.packageValue,
          'signType': payParam.signType,
          'paySign': payParam.paySign,
          success: function (res) {
            wx.redirectTo({
              url: '/pages/checkout/result/result?status=1&payMoney=' + order.payMoney
            });
          },
          fail: function (err) {
            console.log(err);
            wx.redirectTo({
              url: '/pages/checkout/result/result?status=0&payMoney=' + order.payMoney + '&orderId=' + order.orderId
            })
          },
          complete: function (comp) {
            console.log("支付过程结束")
            console.log(comp);
          }
        });
      }
    }).catch(err => {
      console.log(err)
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