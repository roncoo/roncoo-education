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
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('form')">确 定</el-button>
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
import * as api from '@/api/user'
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
    // 关闭编辑弹窗回调
    closeCllback(res) {
      this.form.adminUserNo = res.userNo
      this.form.mobile = res.mobile
      this.ctrl.dialogVisible = false
    },
    // 保存信息
    submitForm(form) {
      if (!this.form.clientName) {
        this.$message({
          type: 'error',
          message: '请输入客户端名称'
        });
        return false
      }
      this.$refs[form].validate((valid) => {
        if (valid) {
          this.handleConfirm()
        } else {
          return false;
        }
      })
    },
    //异步保存
    async handleConfirm() {
      this.load = true
      let res = {}
      if (this.form === undefined) {
        this.form = {}
        this.$alert(res.msg || '提交失败')
      } else {
        res = await api.platformSave(this.form)
        // this.tips('成功', 'success')
      }
      this.load = false
      if (res.code === 200 && res.data > 0) {
        // 提交成功, 关闭窗口, 刷新列表
        this.handleClose('close-cllback')
      } else {
        this.form = {}
        this.$alert(res.msg || '提交失败')
      }
    },
    // 关闭弹窗
    handleClose() {
      this.form = {}
      this.$emit('close-cllback')
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

