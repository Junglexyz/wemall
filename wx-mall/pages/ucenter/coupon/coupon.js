// pages/ucenter/coupon/coupon.js
const util = require('../../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    coupons: [],
    myCoupons: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that =this
    let userInfo = app.globalData.userInfo
    console.log(userInfo != null)
    let userId = null
    if (Object.keys(userInfo).length != 0 || userInfo != null){
      userId  = userInfo.userId
    }
    /**
     * 用户未使用优惠券
     */
    util.wxRequest(app.globalData.url + 'wx/coupon/getCouponByUser', { userId: userId }, "post").then(res => {
      let data = res.data
      console.log(data)
      if (data.errno == 0) {
        let list = data.list
        for (let i = 0; i < list.length; i++) {
          list[i].endTime = util.formatTime(new Date(list[i].endTime))
        }
        that.setData({
          myCoupons: list
        })
      }
    }).catch(err => {
      console.log(err)

    })
    /**
     * 可领取的优惠券
     */
    util.wxRequest(app.globalData.url + 'wx/coupon/list', {userId: userId}, "post").then(res => {
      let data = res.data
      console.log(data)
      if(data.errno == 0){
        let list = data.list
        for(let i = 0; i < list.length; i++){
          list[i].endTime = util.formatTime(new Date(list[i].endTime))
        }
        that.setData({
          coupons: list
        })
      }
    }).catch(err => {
        console.log(err)
        
    })
  },
  /**
   * 领取优惠券
   */
  acceptCoupon: function(e){
    let that = this
    let index = e.currentTarget.dataset.index
    let coupon = that.data.coupons[index]
    console.log(coupon)
    let userInfo = app.globalData.userInfo
    if(userInfo == null){
      that.login()
      return
    }
    let userId = app.globalData.userInfo.userId
    let mobile = app.globalData.userInfo.mobile
    let data = {couponId: coupon.couponId, userId: userId, mobile: mobile, endTime: coupon.endTime, title: coupon.title, types: coupon.types, targetMoney: coupon.targetMoney, discountMoney: coupon.discountMoney}
    util.wxRequest(app.globalData.url + 'wx/coupon/accept', data, "post").then(res => {
      let data = res.data
      console.log(data)
      if(data.errno == 0){
        wx.showToast({
          title: '领取成功！',
          icon: 'success',
          duration: 2000
        })
        this.onLoad()
      }
    }).catch(err => {
        console.log(err)
        
    })
  },

  login: function () {
    // 登录
    wx.login({
      success: res => {
        console.log(res)
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        util.wxRequest(app.globalData.url + 'wx/auth/openid', { code: res.code }, "post").then(res => {
          let data = res.data
          if (data.errno == 0) {
            console.log('获取openId成功')
            wx.setStorageSync("openid", data.result)
            wx.navigateTo({
              url: '../../../pages/auth/accountLogin/accountLogin',
            })
          }
        }).catch(err => {
          console.log(err)
        })
      }
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