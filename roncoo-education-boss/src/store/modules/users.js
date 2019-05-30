import { getUserInfoList,
        editStatus,
        editPwd,
        editPayPwd,
        sendPayKeyToEmail,
        resetPaySecret,
        updateProxyPayRuleStatus,
        listPayType,
        updatePayType,
        sendGoogleSecretToEmail,
        resetGoogleSecret,
        lookAgentInfo,
        editAffiliateWithAgent,
        editUI } from '@/api/users'

const users = {
  namespaced: true,
  actions: {
    getUserInfoList(store, params = {}) {
      console.log(params)
      return new Promise((resolve, reject) => {
        getUserInfoList(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 激活或冻结
    editStatus(store, params = {}) {
      console.log(params)
      return new Promise((resolve, reject) => {
        editStatus(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 重置登录密码
    editPwd(store, params = {}) {
      console.log(params)
      return new Promise((resolve, reject) => {
        editPwd(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 重置支付密码
    editPayPwd(store, params = {}) {
      console.log(params)
      return new Promise((resolve, reject) => {
        editPayPwd(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 发送支付秘钥
    sendPayKeyToEmail(store, params = {}) {
      console.log(params)
      return new Promise((resolve, reject) => {
        sendPayKeyToEmail(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 重置支付秘钥
    resetPaySecret(store, params = {}) {
      console.log(params)
      return new Promise((resolve, reject) => {
        resetPaySecret(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 打开或关闭代付路由
    updateProxyPayRuleStatus(store, params = {}) {
      console.log(params)
      return new Promise((resolve, reject) => {
        updateProxyPayRuleStatus(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 查看交易（代付）开关接口
    listPayType(store, params = {}) {
      console.log(params)
      return new Promise((resolve, reject) => {
        listPayType(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 打开或关闭交易（代付）方式接口
    updatePayType(store, params = {}) {
      console.log(params)
      return new Promise((resolve, reject) => {
        updatePayType(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 发送谷歌密钥接口
    sendGoogleSecretToEmail(store, params = {}) {
      console.log('sendGoogleSecretToEmail')
      console.log(params)
      return new Promise((resolve, reject) => {
        sendGoogleSecretToEmail(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 重置谷歌密钥接口
    resetGoogleSecret(store, params = {}) {
      console.log('resetGoogleSecret')
      console.log(params)
      return new Promise((resolve, reject) => {
        resetGoogleSecret(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 查看代理信息接口
    lookAgentInfo(store, params = {}) {
      console.log('lookAgentInfo')
      console.log(params)
      return new Promise((resolve, reject) => {
        lookAgentInfo(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 挂靠/解挂代理商关系接口
    editAffiliateWithAgent(store, params = {}) {
      console.log('editAffiliateWithAgent')
      console.log(params)
      return new Promise((resolve, reject) => {
        editAffiliateWithAgent(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 根据用户编号获取用户信息、支付配置以及银行账户信息
    editUI(store, params = {}) {
      console.log('editUI')
      console.log(params)
      return new Promise((resolve, reject) => {
        editUI(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}

export default users
