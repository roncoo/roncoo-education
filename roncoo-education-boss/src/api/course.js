import request from '@/utils/request'

// 审核课程分页列表接口
export function getCourseAuditList(params, page = 1, pageSize = 20) {
  return request({
    url: '/course/pc/course/audit/list',
    method: 'post',
    data: { pageCurrent: page, pageSize: pageSize, ...params }
  })
}

// 审核课程详情接口
export function courseAuditView(id) {
  return request({
    url: '/course/pc/course/audit/view',
    method: 'post',
    data: { id: id }
  })
}

// 审核课程更新接口
export function courseUpdate(data = {}) {
  return request({
    url: '/course/pc/course/audit/update',
    method: 'post',
    data: data
  })
}

// 审核课程审核接口
export function courseAudit(data = {}) {
  return request({
    url: '/course/pc/course/audit/audit',
    method: 'post',
    data: data
  })
}
