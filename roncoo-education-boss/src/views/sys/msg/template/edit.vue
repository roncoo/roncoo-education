<template>
  <!--弹窗-->
  <el-dialog
    width="35%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" :rules="rules" label-width="100px">
      <el-form-item label="名称:" prop="title">
        <el-input v-model="formData.title"></el-input>
      </el-form-item>
      <el-form-item label="模板:">
        <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入模板" v-model="formData.remark">
        </el-input>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
      <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确 定</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
import * as api from '@/api/sys'
export default {
  name: 'Add',
  data() {
    return {
      ctrl: {
        dialogVisible: false
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' }
        ]
      }
    }
  },
  props: {
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
    userList() {
      this.ctrl.dialogVisible = true
    },
    // 关闭编辑弹窗回调
    closeCllback(res) {
      this.form.adminUserNo = res.userNo
      this.form.mobile = res.mobile
      this.ctrl.dialogVisible = false
    },
    // 保存管理员信息
    submitForm(form) {
      if (!this.formData.title) {
        this.$message({
          type: 'error',
          message: '请输入标题'
        });
        return false
      }
      this.$refs[form].validate((valid) => {
        if (valid) {
          if (this.formData === undefined) {
            this.$alert('提交失败')
          } else {
            this.loading.show()
            api.templateUpdate(this.formData).then(res => {
              this.loading.hide()
              if (res.code === 200 && res.data > 0) {
                // 提交成功, 关闭窗口, 刷新列表
                this.tips('更新成功', 'success')
                this.handleClose()
              } else {
                this.$message({
                  type: 'error',
                  message: "更新失败"
                });
              }
            }).catch(() => {
              this.loading.hide()
            })
          }
        } else {
          this.$alert('提交失败')
        }
      })
    },
    // 关闭弹窗
    handleClose(done) {
      this.$refs['formData'].resetFields()
      this.$emit('close-callback')
    }
  }
}
</script>
