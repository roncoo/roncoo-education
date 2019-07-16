<template>
  <!--弹窗-->
  <el-dialog
    width="30%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" :rules="rules" label-width="80px">
      <el-form-item label="菜单名称:" prop="menuName">
        <el-input v-model="formData.menuName"></el-input>
      </el-form-item>
      <el-form-item label="菜单路径:" prop="menuUrl">
        <el-input v-model="formData.menuUrl"></el-input>
      </el-form-item>
      <el-form-item label="目标名称:" prop="menuUrl">
        <el-input v-model="formData.menuUrl"></el-input>
      </el-form-item>
      <el-form-item label="排序:">
        <el-input v-model="formData.sort"></el-input>
      </el-form-item>
      <el-form-item label="备注:">
        <el-input type="textarea" v-model="formData.remark"></el-input>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确 定</el-button>
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
  import * as api from '@/api/system'
  export default {
    name: 'Edit',
    data() {
      return {
        rules: {
          roleName: [
            { required: true, message: '请输入名称', trigger: 'blur' }
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
        this.load = true
        let res = {}
        if (this.formData.id === undefined) {
          this.$alert(res.msg || '提交失败')
        } else {
          res = await api.roleUpdate(this.formData)
          // this.tips('成功', 'success')
        }
        this.load = false
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
