// pages/checkout/result/result.js
const util = require('../../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    status: 0,
    payMoney: 0.00,
    orderId: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let status = options.status
    let payMoney = options.payMoney
    let orderId = options.orderId
    this.setData({
      status: status,
      payMoney: payMoney,
      orderId: orderId
    })
  },

  /**
   * 返回主页
   */
  goback(){
    wx.switchTab({
      url: '/pages/index/index',
    })
  },
  goOrder(){
    wx.switchTab({
      url: '/pages/order/order',
    })
  },
  pay(){
    let that = this
    let openId = app.globalData.userInfo.openId
    let orderId = that.data.orderId
    let payMoney = that.data.payMoney
    let order = {payMoney: payMoney, orderId: orderId, openid: openId}
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
            console.log("支付过程成功");
            wx.redirectTo({
              url: '/pages/checkout/result/result?status=1&payMoney=' + order.payMoney
            });
          },
          fail: function (err) {
            console.log("支付过程失败");
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