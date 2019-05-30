import request from '@/utils/request'

// 用户信息记录分页列出
export function getUserInfoList(data = {}) {
  return request({
    url: '/auth/user/userInfo/list',
    method: 'post',
    data: data
  })
}

// 激活或冻结
export function editStatus(data = {}) {
  return request({
    url: `/auth/user/userInfo/editStatus?status=${data.status}&userNo=${data.userNo}`,
    method: 'post',
    data: data
  })
}

// 重置登录密码
export function editPwd(data = {}) {
  return request({
    url: `/auth/user/userInfo/editPwd?userNo=${data.userNo}`,
    method: 'post'
  })
}

// 重置支付密码
export function editPayPwd(data = {}) {
  return request({
    url: `/auth/user/userInfo/editPayPwd?userNo=${data.userNo}`,
    method: 'post'
  })
}

// 发送支付秘钥
export function sendPayKeyToEmail(data = {}) {
  return request({
    url: `/auth/user/userInfo/sendPayKeyToEmail?userNo=${data.userNo}`,
    method: 'post'
  })
}

// 重置支付秘钥
export function resetPaySecret(data = {}) {
  return request({
    url: `/auth/user/userInfo/resetPaySecret?userNo=${data.userNo}`,
    method: 'post'
  })
}

// 打开或关闭代付路由
export function updateProxyPayRuleStatus(data = {}) {
  return request({
    url: `/auth/user/userInfo/updateProxyPayRuleStatus?isOpenProxyPayRule=${data.isOpenProxyPayRule}&userNo=${data.userNo}`,
    method: 'post'
  })
}

// 查看交易（代付）开关接口
export function listPayType(data = {}) {
  return request({
    url: `/auth/user/userInfo/listPayType?payType=${data.payType}&userNo=${data.userNo}`,
    method: 'post'
  })
}

// 打开或关闭交易（代付）方式接口
export function updatePayType(data = {}) {
  return request({
    url: `/auth/user/userInfo/updatePayType`,
    method: 'post',
    data: data
  })
}

// 发送谷歌密钥接口
export function sendGoogleSecretToEmail(data = {}) {
  return request({
    url: `/auth/user/userInfo/sendGoogleSecretToEmail?userNo=${data.userNo}`,
    method: 'post'
  })
}

// 重置谷歌密钥接口
export function resetGoogleSecret(data = {}) {
  return request({
    url: `/auth/user/userInfo/resetGoogleSecret?userNo=${data.userNo}`,
    method: 'post'
  })
}

// 查看代理信息接口
export function lookAgentInfo(data = {}) {
  return request({
    url: `/auth/user/userInfo/lookAgentInfo?userNo=${data.userNo}`,
    method: 'post'
  })
}

// 挂靠/解挂代理商关系接口
export function editAffiliateWithAgent(data = {}) {
  return request({
    url: `/auth/user/userInfo/editAffiliateWithAgent?affiliateWithAgent=${data.affiliateWithAgent}&userNo=${data.userNo}`,
    method: 'post'
  })
}

// 根据用户编号获取用户信息、支付配置以及银行账户信息
export function editUI(data = {}) {
  return request({
    url: `/auth/user/userInfo/editUI`,
    method: 'post',
    data: data
  })
}
