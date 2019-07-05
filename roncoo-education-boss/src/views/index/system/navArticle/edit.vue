<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">

    <el-form :model="formData" :rules="rules" ref="formData">
      <el-form-item label="导航标题" prop="permission">
        <el-input v-model="formData.permission" :disabled="!!formData.id"></el-input>
        <el-tag type="danger" v-if="!formData.id">提示：权限标识添加后将不可修改，请确保准确性！</el-tag>
      </el-form-item>
      <el-form-item label="权限描述" prop="remark">
        <el-input v-model="formData.remark" type="textarea"></el-input>
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
          navName: [
            { required: true, message: '请输入导航名称', trigger: 'blur', autocomplete: 'on' }
          ],
          sort: [
            { required: true, message: '请输入导航排序', trigger: 'blur' }
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
          //新增底部导航栏，给父ID赋值
          this.formData.parentId = 0
          res = await apis.websiteNavSave(this.formData)
        } else {
          // 编辑
          res = await apis.websiteNavUpate(this.formData)
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
