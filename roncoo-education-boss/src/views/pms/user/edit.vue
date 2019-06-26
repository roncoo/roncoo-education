<template>
  <!--弹窗-->
  <el-dialog
    width="45%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" label-width="80px">
      <el-form-item label="用户手机:">
        <el-input :disabled="true" v-model="formData.mobile"></el-input>
      </el-form-item>
      <el-form-item label="名称:">
        <el-input v-model="formData.realName"></el-input>
      </el-form-item>
      <el-form-item label="排序:">
        <el-input v-model="formData.sort"></el-input>
      </el-form-item>
      <el-form-item label="备注:">
        <el-input type="textarea" v-model="formData.remark"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button class="button" type="primary" @click="submitForm('formData')">确 定</el-button>
      <el-button class="button" type="danger" plain @click="handleClose">取 消</el-button>
    </div>
  </el-dialog>
</template>
<script>
  import * as api from '@/api/system'
  export default {
    name: 'Edit',
    data() {
      return {
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
        this.ctrl.load = true
        let res = {}
        if (this.formData.id === undefined) {
          this.$alert(res.msg || '提交失败')
        } else {
          res = await api.userUpdate(this.formData)
          // this.tips('成功', 'success')
        }
        this.ctrl.load = false
        if (res.code === 200 && res.data > 0) {
          // 提交成功, 关闭窗口, 刷新列表
          this.tips('成功', 'success')
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
    text-align: right;
  }
  .button {
    padding: 5px 10px;
  }
</style>
