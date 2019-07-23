<template>
  <!--弹窗-->
  <el-dialog
    width="35%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" :rules="rules" label-width="100px">
      <el-form-item label="菜单名称:" prop="menuName">
        <el-input v-model="formData.menuName"></el-input>
      </el-form-item>
      <el-form-item label="菜单地址:" prop="menuUrl">
        <el-input v-model="formData.menuUrl"></el-input>
      </el-form-item>
      <el-form-item label="目标名称:">
        <el-input v-model="formData.targetName"></el-input>
      </el-form-item>
      <el-form-item label="图标:" prop="menuIcon">
        <el-input v-model="formData.menuIcon"></el-input>
      </el-form-item>
      <el-form-item label="排序:">
        <el-input-number style="width: 300px;" v-model="sort" @change="handleChange" :min="1" :max="10000"></el-input-number>
      </el-form-item>
      <el-form-item label="备注:">
        <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容" v-model="formData.remark">
        </el-input>
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
  name: 'Add',
  data() {
    return {
      sort: 1,
      rules: {
        menuName: [
          { required: true, message: '请输入菜单名称', trigger: 'blur' }
        ],
        menuUrl: [
          { required: true, message: '请输入菜单地址', trigger: 'blur' }
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
    handleChange(value) {
      this.formData.sort = value
    },
    // 保存管理员信息
    submitForm(formData) {
      if (!this.formData.menuName) {
        this.$message({
          type: 'error',
          message: '请输入菜单名称'
        });
        return false
      }
      if (!this.formData.menuUrl) {
        this.$message({
          type: 'error',
          message: '请输入菜单地址'
        });
        return false
      }
      this.$refs[formData].validate((valid) => {
        if (valid) {
          this.loading.show()
          api.menuSave(this.formData).then(res => {
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
      this.$emit('close-callback')
    }
  }
}
</script>
