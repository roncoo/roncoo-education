<template>
  <!--弹窗-->
  <div>
  <el-dialog
    width="60%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="formData" :model="formData" :rules="rules" label-width="100px">
      <el-form-item label="标题:" prop="msgTitle">
        <el-input v-model="formData.msgTitle"></el-input>
      </el-form-item>
      <el-form-item label="是否置顶:">
        <el-radio-group v-model="formData.isTop">
          <el-radio :label="1">是</el-radio>
          <el-radio :label="0">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="内容:">
         <div id="msgText"></div>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确 定</el-button>
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
    </el-row>
  </el-dialog>
  </div>
</template>
<script>
import * as api from '@/api/system'
import * as userApi from '@/api/user'
export default {
  name: 'Add',
  data() {
    return {
      rules: {
        msgTitle: [
          { required: true, message: '请选择标题', trigger: 'blur' }
        ]
      }
    }
  },
  props: {
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
  watch: {
    formData: function(val) {
      if (val !== undefined) {
        setTimeout(() => {
          this.editor.create();
          this.editor.customConfig.customUploadImg = this.editorUpload
          if (this.formData.msgText !== undefined && this.formData.msgText !== '' && this.formData.msgText !== null) {
            this.editor.txt.html(this.formData.msgText)
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
    createEdit() {
      const E = require('wangeditor')
      this.editor = new E('#msgText')
    },
    // 保存管理员信息
    submitForm(form) {
      if (!this.formData.msgTitle) {
        this.$message({
          type: 'error',
          message: '请输入标题'
        });
        return false
      }
      this.$refs[form].validate((valid) => {
        if (valid) {
          this.formData.msgText = this.editor.txt.html()
          this.handleConfirm()
        } else {
          return false;
        }
      })
    },
    //异步更新管理员信息
    async handleConfirm() {
      this.load = true
      let res = {}
      if (this.formData === undefined) {
        this.$alert(res.msg || '提交失败')
      } else {
        res = await api.msgUpdate(this.formData)
        // this.tips('成功', 'success')
      }
      this.load = false
      if (res.code === 200 && res.data > 0) {
        // 提交成功, 关闭窗口, 刷新列表
        this.editor.txt.clear()
        this.$emit('close-cllback')
      } else {
        this.editor.txt.clear()
        this.$alert(res.msg || '提交失败')
      }
    },
    // 编辑器上传图片
    editorUpload(files, insert) {
      const file = files[0];
      const param = new FormData();
      param.append('picFile', file, file.name);
      userApi.uploadPic(param).then(res => {
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
    // 关闭弹窗
    handleClose(done) {
      this.form = {}
      this.editor.txt.clear()
      this.$emit('close-cllback')
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
