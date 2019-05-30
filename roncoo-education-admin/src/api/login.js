import request from '@/utils/request'

export function login(username, password, cipher) {
  console.log(username)
  const data = {
    loginName: username,
    loginPwd: password,
    loginCipher: cipher
  }
  return request({
    url: '/login',
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
