import request from '@/utils/request'

export function listIndeed(query) {
  return request({
    url: '/indeed/list',
    method: 'post',
    data: query
  })
}

export function createIndeed(query) {
  return request({
    url: '/indeed/create',
    method: 'post',
    data: query
  })
}