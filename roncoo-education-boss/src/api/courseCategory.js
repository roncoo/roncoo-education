import request from '@/utils/request'

// 课程分类推荐分页列表接口
export function categoryList(params, page = 1, pageSize = 20) {
  return request({
    url: '/course/pc/course/category/list',
    method: 'post',
    data: { pageNum: page, numPerPage: pageSize, ...params }
  })
}

// 课程分类保存接口
export function categorySave(data = {}) {
  return request({
    url: '/course/pc/course/category/save',
    method: 'post',
    data: data
  })
}

// 课程分类更新接口
export function categoryUpdate(data = {}) {
  return request({
    url: '/course/pc/course/category/update',
    method: 'post',
    data: data
  })
}

// 课程分类删除接口
export function categoryDelete(data = {}) {
  return request({
    url: '/course/pc/course/category/delete',
    method: 'post',
    data: data
  })
}

