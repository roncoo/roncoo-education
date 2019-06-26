import request from '@/utils/request'

// 首页专区PC端分页列表接口
export function coursePcZoneList(params, page = 1, pageSize = 20) {
  return request({
    url: '/course/pc/zone/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
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
