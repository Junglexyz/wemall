// pages/upload/upload.js
const app = getApp()
const util = require('../../utils/util.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods: {},
    price: '',
    totalPrice: null,
    totalUnit: null
  },

  bindinputPrice: function (event) {
    let price = event.detail.value
    console.log(price)
    this.setData({
      price: price
    })
  },
  bindinputTotalPrice: function (event) {
    let totalPrice = event.detail.value
    this.setData({
      totalPrice: totalPrice
    })
  },
  bindinputTotalUnit: function (event) {
    let totalUnit = event.detail.value
    this.setData({
      totalUnit: totalUnit
    })
  },
  submit(e){
    let that = this
    let goods = e.currentTarget.dataset.goods
    let price = that.data.price
    if(price == '' || price == 0){
      wx.showToast({
        title: '价格不能为空或0',
        icon: 'none'
      })
      return
    }
    goods.sellPrice = price
    let specifications = goods.specifications
    specifications[0].price = price
    console.log(that.data.totalPrice)
    if ((that.data.totalPrice != null) && (that.data.totalUnit != null)){
      let spec = {price: that.data.totalPrice, specification: that.data.totalUnit,goodsId: goods.goodsId}
      specifications.push(spec)
    }

    console.log(goods)
    let goodsAllinone = { goods: goods, specifications: specifications }
    util.wxRequest(app.globalData.url + 'wx/goods/update', goodsAllinone, "post").then(res => {
      let data = res.data
      console.log(data)
      if (data.errno == 0) {
        wx.showToast({
          title: '价格修改成功',
        })
        that.setData({
          price: '',
          totalPrice: null,
          totalUnit: null,
          goods: {}
        })
        util.wxRequest(app.globalData.url + 'wx/goods/single', {}, "post").then(res => {
          let data = res.data
          console.log(data)
          if (data.errno == 0) {
            this.setData({
              goods: data.result
            })
          }
        }).catch(err => {
          console.log(err)
          wx.showToast({
            title: '修改失败',
            icon: 'none'
          })
        })
      }
    }).catch(err => {
      console.log(err)
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
    util.wxRequest(app.globalData.url + 'wx/goods/single', {}, "post").then(res => {
      let data = res.data
      console.log(data)
      if (data.errno == 0) {
        this.setData({
          goods: data.result
        })
      }
    }).catch(err => {
      console.log(err)
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