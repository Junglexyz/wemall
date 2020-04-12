import request from '@/utils/request'

export function listAccident() {
  return request({
    url: '/accident/list',
    method: 'post'
  })
}

export function createAccident(params){
	return request({
    url: '/accident/create',
    method: 'post',
    data: params
  })
}

export function updateAccident(params){
	return request({
    url: '/accident/update',
    method: 'post',
    data: params
  })
}

export function listActivityRecord(params){
	return request({
    url: '/activity/record',
    method: 'post',
    data: params
  })
}