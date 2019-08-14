<template>
  <!--弹窗-->
  <el-dialog
    width="30%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <el-form-item label="名称:" prop="title">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="模板:">
        <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入模板" v-model="form.remark">
        </el-input>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
      <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('form')">确 定</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
import * as api from '@/api/sys'
export default {
  name: 'Add',
  data() {
    return {
      form: {},
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
      if (!this.form.title) {
        this.$message({
          type: 'error',
          message: '请输入标题'
        });
        return false
      }
      this.$refs[form].validate((valid) => {
        if (valid) {
          if (this.form === undefined) {
            this.$alert('提交失败')
          } else {
            this.loading.show()
            api.templateSave(this.form).then(res => {
              this.loading.hide()
              if (res.code === 200 && res.data > 0) {
                // 提交成功, 关闭窗口, 刷新列表
                this.$emit('close-callback')
              } else {
                this.$alert('提交失败')
              }
            })
            // this.tips('成功', 'success')
          }
        } else {
          return false;
        }
      })
    },
    // 关闭弹窗
    handleClose(done) {
      this.form = {}
      this.$refs['form'].resetFields()
      this.$emit('close-callback')
    }
  }
}
</script>
