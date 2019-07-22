import request from '@/utils/request'

// 审核课程分页列表接口
export function getCourseAuditList(params, pageCurrent = 1, pageSize = 20) {
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
export function courseViewForEdit(id) {
  return request({
    url: '/course/pc/course/get',
    method: 'post',
    data: { id: id }
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

// 首页专区PC端分页列表接口
export function coursePcZoneList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/course/pc/zone/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 专区添加接口
export function coursePcZoneSave(params) {
  return request({
    url: '/course/pc/zone/save',
    method: 'post',
    data: params
  })
}

// 专区删除接口
export function coursePcZoneDelete(params) {
  return request({
    url: '/course/pc/zone/delete',
    method: 'post',
    data: params
  })
}

// 专区更新接口
export function coursePcZoneUpdate(params) {
  return request({
    url: '/course/pc/zone/update',
    method: 'post',
    data: params
  })
}

// 专区查看接口
export function coursePcZoneView(params) {
  return request({
    url: '/course/pc/zone/view',
    method: 'post',
    data: params
  })
}

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

// 用户学习日志分页列表接口
export function studyLoglist(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/course/pc/course/user/study/log/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 支付记录分页列表接口
export function orderPaylist(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/course/pc/order/pay/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 分页列出专区课程信息
export function zoneCourselist(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/course/pc/zone/course/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 删除专区课程信息
export function zoneCourseDelete(data = {}) {
  return request({
    url: '/course/pc/zone/course/delete',
    method: 'post',
    data: data
  })
}

// 更新专区课程信息
export function zoneCourseUpdate(data = {}) {
  return request({
    url: '/course/pc/zone/course/update',
    method: 'post',
    data: data
  })
}

// 添加专区课程信息
export function zoneCourseSave(data = {}) {
  return request({
    url: '/course/pc/zone/course/save',
    method: 'post',
    data: data
  })
}
