<template>
  <!--弹窗-->
  <el-dialog
    width="27%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" label-width="80px">
      <el-form-item label="审核结果:">
        <el-radio-group v-model="formData.auditStatus">
          <el-radio :label="1">通过</el-radio>
          <el-radio :label="2">不通过</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="审核意见:">
        <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容" v-model="formData.auditOpinion"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button class="button" type="primary" @click="submitForm('formData')">确 定</el-button>
      <el-button class="button" type="danger" plain @click="handleClose">取 消</el-button>
    </div>
  </el-dialog>
</template>
<script>
  import * as userApi from '@/api/user'
  export default {
    name: 'Audit',
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
          this.$alert(res.msg || '审核失败')
        } else {
          res = await userApi.lecturerAudit(this.formData)
          this.tips('成功', 'success')
        }
        this.ctrl.load = false
        if (res.code === 200 && res.data > 0) {
          // 提交成功, 关闭窗口, 刷新列表
          this.$emit('close-cllback')
        } else {
          this.$alert(res.msg || '审核失败')
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
