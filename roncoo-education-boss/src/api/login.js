import request from '@/utils/request'

export function login(mobile, password, clientId, ip) {
  const data = {
    mobile: mobile,
    password: password,
    clientId: clientId,
    ip: ip
  }
  // 密码登录接口
  return request({
    url: '/user/pc/api/user/login/password',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
