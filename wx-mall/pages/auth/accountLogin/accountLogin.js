// pages/auth/accountLogin/accountLogin.js
const util = require('../../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    mobile: '',
    verifyCode: '',
    verifyStatus: '请输入验证码',
    counts: 60,
    countDown: false,
    vaildMobile: false,
    login: false //判断是否可登录
  },
  // 获取input手机号内容
  bindinputMobile: function(event){
    let mobile = event.detail.value
    let vaildMobile = false
    console.log(typeof(mobile))
    if(mobile.length == 11 && typeof(mobile) == 'string'){
      vaildMobile = true
    }
    this.setData({
      mobile: mobile,
      vaildMobile: vaildMobile
    })
  },
  // 获取input 验证码内容
  bindinputVerify: function(event){
    let verifyCode = event.detail.value
    let login = false
    if(verifyCode.length == 6 && typeof(verifyCode) == 'string'){
      login = true
    }
    this.setData({
      verifyCode: event.detail.value,
      login: login
    })
  },
  // 从后台获取验证码
  getVerify: function(){
    let that = this
    let mobile = that.data.mobile
    let vaildMobile = that.data.vaildMobile
    if(!vaildMobile){
      return;
    }
    console.log(mobile)
    that.setData({
      countDown: true,
      verifyCode: ''
    });
    // 倒计时主要部分，利用定时器
    var getCountDown =  setInterval(function(){
      var times = that.data.counts-1;
        that.setData({
          counts: times,
          countDown: true,
        });
        console.log(times)
        if (that.data.counts < 1){
          clearInterval(getCountDown);
          that.setData({
            countDown: false,
            counts:60
          })
        }
    },1000);
    util.wxRequest(app.globalData.url + 'wx/auth/getVerify', {mobile: mobile}, "post").then(res => {
      let data = res.data
      console.log(data)
      if(data.errno == 0){
        wx.showToast({
          title: '验证码短信已发送，请注意查收。',
          icon: 'none',
          duration: 2000
        })
      }
     }).catch(err => {
        wx.showToast({
          title: '验证码短信发送失败',
          icon: 'none',
          duration: 2000
        })
     })
  },
  // 手机验证码登录
  login: function(){
    let that = this
    let mobile = that.data.mobile
    let captcha = that.data.verifyCode
    let login = that.data.login
    let openid = wx.getStorageSync('openid')
    console.log(mobile, captcha,openid)
    let data = {mobile: mobile, captcha: captcha, openid: openid}
    if(!login){
      return;
    }
    util.wxRequest(app.globalData.url + 'wx/auth/loginByCaptcha', data, "post").then(res => {
      let data = res.data
      console.log(data)
      if(data.errno == 1001){
        console.log('验证码已过期！')
        wx.showToast({
          title: '验证码已过期！',
        })
      }
      if(data.errno == 1002){
        console.log('验证码错误')
        wx.showToast({
          title: '验证码错误',
        })
      }
      if(data.errno == 0){
        console.log('验证成功')
        let userInfo = data.result
        wx.setStorageSync('userInfo', userInfo)
        app.globalData.userInfo = userInfo
        wx.navigateBack({
          delta: 1
        })
      }
     }).catch(err => {
        console.log(err)
        console.log('登录失败！')
        wx.showToast({
          title: '登录失败,请联系管理员',
        })
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