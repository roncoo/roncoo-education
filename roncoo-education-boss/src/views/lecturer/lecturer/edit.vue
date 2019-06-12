<template>
  <!--弹窗-->
  <el-dialog
    width="60%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" label-width="80px">
      <div>一、讲师基本信息
        <br/>
        <div>
          <br/>
          <el-row>
            <el-col :span="12"><div>
              <el-form-item label="用户手机">
                <el-input :disabled="true" v-model="formData.lecturerMobile"></el-input>
              </el-form-item>
            </div></el-col>
            <el-col :span="12"><div>
              <el-form-item label="讲师名称">
                <el-input v-model="formData.lecturerName"></el-input>
              </el-form-item>
            </div></el-col>
          </el-row>
          <el-row>
            <el-col :span="12"><div>
              <el-form-item label="邮箱">
                <el-input :disabled="true" v-model="formData.lecturerEmail"></el-input>
              </el-form-item>
            </div></el-col>
            <el-col :span="12"><div>
              <el-form-item label="排序">
                <el-input v-model="formData.sort"></el-input>
              </el-form-item>
            </div></el-col>
          </el-row>
        </div>
      </div>
      <div>二、讲师分成及银行信息
        <div>
          <br/>
          <el-row>
            <el-col :span="12"><div>
              <el-form-item label="银行名称">
                <el-input :disabled="true" v-model="formData.bankName"></el-input>
              </el-form-item>
            </div></el-col>
            <el-col :span="12"><div>
              <el-form-item label="银行卡号">
                <el-input v-model="formData.bankCardNo"></el-input>
              </el-form-item>
            </div></el-col>
          </el-row>
          <el-row>
            <el-col :span="12"><div>
              <el-form-item label="开户名称">
                <el-input :disabled="true" v-model="formData.bankUserName"></el-input>
              </el-form-item>
            </div></el-col>
            <el-col :span="12"><div>
              <el-form-item label="银行卡号">
                <el-input v-model="formData.bankIdCardNo"></el-input>
              </el-form-item>
            </div></el-col>
          </el-row>
          <el-form-item label="支行名称">
            <el-input :disabled="true" v-model="formData.bankBranchName"></el-input>
          </el-form-item>
        </div>
      </div>
      <el-form-item>
        <el-button type="primary" @click="submitForm('formData')">提交</el-button>
        <el-button type="danger" plain @click="handleClose">取 消</el-button>
      </el-form-item>
    </el-form>
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
        this.ctrl.loading = true
        let res = {}
        if (this.formData.id === undefined) {
          this.$alert(res.msg || '提交失败')
        } else {
          res = await userApi.userExtUpdate(this.formData)
          // this.tips('成功', 'success')
        }
        this.ctrl.loading = false
        if (res.code === 200) {
          // 提交成功, 关闭窗口, 刷新列表
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
    width: 600px
  }
</style>

