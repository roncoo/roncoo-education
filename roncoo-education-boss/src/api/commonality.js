import request from '@/utils/request'
//公共接口

// 上传图片
export function uploadPic(data = {}) {
  return request({
    url: `/course/pc/upload/pic`,
    method: 'post',
    data: data
  })
}