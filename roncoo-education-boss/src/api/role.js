import request from '@/utils/request'

export function login(mobile, password, clientId, ip) {
  console.log(mobile)
  const data = {
    mobile: mobile,
    loginPwd: password,
    clientId: clientId,
    ip: ip
  }
  return request({
    url: '/user/pc/user/login/password',
    method: 'post',
    data
  })
}

export function roleList(params) {
  return request({
    url: '/auth/pms/role/list',
    method: 'post',
    data: params
  })
}

export function addRole(params) {
  return request({
    url: '/auth/pms/role/add',
    method: 'post',
    data: params
  })
}

export function deleteRole(params) {
  return request({
    url: '/auth/pms/role/delete',
    method: 'post',
    data: params
  })
}

export function updateRole(params) {
  return request({
    url: '/auth/pms/role/update',
    method: 'post',
    data: params
  })
}

/**
 * 获取系统枚举
 * @param enumName 枚举名称
 * @param methodName 枚举关联方法
 * @param inParam 关联参数
 */
export function linkageEnumList(params) {
  const arr = [];
  for (const attr in params) {
    arr.push(`${attr}=${params[attr]}`);
  }
  const query = arr.join('&');
  return request({
    url: `/system/enum/listByMethodName?${query}`,
    method: 'post'
  })
}

export function getMenuAll(params) {
  console.log(params)
  return request({
    headers: { 'Accept': '*/*' },
    statusText: 'OK',
    url: `/auth/pms/menu/listAll`,
    method: 'post'
  })
}

export function pmsList(page, params) {
  const data = {
    pageNum: page,
    ...params
  }
  return request({
    url: `/auth/pms/pmsp/listPage`,
    method: 'post',
    data: data
  })
}

export function addPms(params) {
  console.log(params)
  return request({
    url: '/auth/pms/pmsp/add',
    method: 'post',
    data: params
  })
}

export function deletePms(pmsPermissionId) {
  console.log(pmsPermissionId)
  return request({
    url: `/auth/pms/pmsp/delete?pmsPermissionId=${pmsPermissionId}`,
    method: 'post'
  })
}

export function updatePms(params) {
  console.log(params, 'update')
  return request({
    url: '/auth/pms/pmsp/update',
    method: 'post',
    data: params
  })
}

export function assignMenu(params) {
  return request({
    url: '/auth/pms/role/assignMenu',
    method: 'post',
    data: params
  })
}

export function assignPermission(params) {
  return request({
    url: '/auth/pms/role/assignPermission',
    method: 'post',
    data: params
  })
}

export function addMenu(params) {
  if (params.parentId === 0) {
    params.isLeaf = 'NO'
  } else {
    params.isLeaf = 'YES'
  }
  return request({
    url: '/auth/pms/menu/add',
    method: 'post',
    data: params
  })
}

export function updateMenu(params) {
  return request({
    url: '/auth/pms/menu/update',
    method: 'post',
    data: params
  })
}

export function removeMenu(id) {
  return request({
    url: '/auth/pms/menu/delete',
    method: 'post',
    data: { pmsMenuId: id }
  })
}
