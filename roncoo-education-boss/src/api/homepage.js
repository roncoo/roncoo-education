import request from '@/utils/request'
// 首页管理接口

// 首页轮播PC端分页列表接口
export function advList(params, page = 1, pageSize = 20) {
  return request({
    url: '/course/pc/adv/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}

// 轮播广告添加接口
export function advSave(params) {
  return request({
    url: '/course/pc/adv/save',
    method: 'post',
    data: params
  })
}

// 轮播广告更新接口
export function advUpdate(params) {
  return request({
    url: '/course/pc/adv/update',
    method: 'post',
    data: params
  })
}

// 轮播广告删除接口
export function advDelete(params) {
  return request({
    url: '/course/pc/adv/delete',
    method: 'post',
    data: params
  })
}

// 轮播广告查看接口
export function advView(params) {
  return request({
    url: '/course/pc/adv/view',
    method: 'post',
    data: params
  })
}

// 头部导航分页列表接口
export function navBarList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/system/pc/nav/bar/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 头部导航保存接口
export function navBarSave(params) {
  return request({
    url: '/system/pc/nav/bar/save',
    method: 'post',
    data: params
  })
}

// 头部导航删除接口
export function navBarDelete(params) {
  return request({
    url: '/system/pc/nav/bar/delete',
    method: 'post',
    data: params
  })
}

// 头部导航删除接口
export function navBarUpdate(params) {
  return request({
    url: '/system/pc/nav/bar/update',
    method: 'post',
    data: params
  })
}

// 头部导航查看接口
export function navBarView(params) {
  return request({
    url: '/system/pc/nav/bar/view',
    method: 'post',
    data: params
  })
}

// 站点友情链接分页列表接口
export function websiteLinkList(params, page = 1, pageSize = 20) {
  return request({
    url: '/system/pc/website/link/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
 }

// 站点友情链接保存接口
export function websiteLinkSave(params) {
  return request({
    url: '/system/pc/website/link/save',
    method: 'post',
    data: params
  })
 }

// 站点友情链接删除接口
export function websiteLinkDelete(params) {
  return request({
    url: '/system/pc/website/link/delete',
    method: 'post',
    data: params
  })
}

// 站点友情链接更新接口
export function websiteLinkUpdate(params) {
  return request({
    url: '/system/pc/website/link/update',
    method: 'post',
    data: params
  })
}

// 站点友情链接查看接口
export function websiteLinkView(params) {
  return request({
    url: '/system/pc/website/link/view',
    method: 'post',
    data: params
  })
}

// 底部导航分页列表接口
export function websiteNavList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/system/pc/website/nav/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 底部导航保存接口
export function websiteNavSave(params) {
  return request({
    url: '/system/pc/website/nav/save',
    method: 'post',
    data: params
  })
}

// 底部导航删除接口
export function websiteNavDelete(params) {
  return request({
    url: '/system/pc/website/nav/delete',
    method: 'post',
    data: params
  })
}

// 底部导航航更新接口
export function websiteNavUpate(params) {
  return request({
    url: '/system/pc/website/nav/update',
    method: 'post',
    data: params
  })
}

// 底部导航查看接口
export function websiteNavView(params) {
  return request({
    url: '/system/pc/website/nav/view',
    method: 'post',
    data: params
  })
}

// 站点导航文章查看
export function navArticleView(data = {}) {
  return request({
    url: '/system/pc/website/nav/article/view',
    method: 'post',
    data: data
  })
}

// 更新站点导航文章接口
export function navArticleUpdate(data = {}) {
  return request({
    url: '/system/pc/website/nav/article/update',
    method: 'post',
    data: data
  })
}

// 首页专区PC端分页列表接口
export function pcZoneList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/course/pc/zone/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 专区添加接口
export function pcZoneSave(params) {
  return request({
    url: '/course/pc/zone/save',
    method: 'post',
    data: params
  })
}

// 专区删除接口
export function pcZoneDelete(params) {
  return request({
    url: '/course/pc/zone/delete',
    method: 'post',
    data: params
  })
}

// 专区更新接口
export function pcZoneUpdate(params) {
  return request({
    url: '/course/pc/zone/update',
    method: 'post',
    data: params
  })
}

// 专区查看接口
export function pcZoneView(params) {
  return request({
    url: '/course/pc/zone/view',
    method: 'post',
    data: params
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
