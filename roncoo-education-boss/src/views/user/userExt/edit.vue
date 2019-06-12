<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" label-width="80px">
      <el-form-item label="头像">
        <el-upload
          class="upload-demo"
          action="http://192.168.31.134:5840/course/pc/upload/pic/{USER}"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
           multiple
          :limit="1"
          :on-exceed="handleExceed"
          :on-success="success"
          :file-list="fileList">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </el-form-item>
      <el-form-item label="用户手机">
        <el-input :disabled="true" v-model="formData.mobile"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="formData.nickname"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="formData.sex">
          <el-radio :label="1">男</el-radio>
          <el-radio :label="2">女</el-radio>
          <el-radio :label="3">保密</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="年龄">
        <el-input v-model="formData.age"></el-input>
      </el-form-item>
      <el-form-item label="备注">
        <el-input type="textarea" v-model="formData.remark"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('formData')">提交</el-button>
        <el-button type="danger" plain @click="handleClose">取 消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
  import * as userApi from '@/api/user'
  export default {
    name: 'Edit',
    data() {
      return {
        fileList: [],
        ctrl: {
          dialogVisible: true
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
        this.$emit('close-cllback')
      },
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
        this.ctrl.loading = true
        let res = {}
        if (this.formData.id === undefined) {
          this.$alert(res.msg || '提交失败')
        } else {
          res = await userApi.userExtUpdate(this.formData)
          // this.tips('成功', 'success')
        }
        this.ctrl.loading = false
        if (res.code === 200) {
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
    text-align: center;
  }
</style>
