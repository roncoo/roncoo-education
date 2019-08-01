<template>
  <!--弹窗-->
  <el-dialog
    width="35%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" :rules="rules" label-width="100px">
      <el-form-item label="分类名称：" prop="categoryName">
        <el-input v-model="formData.categoryName"></el-input>
      </el-form-item>
      <el-form-item label="备注：">
        <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容" v-model="formData.remark">
        </el-input>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确 定</el-button>
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
import * as api from '@/api/course'
export default {
  name: 'Add',
  data() {
    return {
      rules: {
        categoryName: [
          { required: true, message: '请输入分类名称', trigger: 'blur' }
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
    // 保存信息
    submitForm(formData) {
      if (formData.categoryName) {
        this.$message({
          type: 'error',
          message: '请输入分类名称'
        });
        return false
      }
      this.$refs[formData].validate((valid) => {
        if (valid) {
          this.formData.categoryType = 1
          this.loading.show()
          api.categorySave(this.formData).then(res => {
            this.loading.hide()
            if (res.code === 200 && res.data > 0) {
              // 提交成功, 关闭窗口, 刷新列表
              this.tips('保存成功', 'success')
              this.handleClose()
            } else {
              this.$message({
                type: 'error',
                message: "保存失败"
              });
            }
          }).catch(() => {
            this.ctrl.load = true
          })
        } else {
          this.$message({
            type: 'error',
            message: "保存失败"
          });
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
