import request from '@/utils/request'

export function listAd(){
	return request({
    url: '/ad/list',
    method: 'post'
  })
}

export function createAd(params){
	return request({
    url: '/ad/create',
    method: 'post',
    data: params
  })
}

export function updateAd(params) {
  return request({
    url: '/ad/update',
    method: 'post',
    data: params
  })
}

export function deleteAd(query) {
  return request({
    url: '/ad/delete',
    method: 'post',
    data: query
  })
}