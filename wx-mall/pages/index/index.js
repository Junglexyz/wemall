//index.js
//获取应用实例
const util = require('../../utils/util.js')
const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    banners: [],
    listCategory: [],
    specialGoods: []
  },
  navigateToGoods: function(e){
    let that =this
    let index = e.currentTarget.dataset.index
    let categoryId = e.currentTarget.dataset.categoryid
    app.goodsCategory = {categoryIndex: index, categoryId: categoryId, fromPage: true }
    wx.reLaunch({
      url: '../goods/goods'
    })
  },
  onLoad: function () {
    let that = this
    wx.showLoading({
      title: '数据加载中...',
    })
    that.getListCategory()
    util.wxRequest(app.globalData.url + 'wx/home/index', {}, "post").then(res => {
      let data = res.data
      console.log(res)
      if(data.errno == 0){
        wx.hideLoading()
        that.setData({
          banners: data.result.listAd,
          specialGoods: data.result.listSpecialGoods,
          listCategory: data.result.listCategory
        })
      }
     }).catch(err => {
        console.log(err)
     })
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function(e) {
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  /**
   * 获取商品分类
   */
  getListCategory: function () {
    util.wxRequest(app.globalData.url + 'wx/category/list', {}, "GET").then(res => {
      let data = res.data
      if (data.errno == 0) {
        let categories = data.list
        let cascader = []
        for (let i = 0; i < categories.length; i++) {
          let subCategory = []
          let child = categories[i].children
          for (let j = 0; j < child.length; j++) {
            let subObj = { label: child[j].name, value: child[j].id, level: child[j].level, number: 0 }
            subCategory.push(subObj)
          }
          let obj = { label: categories[i].name, value: categories[i].id, level: categories[i].level, children: subCategory ,number: 0}
          cascader.push(obj)
        }
        let subCurrentCategory = cascader[0].children[0].value
        app.categories = cascader
        this.setData({
          categories: cascader,
          currentCategory: cascader[0].value,
          subCurrentCategory: subCurrentCategory
        })
        // this.getListGoods(subCurrentCategory)
      }
    }).catch(err => {
      console.log(err)
    })
  }
})
