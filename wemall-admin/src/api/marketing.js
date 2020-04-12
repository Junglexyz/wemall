import request from '@/utils/request'

export function createCoupon(params){
	return request({
    url: '/coupon/create',
    method: 'post',
    data: params
  })
}