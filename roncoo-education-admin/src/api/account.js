import request from '@/utils/request'

export function getAccountByUserNo(userNo) {
  const data = {
    userNo
  }
  return request({
    url: '/auth/account/account/getAccountByUserNo',
    method: 'post',
    data
  })
}

export function getAccountList(data = {}) {
  return request({
    url: '/auth/account/account/listPage',
    method: 'post',
    data: data
  })
}
// 根据用户编号查询账户明细
export function getDetailList(data = {}) {
  return request({
    url: '/auth/account/account/accountDetailList',
    method: 'post',
    data: data
  })
}

export function getHistoryList(data = {}) {
  return request({
    url: '/auth/account/history/listPage',
    method: 'post',
    data: data
  })
}

// 获取所有支付通道
export function getBankwayList(data = {}) {
  return request({
    url: '/auth/pay/bankway/list',
    method: 'post',
    data: data
  })
}

// 发起手工打款申请
export function sendProxyPay(data = {}) {
  return request({
    url: '/auth/account/account/offLineProxyPay',
    method: 'post',
    data: data
  })
}

// 商户提现银行卡分页列出接口
export function getDrawBank(data = {}) {
  return request({
    url: '/auth/bank/drawBankAccount/listPage',
    method: 'post',
    data: data
  })
}

// 发起手工调账
export function debugAmount(params = {}) {
  return request({
    url: '/auth/account/account/debugAmount',
    method: 'post',
    data: params
  })
}
