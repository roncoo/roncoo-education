<template>
  <!--弹窗-->
  <el-dialog
    width="30%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" label-width="100px">
      <el-form-item label="审核结果：">
        <el-radio-group v-model="formData.auditStatus">
          <el-radio :label="1">通过</el-radio>
          <el-radio :label="2">不通过</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="审核意见：">
        <el-input type="textarea" :rows="3" :autosize="{ minRows: 2, maxRows: 4}" placeholder="系统默认通过" v-model="formData.auditOpinion"></el-input>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确 定</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
  import * as api from '@/api/lecturer'
  export default {
    name: 'Audit',
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
      submitForm(formData) {
        this.$refs[formData].validate((valid) => {
        if (valid) {
          if (this.formData.auditOpinion === '') {
            this.formData.auditOpinion = "系统默认通过"
          }
          if (this.formData.id === undefined) {
            this.tips('审核失败', 'error')
          }
          this.loading.show()
          api.lecturerAudit(this.formData).then(res => {
            this.loading.hide()
            if (res.code === 200 && res.data > 0) {
              // 提交成功, 关闭窗口, 刷新列表
              this.tips('操作成功', 'success')
              this.handleClose()
            } else {
              this.$message({
                type: 'error',
                message: "审核失败"
              });
            }
          }).catch(() => {
            this.loading.hide()
          })
        } else {
          this.loading.hide()
          this.$message({
            type: 'error',
            message: "审核失败"
          });
        }
      })
      }
    }
  }
</script>
