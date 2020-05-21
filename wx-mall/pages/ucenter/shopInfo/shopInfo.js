// pages/ucenter/shopInfo/shopInfo.js
const util = require('../../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    shopInfo: {}
  },

  makePhoneCall: function(){
    let that = this
    console.log('makepCall')
    wx.makePhoneCall({
      phoneNumber: that.data.shopInfo.contanctMobile
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('0000')
    let that = this
    util.wxRequest(app.globalData.url + 'wx/shopInfo/info', {}, "post").then(res => {
      let data = res.data
      console.log(data)
      if(data.errno == 0){
        that.setData({
          shopInfo: data.result
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
    console.log('0000')
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