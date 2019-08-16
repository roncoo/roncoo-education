import request from '@/utils/request'

// 支付记录分页列表接口
export function orderPaylist(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/course/pc/order/pay/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
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

// 站内信消息手动发送接口
export function msgPush(data = {}) {
  return request({
    url: `/system/pc/msg/push`,
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

// 用户消息分页列表接口
export function msgUserList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/system/pc/msg/user/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
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

// 消息模板查看接口
export function templateView(data = {}) {
  return request({
    url: `/system/pc/msg/template/view`,
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

// 平台信息分页列表接口
export function platformList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/user/pc/platform/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 更新平台信息接口
export function platformUpdate(data = {}) {
  return request({
    url: `/user/pc/platform/update`,
    method: 'post',
    data: data
  })
}

// 平台信息查看接口
export function platformView(data = {}) {
  return request({
    url: `/user/pc/platform/view`,
    method: 'post',
    data: data
  })
}

// 平台信息保存接口
export function platformSave(data = {}) {
  return request({
    url: `/user/pc/platform/save`,
    method: 'post',
    data: data
  })
}

// 平台信息删除接口
export function platformDelete(data = {}) {
  return request({
    url: `/user/pc/platform/delete`,
    method: 'post',
    data: data
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

// 后台管理员查看口
export function userView(data = {}) {
  return request({
    url: `/system/pc/sys/user/view`,
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

// 角色查看接口
export function roleView(data = {}) {
  return request({
    url: `/system/pc/sys/role/view`,
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

// 菜单信息查看接口
export function menuView(data = {}) {
  return request({
    url: `/system/pc/menu/view`,
    method: 'post',
    data: data
  })
}
