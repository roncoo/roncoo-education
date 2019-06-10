<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
     <el-form :inline="true">
      <el-form-item label="用户编号">
        <span class="inline_text">{{formData.userNo}}</span>
      </el-form-item>
      <el-form-item label="用户类型">
        <span class="inline_text">{{formData.userType}}</span>
      </el-form-item>
      <el-form-item label="手机号">
        <span class="inline_text">{{formData.mobile}}</span>
      </el-form-item>
      <el-form-item label="昵称">
        <span class="inline_text">{{formData.nickname}}</span>
      </el-form-item>
      <el-form-item label="注册时间">
        <span class="inline_text">{{formData.gmtCreate}}</span>
      </el-form-item>
      <el-form-item label="状态">
        <span class="inline_text">{{formData.statusId}}</span>
      </el-form-item>
      <el-form-item label="用户头像">
        <span class="inline_text">{{formData.headImgUrl}}</span>
      </el-form-item>
      <el-form-item label="性别">
        <span class="inline_text">{{formData.sex}}</span>
      </el-form-item>
      <el-form-item label="年龄">
        <span class="inline_text">{{formData.age}}</span>
      </el-form-item>
      <el-form-item label="备注">
        <span class="inline_text">{{formData.remark}}</span>
      </el-form-item>
    </el-form>
    <el-row>
      <el-button type="danger" plain @click="handleClose">取 消</el-button>
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
        this.$emit('close-cllback')
      }
    }
  }
</script>
