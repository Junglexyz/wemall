// pages/ucenter/balance/detail/detail.js
const util = require('../../../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    balances: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this
    let userInfo = app.globalData.userInfo
    console.log(userInfo != null)
    let userId = null
    if (Object.keys(userInfo).length != 0 || userInfo != null) {
      userId = userInfo.userId
    }
    util.wxRequest(app.globalData.url + 'wx/withdraw/list', { userId: userId }, "post").then(res => {
      let data = res.data
      console.log(data)
      if (data.errno == 0) {
        let list = data.list
        for(let i = 0; i < list.length; i++){
          list[i].createTime = util.formatTime(new Date(list[i].createTime))
        }
        that.setData({
          balances: list
        })
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