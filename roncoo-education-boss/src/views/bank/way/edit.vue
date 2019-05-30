<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">

    <el-form :model="formData" :rules="rules" ref="formData">
      <el-form-item label="银行通道编码" prop="bankWayCode">
        <el-input v-model="formData.bankWayCode" :disabled="!!formData.id" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="银行通道名称" prop="desc">
        <el-input v-model="formData.desc" :disabled="!!formData.id" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="对账文件类型" prop="checkFileType">
        <el-radio v-model="formData.checkFileType" label="SPLIT">分开</el-radio>
        <el-radio v-model="formData.checkFileType" label="ALL">全部</el-radio>
      </el-form-item>
      <el-form-item label="是否平台结算" prop="isPlatSett">
        <el-radio v-model="formData.isPlatSett" :label="true">是</el-radio>
        <el-radio v-model="formData.isPlatSett" :label="false">否</el-radio>
      </el-form-item>
      <el-form-item label="银行通道状态" prop="status">
        <el-radio v-model="formData.status" label="YES">激活</el-radio>
        <el-radio v-model="formData.status" label="NO">冻结</el-radio>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="submitForm('formData')">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import * as apis from '@/api/bank'
  export default {
    data() {
      return {
        ctrl: {
          dialogVisible: true
        },
        form: {},
        rules: {
          desc: [
            { required: true, message: '请输入银行通道名称', trigger: 'blur' }
          ],
          bankWayCode: [
            { required: true, message: '请输入银行通道编码', trigger: 'blur' }
          ],
          checkFileType: [
            { required: true, message: '请选择对账文件类型', trigger: 'blur' }
          ],
          isPlatSett: [
            { required: true, message: '请选择是否平台结算', trigger: 'change' }
          ],
          status: [
            { required: true, message: '请输入银行通道状态', trigger: 'blur' }
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
        this.$emit('close-cllback')
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
      handleConfirm() {
        this.loading.show()
        let action = 'addBankWay'
        if (this.formData.id !== undefined) {
          action = 'updateBankWay'
        }
        apis[action](this.formData).then(res => {
          if (res.code === 0) {
            this.loading.hide()
            // 提交成功, 关闭窗口, 刷新列表
            this.$emit('close-cllback')
          } else {
            this.$alert(res.msg || '提交失败')
          }
        }).catch(() => {
          this.loading.hide()
        })
      }
    }
  }
</script>

<style scoped>

</style>
