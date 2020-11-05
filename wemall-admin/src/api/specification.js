import request from '@/utils/request'

export function createSpec(data) {
  return request({
    url: '/spec/create',
    method: 'post',
    data: data
  })
}

export function deleteSpec(data) {
  return request({
    url: '/spec/delete',
    method: 'post',
    data: data
  })
}

export function updateSpec(data) {
  return request({
    url: '/spec/update',
    method: 'post',
    data: data
  })
}

