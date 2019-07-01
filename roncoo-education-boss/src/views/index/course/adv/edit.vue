<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">

    <el-form :model="formData" :rules="rules" ref="formData">
      <el-form-item label="广告图片" prop="permissionName">
        <el-input v-model="formData.advImg"></el-input>
      </el-form-item>
      <el-form-item label="广告标题" prop="permission">
        <el-input v-model="formData.advTitle" :disabled="!!formData.id"></el-input>
      </el-form-item>
      <el-form-item label="广告状态" prop="statusId">
        <el-input v-model="formData.statusId" ></el-input>
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
