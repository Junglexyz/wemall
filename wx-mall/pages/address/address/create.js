// pages/address/address/create.js
const util = require('../../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    address: {
      id: 0,
      areaCode: 0,
      address: '',
      name: '',
      tel: '',
      isDefault: 0,
      town: '',
      village: '',
      groups: '',
      goUp: false,
      province: 0 //楼层
    },
    city: '',//存放地区
    station: '',//存放维修站
    perList: '',// 存放维修人员
    multiArray: [[], [], []],
    multiIndex: [-1, -1, -1],
    update: false,
    goUp: false, //上楼
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options)
    let that = this
    let multiArray = that.data.multiArray
    console.log(!options)
    if(options.id){
      wx.setNavigationBarTitle({
        title: "修改收货地址"
      })
      let address = that.data.address
      address.name = options.name
      address.tel = options.tel
      address.province = options.province
      if(options.address == 'undefined'){
        address.addressDetail = ''
      } else {
        address.addressDetail = options.address
      }
      address.isDefault = options.isDefault
      address.id = options.id
      this.getCityStationPer()
      that.setData({
        address: address,
        update: true,
        goUp: (parseInt(address.province) > 1 )
      })
    }else {
      wx.setNavigationBarTitle({
        title: "新增收货地址"
      })
      this.getCityStationPer()
    }
  },

  bindinputName(event) {
    let address = this.data.address;
    address.name = event.detail.value;
    this.setData({
      address: address
    });
  },
  bindinputMobile(event) {
    let address = this.data.address;
    address.tel = event.detail.value;
    this.setData({
      address: address
    });
  },
  bindinputAddress(event){
    let address = this.data.address;
    address.addressDetail = event.detail.value;
    this.setData({
      address: address
    });
  },
  bindIsDefault() {
    let address = this.data.address;
    address.isDefault = !address.isDefault;
    this.setData({
      address: address
    });
  },
  switchSelect(e){
    console.log(e)
    let value = e.detail.value
    let address = this.data.address;
    address.goUp = value;
    this.setData({
      goUp: value,
      address: address
    })
  },
  floorChange(e){
    let address = this.data.address;
    address.province = e.detail.value;
    console.log(address)
    this.setData({
      address: address
    });
  },
  /**
  * 保存地址
  */
  saveAddress() {
    let address = this.data.address;
    console.log(address)
    let userInfo = app.globalData.userInfo || null
    let userId
    if (userInfo != null) {
      userId = userInfo.userId
    }
    if (address.name == '' || address.name == null){
      wx.showToast({
        title: '姓名不能为空',
        icon: 'none'
      })
      return
    }
    if (address.tel == '' || address.tel == null) {
      wx.showToast({
        title: '手机号码不能为空',
        icon: 'none'
      })
      return
    }
    if (address.tel.length != 11) {
      wx.showToast({
        title: '手机号码错误',
        icon: 'none'
      })
      return
    }
    if (address.town == '' || address.town == null){
      wx.showToast({
        title: '请选择地址',
        icon: 'none'
      })
      return
    }
    if (address.village == '' || address.village == null) {
      wx.showToast({
        title: '请选择地址',
        icon: 'none'
      })
      return
    }
    if (address.groups == '' || address.groups == null) {
      wx.showToast({
        title: '请选择地址',
        icon: 'none'
      })
      return
    }
    if ((address.town != '到店自取') && (address.addressDetail == '' || address.addressDetail == null)) {
      wx.showToast({
        title: '详细地址不能为空',
        icon: 'none'
      })
      return
    }
    let data = {
      userId: userId,
      mobile: address.tel,
      username: address.name,
      town: address.town,
      village: address.village,
      groups: address.groups,
      address: address.addressDetail,
      defaulted: address.isDefault ? 1 : 0,
      province: address.province // 楼层及费用
    }
    util.wxRequest(app.globalData.url + 'wx/address/create', data, "post").then(res => {
      let data = res.data
      console.log(data)
      if(data.errno == 0){
        console.log('创建成功！')
        wx.navigateBack({
          
        })
      }
     }).catch(err => {
        console.log(err)
        that.setData({
          listGoods:[]
        })
     })
  },
  /**
  * 修改地址
  */
  updateAddress() {
    let address = this.data.address;
    let userInfo = app.globalData.userInfo || null
    let userId
    if (userInfo != null) {
      userId = userInfo.userId
    }
    console.log(address.isDefault)
    let data = {
      id: address.id,
      userId: userId,
      mobile: address.tel,
      username: address.name,
      town: address.town,
      village: address.village,
      groups: address.groups,
      address: address.addressDetail,
      province: address.province,
      defaulted: address.isDefault == 1 ? 1 : 0
    }
    util.wxRequest(app.globalData.url + 'wx/address/update', data, "post").then(res => {
      let data = res.data
      console.log(data)
      if(data.errno == 0){
        console.log('更新成功！')
        wx.navigateBack({
          
        })
      }
     }).catch(err => {
        console.log(err)
        that.setData({
          listGoods:[]
        })
     })
  },
  /**返回上一页*/
  cancelAddress: function(){
    wx.navigateBack({
      
    })
  },
  /*****获取地址 */
  getCityStationPer: function () {
    let that = this
    // that.setData({
    //   city: ['双安镇', '汉王镇'],//镇
    //   station: { '双安镇': ['桐安村', '闹河村', '林本河村'], '汉王镇': ['马家营村', '五郎坪村'] },//村
    //   perList: { '桐安村': ['一组', '二组', '三组'], '闹河村': ['一组', '二组'], '林本河村': ['一组', '二组'], '马家营村': ['一组'], '五郎坪村': ['一组', '二组'] },// 组
    // })
    //'五小区': ['1号楼 ', '2号楼 ', '3号楼 ', '4号楼 ', '5号楼 ', '6号楼 ']
    //'1号楼 ': ['一单元', '二单元'], '2号楼 ': ['一单元', '二单元', '三单元'], '3号楼 ': ['一单元', '二单元', '三单元', '四单元'],'4号楼 ':['一单元'],'5号楼 ': ['一单元'], '6号楼 ': ['一单元']
    that.setData({
      city: ['到店自取', '一小区', '二小区', '三小区', '四小区', '五小区', '街道'],
      station: { '到店自取': ['到店自取'], '一小区': ['一小区'], '二小区': ['二小区'], '三小区': ['三小区'], '四小区': ['1号楼', '2号楼', '3号楼', '4号楼', '5号楼', '6号楼', '7号楼', '8号楼', '9号楼'], '五小区': ['1号楼 ', '2号楼 ', '3号楼 ', '4号楼 ', '5号楼 ', '6号楼 '], '街道': ['街道'] },
      perList: { '到店自取': ['到店自取'], '一小区': ['一小区'], '二小区': ['二小区'], '三小区': ['三小区'], '1号楼': ['一单元', '二单元', '三单元', '四单元'], '2号楼': ['一单元', '二单元', '三单元', '四单元'], '3号楼': ['一单元', '二单元'], '4号楼': ['一单元'], '5号楼': ['一单元', '二单元'], '6号楼': ['一单元', '二单元', '三单元', '四单元'], '7号楼': ['一单元', '二单元', '三单元', '四单元'], '8号楼': ['一单元', '二单元', '三单元'], '9号楼': ['一单元'], '1号楼 ': ['一单元', '二单元'], '2号楼 ': ['一单元', '二单元', '三单元'], '3号楼 ': ['一单元', '二单元', '三单元', '四单元'], '4号楼 ': ['一单元'], '5号楼 ': ['一单元'], '6号楼 ': ['一单元'], '街道': ['街道两公里内']}
    })
    that.data.multiArray[0] = that.data.city
    that.data.multiArray[1] = this.getArr(that.data.city[0], that.data.station)
    that.data.multiArray[2] = this.getArr(that.data.multiArray[1][0], that.data.perList)
    console.log(that.data.multiArray)
    let address = this.data.address;
    address.town = that.data.multiArray[0][that.data.multiIndex[0]]
    address.village = that.data.multiArray[1][that.data.multiIndex[1]]
    address.groups = that.data.multiArray[2][that.data.multiIndex[2]]
    that.setData({
      multiArray: that.data.multiArray,
      address: address
    })
  },
  /****列发生改变 */
  bindMultiPickerColumnChange: function (e) {
    let that = this
    console.log('修改的列为', e.detail.column, '，值为', e.detail.value);
    var data = {
      multiArray: this.data.multiArray,
      multiIndex: this.data.multiIndex
    };
    data.multiIndex[e.detail.column] = e.detail.value;
    switch (e.detail.column) {
      case 0:
        //第一列改变  设置第二列数据
        let arr = that.getArr(that.data.city[e.detail.value], that.data.station)
        data.multiArray[1] = arr
        that.setData({
          multiArray: data.multiArray
        })
        //从第二列中拿出第一项，设置第三组人员
        let arrColumn2 = that.getArr(arr[0], that.data.perList)
        data.multiArray[2] = arrColumn2
        that.setData({
          multiArray: data.multiArray
        })
        break;
      case 1:
        //第二列改变 设置第三列数据
        let arr2 = that.getArr(data.multiArray[1][e.detail.value], that.data.perList)
        data.multiArray[2] = arr2
        that.setData({
          multiArray: data.multiArray
        })
        break;
    }
    let address = this.data.address;
    address.town = data.multiArray[0][data.multiIndex[0]]
    address.village = data.multiArray[1][data.multiIndex[1]]
    address.groups = data.multiArray[2][data.multiIndex[2]]
    this.setData({
      address: address
    });
    console.log('获取地址',data.multiArray[0][data.multiIndex[0]], data.multiArray[1][data.multiIndex[1]], data.multiArray[2][data.multiIndex[2]])
  },

  getArr: function (value, arr) {
    for (let i in arr) {
      if (value == i) {
        return arr[i]
      }
    }
  },

  /****值发生改变 */
  bindMultiPickerChange: function (e) {
    this.setData({
      multiIndex: e.detail.value
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