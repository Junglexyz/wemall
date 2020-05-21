//app.js
const util = require('./utils/util.js')
App({
  onLaunch: function () {
    // 展示本地存储能力
    // var logs = wx.getStorageSync('logs') || []
    // logs.unshift(Date.now())
    // wx.setStorageSync('logs', logs)
    // 用户信息获取
    var userInfo = wx.getStorageSync('userInfo') || null
    if(userInfo){
      this.globalData.userInfo = userInfo
    }

    wx.removeStorage({
      key: 'cart',
      success: function(res) {
        console.log('删除购物车缓存')
      },
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              console.log(res)
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null,
    home: 188,
    url: 'http://localhost:8081/'
    // url: 'https://www.junglechen.xyz/'
  }
})
/**
 {
        "pagePath": "pages/goods/goods",
        "iconPath": "/images/category.png",
        "selectedIconPath": "/images/category@selected.png",
        "text": "分类"
      },
 */