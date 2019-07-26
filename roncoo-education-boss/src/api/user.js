import request from '@/utils/request'

// 用戶分页列出接口
export function userList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/user/pc/user/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}

// 学员分页列表接口
export function userExtList(params, pageCurrent = 1, pageSize = 20) {
  return request({
    url: '/user/pc/user/ext/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
  })
}
// 更新用户信息
export function userExtUpdate(data = {}) {
  return request({
    url: `/user/pc/user/ext/update`,
    method: 'post',
    data: data
  })
}

// 根据用户id或用户编号查看用户信息
export function userExtView(data = {}) {
  return request({
    url: `/user/pc/user/ext/view`,
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
