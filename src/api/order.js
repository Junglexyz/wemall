import request from '@/utils/request'

export function listOrder(params) {
  return request({
    url: '/order/list',
    method: 'post',
    data: params
  })
}

export function updateOrder(params) {
  return request({
    url: '/order/update',
    method: 'post',
    data: params
  })
}

export function exportOrder() {
  return request({
    url: '/order/export',
    method: 'get'
  })
}
