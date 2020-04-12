import request from '@/utils/request'

export function createGoods(goods) {
  return request({
    url: '/goods/create',
    method: 'post',
    data: goods
  })
}

export function listGoods(query) {
  return request({
    url: '/goods/list',
    method: 'post',
    data: query
  })
}

export function updateGoods(goods) {
  return request({
    url: '/goods/update',
    method: 'post',
    data: goods
  })
}

export function deleteGoods(query) {
  return request({
    url: '/goods/delete',
    method: 'post',
    data: query
  })
}