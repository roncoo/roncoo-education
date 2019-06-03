<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">

    <el-form :model="formData" :rules="rules" ref="formData">
      <el-form-item label="链接地址" prop="linkUrl">
        <el-input v-model="formData.linkUrl"></el-input>
      </el-form-item>
      <el-form-item label="链接名称" prop="linkName">
        <el-input v-model="formData.linkName" ></el-input>
      </el-form-item>
      <el-form-item label="打开方式" prop="linkTarget"  width="200">
        <template>
          <el-radio v-model="formData.linkTarget" label="_blank">新窗口打开</el-radio>
          <el-radio v-model="formData.linkTarget" label="_self">同窗口打开</el-radio>
        </template>
      </el-form-item>

      <el-form-item label="排序" prop="sort" >
        <el-input v-model="formData.sort" ></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="submitForm('formData')">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import * as apis from '@/api/system'
  export default {
    name: 'Edit',
    data() {
      return {
        ctrl: {
          dialogVisible: true
        },
        form: { },
        rules: {
          linkUrl: [
            { required: true, message: '请输入链接地址', trigger: 'blur', autocomplete: 'on' }
          ],
          linkName: [
            { required: true, message: '请输入链接名称', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '请输入链接排序', trigger: 'blur' }
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
         res = await apis.websiteLinkSave(this.formData)
        } else {
          // 编辑
         res = await apis.websiteLinkUpdate(this.formData)
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
