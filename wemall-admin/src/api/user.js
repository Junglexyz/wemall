import request from '@/utils/request'

export function listUser(query) {
  return request({
    url: '/user/list',
    method: 'post',
    data: query
  })
}

export function updateUser(query) {
  return request({
    url: '/user/update',
    method: 'post',
    data: query
  })
}

export function listCollect(query) {
  return request({
    url: '/collect/list',
    method: 'get',
    params: query
  })
}

export function listFeedback(query) {
  return request({
    url: '/feedback/list',
    method: 'get',
    params: query
  })
}

export function listFootprint(query) {
  return request({
    url: '/footprint/list',
    method: 'get',
    params: query
  })
}

export function listHistory(query) {
  return request({
    url: '/history/list',
    method: 'get',
    params: query
  })
}

