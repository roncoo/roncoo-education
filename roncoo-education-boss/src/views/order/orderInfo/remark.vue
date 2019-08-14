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
import * as orderApi from '@/api/order'
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
          if (this.formData.id === undefined) {
            this.$message({
              type: 'error',
              message: "提交失败"
            });
          } else {
            this.loading.show()
            orderApi.orderUpdate(this.formData).then(res => {
              this.loading.hide()
              if (res.code === 200 && res.data > 0) {
                // 提交成功, 关闭窗口, 刷新列表
                this.tips('操作成功', 'success')
                this.handleClose()
              } else {
                this.$message({
                  type: 'error',
                  message: "提交失败"
                });
              }
            }).catch(() => {
              this.loading.hide()
            })
          }
        } else {
          this.$message({
            type: 'error',
            message: "提交失败"
          });
        }
      })
    },
    handleClose(done) {
      this.$emit('close-callback')
    }
  }
}
</script>

