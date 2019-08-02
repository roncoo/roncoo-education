<template>
  <!--弹窗-->
  <el-dialog
     width="30%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form :model="formData" :rules="rules" ref="formData" label-width="100px">
      <el-form-item label="专区名称" prop="zoneName">
        <el-input v-model="formData.zoneName" ></el-input>
      </el-form-item>
      <el-form-item label="专区排序" prop="sort">
        <el-input-number style="width: 300px;" v-model="formData.sort" @change="handleChange" :min="1" :max="10000"></el-input-number>
      </el-form-item>
      <el-form-item label="专区描述" prop="zoneDesc">
        <el-input v-model="formData.zoneDesc" type="textarea"></el-input>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确 定</el-button>
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
  import * as api from '@/api/homepage'
  export default {
    name: 'Edit',
    data() {
      return {
        ctrl: {
          dialogVisible: true
        },
        form: {},
        rules: {
          zoneName: [
            { required: true, message: '请输入专区名称', trigger: 'blur', autocomplete: 'on' }
          ],
          sort: [
            { required: true, message: '请输入专区排序', trigger: 'blur', autocomplete: 'on' }
          ],
          zoneDesc: [
            { required: false, message: '请输入专区描述', trigger: 'blur' }
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
      //关闭弹窗
      handleClose(done) {
        this.$emit('close-callback')
      },
      handleChange(value) {
        this.formData.sort = value
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.loading.show()
            if (this.formData.id === undefined) {
              api.pcZoneSave(this.formData).then(res => {
                this.loading.hide()
                if (res.code === 200 && res.data > 0) {
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
              // 编辑
              api.pcZoneUpdate(this.formData).then(res => {
                this.loading.hide()
                if (res.code === 200 && res.data > 0) {
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
            this.$message({
              type: 'error',
              message: "提交失败"
            });
          }
        });
      }
    }
  }
</script>
