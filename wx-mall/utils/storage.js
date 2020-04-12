const setStorage = (key, data, expiration) => {
  console.log(key, data)
  data['expiration'] = expiration
  wx.setStorageSync('c' + key, data)
  console.log('c' + key, data)
}

const getStorage = key => {
  let data = wx.getStorageSync('c' + key)
  if (null == data || data == undefined) {
    return
  }
  let expiration = data['expiration']
  var timestamp = Date.parse(new Date());//获取现在时间
  console.log(expiration, timestamp, data)
  // 进行时间比较
  if (expiration < timestamp) {//过期了，清空缓存值
    console.log("缓存已过期");
    wx.removeStorageSync('c' + key)
    return;
  }
  return data
}

/**页面十秒刷新一次缓存 */
const flushStorage = page => {
  let data = wx.getStorageSync('page')
}

module.exports = {
  setStorage: setStorage,
  getStorage: getStorage
}