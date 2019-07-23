import request from '@/utils/request'

// 列出用户菜单
export function menuUserList(params) {
  return request({
    url: '/system/pc/menu/user/list',
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

export function enumList(enumName) {
  return request({
    url: `/system/pc/sys/enum/list`,
    method: 'post',
    data: { enumName: enumName }
  })
}

// 后台管理员分页列表接口
export function sysUserList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/system/pc/sys/user/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 后台管理员添加接口
export function userSave(data = {}) {
  return request({
    url: `/system/pc/sys/user/save`,
    method: 'post',
    data: data
  })
}

// 后台管理员删除接口
export function userDelete(data = {}) {
  return request({
    url: `/system/pc/sys/user/delete`,
    method: 'post',
    data: data
  })
}

// 后台管理员更新接口
export function userUpdate(data = {}) {
  return request({
    url: `/system/pc/sys/user/update`,
    method: 'post',
    data: data
  })
}

// 后台管理员密码接口
export function updatePassword(data = {}) {
  return request({
    url: `/system/pc/sys/user/update/password`,
    method: 'post',
    data: data
  })
}

// 列出角色用户关联信息接口
export function roleUserList(data = {}) {
  return request({
    url: `/system/pc/sys/role/user/list`,
    method: 'post',
    data: data
  })
}

// 添加用户角色信息接口
export function roleUserSave(data = {}) {
  return request({
    url: `/system/pc/sys/role/user/save`,
    method: 'post',
    data: data
  })
}

// 角色分页列表接口
export function roleList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/system/pc/sys/role/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 角色添加接口
export function roleSave(data = {}) {
  return request({
    url: `/system/pc/sys/role/save`,
    method: 'post',
    data: data
  })
}

// 角色删除接口
export function roleDelete(data = {}) {
  return request({
    url: `/system/pc/sys/role/delete`,
    method: 'post',
    data: data
  })
}

// 角色更新接口
export function roleUpdate(data = {}) {
  return request({
    url: `/system/pc/sys/role/update`,
    method: 'post',
    data: data
  })
}

// 列出菜单角色关联信息接口
export function menuRoleList(data = {}) {
  return request({
    url: `/system/pc/menu/role/list`,
    method: 'post',
    data: data
  })
}

// 添加角色菜单关联信息接口
export function menuRoleSave(data = {}) {
  return request({
    url: `/system/pc/menu/role/save`,
    method: 'post',
    data: data
  })
}

// 菜单信息列出信息接口
export function menuList(data = {}) {
  return request({
    url: `/system/pc/menu/list`,
    method: 'post',
    data: data
  })
}

// 菜单信息保存接口
export function menuSave(data = {}) {
  return request({
    url: `/system/pc/menu/save`,
    method: 'post',
    data: data
  })
}

// 菜单信息删除接口
export function menuDelete(data = {}) {
  return request({
    url: `/system/pc/menu/delete`,
    method: 'post',
    data: data
  })
}

// 菜单信息更新接口
export function menuUpdate(data = {}) {
  return request({
    url: `/system/pc/menu/update`,
    method: 'post',
    data: data
  })
}

// 获取站点信息接口
export function websiteView(data = {}) {
  return request({
    url: `/system/pc/website/view`,
    method: 'post',
    data: data
  })
}

// 更新站点信息接口
export function websiteUpdate(data = {}) {
  return request({
    url: `/system/pc/website/update`,
    method: 'post',
    data: data
  })
}

// 获取系统配置信息
export function sysView(data = {}) {
  return request({
    url: `/system/pc/sys/view`,
    method: 'post',
    data: data
  })
}

// 更新系统配置信息接口
export function sysUpdate(data = {}) {
  return request({
    url: `/system/pc/sys/update`,
    method: 'post',
    data: data
  })
}

// 站内信消息分页列表接口
export function msgList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/system/pc/msg/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 站内信消息保存接口
export function msgSave(data = {}) {
  return request({
    url: `/system/pc/msg/save`,
    method: 'post',
    data: data
  })
}

// 站内信消息删除接口
export function msgDelete(data = {}) {
  return request({
    url: `/system/pc/msg/delete`,
    method: 'post',
    data: data
  })
}

// 站内信消息更新接口
export function msgUpdate(data = {}) {
  return request({
    url: `/system/pc/msg/update`,
    method: 'post',
    data: data
  })
}

// 站内信消息查看接口
export function msgView(data = {}) {
  return request({
    url: `/system/pc/msg/view`,
    method: 'post',
    data: data
  })
}

// 站内信消息手动发送接口
export function msgPush(data = {}) {
  return request({
    url: `/system/pc/msg/push`,
    method: 'post',
    data: data
  })
}

// 用户消息删除接口
export function msgUserDelete(data = {}) {
  return request({
    url: `/system/pc/msg/user/delete`,
    method: 'post',
    data: data
  })
}

// 用户消息分页列表接口
export function msgUserList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/system/pc/msg/user/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 用户消息查看接口
export function msgUserView(data = {}) {
  return request({
    url: `/system/pc/msg/user/view`,
    method: 'post',
    data: data
  })
}

// 消息模板分页列表接口
export function templateList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/system/pc/msg/template/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 消息模板删除接口
export function templateDelete(data = {}) {
  return request({
    url: `/system/pc/msg/template/delete`,
    method: 'post',
    data: data
  })
}

// 消息模板保存接口
export function templateSave(data = {}) {
  return request({
    url: `/system/pc/msg/template/save`,
    method: 'post',
    data: data
  })
}

// 消息模板更新接口
export function templateUpdate(data = {}) {
  return request({
    url: `/system/pc/msg/template/update`,
    method: 'post',
    data: data
  })
}

// 后台操作日志分页列表接口
export function sysLogList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/system/pc/sys/log/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}
