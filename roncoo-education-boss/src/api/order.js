import request from '@/utils/request'
// 订单接口

// 订单分页列表接口
export function orderList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/course/pc/order/info/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 订单后台备注更新接口
export function orderUpdate(data = {}) {
  return request({
    url: '/course/pc/order/info/update',
    method: 'post',
    data: data
  })
}

// 订单查看接口
export function orderView(id) {
  return request({
    url: '/course/pc/order/info/view',
    method: 'post',
    data: { id: id }
  })
}

// 统计订单收入情况接口
export function orderStatistical(data = {}) {
  return request({
    url: '/course/pc/order/info/statistical',
    method: 'post',
    data: data
  })
}
