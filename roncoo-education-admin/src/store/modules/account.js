import { getAccountByUserNo,
          getAccountList,
          getHistoryList,
          getBankwayList
        } from '@/api/account'

const account = {
  namespaced: true,
	state: {
    userNo: ''
  },
  mutations: {
    SET_USERNO: (state, userNo) => {
      state.userNo = userNo
    }
  },
  actions: {
    getAccountByUserNo(store, userNo) {
      return new Promise((resolve, reject) => {
        getAccountByUserNo(userNo).then(res => {
          console.log(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    getAccountList(store, params = {}) {
      console.log(params)
      return new Promise((resolve, reject) => {
        getAccountList(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },

    getHistoryList(store, params = {}) {
      console.log(params)
      return new Promise((resolve, reject) => {
        getHistoryList(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    // 获取所有支付通道
    getBankwayList(store, params = {}) {
      console.log(params)
      return new Promise((resolve, reject) => {
        getBankwayList(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}

export default account
