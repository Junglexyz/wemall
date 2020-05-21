// pages/order/order.js
const util = require('../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    status: [{value: '全部', number: 0}, {value: '待付款', number: 0}, {value: '待发货', number: 0}, {value: '待收货', number: 0}],
    orderStatus: ['已完结','待付款','待发货','待收货'],
    currentIndex: 0,
    orderList: [],
    orderListAll: [],
    currentIndex: 0,
    login: false
  },

  pay(e) {
    let that = this
    let orderInfo = e.currentTarget.dataset.order
    let openId = app.globalData.userInfo.openId
    let order = { payMoney: orderInfo.payMoney, orderId: orderInfo.orderId, openid: openId }
    wx.showLoading({
      title: '发起支付',
    })
    util.wxRequest(app.globalData.url + 'wx/order/prepay', { order: order }, "post").then(res => {
      let data = res.data
      console.log(data)
      if (data.errno == 0) {
        console.log("支付过程开始");
        wx.hideLoading()
        const payParam = res.data.result;
        wx.requestPayment({
          'timeStamp': payParam.timeStamp,
          'nonceStr': payParam.nonceStr,
          'package': payParam.packageValue,
          'signType': payParam.signType,
          'paySign': payParam.paySign,
          success: function (res) {
            console.log("支付过程成功");
            wx.redirectTo({
              url: '/pages/checkout/result/result?status=1&payMoney=' + order.payMoney
            });
          },
          fail: function (err) {
            console.log("支付过程失败");
            console.log(err);
            wx.redirectTo({
              url: '/pages/checkout/result/result?status=0&payMoney=' + order.payMoney + '&orderId=' + order.orderId
            })
          },
          complete: function (comp) {
            console.log("支付过程结束")
            console.log(comp);
          }
        });
      }
    }).catch(err => {
      console.log(err)
    })
  },
  /**
   * 状态切换
  **/
  status: function (e){
    let that = this
    let index = e.currentTarget.dataset.index
    let orderList = that.data.orderListAll
    // 状态1待发货订单
    let orderListStatus = []
    for(let i = 0; i < orderList.length; i++){
      if(orderList[i].status == index){
        orderListStatus.push(orderList[i])
      }
    }
    that.setData({
      currentIndex: index,
      orderList: index != 0 ? orderListStatus : orderList
    })
  },
  unfold: function(e){
    let that = this
    let index = e.currentTarget.dataset.index
    let currentIndex = that.data.currentIndex
    let orderListAll = []
    if(currentIndex == 0){
      orderListAll = that.data.orderListAll
    }else{
      orderListAll = that.data.orderList
    }
    for(let i = 0; i < orderListAll.length; i ++){
      if(i == index){
        let currentOrder = orderListAll[index].orderDetail
        orderListAll[i].fold = false
        for(let j = 0; j < currentOrder.length; j++){
          currentOrder[j].unfold = true
        }
      }else{
        orderListAll[i].fold = orderListAll[i].fold
        let currentOrder = orderListAll[i].orderDetail
        for(let j = 0; j < currentOrder.length; j++){
          currentOrder[j].unfold = currentOrder[j].unfold
        }
      }
    }
    that.setData({
      orderList:orderListAll
    })
  }, 
  fold: function (e) {
    let that = this
    let index = e.currentTarget.dataset.index
    let currentIndex = that.data.currentIndex
    let orderListAll = []
    if(currentIndex == 0){
      orderListAll = that.data.orderListAll
    }else{
      orderListAll = that.data.orderList
    }
    for(let i = 0; i < orderListAll.length; i ++){
      if(i == index){
        let currentOrder = orderListAll[index].orderDetail
        orderListAll[index].fold = true
        for(let j = 0; j < currentOrder.length; j++){
          currentOrder[j].unfold = false
        }
      }else{
        orderListAll[i].fold = orderListAll[i].fold
        let currentOrder = orderListAll[i].orderDetail
        for(let j = 0; j < currentOrder.length; j++){
          currentOrder[j].unfold = currentOrder[j].unfold
        }
      }
    }
    that.setData({
      orderList: orderListAll,
    })
  },  

  login: function () {
    // 登录
    wx.login({
      success: res => {
        console.log(res)
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        util.wxRequest(app.globalData.url + 'wx/auth/openid', { code: res.code }, "post").then(res => {
          let data = res.data
          if (data.errno == 0) {
            console.log('获取openId成功')
            wx.setStorageSync("openid", data.result)
            wx.navigateTo({
              url: '../../pages/auth/accountLogin/accountLogin',
            })
          }
        }).catch(err => {
          console.log(err)
        })
      }
    })
  },
  /**
   * 取消订单倒计时
   **/

  countDown: function(){
    let orderList = that.data.orderListAll
    for(let i = 0; i < orderList.length; i++){
      if(orderList[i].status == 1){
        let crateTime = new Date(orderList[i].createTime).getTime()
        let now = new Date().getTime()
        orderList.leftTime = 30 - (now - createTime) / (1000 * 60);
      }
    }
    this.setData({
      orderListAll: orderList
    })
  },

  /**
   * 生命周期函数--监听页面加载
   **/
  onLoad: function (options) {
    let that = this
    if (app.globalData.userInfo == null){
      that.setData({
        login: true
      })
      return
    }
    let status = that.data.status
    let orderStatus = that.data.orderStatus
    let order = {}
    order.status = 0
    order.createTime = util.formatTimeBefore30(new Date())
    order.endTime = util.formatTime(new Date())
    order.userId = app.globalData.userInfo.userId
    // 初始化订单
    util.wxRequest(app.globalData.url + 'wx/order/list', {order: order}, "post").then(res => {
      let data = res.data
      console.log(data)
      if(data.errno == 0){
        let orderListAll = data.result.list
        for(let i = 0; i < orderListAll.length; i++) {
          if (orderListAll[i].status > 3 || orderListAll[i].status == 0){
            continue
          }
          if (orderListAll[i].status == 1) {
            let createTime = new Date(orderListAll[i].createTime).getTime()
            let now = new Date().getTime()
            console.log(orderListAll[i],createTime, now, now-createTime)
            orderListAll[i].leftTime = (30 - (now - createTime) / (1000 * 60)).toFixed(0);
            // 删除超时订单
            if ((30 - (now - createTime) / (1000 * 60)).toFixed(0) < 0){
              let deleteData = { id: orderListAll[i].id, orderId: orderListAll[i].orderId}
              util.wxRequest(app.globalData.url + 'wx/order/delete', deleteData, "post").then(res => {
                let data = res.data
                if (data.errno == 0) {
                  status[orderList[index].status].number -= 1
                  let spliceOrder = orderList.splice(index, 1)
                  console.log(spliceOrder)
                  that.setData({
                    orderList: orderList,
                    status: status
                  })
                  wx.showToast({
                    title: '取消成功！',
                  })
                  return true
                }
              }).catch(err => {

              })
              orderListAll.splice(i, 1)
              i--
              continue
            }
          }
          status[orderListAll[i].status].number = status[orderListAll[i].status].number + 1
        }
        for(let i = 0; i < orderListAll.length; i ++){
          let currentOrder = orderListAll[i].orderDetail
          orderListAll[i].fold = true
          orderListAll[i].createTime = util.formatTime(new Date(orderListAll[i].createTime))
          orderListAll[i]['orderStatus'] = orderStatus[orderListAll[i].status]
          let totalNumber = 0
          for(let j = 0; j < currentOrder.length; j++){
            currentOrder[j].unfold = false
            totalNumber = totalNumber + currentOrder[j].number
          }
          orderListAll[i].totalNumber = totalNumber
        }
        console.log(orderListAll)
        that.setData({
          orderList: orderListAll,
          status: status,
          orderListAll: orderListAll
        })
      }
     }).catch(err => {
        console.log(err)
        console.log('获取订单列表失败！')
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
    console.log('到底了')
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  removeOrder: function(e){
    console.log(e)
    let that = this
    let orderList = that.data.orderList
    let order = e.currentTarget.dataset.order
    let index = e.currentTarget.dataset.index
    console.log(index)
    let result = this.deleteOrder(order,index)
    console.log(result)
    if(result){
      wx.showToast({
        title: '取消成功！',
      })
    }
  },
  /**
   * 删除超时未支付订单
   */
  deleteOrder(order,index = 0){
    let that = this
    console.log('index:',index)
    let data = {id: order.id, orderId: order.orderId}
    let orderList = that.data.orderList
    let currentIndex = that.data.currentIndex
    let status = that.data.status
    console.log(orderList)
    util.wxRequest(app.globalData.url + 'wx/order/delete', data, "post").then(res => {
      let data = res.data
      if (data.errno == 0) {
        status[orderList[index].status].number -= 1
        let spliceOrder = orderList.splice(index, 1)
        console.log(spliceOrder)
        that.setData({
          orderList: orderList,
          status: status
        })
        wx.showToast({
          title: '取消成功！',
        })
        return true
      }
    }).catch(err => {

    })
  }
})