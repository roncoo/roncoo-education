<template>
  <!--弹窗-->
  <el-dialog
    width="35%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <el-form-item label="客户端名称:" prop="clientName">
        <el-input v-model="form.clientName"></el-input>
      </el-form-item>
      <el-form-item label="备注:">
        <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容" v-model="form.remark">
        </el-input>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('form')">确 定</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
import * as api from '@/api/sys'
export default {
  name: 'Add',
  data() {
    return {
      form: {},
      rules: {
        clientName: [
          { required: true, message: '请选择客户端名称', trigger: 'blur' }
        ]
      }
    }
  },
  props: {
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
    submitForm(form) {
      if (!this.form.clientName) {
        this.$message({
          type: 'error',
          message: '请输入客户端名称'
        });
        return false
      }
      if (this.form === undefined) {
        this.$message({
          showClose: true,
          message: '提交失败',
          type: 'error'
        });
      } else {
        this.loading.show()
        api.platformSave(this.form).then(res => {
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
    },
    // 关闭弹窗
    handleClose() {
      this.form = {}
      this.$refs['form'].resetFields()
      this.$emit('close-callback')
    }
  }
}
</script>
