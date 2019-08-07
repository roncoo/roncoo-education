import mockMenuData from '@/router/menu.json5'
import * as api from '@/api/commonality'
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
        api.menuBtnList({}).then(response => {
          // 调用递归方法获得按钮数据
          localStorage.setItem('menuList', JSON.stringify(response.data.sysMenu));
        })
        // 保存菜单按钮权限数据至vuex中
        api.menuUserList({}).then(res => {
          if (res.data.sysMenu !== []) {
            // TODO 后续续修改为从服务器获取，并且做数据处理
            commit('set_system_menu', res.data.sysMenu)
          } else {
            // 如果没有权限默认初始化首页权限页路由
            commit('set_system_menu', mockMenuData)
          }
          resolve()
        }).catch(() => {
          // 如果没有权限默认初始化首页权限页路由
            commit('set_system_menu', mockMenuData)
        })
      })
    }
  }
}

export default menu
