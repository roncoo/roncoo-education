import request from '@/utils/request'
// 银行通道列表
export function bankWayList(params, page = 1, pageSize = 20) {
  return request({
    url: '/auth/bank/way/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}

// 列出所有银行通道
export function bankWayAll(params) {
  return request({
    url: '/auth/bank/way/listAll',
    method: 'post',
    data: params
  })
}
// 添加银行通道
export function addBankWay(params) {
  return request({
    url: '/auth/bank/way/add',
    method: 'post',
    data: params
  })
}
// 修改银行通道状态
export function changeBankWay(params) {
  return request({
    url: '/auth/bank/way/editStatus',
    method: 'post',
    data: params
  })
}
// 更新银行通道
export function updateBankWay(params) {
  return request({
    url: '/auth/bank/way/edit',
    method: 'post',
    data: params
  })
}
// 添加银行渠道编码
export function addBankCode(params) {
  return request({
    url: '/auth/bank/channelCode/add',
    method: 'post',
    data: params
  })
}
// 银行渠道列表
export function bankChannelList(params, page = 1, pageSize = 20) {
  return request({
    url: '/auth/bank/channel/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}
// 添加银行渠道
export function addBankChannel(params) {
  return request({
    url: '/auth/bank/channel/add',
    method: 'post',
    data: params
  })
}
// 获取银行渠道
export function getBankChannel(params) {
  return request({
    url: '/auth/bank/way/list',
    method: 'post',
    data: params
  })
}
// 更新银行渠道
export function updateBankChannel(params) {
  return request({
    url: '/auth/bank/way/list',
    method: 'post',
    data: params
  })
}
// 修改银行渠道编码状态
export function updateChannelStatus(params) {
  return request({
    url: '/auth/bank/channel/editStatus',
    method: 'post',
    data: params
  })
}
// 银行渠道编码列表
export function bankCodeList(params, page = 1, pageSize = 20) {
  return request({
    url: '/auth/bank/channelCode/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}
// 更新银行渠道编码
export function updateBankCode(params) {
  return request({
    url: '/auth/bank/channelCode/edit',
    method: 'post',
    data: params
  })
}
// 获取银行渠道编码
export function getBankChannelCode(params) {
  return request({
    url: '/auth/bank/channelCode/view',
    method: 'post',
    data: params
  })
}
// 渠道分流列出
export function bankChannelFlowList(params, page = 1, pageSize = 20) {
  return request({
    url: '/auth/channel/flow/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}
// 新增渠道分流信息addChannelFlow
export function addChannelFlow(params) {
  return request({
    url: '/auth/channel/flow/add',
    method: 'post',
    data: params
  })
}
// 银行渠道商户余额列出
export function bankSumList(params, page = 1, pageSize = 20) {
  return request({
    url: '/auth/bank/bankSum/bankChannelList',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}
