import request from '@/utils/request'

// 根据用户编号获取用户信息、支付配置以及银行账户信息
export function editUI(data = {}) {
  return request({
    url: `/auth/user/userInfo/editUI`,
    method: 'post',
    data: data
  })
}