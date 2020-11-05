// pages/checkout/checkout.js
const util = require('../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    way: 'mall',
    fold: true,
    goodsList: [],
    preferentialInfos: [], // 优惠详情
    sumOfMoney: 0.00,
    sumOfPre: 0.00, //优惠总额
    sumOfNumber: 0, //订单商品总数
    discountMoney: 0.00,
    defaultAddress: false,
    address:{},
    tip: '', // 优惠提示
    tips: '',
    fullDecrement: 0.00
  },
  /**
   *选择收货地址
   */
  selectAddress: function(){
    wx.navigateTo({
      url: '../address/address?fromed=select'
    })
  },
  /**
  * 配送方式选择
  **/
  selectWay: function(e) {
    let that = this
    let way = e.currentTarget.dataset.way
    let sumOfMoney = that.data.sumOfMoney
    if(way == 'home'){
      if (sumOfMoney < app.globalData.home){
        wx.showToast({
          title: '满' + app.globalData.home + '元指定范围内用户可配送到家',
          icon: 'none',
          duration: 2000
        })
        return
      }
      let address = that.data.address
      if (address.province > 0) {
        let sendCost = parseInt(address.province) - 1
        that.setData({
          sumOfMoney: that.data.sumOfMoney + sendCost,
          sendCost: sendCost 
        })
      }
      that.setData({
        way : 'home'
      })
    }else{
      let sendCost  = that.data.sendCost
      if(sendCost > 0){
        that.setData({
          sumOfMoney: that.data.sumOfMoney - sendCost,
          sendCost: 0
        })
      }
      that.setData({
        way : 'mall'
      })
    }
  },
  /**
   * 展开与折叠商品列表
  **/
  foldGoodsList: function(){
    let that = this
    let fold = that.data.fold
    let goodsList = that.data.goodsList
    let len = goodsList.length
    if(fold){
      fold = false
      for(let i = 0; i < len; i++){
        goodsList[i].fold = true
      }
    }else{
      fold = true
      for(let i = 0; i < len; i++){
        if(i>1){
            goodsList[i].fold = false
        }
      }
    }
    that.setData({
      fold: fold,
      goodsList: goodsList
    })
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({
      title: '结算中...',
      mask: true
    })
    let userInfo = app.globalData.userInfo
    if (userInfo == null) {
      wx.navigateTo({
        url: '../../pages/auth/accountLogin/accountLogin'
      })
      return
    } 
    let that = this
    let goodsObject = wx.getStorageSync('cart') || []
    let goodsList = []
    let sumOfPre = 0 // 优惠金额
    let sumOfMoney = 0
    let sumOfNumber = 0
    let fullDecrement = 0 // 满减门槛
    let discountMoney = 0 // 优惠金额
    let tip = ''
    let tips = ''
    // 汇总订单
    for(let i in goodsObject){
      let goods = goodsObject[i]
      // 过滤无效商品
      if(goods.number == 0 || i.substring(0,1) != 'g'){
        continue
      }
      if(goods.categoryId == 100029 || goods.categoryId == 100030 || goods.categoryId == 100038){
        tips = '您的订单存在散装称重商品，该类商品以实际称重为准， 最终结算金额多退少补'
      }
      sumOfMoney += goods.number * goods.price
      sumOfPre += goods.price
      sumOfNumber += goods.number
      if(goods.fullDecrement == 1){
        fullDecrement += goods.number * goods.price
      }
      let goodsObj = {id: goods.id, name: goods.name, number:goods.number, price:parseFloat(goods.number * goods.price).toFixed(2), categoryId: goods.categoryId, unit: goods.unit}
      if(goodsList.length <5){
        goodsObj['fold'] = true
      }
      goodsList.push(goodsObj)
    }
    console.log(sumOfMoney, fullDecrement)
    // 计算优惠详情
    util.wxRequest(app.globalData.url + 'wx/coupon/getCouponByUser', {userId: app.globalData.userInfo.userId}, "post").then(res => {
      console.log(res)
      let data = res.data
      if(data.errno == 0){
        let preferentialInfos = data.list // 优惠券信息
        let coupons = []
        let indepence = false // 不叠加使用的红包标记 true则不享有其他优惠
        let max = that.max(preferentialInfos, fullDecrement)
        console.log(max)
        if(max.length != 0){
          coupons.push(max) // 最大满减额度
          discountMoney += max.discountMoney
        }
        for(let i = 0; i < preferentialInfos.length; i++){
          if(preferentialInfos[i].types != '1'){
            coupons.push(preferentialInfos[i])
            discountMoney += preferentialInfos[i].discountMoney
          }
          // 不叠加使用红包
          if (preferentialInfos[i].types == '3'){
            coupons = []
            coupons.push(preferentialInfos[i])
            discountMoney = preferentialInfos[i].discountMoney
            indepence = true
            break
          }
        }
        let address = wx.getStorageSync('selectedAddress') || {} //获取默认地址
        // 不叠加红包使用金额校验
        if (indepence && coupons[0].discountMoney > sumOfMoney){
          wx.showToast({
            title: '您的红包满' + (coupons[0].discountMoney+0.01)+ '可用',
            icon: 'none'
          })
          discountMoney = 0.00
          tip = '满' + (coupons[0].discountMoney+0.01) + '可用'
        }
        // 优惠券使用条件
        // if (indepence && coupons[0].discountMoney < sumOfMoney){
        //   sumOfMoney = sumOfMoney - discountMoney
        // }
        if (indepence && coupons[0].discountMoney < sumOfMoney){
          sumOfMoney = sumOfMoney - discountMoney
        }
        if(!indepence){
          sumOfMoney = sumOfMoney - discountMoney
        }
        let way = 'mall'
        // 取货条件
        if(sumOfMoney > app.globalData.home){
          way = 'home'
        }
        if (address.id != null && way == 'home') {
          let sendCost = 0
          if (address.province != null && address.province > 0){
            sendCost = parseInt(address.province) - 1
          }
          that.setData({
            tip: tip,
            tips: tips,
            address: address,
            defaultAddress: true,
            goodsList: goodsList,
            sumOfMoney: (sumOfMoney + sendCost).toFixed(2),
            sumOfPre: sumOfPre.toFixed(2),
            sumOfNumber: sumOfNumber,
            fold: true,
            way:way,
            preferentialInfos: coupons,
            discountMoney: discountMoney,
            sendCost: sendCost,
            fullDecrement: fullDecrement
          })
        } else {
          that.setData({
            tip: tip,
            tips: tips,
            goodsList: goodsList,
            sumOfMoney: sumOfMoney.toFixed(2),
            sumOfPre: sumOfPre.toFixed(2),
            sumOfNumber: sumOfNumber,
            fold: true,
            way: way,
            preferentialInfos: coupons,
            discountMoney: discountMoney,
            fullDecrement: fullDecrement
          })
        }
      wx.hideLoading()
      }
    }).catch(err => {
      console.log(err)
      that.setData({
        preferentialInfos: []
      })
    })
  },
  /**提交订单支付*/
  pay: function(){
    let that = this
    let address = that.data.address
    let way = (that.data.way == 'home' ? 1 : 2) // 配送方式 1送货上门 2到店自取
    if(way == 1 && (Object.keys(address).length == 0 || address == null)){
      wx.showToast({
        title: '请填写地址',
        icon: 'none'
      })
      return
    }
    let cart = that.data.goodsList
    let userId = app.globalData.userInfo.userId 
    let payMoney = that.data.sumOfMoney //- that.data.discountMoney // 实付金额 = 订单总额 - 优惠金额
    let totalMoney = that.data.sumOfMoney // 订单总额
    let discountMoney = that.data.discountMoney // 折扣金额 满减优惠
    let returnMoney = 0  // 返现金额
    let totalNumber = that.data.sumOfNumber // 订单商品总数
    let payWay = 1 // 支付方式 1：在线支付
    let status = 1 // 订单状态 1：未付款
    let pres = that.data.preferentialInfos
    let coupons = []

    // 筛选优惠详情   1为满减
    for(let i = 0; i < pres.length; i++){
      if(pres[i].types != '1'){
        coupons.push({couponId: pres[i].couponId})
      }
    }
    let order = {
      userId: userId, 
      payMoney: payMoney, 
      totalMoney: totalMoney, 
      totalNumber: totalNumber,
      payWay: payWay, 
      discountMoney: discountMoney, 
      returnMoney: returnMoney,
      status: status,
      deliveryWay: way   // 配送方式
    }
    if (Object.keys(address).length != 0 && address.town == '到店自取'){
      order.deliveryWay = 2
    }
    let sendCost = that.data.sendCost
    if(way == 1){
      order.addressId = address.id
      order.sendCost = sendCost
    }
    console.log(order)
    let ordersDetail = [] // 订单详情
    for(let i = 0; i < cart.length; i++){
      let obj = {goodsId: cart[i].id, title: cart[i].name, sellPrice: cart[i].price, number: cart[i].number, categoryId: cart[i].categoryId}
      ordersDetail.push(obj)
    } 

   // 微信通知
    wx.requestSubscribeMessage({
      tmplIds: app.globalData.tmplIds,
      success(subscribeRes) {
        if (subscribeRes['r3z_88IXEvYIBcYvGizFH8Z7KgBZhEOX8KqqxDSKSf0'] == 'accept' && subscribeRes['IhX6Zo8c8zsC56j_MxtHX3HeAHwruceTywI7UV2_jLI'] == 'accept'){

          // wx.showModal({
          //   title: '确定下单？',
          //   content: '散装称重商品以实际称重为准，可能存在误差, 最终结算金额多退少补',
          //   confirmColor: '#108ee9',
          //   success(res) {
          //     if (res.confirm) {
          //       console.log('用户点击确定')
          //       wx.removeStorage({
          //         key: 'userInfo',
          //         success: function (res) {
          //           console.log('退出成功')
          //           that.setData({
          //             isLogin: false
          //           })
          //           that.onReady()
          //         },
          //       })
          //     } else if (res.cancel) {
          //       console.log('用户点击取消')
          //     }
          //   }
          // })

          wx.showLoading({
            title: '发起支付',
            mask: true
          })
          let data = { order: order, orders: ordersDetail, coupons: coupons }
          util.wxRequest(app.globalData.url + 'wx/order/submit', data, "post").then(submitRes => {
            let data = submitRes.data
            if (data.errno == 0) {
              let sellOutGoodsLength = data.result.sellOutGoods.length
              let sellOutGoods = data.result.sellOutGoods
              if(sellOutGoodsLength != 0){
                // TODO 商品售罄解决方案
                let cart = wx.getStorageSync("cart")
                let tip = ''
                for(let i = 0; i < sellOutGoodsLength; i ++){
                  let key = 'g' + sellOutGoods[i].goodsId
                  cart[key].number = 0
                  that.categoryMark(sellOutGoods[i].categoryId, -sellOutGoods[i].number)
                  if(i < 2){
                    tip += sellOutGoods[i].title
                  }
                }
                wx.setStorageSync("cart", cart)

                wx.showModal({
                  title: '部分商品售罄',
                  content: tip+'等库存不满足您的需求，请重新下单',
                  confirmColor: '#108ee9',
                  success(res) {
                    if (res.confirm) {
                      wx.navigateBack({
                        
                      })
                    } else if (res.cancel) {
                      wx.navigateBack({
                        
                      })
                    }
                  }
                })
                return
              }
              wx.removeStorage({
                key: 'cart',
                success: function(res) {},
              })
              order.orderId = data.result.orderId
              order.mobile = app.globalData.userInfo.mobile
              order.openid = app.globalData.userInfo.openId
              util.wxRequest(app.globalData.url + 'wx/order/prepay', { order: order }, "post").then(payRequestRes => {
                let data = payRequestRes.data
                if (data.errno == 0) {
                  wx.hideLoading()
                  const payParam = payRequestRes.data.result;
                  wx.requestPayment({
                    'timeStamp': payParam.timeStamp,
                    'nonceStr': payParam.nonceStr,
                    'package': payParam.packageValue,
                    'signType': payParam.signType,
                    'paySign': payParam.paySign,
                    success: function (paymeRes) {
                      wx.redirectTo({
                        url: './result/result?status=1&payMoney=' + order.payMoney
                      });
                    },
                    fail: function (paymentErr) {
                      wx.redirectTo({
                        url: './result/result?status=0&payMoney=' + order.payMoney + '&orderId=' + order.orderId
                      });
                    },
                    complete: function (patmentComp) {
                    }
                  });
                  // 支付成功
                  that.removeCategoryMark()
                }
              }).catch(payRequestErr => {
                console.log(payRequestErr)
              })
            }
          }).catch(submitErr => {
            console.log(submitErr)
          })
        } else{
          wx.showModal({
            title: '打开支付失败',
            content: '请打开微信通知以便后续收取订单相关通知',
            confirmColor: '#108ee9',
            success(res) {
              if (res.confirm) {
                console.log('用户点击确定')
                wx.openSetting({
                  
                })
              } else if (res.cancel) {
                console.log('用户点击取消')
              }
            }
          })
        }
      },
      fail(subscribeErr) {
        console.log(subscribeErr)
      }
    })

  },
  /**
   * 目录数量提示
   */
  categoryMark(subCayegoryId, number) {
    let that = this
    let categories = app.categories
    for (let i = 0; i < categories.length; i++) {
      let children = categories[i].children
      for (let j = 0; j < children.length; j++) {
        if (children[j].value == subCayegoryId) {
          app.categories[i].number += number
          app.categories[i]['children'][j].number += number
        }
      }
    }
    return categories
  },
  /**
   * 清楚目录数量提示
   */
  removeCategoryMark() {
    let that = this
    let categories = app.categories
    for (let i = 0; i < categories.length; i++) {
      let children = categories[i].children
      for (let j = 0; j < children.length; j++) {
        app.categories[i].number = 0
        app.categories[i]['children'][j].number = 0
      }
    }
    app.categories = categories
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
    let address = wx.getStorageSync('selectedAddress') || {}
    let way = that.data.way
    console.log(address)
    let sendCost = 0
    if (address.province != null && address.province > 0) {
      sendCost = parseInt(address.province - 1)
    }
    if(address.id != null){
      that.setData({
        address: address,
        defaultAddress: true,
        sendCost: way == 'home' ? sendCost : 0
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

  },
  max(pres, totalMoney){
    let max = -1
    for(let i = 0; i < pres.length; i++){
      if (pres[i].types == '1' && pres[i].targetMoney <= totalMoney) {
        max = i
        continue
      }
      if (max == -1){
        continue
      }
      if (pres[i].types == '1' && pres[i].targetMoney > pres[max].targetMoney && pres[i].targetMoney <= totalMoney){
        max = i
      }
    }
    return max != -1 ? pres[max] : []
  }
})