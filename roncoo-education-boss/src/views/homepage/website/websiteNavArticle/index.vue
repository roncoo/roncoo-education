<template>
  <div class="pad20">
    <el-form ref="formData" :model="formData" label-width="100px">
      <el-form-item label="文章名称：">
        <el-input v-model="formData.artTitle"></el-input>
      </el-form-item>
      <el-form-item label="文章描述：" style="width:80%">
         <div id="artDesc" style="height:400px;max-height:500px;"></div>
      </el-form-item>
      <el-form-item label="排序：">
        <el-input-number style="width: 300px;" v-model="formData.sort" @change="handleChange" :min="1" :max="10000"></el-input-number>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
      <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
      <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确定</el-button>
    </el-row>
  </div>
</template>
<script>
  import * as api from '@/api/homepage'
  import * as commonalityApi from '@/api/commonality'
  export default {
    data() {
      return {
        ctrl: {
          loading: false
        },
        formData: {},
        navId: ''
      }
    },
    mounted() {
      this.navId = this.$route.query.navId
      this.getArticala(this.navId)
    },
    methods: {
      createEdit() {
        const E = require('wangeditor')
        this.editor = new E('#artDesc')
          setTimeout(() => {
          this.editor.create(); // 创建用户协议富文本编辑器
          this.editor.customConfig.customUploadImg = this.editorUpload
          if (this.formData.artDesc !== undefined && this.formData.artDesc !== '' && this.formData.artDesc !== null) {
            this.editor.txt.html(this.formData.artDesc)
          } else {
            this.editor.txt.html('')
          }
        }, 100)
      },
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
      handleChange(value) {
        this.formData.sort = value
      },
      getArticala(navId) {
        this.loading.show()
        api.navArticleView({ navId: navId }).then(res => {
          this.createEdit();
          this.loading.hide()
          if (res.code === 200) {
              this.formData = res.data
          }
        }).catch(() => {
          this.loading.hide()
        })
      },
      handleClose() {
        this.editor.txt.clear()
        window.opener = null;
        window.open("about:blank", "_top").close()
        this.$router.go(-1)
      },
      submitForm(formData) {
        this.$refs[formData].validate((valid) => {
          if (valid) {
            this.loading.show()
            api.navArticleUpdate(this.formData).then(res => {
              this.loading.hide()
              if (res.code === 200 && res.data > 0) {
                this.tips('操作成功', 'success')
                this.handleClose()
              } else {
                this.$message({
                  type: 'error',
                  message: "更新失败"
                });
              }
            }).catch(() => {
              this.loading.hide()
            })
          } else {
            this.loading.hide()
            this.$message({
              type: 'error',
              message: "更新失败"
            });
          }
        })
      }
    }
  }
</script>
