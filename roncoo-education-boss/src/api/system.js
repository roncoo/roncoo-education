import request from '@/utils/request'

export function enumList(enumName) {
  return request({
    url: `/system/pc/sys/enum/list`,
    method: 'post',
    data: { enumName: enumName }
  })
}

// 后台管理员分页列表接口
export function userList(params, pageCurrent = 1, pageSize = 20) {
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

// 后台管理员查看接口
export function userView(data = {}) {
  return request({
    url: `/system/pc/sys/user/view`,
    method: 'post',
    data: data
  })
}
