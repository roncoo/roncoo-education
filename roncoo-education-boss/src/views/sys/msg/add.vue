<template>
  <!--弹窗-->
  <div>
  <el-dialog
    width="80%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <el-form-item label="标题:" prop="msgTitle">
        <el-input v-model="form.msgTitle"></el-input>
      </el-form-item>
      <el-form-item label="内容:" style="width:80%">
         <div id="msgText" style="height:400px;max-height:500px;"></div>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button class="button" type="primary" @click="submitForm('form')">确 定</el-button>
      <el-button class="button" type="danger" plain @click="handleClose">取 消</el-button>
    </div>
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
      form: {},
      rules: {
        msgTitle: [
          { required: true, message: '请选择标题', trigger: 'blur' }
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
  watch: {
    visible: function(val) {
      console.log(console)
      if (val !== false) {
        setTimeout(() => {
          this.editor.create();
          this.editor.customConfig.customUploadImg = this.editorUpload
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
      if (!this.form.msgTitle) {
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
    //异步保存管理员信息
    async handleConfirm() {
      this.load = true
      let res = {}
      if (this.form === undefined) {
        this.$alert(res.msg || '提交失败')
      } else {
        res = await api.msgSave(this.form)
        // this.tips('成功', 'success')
      }
      this.load = false
      if (res.code === 200 && res.data > 0) {
        // 提交成功, 关闭窗口, 刷新列表
        this.$emit('close-cllback')
      } else {
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

