<template>
  <!--弹窗-->
  <el-dialog
    width="35%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" :rules="rules" label-width="100px">
      <el-form-item label="新密码：" prop="mobilePsw">
        <el-input placeholder="请输入新密码" v-model="formData.mobilePsw" show-password></el-input>
      </el-form-item>
      <el-form-item label="确认密码：" prop="rePwd">
        <el-input placeholder="请输入确认密码" v-model="formData.rePwd" show-password></el-input>
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
      newUser: 1,
      check: 1,
      ctrl: {
        addDialogVisible: true
      },
      rules: {
        mobilePsw: [
          { required: true, message: '请输入新密码', trigger: 'blur' }
        ],
        rePwd: [
          { required: true, message: '请输入确认密码', trigger: 'blur' }
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
    // 关闭弹窗
    handleClose(done) {
      this.$refs['formData'].resetFields()
      this.$emit('close-callback')
    },
    // 更新管理员密码
    submitForm(formData) {
      if (!this.formData.mobilePsw) {
        this.$message({
          type: 'error',
          message: '请输入新密码'
        });
        return false
      }
      if (!this.formData.rePwd) {
        this.$message({
          type: 'error',
          message: '请输入确认密码'
        });
        return false
      }
      if (this.formData.mobilePsw !== this.formData.rePwd) {
        this.$message({
          type: 'error',
          message: '两次输入密码不一致'
        });
        return false
      }
      this.$refs[formData].validate((valid) => {
        if (valid) {
          this.loading.show()
          // 更新管理员密码
          api.updatePassword(this.formData).then(res => {
            this.loading.hide()
            if (res.code === 200 && res.data > 0) {
              // 提交成功, 关闭窗口, 刷新列表
              this.tips('成功', 'success')
              this.$emit('close-callback')
            } else {
              this.$message({
                type: 'error',
                message: "提交失败"
              });
            }
          }).catch(() => {
            this.loading.hide()
          })
        } else {
          this.$message({
            type: 'error',
            message: "提交失败"
          });
        }
      })
    }
  }
}
</script>
<style scoped>
