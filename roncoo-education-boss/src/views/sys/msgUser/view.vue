<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
     <el-form :inline="true">
      <el-form-item label="内容:" v-html="formData.introduce">
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
        textStatusId: {
          1: '正常',
          0: '禁用'
        },
        textUserType: {
          1: '用户',
          2: '讲师'
        },
        textUex: {
          1: '男',
          2: '女',
          3: '保密'
        }
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
      textClass(isFree) {
        return {
          c_red: isFree === 0,
          c_blue: isFree === 2
        }
      },
      handleClose(done) {
        this.$emit('close-cllback')
      }
    }
  }
</script>
<style scoped>
  .cancel {
    text-align: right;
  }
  .button {
    padding: 5px 10px;
  }
</style>

