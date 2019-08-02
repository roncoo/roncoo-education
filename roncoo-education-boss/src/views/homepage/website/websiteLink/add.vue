<template>
  <!--弹窗-->
  <el-dialog
     width="35%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form :model="formData" :rules="rules" ref="formData" label-width="100px">
      <el-form-item label="链接地址：" prop="linkUrl">
      <el-input v-model="formData.linkUrl" placeholder="请输入链接地址"></el-input>
    </el-form-item>
      <el-form-item label="链接名称：" prop="linkName">
        <el-input v-model="formData.linkName" placeholder="请输入链接名称"></el-input>
      </el-form-item>
      <el-form-item label="打开方式：" width="200">
        <template>
          <el-radio v-model="formData.linkTarget" label="_blank">新窗口打开</el-radio>
          <el-radio v-model="formData.linkTarget" label="_self">同窗口打开</el-radio>
        </template>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确 定</el-button>
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
import * as api from '@/api/homepage'
  export default {
    name: 'Edit',
    data() {
      return {
        ctrl: {
          dialogVisible: true
        },
        formData: {
          linkTarget: '_blank'
        },
        rules: {
          linkUrl: [
            { required: true, message: '请输入链接地址', trigger: 'blur', autocomplete: 'on' }
          ],
          linkName: [
            { required: true, message: '请输入链接名称', trigger: 'blur' }
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
      handleClose(done) {
        this.formData = {}
        this.$emit('close-callback')
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (!this.formData.linkUrl) {
            this.$message({
              type: 'error',
              message: '请输入链接地址'
            });
            return false
          }
          if (!this.formData.linkName) {
            this.$message({
              type: 'error',
              message: '请输入链接名称'
            });
            return false
          }
          if (valid) {
            this.loading.show()
            api.websiteLinkSave(this.formData).then(res => {
              this.loading.hide()
              if (res.code === 200 && res.data > 0) {
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
      }
    }
  }
</script>
