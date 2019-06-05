import request from '@/utils/request'

export function enumList(enumName) {
  console.log(enumName)
  return request({
    url: `/system/pc/sys/enum/list`,
    method: 'post',
    data: { enumName: enumName }
  })
}
