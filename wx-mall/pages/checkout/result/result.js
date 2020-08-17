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
    orderId: '',
    hd_open_top: 290,
    hb_body_top: 240,
    hb_head_radius: 30,
    show_open: true,
    hb_body_radius_shang: 0,
    hb_body_radius_xia: 120,
    hb_money_top: 240,
    hb_body_zindex: 4,
    requested: false,
    info: '',
    show: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let status = options.status
    let payMoney = options.payMoney
    let orderId = options.orderId
    let that = this
    util.wxRequest(app.globalData.url + 'wx/award/get', {}, "get").then(res => {
      let data = res.data
      console.log(res)
      if (data.errno == 0) {
        if (status == 1 && payMoney > 88) {
          that.setData({
            show: true
          })
          that.openHongbao()
        }
      }
    }).catch(err => {
      console.log(err)
    })
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
  openHongbao: function () {
    let userInfo = app.globalData.userInfo
    let requested = this.data.requested
    var that = this
    if (!requested) { // 防止多次触发
      util.wxRequest(app.globalData.url + 'wx/award/get', {}, "get").then(res => {
        let data = res.data
        console.log(res)
        if (data.errno == 0) {
          let info = {
            userId: userInfo.userId,
            awardId: data.result.id,
            userTel: userInfo.mobile
          }
          if (data.result.count > 0) {
            util.wxRequest(app.globalData.url + 'wx/award/generate', info, "post").then(res => {
              console.log(res)
              if (res.data.errno == 0) {
                that.setData({
                  info: '恭喜您获得' + res.data.result.grade + '元无门槛优惠券'
                })
              }
            }).catch(err => {

            })
          } else {
            that.setData({
              info: '您来晚了，红包已被抢光了'
            })
          }
        } 
      }).catch(err => {
        console.log(err)
      })
    }
    this.setData({
      requested: true
    })
    var hd_open_top = this.data.hd_open_top;
    var hb_body_top = this.data.hb_body_top;
    var hb_body_radius_shang = this.data.hb_body_radius_shang;
    var hb_body_radius_xia = this.data.hb_body_radius_xia;
    var hb_money_top = this.data.hb_money_top;
    if (hd_open_top < 360) {
      var timerTem = setTimeout(function () {
        hd_open_top = hd_open_top + 10;
        that.setData({
          hd_open_top: hd_open_top
        })
        that.openHongbao()
      }, 20)
    } else if (hb_body_top > 0) {
      var timerTem = setTimeout(function () {
        hb_body_top = hb_body_top - 20;
        hb_body_radius_xia = hb_body_radius_xia - 10;
        hb_body_radius_shang = hb_body_radius_shang + 20;
        that.setData({
          hb_body_top: hb_body_top,
          show_open: false,
          hb_head_radius: 0,
          hb_body_radius_xia: hb_body_radius_xia,
          hb_body_radius_shang: hb_body_radius_shang
        })
        that.openHongbao()
      }, 20)
    } else if (hb_money_top > 30) {
      var timerTem = setTimeout(function () {
        hb_money_top = hb_money_top - 2;
        that.setData({
          hb_money_top: hb_money_top,
          hb_body_zindex: 1
        })
        that.openHongbao()
      }, 20)
    }
  },
  cancel() {
    this.setData({
      show: false
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