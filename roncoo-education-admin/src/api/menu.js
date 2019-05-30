import request from '@/utils/request'

export function getMenu() {
  return request({
    url: '/auth/pms/menu/list',
    method: 'post',
    data: {}
  })
}
