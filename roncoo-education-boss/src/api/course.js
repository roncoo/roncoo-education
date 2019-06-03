import request from '@/utils/request'

// 订单分页列表接口
export function getCourseAuditList(params, page = 1, pageSize = 20) {
  return request({
    url: '/course/pc/course/audit/list',
    method: 'post',
    data: { pageCurrent: page, pageSize: pageSize, ...params }
  })
}
