<template>
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose"
    width="32%">
    <el-form ref="formData" :model="formData" label-width="100px">
      <el-row>
        <el-col :span="3">备注：</el-col>
        <el-col :span="16">
          <el-input type="textarea" :rows="4" placeholder="请输入内容" v-model="formData.remark"></el-input>
        </el-col>
      </el-row>
      <el-row style="margin-top:17px; ">
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确定</el-button>
      </el-row>
    </el-form>
  </el-dialog>
</template>
<script>
import * as courseApis from '@/api/course'
export default {
  name: 'Remark',
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
    submitForm(formData) {
      this.$refs[formData].validate((valid) => {
        if (valid) {
          this.handleConfirm()
        } else {
          return false;
        }
      })
    },
    async handleConfirm() {
      let res = {}
      res = await courseApis.orderUpdate(this.formData)
      this.tips('成功', 'success')
      if (res.code === 200 && res.data > 0) {
        // 提交成功, 关闭窗口, 刷新列表
        this.$emit('close-cllback')
      } else {
        this.$alert(res.msg || '备注失败')
      }
    },
    handleClose(done) {
      this.$emit('close-cllback')
    }
  }
}
</script>

