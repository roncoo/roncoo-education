<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">

    <el-form :model="formData" :rules="rules" ref="formData">
      <el-form-item label="广告图片">
        <el-upload
          class="upload-demo"
          action="http://192.168.31.134:5840/course/pc/upload/pic/{COURSE}"
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
      <el-form-item label="广告标题" prop="advTitle">
        <el-input v-model="formData.advTitle" ></el-input>
      </el-form-item>
      <el-form-item label="广告排序" prop="sort">
        <el-input v-model="formData.sort" ></el-input>
      </el-form-item>
      <el-form-item label="广告状态">
        <el-radio-group v-model="formData.statusId">
          <el-radio :label="0">禁用</el-radio>
          <el-radio :label="1">正常</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="beginTime" label="开始时间">
        <el-date-picker v-model="formData.beginTime" ></el-date-picker>
      </el-form-item>
      <el-form-item prop="endTime" label="结束时间">
        <el-date-picker v-model="formData.endTime" ></el-date-picker>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="submitForm('formData')">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import * as apis from '@/api/adv'
  export default {
    name: 'Edit',
    data() {
      return {
        ctrl: {
          dialogVisible: true
        },
        form: {},
        fileList: [],
        rules: {
          advImg: [
            { required: true, message: '请输入广告图片', trigger: 'blur', autocomplete: 'on' }
          ],
          advTitle: [
            { required: true, message: '请输入广告标题', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '请输入广告排序', trigger: 'blur' }
          ],
          beginTime: [
            { required: true, message: '请输入开始时间', trigger: 'blur' }
          ],
          endTime: [
            { required: true, message: '请输入结束时间', trigger: 'blur' }
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
        this.$emit('close-callback')
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
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.handleConfirm()
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      async handleConfirm() {
        this.loading.show()
        let res = {}
        if (this.formData.id === undefined) {
          res = await apis.coursePcAdvSave(this.formData)
        } else {
          // 编辑
          res = await apis.coursePcAdvUpdate(this.formData)
          // this.tips('成功', 'success')
        }
        this.loading.hide()
        if (res.code === 200) {
          // 提交成功, 关闭窗口, 刷新列表
          this.$emit('close-callback')
        } else {
          this.$alert(res.msg || '提交失败')
        }
      }
    }
  }
</script>

<style scoped>

</style>
