<template>
  <!--弹窗-->
  <el-dialog
    width="35%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" :rules="rules" label-width="100px">
      <el-form-item label="讲师名称：" prop="lecturerName">
        <el-input v-model="formData.lecturerName"></el-input>
      </el-form-item>
      <el-form-item label="手机号码：" prop="lecturerMobile">
        <el-input v-model="formData.lecturerMobile"></el-input>
        <el-button v-has="'/user/pc/lecturer/audit/check'" type="primary" v-if="check === 1" @click="checkMobile()">校验</el-button>
        <el-button type="success" icon="el-icon-check" v-if="check === 2"></el-button>
        <el-button type="danger" icon="el-icon-close" v-if="check === 3"></el-button>
      </el-form-item>
      <el-form-item v-if="newUser === 2" label="登录密码：">
        <el-input v-model="formData.mobilePsw" placeholder="请输入登录密码"></el-input>
      </el-form-item>
      <el-form-item v-if="newUser === 2" label="确认密码：">
        <el-input v-model="formData.confirmPasswd" placeholder="请输入确认密码"></el-input>
      </el-form-item>
      <el-form-item label="邮箱：" prop="lecturerEmail">
        <el-input v-model="formData.lecturerEmail"></el-input>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
      <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确 定</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
import { isvalidMobile } from '@/utils/validate'
import { isvalidEmail } from '@/utils/validate'
import * as api from '@/api/lecturer'
export default {
  name: 'Add',
  data() {
    return {
      newUser: 1,
      check: 1,
      formData: {},
      rules: {
        lecturerMobile: [
          { required: true, message: '请输入手机号码', trigger: 'blur' }
        ],
        lecturerName: [
          { required: true, message: '请输入讲师名称', trigger: 'blur' }
        ],
        lecturerEmail: [
          { required: true, message: '请输入邮箱', trigger: 'blur' }
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
    // 关闭弹窗
    handleClose(done) {
      this.formData = {}
      this.newUser = 1
      this.check = 1
      this.$refs['formData'].resetFields()
      this.$emit('close-callback')
    },
    // 校验手机号是否已注册成为用户或已申请成为讲师
    checkMobile() {
      if (!this.formData.lecturerMobile) {
        this.$message({
          type: 'error',
          message: '请输入手机号码'
        });
        return false
      }
      this.load === true
      api.lecturerAuditCheck({ lecturerMobile: this.formData.lecturerMobile }).then(res => {
        if (res.data === 501) {
          // 该手机没注册
          this.newUser = 2
          this.check = 2
        } else if (res.data === 503) {
          // 已成为讲师
          this.$message({
            type: 'error',
            message: '该账号已成为讲师，请直接登录'
          });
          return false
        } else if (res.data === 502) {
          // 已成为讲师
          this.$message({
            type: 'error',
            message: '该账号已提交申请入驻成为讲师、待审核中,在7个工作日内会有相关人员与您联系确认'
          });
          return false
        } else if (res.data === 506) {
          // 已成为讲师
          this.$message({
            type: 'error',
            message: '该账号已提交申请入驻成为讲师,审核不通过，请联系平台管理员'
          });
          return false
        } else {
          // 该手机已注册、没申请成为讲师
          this.newUser = 1
          this.check = 2
        }
      }).catch(() => {
          this.load = false
        })
    },
    // 保存讲师信息
    submitForm(formData) {
      if (!this.formData.lecturerName) {
        this.$message({
          type: 'error',
          message: '请输入讲师名称'
        });
        return false
      }
      if (!this.formData.lecturerMobile) {
        this.$message({
          type: 'error',
          message: '请输入手机号码'
        });
        return false
      }
      if (!isvalidMobile(this.formData.lecturerMobile)) {
        this.$message({
          type: 'error',
          message: '请输入正确的手机号'
        });
        return false
      }
      if (!isvalidEmail(this.formData.lecturerEmail)) {
        this.$message({
          type: 'error',
          message: '请输入正确的邮箱'
        });
         return false
      }
      this.$refs[formData].validate((valid) => {
        if (valid) {
          this.loading.show()
          api.lecturerAuditSave(this.formData).then(res => {
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
      })
    }
  }
}
</script>
