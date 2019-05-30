<template>
  <iframe
    ref="iframe"
    :src="$route.query.source"
    class="iframe"
  />

</template>

<script>
export default {
  props: {
    source: {
      type: String,
      default: ''
    }
  },
  data() {
    return {

    }
  },
  created() {
  },
  methods: {

  }
}
</script>

<style lang="scss">
  .iframe {
    height: calc(100vh - 50px - 34px);
    width: 100%;
    border: 0;
    overflow: hidden;
    box-sizing: border-box;
  }
</style>
