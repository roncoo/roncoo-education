<template>
  <el-breadcrumb class="app-breadcrumb" separator="/">
    <transition-group name="breadcrumb">
      <el-breadcrumb-item v-for="(item,index) in levelList" v-if="item.title || item.name" :key="item.path">
        <span v-if="item.redirect === false || index === levelList.length-1" class="no-redirect">{{ item.title || item.name }}</span>
        <router-link v-else :to="resolvePath(item)">{{ item.title || item.name }}</router-link>
      </el-breadcrumb-item>
    </transition-group>
  </el-breadcrumb>
</template>

<script>
// import pathToRegexp from 'path-to-regexp'
import { validateURL } from '@/utils/validate'

export default {
  data() {
    return {
      levelList: null
    }
  },
  watch: {
    $route() {
      this.getBreadcrumb()
    }
  },
  created() {
    this.getBreadcrumb()
  },
  methods: {
    getBreadcrumb() {
      const { path, query } = this.$route
      const menuSet = this.$store.getters.menu.menuSet
      if (path !== '/iframe') {
        this.levelList = menuSet[path] ? menuSet[path].parents.concat([menuSet[path]]) : []
      } else {
        const { source } = query
        this.levelList = menuSet[source].parents.concat([menuSet[source]])
      }
    },
    resolvePath(route) {
      if (this.isExternalLink(route.path)) {
        if (route.external === true) {
          return route.path
        }
        return `/iframe?source=${encodeURIComponent(route.path)}`
      }
      return route.path
    },
    isExternalLink(routePath) {
      return validateURL(routePath)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .app-breadcrumb.el-breadcrumb {
    display: inline-block;
    font-size: 14px;
    line-height: 50px;
    margin-left: 10px;
    .no-redirect {
      color: #97a8be;
      cursor: text;
    }
  }
</style>
