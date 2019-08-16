import request from '@/utils/request'

// 审核课程分页列表接口
export function courseAuditList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/course/pc/course/audit/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
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
export function courseAuditUpdate(data = {}) {
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

// 课程分页列表接口
export function courseList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/course/pc/course/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 课程更新接口
export function courseUpdate(data = {}) {
  return request({
    url: '/course/pc/course/update',
    method: 'post',
    data: data
  })
}

// 课程详情接口
export function courseView(id) {
  return request({
    url: '/course/pc/course/view',
    method: 'post',
    data: { id: id }
  })
}

// 课程详情接口(用于修改)
export function courseViewForEdit(data = {}) {
  return request({
    url: '/course/pc/course/get',
    method: 'post',
    data: data
  })
}

// 课程分类分页列表接口
export function categoryList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/course/pc/course/category/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 课程分类保存接口
export function categorySave(data = {}) {
  return request({
    url: '/course/pc/course/category/save',
    method: 'post',
    data: data
  })
}

// 课程分类更新接口
export function categoryUpdate(data = {}) {
  return request({
    url: '/course/pc/course/category/update',
    method: 'post',
    data: data
  })
}

// 课程分类删除接口
export function categoryDelete(data = {}) {
  return request({
    url: '/course/pc/course/category/delete',
    method: 'post',
    data: data
  })
}

// 课程分类查看接口
export function categoryView(data = {}) {
  return request({
    url: '/course/pc/course/category/view',
    method: 'post',
    data: data
  })
}
