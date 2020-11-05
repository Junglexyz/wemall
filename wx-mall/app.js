//app.js
const util = require('./utils/util.js')
App({
  onLaunch: function () {
    if (wx.canIUse('getUpdateManager')) {
      const updateManager = wx.getUpdateManager()
      updateManager.onCheckForUpdate(function (res) {
        console.log('onCheckForUpdate====', res)
        // 请求完新版本信息的回调
        if (res.hasUpdate) {
          console.log('res.hasUpdate====')
          updateManager.onUpdateReady(function () {
            wx.showModal({
              title: '更新提示',
              content: '新版本已经准备好，是否重启应用？',
              success: function (res) {
                console.log('success====', res)
                // res: {errMsg: "showModal: ok", cancel: false, confirm: true}
                if (res.confirm) {
                  // 新的版本已经下载好，调用 applyUpdate 应用新版本并重启
                  updateManager.applyUpdate()
                }else {
                  wx.showModal({
                    title: '已经有新版本了哟~',
                    content: '新版本已经上线啦~，请您删除当前小程序，重新搜索打开哟~',
                    confirmColor: '#108ee9',
                    success(res) {
                      if (res.confirm) {
                        updateManager.applyUpdate()
                      } else if (res.cancel) {
                        console.log('用户点击取消')
                      }
                    }
                  })
                }
              }
            })
          })
          updateManager.onUpdateFailed(function () {
            // 新的版本下载失败
            console.log('更新失败')
            wx.showModal({
              title: '已经有新版本了哟~',
              content: '新版本已经上线啦~，请您删除当前小程序，重新搜索打开哟~'
            })
          })
        }
      })
    }
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
  },
  globalData: {
    userInfo: null,
    home: 88, //起送价 
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