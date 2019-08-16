/**
 * Created by jiachenpan on 16/11/18.
 */
// 校验手机号是否合法
export function isvalidMobile(str) {
  const valid_map = 11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/
  return valid_map.test(str)
}

// 校验邮箱是否合法
export function isvalidEmail(str) {
  const valid_map = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
  return valid_map.test(str)
}

/* 合法uri*/
export function validateURL(textval) {
  const urlregex = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/
  return urlregex.test(textval)
}

export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}
