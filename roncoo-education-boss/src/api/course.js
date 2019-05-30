import request from '@/utils/request'

// 订单分页列表接口
export function orderInfoList(params, page = 1, pageSize = 20) {
  return request({
    url: '/course/pc/order/info/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}