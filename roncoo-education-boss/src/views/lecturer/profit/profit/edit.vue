<template>
  <!--弹窗-->
  <el-dialog
    width="20%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" label-width="80px">
      <el-form-item label="分润状态:" prop="profitStatus" >
        <el-select v-model="formData.profitStatus" class="auto-width" clearable filterable placeholder="分润状态" style="width: 110px">
          <el-option
            v-for="item in profitStatusList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
      <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
      <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确定</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
  import * as api from '@/api/lecturer'
  export default {
    name: 'Edit',
    data() {
      return {
        profitStatusList: [],
        rules: {
          profitStatus: [
            { required: true, message: '请选择分润状态', trigger: 'blur' }
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
    mounted() {
      this.$store.dispatch('GetOpts', { enumName: "ProfitStatusEnum", type: 'arr' }).then(res => {
        this.profitStatusList = res
      })
    },
    methods: {
      handleClose(done) {
        this.$refs['formData'].resetFields()
        this.$emit('close-callback')
      },
      submitForm(formData) {
        if (!this.formData.profitStatus) {
          this.$message({
            type: 'error',
            message: '请选择分润状态'
          });
          return false
        }
        this.$refs[formData].validate((valid) => {
          if (valid) {
            this.loading.show()
            if (this.formData.id === undefined) {
              this.$message({
                type: 'error',
                message: "提交失败"
              });
            } else {
              api.lecturerProfitUpdate(this.formData).then(res => {
                this.loading.hide()
                if (res.code === 200 && res.data > 0) {
                    this.$emit('close-callback')
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
        })
      }
    }
  }
</script>
