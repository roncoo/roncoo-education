<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form :model="formData" :rules="rules" ref="formData">
      <el-form-item label="专区名称" prop="zoneName">
        <el-input v-model="formData.zoneName" ></el-input>
      </el-form-item>
      <el-form-item label="专区排序" prop="sort">
        <el-input v-model="formData.sort" ></el-input>
      </el-form-item>
      <el-form-item label="专区描述" prop="zoneDesc">
        <el-input v-model="formData.zoneDesc" type="textarea"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="submitForm('formData')">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import * as apis from '@/api/zone'
  export default {
    name: 'Edit',
    data() {
      return {
        ctrl: {
          dialogVisible: true
        },
        form: {},
        rules: {
          zoneName: [
            { required: true, message: '请输入专区名称', trigger: 'blur', autocomplete: 'on' }
          ],
          sort: [
            { required: false, message: '请输入专区排序', trigger: 'blur', autocomplete: 'on' }
          ],
          zoneDesc: [
            { required: false, message: '请输入专区描述', trigger: 'blur' }
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
          res = await apis.coursePcZoneSave(this.formData)
        } else {
          // 编辑
          res = await apis.coursePcZoneUpdate(this.formData)
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
