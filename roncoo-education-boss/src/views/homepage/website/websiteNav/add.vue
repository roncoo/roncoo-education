<template>
  <!--弹窗-->
  <el-dialog
     width="30%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form :model="formData" :rules="rules" ref="formData" label-width="100px">
      <el-form-item label="导航名称：" prop="navName">
        <el-input v-model="formData.navName" placeholder="请输入导航名称"></el-input>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
      <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
      <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确定</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
  import * as api from '@/api/homepage'
  export default {
    name: 'Add',
    data() {
      return {
        ctrl: {
          dialogVisible: true
        },
        form: { },
        rules: {
          navName: [
            { required: true, message: '请输入导航名称', trigger: 'blur', autocomplete: 'on' }
          ]
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
      handleClose(done) {
        this.$refs['formData'].resetFields()
        this.$emit('close-callback')
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (!this.formData.navName) {
            this.$message({
              type: 'error',
              message: '请输入导航名称'
            });
            return false
          }
          if (valid) {
            this.loading.show()
            api.websiteNavSave(this.formData).then(res => {
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
        });
      }
    }
  }
</script>
