// pages/address/address.js
const util = require('../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    listAddress: [],
    fromed: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let fromed = options.fromed
    if(fromed == 'select'){
      wx.setNavigationBarTitle({
        title: "选择收货地址"
      })
    }else{
      wx.setNavigationBarTitle({
        title: "我的收货地址"
      })
    }
    this.setData({
      fromed: fromed
    })
  },
  getAddress: function(e){
    let that = this
    let index = e.currentTarget.dataset.index
    let fromed = that.data.fromed
    let listAddress = that.data.listAddress
    if(fromed == 'select'){
      wx.setStorageSync('selectedAddress', listAddress[index])
      wx.navigateBack({
      
      })
    }
  },
  /** 跳转新增收货地址页面*/
  createAddress: function(){
    wx.navigateTo({
      url: './address/create'
    })
  },

  /** 跳转新增收货地址页面*/
  updateAddress: function(e){
    console.log(e)
    let index = e.currentTarget.dataset.index
    let _address = this.data.listAddress[index]
    console.log(_address)
    let id = _address.id
    let name = _address.username
    let tel = _address.mobile
    let town = _address.town
    let village = _address.village
    let groups = _address.groups
    let addressDetail = _address.address
    let isDefault = _address.defaulted
    let province = _address.province
    wx.navigateTo({
      url: './address/create?id='+id+'&name='+name+'&tel='+tel+'&town='+town+'&village='+village+'&groups='+groups+'&address='+addressDetail+'&isDefault='+isDefault+'&province='+province
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
    let that = this
    let userInfo = app.globalData.userInfo || null
    let userId
    if (userInfo != null) {
      userId = userInfo.userId
    }
    util.wxRequest(app.globalData.url + 'wx/address/list', { userId: userId }, "post").then(res => {
      let data = res.data
      console.log(data)
      if (data.errno == 0) {
        that.setData({
          listAddress: data.list
        })
      }
    }).catch(err => {
      console.log(err)
      that.setData({
        listAddress: []
      })
    })
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