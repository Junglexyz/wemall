// pages/goods/goods.js
const util = require('../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods: [],
    listGoods: [],
    categories: [],
    cart: {},
    totalAmount: 0,
    totalPrice: 0,
    index: 1,
    categoryId: 0,
    top: 0,
    currentCategory: 0,
    subCurrentCategory: 0,
    goodsPlus: false,
    goodsUrl: '',
    specVisiable: false,
    goodsItem: {},
    coupon: 0, //是否满减
  },
  imgPlus(e){
    let cover = e.currentTarget.dataset.cover
    this.setData({
      goodsPlus: true,
      goodsUrl: cover
    })
  },
  cancelPlus(){
    this.setData({
      goodsPlus: false
    })
  },

  search(){
    wx.navigateTo({
      url: './search/search',
    })
  },
  /**
   * 选规格窗口
   */
  specVisiable(e){
    let that = this
    let specVisiable = that.data.specVisiable
    let cart = that.data.cart
    if(specVisiable == true){
      this.setData({
        specVisiable: false
      })
      return 
    }
    let goodsItem = e.currentTarget.dataset.goodsitem
    if (cart['g' + goodsItem.goodsId] != null && cart['g'+goodsItem.goodsId].number > 0){
      goodsItem.sellPrice = cart['g' + goodsItem.goodsId].price
      goodsItem.unit = goodsItem.unit
      goodsItem.specId = cart['g' + goodsItem.goodsId].specId
    } else {
      // 默认选中第一个规格值
      goodsItem.sellPrice = goodsItem.specifications[0].price
      goodsItem.unit = goodsItem.specifications[0].specification
      goodsItem.specId = goodsItem.specifications[0].id
    }
    this.setData({
      specVisiable: !specVisiable,
      goodsItem: goodsItem
    })
  },
  /**
   * 选规格
   * 选中项改变sellPrice为当前售价
   * 
   */
  selectSpecification(e){
    let that = this
    let spec = e.currentTarget.dataset.spec
    let goodsItem = that.data.goodsItem
    let categories = that.data.categories
    let cart = that.data.cart
    let key = 'g' + spec.goodsId
    if (cart[key] != null && cart[key].specId != null && cart[key].specId != spec.id) {
      cart[key].price = goodsItem.sellPrice
      categories = that.categoryMark(that.data.currentCategory, that.data.subCurrentCategory, -cart[key].number)
      cart[key].number = 0
    }
    goodsItem.sellPrice = spec.price
    goodsItem.unit = spec.specification
    goodsItem.specId = spec.id
    that.setData({
      goodsItem: goodsItem,
      cart: cart,
      categories: categories
    })
    wx.setStorageSync("cart", cart)
    this.calCart(cart)
  },

  /**
   * 点击切换目录
   */
  changeCategory: function(e){
    let that = this
    let categoryId = e.currentTarget.dataset.id
    let index = e.currentTarget.dataset.index
    let categories = that.data.categories || app.categories
    let subCurrentCategory = categories[index].children[0].value
    console.log(categories[index].children[0].coupon)
    that.setData({
      coupon: categories[index].children[0].coupon
    })
    that.loadCategory(categoryId, subCurrentCategory)
  },
  /**
   * 加载类目详情
   * currentCategory 类目id
   * subCurrentCategory 子类目id
   */
  loadCategory: function (currentCategory,subCurrentCategory) {
    let that = this
    that.getListGoods(subCurrentCategory)
    that.setData({
      currentCategory: currentCategory,
      subCurrentCategory: subCurrentCategory
    })
  },
  /**
   *获取商品列表
   */
  getListGoods: function (categoryId) {
    wx.showLoading({
      title: '加载中',
    })
    let that = this
    util.wxRequest(app.globalData.url + 'wx/goods/list', { categoryId: categoryId }, "post").then(res => {
      let data = res.data
      console.log(data)
      if (data.errno == 0) {
        wx.hideLoading()
        that.setData({
          listGoods: data.list
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
   * 点击切换子目录
   */
  subChangeCategory: function (e) {
    let that = this
    let subCategoryId = e.currentTarget.dataset.subid
    let coupon = e.currentTarget.dataset.coupon
    that.getListGoods(subCategoryId)
    that.setData({
      subCurrentCategory: subCategoryId,
      coupon: coupon
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
    let coupon = e.currentTarget.dataset.coupon
    let special = goods.special // 是否特价商品
    let id = goods.goodsId //获取商品id
    console.log(coupon)
    let key = 'g' + id
    let cart = that.data.cart
    let isFirstAdd = true;
    let categories = null
    // 判断是否第一次加购物车
    if (null != cart[key] && cart[key].id == id) {
      // if (cart[key].number >= 1 && special){
      //   wx.showToast({
      //     title: '该商品限购一份',
      //     icon: 'none'
      //   })
      //   return
      // }
      if (cart[key].specId != null && cart[key].specId != goods.specId){
        cart[key].price = goods.sellPrice
        cart[key].unit = goods.unit
        categories = that.categoryMark(that.data.currentCategory, that.data.subCurrentCategory, -cart[key].number)
        cart[key].number = 0
      } else {
        cart[key].number = cart[key].number + 1
        isFirstAdd = false
        categories = that.categoryMark(that.data.currentCategory, that.data.subCurrentCategory, 1)
      }
    }
    // 第一次加入购物车
    if (isFirstAdd) {

      cart[key] = { id: goods.goodsId, number: 1, price: goods.sellPrice, name: goods.title, categoryId: goods.categoryId, specId: goods.specId, unit: goods.unit, fullDecrement: coupon}
      categories = that.categoryMark(that.data.currentCategory, that.data.subCurrentCategory, 1)
    }
    that.setData({
      cart: cart,
      categories: categories
    })
    console.log(cart)
    wx.setStorageSync("cart", cart)
    this.calCart(cart)
  },
  /**
   * 目录数量提示
   */
  categoryMark(categoryId, subCayegoryId, number){
    let that = this
    let categories = that.data.categories
    console.log(categories)
    for(let i = 0; i < categories.length; i ++){
      if(categories[i].value == categoryId){
        categories[i].number += number
      }
      let children = categories[i].children
      for(let j = 0; j < children.length; j++){
        if(children[j].value == subCayegoryId){
          categories[i]['children'][j].number += number
        }
      }
    }
    return categories
  },
  /**
   * 移除购物车
   */
  reduceCart: function (e) {
    let that = this
    let id = e.currentTarget.dataset.id //获取商品id
    let goods = e.currentTarget.dataset.goods
    let key = 'g' + goods.goodsId
    let cart = that.data.cart
    console.log(goods)
    console.log(cart)
    let categories = null
    // 删除购物车
    if (cart[key].number <= 0){
      return
    }
    if (null != cart[key] && cart[key].id == goods.goodsId) {
      cart[key].number = cart[key].number - 1
      categories = that.categoryMark(that.data.currentCategory, that.data.subCurrentCategory, -1)
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
  calCart: function(carts){
    let totalPrice = 0
    let totalAmount = 0
    let cartList = []
    for(var i in carts){
      let cart = carts[i]
      if(cart.number == 0){ // 数量为0直接跳过
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
  toCheckout: function(){
    let that = this
    let totalAmount = that.data.totalAmount
    let totalPrice = that.data.totalAmount
    if(totalAmount == 0){
      return
    }
    wx.navigateTo({
      url: '/pages/checkout/checkout?totalAmount='+totalAmount+'&totalPrice='+totalPrice
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
    let that = this
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    let that = this
    let categories = app.categories
    let category = {}
    let cart = wx.getStorageSync("cart") || {}
    let index = 0
    // 首页点击的类目
    if (!app.goodsCategory) {
      category = { categoryIndex: categories[0].value, categoryId: categories[0].value }
    } else {
      category = app.goodsCategory
      index = category.categoryIndex
    }

    let categoryId = category.categoryId
    that.setData({
      categories: categories,
      cart: cart,
      coupon: categories[index].children[0].coupon
    })
    that.calCart(cart)
    let subCurrentCategory = categories[index].children[0].value
    that.loadCategory(categoryId, subCurrentCategory)
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
    console.log('到顶了')
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    console.log('到底了')
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  amountOver(){
    wx.showToast({
      title: '已售罄',
      icon: 'none'
    })
  },
  toPay(){
    let totalAmount = this.data.totalAmount
    let totalPrice = this.data.totalPrice
    let userInfo = app.globalData.userInfo
    if (userInfo == null) {
      // 登录
      wx.login({
        success: res => {
          // 发送 res.code 到后台换取 openId, sessionKey, unionId
          util.wxRequest(app.globalData.url + 'wx/auth/openid', { code: res.code }, "post").then(res => {
            let data = res.data
            if (data.errno == 0) {
              wx.setStorageSync("openid", data.result)
              wx.navigateTo({
                url: '/pages/auth/accountLogin/accountLogin',
              })
            }
          }).catch(err => {
            console.log(err)
          })
        }
      })
    } else {
      wx.navigateTo({
        url: "/pages/checkout/checkout?totalAmount={{totalAmount}}&totalPrice={{totalPrice}}"
      })
    }
  }
})