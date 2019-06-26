<template>
  <!--弹窗-->
  <el-dialog
    width="35%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <el-form-item label="名称:" prop="roleName">
        <el-input v-model="form.roleName"></el-input>
      </el-form-item>
      <el-form-item label="备注:">
        <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容" v-model="form.remark">
        </el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button class="button" type="primary" @click="submitForm('form')">确 定</el-button>
      <el-button class="button" type="danger" plain @click="handleClose">取 消</el-button>
    </div>
  </el-dialog>
</template>
<script>
import * as api from '@/api/system'
export default {
  name: 'Add',
  data() {
    return {
      form: {},
      rules: {
        roleName: [
          { required: true, message: '请输入名称', trigger: 'blur' }
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
    // 保存管理员信息
    submitForm(form) {
      if (!this.form.roleName) {
        this.$message({
          type: 'error',
          message: '请输入名称'
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
    //异步保存角色信息
    async handleConfirm() {
      this.load = true
      let res = {}
      if (this.form === undefined) {
        this.$alert(res.msg || '提交失败')
      } else {
        res = await api.roleSave(this.form)
        // this.tips('成功', 'success')
      }
      this.load = false
      if (res.code === 200 && res.data > 0) {
        // 提交成功, 关闭窗口, 刷新列表
        this.$emit('close-cllback')
      } else {
        this.$alert(res.msg || '提交失败')
      }
    },
    // 关闭弹窗
    handleClose(done) {
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

