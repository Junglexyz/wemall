import request from '@/utils/request'

export function createAward(data) {
  return request({
    url: '/award/create',
    method: 'post',
    data: data
  })
}

export function listAward() {
  return request({
    url: '/award/list',
    method: 'get'
  })
}

export function listPrize(data) {
  return request({
    url: '/award/prizes',
    method: 'post',
    data: data
  })
}

export function deleteAward(data) {
  return request({
    url: '/award/delete',
    method: 'post',
    data: data
  })
}
