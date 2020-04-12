// pages/goods/search/search.js
const util = require('../../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    searchContent: '',
    listGoods: [],
    cart: {},
    categories: []
  },

  searchContent(e){
    console.log(e.detail.value)
    let that = this
    let searchContent = e.detail.value
    that.setData({
      searchContent: searchContent
    })
  },

  /**
   * 添加到购物车
   */
  addCart: function (e) {
    let that = this;
    let goods = e.currentTarget.dataset.goods
    let goodsName = goods.title
    let goodsPrice = goods.sellprice
    let special = goods.special // 是否特价商品
    let id = goods.goodsId //获取商品id
    console.log(goods)
    let categoryId = goods.categoryId

    let key = 'g' + id
    let cart = that.data.cart
    let isFirstAdd = true;
    let categories = null
    // 判断是否第一次加购物车
    if (null != cart[key] && cart[key].id == id) {
      if (cart[key].number >= 1 && special) {
        wx.showToast({
          title: '该商品限购一份',
          icon: 'none'
        })
        return
      }
      cart[key].number = cart[key].number + 1
      isFirstAdd = false
      categories = that.categoryMark(that.data.currentCategory, categoryId, 1)
      // break
    }
    // 第一次加入购物车
    if (isFirstAdd) {
      cart[key] = { id: goods.goodsId, number: 1, price: goods.sellPrice, name: goods.title, categoryId: goods.categoryId }
      categories = that.categoryMark(that.data.currentCategory, categoryId, 1)
    }
    that.setData({
      cart: cart,
      categories: categories
    })
    wx.setStorageSync("cart", cart)
    this.calCart(cart)
  },

  /**
   * 移除购物车
   */
  reduceCart: function (e) {
    let that = this
    console.log(e)
    let id = e.currentTarget.dataset.id //获取商品id
    let categoryId = e.currentTarget.dataset.categoryid
    let key = 'g' + id
    let cart = that.data.cart
    let categories = null
    // 删除购物车
    if (cart[key].number <= 0) {
      return
    }
    if (null != cart[key] && cart[key].id == id) {
      cart[key].number = cart[key].number - 1
      categories = that.categoryMark(that.data.currentCategory, categoryId, -1)
    }

    that.setData({
      cart: cart,
      categories: categories
    })
    wx.setStorageSync("cart", cart)
    this.calCart(cart)
  },
  /**
  * 汇总购物车数据,购物车对象转数组
  **/
  calCart: function (carts) {
    let totalPrice = 0
    let totalAmount = 0
    let cartList = []
    for (var i in carts) {
      let cart = carts[i]
      if (cart.number == 0) { // 数量为0直接跳过
        continue
      }
      cartList.push(cart)
      totalPrice += cart.price * cart.number
      totalAmount += cart.number
    }
    this.setData({
      totalAmount: totalAmount,
      totalPrice: totalPrice.toFixed(2)
    })
  },

  search(){
    let that = this
    let searchContent = that.data.searchContent
    if(searchContent == ''){
      return
    }
    let data = {content: searchContent}
    wx.showLoading({
      title: '加载商品中',
    })
    util.wxRequest(app.globalData.url + 'wx/goods/search', data, "post").then(res => {
      let data = res.data
      console.log(data)
      if (data.errno == 0) {
        wx.hideLoading()
        let list = data.list
        for(let i = 0; i < list.length; i++){
          list[i].title = list[i].title.replace(searchContent, "<div class='replace-text'>"+searchContent+"</div>")
        }
        that.setData({
          listGoods: list
        })
      }
    }).catch(err => {
      wx.hideLoading()
      that.setData({
        listGoods: []
      })
    })
  },

  /**
   * 目录数量提示
   */
  categoryMark(categoryId, subCayegoryId, number) {
    let that = this
    let categories = that.data.categories
    for (let i = 0; i < categories.length; i++) {
      // if (categories[i].value == categoryId) {
      //   categories[i].number += number
      // }
      let children = categories[i].children
      for (let j = 0; j < children.length; j++) {
        if (children[j].value == subCayegoryId) {
          categories[i]['children'][j].number += number
          categories[i].number += number
        }
      }
    }
    return categories
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
    let that = this
    let cart = wx.getStorageSync("cart") || {}
    let categories = app.categories
    that.setData({
      cart: cart,
      categories: categories
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    console.log('onHide')
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    console.log('onUnload')
    app.categories = this.data.categories
    console.log(app.categories)
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