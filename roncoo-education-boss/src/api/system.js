import request from '@/utils/request'

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

// 头部导航分页列表接口
export function navBarList(params, page = 1, pageSize = 20) {
  return request({
    url: '/system/pc/nav/bar/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
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

// 底部导航分页列表接口
export function websiteNavList(params, page = 1, pageSize = 20) {
  return request({
    url: '/system/pc/website/nav/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
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

// 底部导航文章查看接口
export function articleView(params) {
  return request({
    url: '/system/pc/website/nav/article/view',
    method: 'post',
    data: params
  })
}

// 底部导航文章保存接口
export function articleSave(params) {
  return request({
    url: '/system/pc/website/nav/article/save',
    method: 'post',
    data: params
  })
}

// 底部导航文章更新接口
export function articleUpdate(params) {
  return request({
    url: '/system/pc/website/nav/article/update',
    method: 'post',
    data: params
  })
}

