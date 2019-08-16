import Vue from 'vue'
import Vuex from 'vuex'
import menu from './modules/menu'
import app from './modules/app'
import user from './modules/user'
import tags from './modules/tags'
import opts from './modules/opts'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    menu,
    app,
    tags,
    opts,
    user
  },
  getters
})

export default store
