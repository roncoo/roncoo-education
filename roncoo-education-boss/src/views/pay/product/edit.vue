<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">

    <el-form :model="formData" :rules="rules" ref="formData">
      <el-form-item label="支付产品编号" prop="productCode">
        <el-input v-model="formData.productCode" :disabled="!!formData.id" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="支付产品名称" prop="productName">
        <el-input v-model="formData.productName" :disabled="!!formData.id" auto-complete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="submitForm">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import * as apis from '@/api/pay'
  export default {
    data() {
      return {
        ctrl: {
          dialogVisible: true
        },
        formData: {},
        rules: {
          productCode: [
            { required: true, message: '请输入支付方式编号', trigger: 'blur' }
          ],
          productName: [
            { required: true, message: '请输入支付方式名称', trigger: 'blur' }
          ]
        }
      }
    },
    props: {
      // route object
      Data: {
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
      submitForm() {
        this.$refs.formData.validate((valid) => {
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
        let action = 'addProduct'
        if (this.formData.id !== undefined) {
          action = 'updateBankWay'
        }
        apis[action](this.formData).then(res => {
          if (res.code === 200) {
            this.loading.hide()

            this.$message({
              type: 'success',
              message: '提交成功'
            });
            // 提交成功, 关闭窗口, 刷新列表
            this.$emit('close-callback')
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
