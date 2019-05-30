import request from '@/utils/request'

// 添加支付产品
export function addProduct(params) {
  return request({
    url: '/auth/pay/product/add',
    method: 'post',
    data: params
  })
}
// 审核支付产品状态
export function auditProduct(params) {
  return request({
    url: '/auth/pay/product/audit',
    method: 'post',
    data: params
  })
}
// 删除支付产品
export function deleteProduct(params) {
  return request({
    url: '/auth/pay/product/delete',
    method: 'post',
    data: params
  })
}
// 支付产品列表
export function productList(params, page = 1, pageSize = 20) {
  return request({
    url: '/auth/pay/product/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}
// 添加支付方式
export function addPayWay(params) {
  return request({
    url: '/auth/pay/way/add',
    method: 'post',
    data: params
  })
}
// 获取支付方式
export function getPayWay(params) {
  return request({
    url: '/auth/pay/way/editUI',
    method: 'post',
    data: params
  })
}
// 修改支付方式
export function updatePayWay(params) {
  return request({
    url: '/auth/pay/way/edit',
    method: 'post',
    data: params
  })
}
// 修改支付方式路由状态
export function updateRuleStatus(params) {
  return request({
    url: '/auth/pay/way/updatePayRule',
    method: 'post',
    data: params
  })
}
// 添加支付方式路由
export function addPayRule(params) {
  return request({
    url: '/auth/pay/rule/add',
    method: 'post',
    data: params
  })
}
// 修改支付方式路由
export function updatePayRule(params) {
  return request({
    url: '/auth/pay/rule/edit',
    method: 'post',
    data: params
  })
}
// 开启/关闭支付路由
export function updatePayRuleStatus(params) {
  return request({
    url: '/auth/pay/rule/updateStatus',
    method: 'post',
    data: params
  })
}
// 删除支付路由
export function deletePayRule(params) {
  return request({
    url: '/auth/pay/rule/delete',
    method: 'post',
    data: params
  })
}
// 删除支付方式
export function deletePay(id) {
  return request({
    url: '/auth/pay/way/delete',
    method: 'post',
    data: { id: id }
  })
}
// 支付方式列表
export function wayList(params, page = 1, pageSize = 20) {
  return request({
    url: '/auth/pay/way/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}
// 支付方式路由设置列表
export function ruleList(params, page = 1, pageSize = 20) {
  return request({
    url: '/auth/pay/rule/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}
