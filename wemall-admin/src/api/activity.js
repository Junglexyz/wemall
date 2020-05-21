import request from '@/utils/request'

export function listActivity() {
  return request({
    url: '/activity/list',
    method: 'post'
  })
}

export function createActivity(params){
	return request({
    url: '/activity/create',
    method: 'post',
    data: params
  })
}

export function updateActivity(params){
	return request({
    url: '/activity/update',
    method: 'post',
    data: params
  })
}