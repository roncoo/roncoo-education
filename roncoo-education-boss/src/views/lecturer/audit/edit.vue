<template>
  <!--弹窗-->
  <el-dialog
    width="60%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" label-width="80px">
      <el-alert class="title" :closable="false" title="一、讲师基本信息" type="info" />
        <br/>
        <el-row>
          <el-col :span="12"><div>
            <el-form-item label="用户手机:">
              <el-input :disabled="true" v-model="formData.lecturerMobile"></el-input>
            </el-form-item>
          </div></el-col>
          <el-col :span="12"><div>
            <el-form-item label="讲师名称:">
              <el-input v-model="formData.lecturerName"></el-input>
            </el-form-item>
          </div></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><div>
            <el-form-item label="邮箱:">
              <el-input v-model="formData.lecturerEmail"></el-input>
            </el-form-item>
          </div></el-col>
          <el-col :span="12"><div>
            <el-form-item label="排序:">
              <el-input v-model="formData.sort"></el-input>
            </el-form-item>
          </div></el-col>
        </el-row>
        <el-form-item label="讲师简介:">
          <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容" v-model="formData.introduce"></el-input>
        </el-form-item>
      <el-alert class="title" :closable="false" title="二、讲师分成及银行信息" type="info" />
        <br/>
        <el-row>
          <el-col :span="12"><div>
            <el-form-item label="银行名称:">
              <el-input :disabled="true" v-model="lecturerExt.bankName"></el-input>
            </el-form-item>
          </div></el-col>
          <el-col :span="12"><div>
            <el-form-item label="银行卡号:">
              <el-input :disabled="true" v-model="lecturerExt.bankCardNo"></el-input>
            </el-form-item>
          </div></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><div>
            <el-form-item label="开户名称:">
              <el-input :disabled="true" v-model="lecturerExt.bankUserName"></el-input>
            </el-form-item>
          </div></el-col>
          <el-col :span="12"><div>
            <el-form-item label="银行卡号:">
              <el-input :disabled="true" v-model="lecturerExt.bankIdCardNo"></el-input>
            </el-form-item>
          </div></el-col>
        </el-row>
        <el-form-item label="支行名称:">
          <el-input :disabled="true" v-model="lecturerExt.bankBranchName"></el-input>
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
    name: 'Edit',
    data() {
      return {
        fileList: [],
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
      lecturerExt: {
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
          res = await userApi.lecturerAuditUpdate(this.formData)
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
