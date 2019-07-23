<template>
  <!--弹窗-->
  <el-dialog
     width="30%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" label-width="100px">
      <el-form-item label="排序">
        <el-input-number style="width: 300px;"  v-model="formData.sort" @change="handleChange" :min="1" :max="10000"></el-input-number>
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
    name: 'Edit',
    data() {
      return {
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
      handleChange(value) {
        this.formData.sort = value
      },
      handleClose(done) {
        this.$emit('close-callback')
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.loading.show()
            api.zoneCourseUpdate(this.formData).then(res => {
              this.loading.hide()
              if (res.code === 200 && res.data > 0) {
                  this.$emit('close-callback')
              } else {
                this.$message({
                  type: 'error',
                  message: "更新失败"
                });
              }
            })
          } else {
            this.$message({
              type: 'error',
              message: "更新失败"
            });
          }
        });
      }
    }
  }
</script>

<style scoped>

</style>
