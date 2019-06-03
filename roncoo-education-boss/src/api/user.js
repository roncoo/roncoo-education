import request from '@/utils/request'

// 学员分页列表接口
export function userExtList(params, page = 1, pageSize = 20) {
  return request({
    url: '/user/pc/user/ext/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}
