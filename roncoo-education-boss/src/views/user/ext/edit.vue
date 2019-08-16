<template>
  <!--弹窗-->
  <el-dialog
    width="45%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" label-width="100px">
      <el-form-item label="用户手机：">
        <el-input :disabled="true" v-model="formData.mobile"></el-input>
      </el-form-item>
      <el-form-item label="昵称：">
        <el-input v-model="formData.nickname"></el-input>
      </el-form-item>
      <el-form-item label="性别：">
        <el-radio-group v-model="formData.sex">
          <el-radio :label="1">男</el-radio>
          <el-radio :label="2">女</el-radio>
          <el-radio :label="3">保密</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="年龄：">
        <el-input v-model="formData.age"></el-input>
      </el-form-item>
      <el-form-item label="备注：">
        <el-input type="textarea" v-model="formData.remark"></el-input>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
      <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
      <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确定</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
  import * as userApi from '@/api/user'
  export default {
    name: 'Edit',
    data() {
      return {
        fileList: []
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
      handleChange(value) {
        console.log(value);
      },
      handleRemove(file, fileList) {
        console.log(file, fileList)
      },
      handlePreview(file) {
        console.log(file)
      },
      success(response, file, fileList) {
        console.log("成功" + fileList)
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除${file.name}？`);
      },
      handleClose(done) {
        this.$emit('close-callback')
      },
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
              userApi.userExtUpdate(this.formData).then(res => {
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
      }
    }
  }
</script>
