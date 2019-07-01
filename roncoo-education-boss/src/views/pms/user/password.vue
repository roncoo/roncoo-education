<template>
  <!--弹窗-->
  <el-dialog
    width="50%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" :rules="rules" label-width="100px">
      <el-form-item label="新密码:" prop="mobilePsw">
        <el-input placeholder="请输入新密码" v-model="formData.mobilePsw" show-password></el-input>
      </el-form-item>
      <el-form-item label="确认密码:" prop="rePwd">
        <el-input placeholder="请输入确认密码" v-model="formData.rePwd" show-password></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button class="button" type="primary" @click="submitForm('formData')">确 定</el-button>
      <el-button class="button" type="danger" plain @click="handleClose">取 消</el-button>
    </div>
  </el-dialog>
</template>
<script>
import * as api from '@/api/system'
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
      this.$emit('close-cllback')
    },
    // 保存管理员信息
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
          this.handleConfirm()
        } else {
          return false;
        }
      })
    },
    //异步保存讲师信息
    async handleConfirm() {
      this.ctrl.load = true
      let res = {}
      if (this.formData === undefined) {
        this.$alert(res.msg || '提交失败')
      } else {
        res = await api.updatePassword(this.formData)
        // this.tips('成功', 'success')
      }
      this.ctrl.load = false
      if (res.code === 200 && res.data > 0) {
        // 提交成功, 关闭窗口, 刷新列表
        this.$emit('close-cllback')
      } else {
        this.$alert(res.msg || '提交失败')
      }
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

