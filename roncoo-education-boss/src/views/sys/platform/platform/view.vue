<template>
  <!--弹窗-->
  <el-dialog
     width="30%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
     <el-form :inline="true">
      <el-form-item label="客户端名称:">
        <span >{{formData.clientName}}</span>
      </el-form-item>
      <el-form-item label="客户端ID:">
        <span >{{formData.clientId}}</span>
      </el-form-item>
      <el-form-item label="客户端密匙:">
        <span >{{formData.clientSecret}}</span>
      </el-form-item>
    </el-form>
    <br/>
    <el-row style="margin-top:17px; ">
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
  export default {
    data() {
      return {
      }
    },
     props: {
      // route object
      formData: {
        type: Object,
        default: () => {}
      },
      visible: {
        type: Boolean,
        default: false
      },
      title: {
        type: String,
        default: ''
      }
    },
    methods: {
      handleClose(done) {
        this.$emit('close-callback')
      }
    }
  }
</script>
<style scoped>
