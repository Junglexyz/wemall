import request from '@/utils/request'

export function info(params) {
  return request({
    url: '/home/info',
    method: 'post',
    data: params
  })
}

export function todayInfo(params) {
  return request({
    url: '/home/todayInfo',
    method: 'post',
    data: params
  })
}

export function shopInfo() {
  return request({
    url: '/home/shopInfo',
    method: 'post',
    data: {}
  })
}

export function updateShopInfo(params) {
  return request({
    url: '/home/updateShopInfo',
    method: 'post',
    data: params
  })
}