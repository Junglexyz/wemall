// pages/ucenter/ucenter.js
const app = getApp()
const util = require('../../utils/util.js')
Page({
  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{},
    isLogin: false,
    menus: [{name: '我的地址', img: '/images/address.png', url: '/pages/address/address', needLogin: true},
     {name: '店铺简介', img: '/images/brief.png', url: '/pages/ucenter/shopInfo/shopInfo', needLogin: false},
     {name: '领券中心', img: '/images/coupon.png', url: '/pages/ucenter/coupon/coupon', needLogin: true},
      /*{ name: '汉王微服务', img: '/images/coupon.png', url: '/pages/ucenter/service/service', needLogin: false }*/]
     //   { name: '我的余额', img: '/images/wallet.png', url: '/pages/ucenter/balance/balance', needLogin: true }
  },
  /**点击进入菜单详情*/
  subMenu: function(e){
    let url = e.currentTarget.dataset.url
    let needLogin = e.currentTarget.dataset.needlogin
    let userInfo = app.globalData.userInfo
    console.log(e,userInfo)
    if(userInfo == null && needLogin){
      // 登录
      wx.login({
        success: res => {
          console.log(res)
          // 发送 res.code 到后台换取 openId, sessionKey, unionId
          util.wxRequest(app.globalData.url + 'wx/auth/openid', { code: res.code }, "post").then(res => {
            let data = res.data
            console.log(res)
            if (data.errno == 0) {
              console.log('获取openId成功')
              wx.setStorageSync("openid", data.result)
              wx.navigateTo({
                url: '../auth/accountLogin/accountLogin',
              })
            }
          }).catch(err => {
            console.log(err)
          })
        }
      })
    } else {
      wx.navigateTo({
        url: url
      })
    }
  },
  toLogin: function(){
    let that = this
    let isLogin = that.data.isLogin
    if(isLogin){
      console.log('logout')
      wx.showModal({
        title: '确定退出？',
        content: '退出登录后无法查看订单，重新登录即可查看',
        confirmColor:'#108ee9',
        success(res) {
          if (res.confirm) {
            console.log('用户点击确定')
            wx.removeStorage({
              key: 'userInfo',
              success: function (res) {
                console.log('退出成功')
                that.setData({
                  isLogin: false
                })
                that.onReady()
              },
            })
          } else if (res.cancel) {
            console.log('用户点击取消')
          }
        }
      })
    }else{
      console.log('login')
      // 登录
      wx.login({
        success: res => {
          console.log(res)
          // 发送 res.code 到后台换取 openId, sessionKey, unionId
          util.wxRequest(app.globalData.url + 'wx/auth/openid', { code: res.code }, "post").then(res => {
            let data = res.data
            console.log(res)
            if (data.errno == 0) {
              console.log('获取openId成功')
              wx.setStorageSync("openid", data.result)
              wx.navigateTo({
                url: '../auth/accountLogin/accountLogin',
              })
            }
          }).catch(err => {
            console.log(err)
          })
        }
      })
      // wx.navigateTo({
      //   url: '../auth/accountLogin/accountLogin'
      // })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(app.categories)
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
    let that = this
    let userInfo = app.globalData.userInfo || null
    let isLogin = that.data.isLogin
    console.log(userInfo)
    console.log(!!userInfo)
    if (!!userInfo) {
      that.setData({
        userInfo: userInfo,
        isLogin: true
      })
    }
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