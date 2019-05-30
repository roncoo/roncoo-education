<template>
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">
      <keep-alive :exclude="notCachedViews">
        <router-view :key="key"/>
      </keep-alive>
      <!-- or name="fade" -->
    </transition>
  </section>
</template>

<script>
export default {
  name: 'AppMain',
  computed: {
    notCachedViews() {
      return ['detail', 'no-cache']
    },
    key() {
      return this.$route.fullPath
    }
  }
}
</script>

<style scoped>
.app-main {
  /*50 = navbar  34 = tagsView */
  min-height: calc(100vh - 50px - 34px);
  position: relative;
  overflow: hidden;
}
</style>
