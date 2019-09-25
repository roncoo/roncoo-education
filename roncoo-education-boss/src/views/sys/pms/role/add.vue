<template>
  <!--弹窗-->
  <el-dialog
    width="30%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" :rules="rules" label-width="100px">
      <el-form-item label="名称：" prop="roleName">
        <el-input v-model="formData.roleName"></el-input>
      </el-form-item>
      <el-form-item label="备注：" prop="remark">
        <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容" v-model="formData.remark">
        </el-input>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px;">
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
      formData: {},
      rules: {
        roleName: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ],
        remark: [
          { required: true, message: '请输入备注', trigger: 'blur' }
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
    // 保存管理员信息
    submitForm(formData) {
      if (!this.formData.roleName) {
        this.$message({
          type: 'error',
          message: '请输入名称'
        });
        return false
      }
      if (!this.formData.remark) {
        this.$message({
          type: 'error',
          message: '请输入备注'
        });
        return false
      }
      this.$refs[formData].validate((valid) => {
        if (valid) {
          this.loading.show()
          // 保存角色信息
          api.roleSave(this.formData).then(res => {
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
        } else {
          this.$message({
            type: 'error',
            message: "提交失败"
          });
        }
      })
    },
    // 关闭弹窗
    handleClose(done) {
      this.formData = {}
      this.$refs['formData'].resetFields()
      this.$emit('close-callback')
    }
  }
}
</script>
