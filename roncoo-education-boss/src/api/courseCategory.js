import request from '@/utils/request'

// 课程分类推荐分页列表接口
export function coursePcAdvList(params, page = 1, pageSize = 20) {
  return request({
    url: '/course/pc/course/category/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}
