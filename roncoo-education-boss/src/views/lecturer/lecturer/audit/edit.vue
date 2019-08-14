<template>
  <!--弹窗-->
  <el-dialog
    width="65%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" label-width="100px">
      <el-alert class="title" :closable="false" title="一、讲师基本信息" type="info" />
        <br/>
        <el-row>
          <el-col :span="12"><div>
            <el-form-item label="用户手机：">
              <el-input :disabled="true" v-model="formData.lecturerMobile"></el-input>
            </el-form-item>
          </div></el-col>
          <el-col :span="12"><div>
            <el-form-item label="讲师名称：">
              <el-input v-model="formData.lecturerName"></el-input>
            </el-form-item>
          </div></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><div>
            <el-form-item label="邮箱：">
              <el-input v-model="formData.lecturerEmail"></el-input>
            </el-form-item>
          </div></el-col>
          <el-col :span="12"><div>
            <el-form-item label="排序：">
              <el-input-number style="width: 300px;" v-model="formData.sort" @change="handleChange" :min="1"></el-input-number>
            </el-form-item>
          </div></el-col>
        </el-row>
        <el-form-item label="讲师简介：">
          <div id="introduce"></div>
        </el-form-item>
      <el-alert class="title" :closable="false" title="二、讲师分成及银行信息" type="info" />
        <br/>
        <el-row>
          <el-col :span="12"><div>
            <el-form-item label="银行名称：">
              <el-input :disabled="true" v-model="lecturerExt.bankName"></el-input>
            </el-form-item>
          </div></el-col>
          <el-col :span="12"><div>
            <el-form-item label="银行卡号：">
              <el-input :disabled="true" v-model="lecturerExt.bankCardNo"></el-input>
            </el-form-item>
          </div></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><div>
            <el-form-item label="开户名称：">
              <el-input :disabled="true" v-model="lecturerExt.bankUserName"></el-input>
            </el-form-item>
          </div></el-col>
          <el-col :span="12"><div>
            <el-form-item label="银行卡号：">
              <el-input :disabled="true" v-model="lecturerExt.bankIdCardNo"></el-input>
            </el-form-item>
          </div></el-col>
        </el-row>
        <el-form-item label="支行名称：">
          <el-input :disabled="true" v-model="lecturerExt.bankBranchName"></el-input>
        </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确 定</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
  import * as commonalityApi from '@/api/commonality'
  import * as api from '@/api/lecturer'
  export default {
    name: 'Edit',
    data() {
      return {
        editor: {}
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
    watch: {
      visible: function(val) {
        if (val) {
          setTimeout(() => {
            this.editor.customConfig.customUploadImg = this.editorUpload
              this.editor.create();
              if (this.formData.introduce !== undefined && this.formData.introduce !== '' && this.formData.introduce !== null) {
                this.editor.txt.html(this.formData.introduce)
              } else {
                this.editor.txt.html('')
              }
          }, 100)
        }
      }
    },
    mounted() {
      this.createEdit();
    },
    methods: {
      // 编辑器上传图片
      editorUpload(files, insert) {
        const file = files[0];
        const param = new FormData();
        param.append('picFile', file, file.name);
        commonalityApi.uploadPic(param).then(res => {
          if (res.code === 200) {
            const imgUrl = res.data
            insert(imgUrl)
          }
        }).catch(() => {
          this.$message({
            type: 'error',
            message: '上传图片出错，请稍后重试'
          })
        })
      },
      createEdit() {
        const E = require('wangeditor')
        this.editor = new E('#introduce')
      },
      handleClose(done) {
        this.editor.txt.clear()
        this.$emit('close-callback')
      },
      handleChange(value) {
        this.formData.sort = value
      },
      submitForm(formData) {
        this.$refs[formData].validate((valid) => {
        if (valid) {
          if (this.formData.id === undefined) {
            this.$message({
              type: 'error',
              message: "提交失败"
            });
          }
          this.loading.show()
          this.formData.introduce = this.editor.txt.html()
          api.lecturerAuditUpdate(this.formData).then(res => {
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
      }
    }
  }
</script>
