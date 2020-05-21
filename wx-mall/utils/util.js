const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatTimeBefore30 = date => {
  date.setDate(date.getDate() - 30)
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

/**
 * 封装微信的的request
 */
function wxRequest(url, data = {}, method = "POST") {
  var userInfo = wx.getStorageSync('userInfo') || null
  let token = null
  if (userInfo) {
    token = userInfo.token
  }
  return new Promise(function (resolve, reject) {
    wx.request({
      url: url,
      data: data,
      method: method,
      header: {
        'Content-Type': 'application/json',
        'Wemall-Token': token
      },
      success: function (res) {
        resolve(res);
      },
      fail: function (error) {
        resolve(error);
      }
    })
  });
}

module.exports = {
  formatTime: formatTime,
  formatTimeBefore30: formatTimeBefore30,
  wxRequest: wxRequest
}
