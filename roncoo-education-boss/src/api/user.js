import request from '@/utils/request'

// 用户密码登录
export function userLogin(data = {}) {
  return request({
    url: `/user/pc/user/login/password`,
    method: 'post',
    data: data
  })


}