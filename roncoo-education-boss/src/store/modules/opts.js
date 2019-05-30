import { enumList } from '@/api/role'
import { getSession, setSession } from '@/utils/storage'

function toObj(arr) {
  const obj = {}
  for (var i = 0; i < arr.length; i++) {
    obj[arr[i].name] = arr[i].desc
  }
  return obj
}

const opts = {
  state: {
    sidebar: {
      withoutAnimation: false
    },
    device: 'desktop'
  },
  mutations: {
    CLOSE_SIDEBAR: (state, withoutAnimation) => {
      state.sidebar.opened = false
      state.sidebar.withoutAnimation = withoutAnimation
    },
    TOGGLE_DEVICE: (state, device) => {
      state.device = device
    }
  },
  actions: {
    ToggleSideBar: ({ commit }) => {
    },
    CloseSideBar({ commit }, { withoutAnimation }) {
      commit('CLOSE_SIDEBAR', withoutAnimation)
    },
    // type为返回数据类型,obj、arr
    GetOpts({ commit }, { name, type = 'arr' }) {
      const sessData = getSession(name)
      if (sessData) {
        if (type === 'obj') {
          return toObj(sessData)
        }
        return sessData
      }
      return new Promise((resolve, reject) => {
        enumList(name).then(response => {
          if (response.code === 200) {
            let resData = response.data
            setSession(name, response.data)
            if (type === 'obj') {
              resData = toObj(response.data)
            }
            resolve(resData)
          } else {
            reject(response.msg)
          }
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default opts
