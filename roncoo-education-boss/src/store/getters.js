const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  roles: state => state.user.roles,
  menu: state => state.menu,
  menuArr: state => state.menu.menuArr,
  menuList: state => state.menu.menuList,
  visitedViews: state => state.tags.visitedViews,
  cachedViews: state => state.tags.cachedViews
}
export default getters
