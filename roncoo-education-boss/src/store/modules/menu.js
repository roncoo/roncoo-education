import mockMenuData from '@/router/menu.json5'
import _ from 'lodash'

function flattenMenu(menu, parents = []) {
  if (Array.isArray(menu)) {
    return menu.map(v => flattenMenu(v, [...parents]))
  } else if (menu) {
    return menu.children ?
      [{ ...menu, parents }].concat(flattenMenu(menu.children, [...parents, menu]))
      :
      [{ ...menu, parents }]
  }
}

const menu = {
  state: {
    init: false,
    menuArr: [],
    menuSet: {}
  },
  mutations: {
    set_system_menu: (state, menu) => {
      state.init = true
      state.menuArr = menu
      state.menuSet = _.keyBy(_.flattenDeep(flattenMenu(menu)), 'path')
    }
  },
  actions: {
    setMenu({ commit }) {
      return new Promise(resolve => {
        console.log("用户信息" + commit)
        // TODO 后续续修改为从服务器获取，并且做数据处理
        commit('set_system_menu', mockMenuData)
        resolve()
      })
    }
  }
}

export default menu
