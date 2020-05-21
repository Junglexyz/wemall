import request from '@/utils/request'

export function createCoupon(params){
	return request({
    url: '/coupon/create',
    method: 'post',
    data: params
  })
}

export function listCoupon(){
	return request({
    url: '/coupon/list',
    method: 'post'
  })
}

export function updateCoupon(params){
	return request({
    url: '/coupon/update',
    method: 'post',
    data: params
  })
}

export function deleteCoupon(query) {
  return request({
    url: '/coupon/delete',
    method: 'post',
    data: query
  })
}