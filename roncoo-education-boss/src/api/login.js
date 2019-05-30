import request from '@/utils/request'

export function login(mobile, password, clientId, ip) {
  console.log(mobile)
  const data = {
    mobile: mobile,
    password: password,
    clientId: clientId,
    ip: ip
  }
  return request({
    url: '/user/pc/user/login/password',
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
