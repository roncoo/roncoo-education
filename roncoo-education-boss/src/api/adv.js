import request from '@/utils/request'

// 首页轮播PC端分页列表接口
export function coursePcAdvList(params, page = 1, pageSize = 20) {
  return request({
    url: '/course/pc/adv/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}

// 轮播广告添加接口
export function coursePcAdvSave(params) {
  return request({
    url: '/course/pc/adv/save',
    method: 'post',
    data: params
  })
}

// 轮播广告更新接口
export function coursePcAdvUpdate(params) {
  return request({
    url: '/course/pc/adv/update',
    method: 'post',
    data: params
  })
}

// 轮播广告删除接口
export function coursePcAdvDelete(params) {
  return request({
    url: '/course/pc/adv/delete',
    method: 'post',
    data: params
  })
}

// 轮播广告查看接口
export function coursePcAdvView(params) {
  return request({
    url: '/course/pc/adv/view',
    method: 'post',
    data: params
  })
}
