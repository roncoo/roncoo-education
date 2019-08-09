<template>
  <!--弹窗-->
  <el-dialog
    width="30%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" :rules="rules" label-width="100px">
      <el-form-item label="讲师分成:">
        <el-input v-model="formData.lecturerProportion">%</el-input>
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
  name: 'Proportion',
  data() {
    return {
      rules: {
      }
    }
  },
  props: {
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
    // 更新讲师分成信息
    submitForm(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          if (this.formData.id === undefined) {
            this.$alert('提交失败')
          } else {
            this.loading.show()
            api.lecturerUpdate(this.formData).then(res => {
              this.loading.hide()
              if (res.code === 200 && res.data > 0) {
                // 提交成功, 关闭窗口, 刷新列表
                this.tips('操作成功', 'success')
                this.handleClose()
              } else {
                this.$message({
                  type: 'error',
                  message: "提交失败"
                });
              }
            }).catch(() => {
              this.loading.hide()
            })
          }
        } else {
          return false;
        }
      })
    },
    // 关闭弹窗
    handleClose(done) {
      this.$emit('close-callback')
    }
  }
}
</script>
