import request from '@/utils/request'

// 底部导航文章查看接口
export function articleView(params) {
  return request({
    url: '/system/pc/website/nav/article/view',
    method: 'post',
    data: params
  })
}

// 底部导航文章保存接口
export function articleSave(params) {
  return request({
    url: '/system/pc/website/nav/article/save',
    method: 'post',
    data: params
  })
}

// 底部导航文章更新接口
export function articleUpdate(params) {
  return request({
    url: '/system/pc/website/nav/article/update',
    method: 'post',
    data: params
  })
}

