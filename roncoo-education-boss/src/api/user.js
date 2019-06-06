import request from '@/utils/request'

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

// 根据用户id查看用户信息
export function userExtView(data = {}) {
  return request({
    url: `/user/pc/user/ext/view`,
    method: 'post',
    data: data
  })
}
// 上传图片
export function uploadPic(platformEnum, data = {}) {
  return request({
    url: `/course/pc/upload/pic`,
    method: 'post',
    params: platformEnum,
    data: data
  })
}
