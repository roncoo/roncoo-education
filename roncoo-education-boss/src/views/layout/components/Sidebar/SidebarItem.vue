<template>
  <div v-if="!item.hidden" class="menu-wrapper">
    <template
      v-if="isNotChildren(item)">
      <app-link :to="resolvePath(item)">
        <el-menu-item
          :index="resolvePath(item)"
          :class="{'submenu-title-noDropdown':!isNest}"
        >
          <item
            :icon="item.icon || item.icon"
            :title="item.title || item.name"
          />
        </el-menu-item>
      </app-link>
    </template>

    <el-submenu v-else :index="resolvePath(item)">
      <template slot="title">
        <item
          :icon="item.icon"
          :title="item.title || item.name"
        />
      </template>

      <template v-for="child in item.children" v-if="!child.hidden">
        <sidebar-item
          v-if="child.children && child.children.length>0"
          :is-nest="true"
          :item="child"
          :key="child.path"
          :base-path="resolvePath(child)"
          class="nest-menu"/>
        <app-link v-else :to="resolvePath(child)" :key="child.name">
          <el-menu-item :index="resolvePath(child)">
            <item
              :icon="child.icon"
              :title="child.title || child.name"
            />
          </el-menu-item>
        </app-link>
      </template>
    </el-submenu>

  </div>
</template>

<script>
import { validateURL } from '@/utils/validate'
import Item from './Item'
import AppLink from './Link'
import FixiOSBug from './FixiOSBug'

export default {
  name: 'SidebarItem',
  components: { Item, AppLink },
  mixins: [FixiOSBug],
  props: {
    // route object
    item: {
      type: Object,
      required: true
    },
    isNest: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
    }
  },
  methods: {
    isNotChildren(item) {
      const { children = [] } = item
      return children.filter(item => {
        if (item.hidden) {
          return false
        } else {
          return true
        }
      }).length === 0
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
