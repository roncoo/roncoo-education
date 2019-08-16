<template>
  <!--弹窗-->
  <div>
  <el-dialog
    width="35%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <el-form-item label="手机：" prop="mobile">
        <el-input placeholder="请输入内容" v-model="form.mobile" class="input-with-select">
          <el-button v-has="'/user/pc/user/list'" slot="append" icon="el-icon-search" @click="userList()"></el-button>
        </el-input>
     </el-form-item>
      <el-form-item label="名称：" prop="realName">
        <el-input v-model="form.realName"></el-input>
      </el-form-item>
      <el-form-item label="备注：">
        <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容" v-model="form.remark">
        </el-input>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
      <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
      <el-button style="float:right" size="mini" type="primary" @click="submitForm('form')">确 定</el-button>
    </el-row>
  </el-dialog>
    <list-user :visible="ctrl.dialogVisible" :title="ctrl.dialogTitle" @close-callback="closeCallback"></list-user>
  </div>
</template>
<script>
import { isvalidMobile } from '@/utils/validate'
import * as api from '@/api/sys'
import listUser from './list'
export default {
  components: { listUser },
  name: 'Add',
  data() {
    return {
      form: {},
      ctrl: {
        dialogVisible: false
      },
      rules: {
        mobile: [
          { required: true, message: '请选择用户', trigger: 'blur' }
        ],
        realName: [
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
    userList() {
      this.ctrl.dialogTitle = '用户列表'
      this.ctrl.dialogVisible = true
    },
    // 关闭编辑弹窗回调
    closeCallback(res) {
      this.form.adminUserNo = res.userNo
      this.form.mobile = res.mobile
      this.ctrl.dialogVisible = false
    },
    // 保存管理员信息
    submitForm(form) {
      if (!this.form.realName) {
        this.$message({
          type: 'error',
          message: '请输入名称'
        });
        return false
      }
      if (!this.form.mobile) {
        this.$message({
          type: 'error',
          message: '请输入手机号码'
        });
        return false
      }
      if (!isvalidMobile(this.form.mobile)) {
        this.$message({
          type: 'error',
          message: '请输入正确的手机号'
        });
        return false
      }
      this.$refs[form].validate((valid) => {
        if (valid) {
          this.loading.show()
          api.userSave(this.form).then(res => {
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
        } else {
          this.$message({
            type: 'error',
            message: "提交失败"
          });
        }
      })
    },
    // 关闭弹窗
    handleClose(done) {
      this.form = {}
      this.$refs['form'].resetFields()
      this.$emit('close-callback')
    }
  }
}
</script>
