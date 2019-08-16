<template>
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose"
    width="70%">
    <el-form ref="formData" :model="formData" label-width="100px">
      <!-- <el-form-item label="头像">
        <el-upload
          class="upload-demo"
          action="http://192.168.31.134:5840/course/pc/upload/pic/{USER}"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
           multiple
          :limit="1"
          :on-success="success"
          :file-list="fileList">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </el-form-item> -->

      <el-form-item label="课程名称：">
        <el-input v-model="formData.courseName"></el-input>
      </el-form-item>
      <el-form-item label="是否免费：">
        <el-radio-group v-model="formData.isFree">
          <el-radio :label="0">收费</el-radio>
          <el-radio :label="1">免费</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-row v-if="formData.isFree == 0">
        <el-col :span="12">
          <el-form-item label="原价：">
            <el-input type="text" style="width: 120px" placeholder="请输入价格" v-model="formData.courseOriginal"></el-input> 元
          </el-form-item>
        </el-col>
        <!-- <el-col :span="12">
          <el-form-item label="优惠价：">
            <el-input type="text" style="width: 120px" placeholder="请输入价格" v-model="formData.courseDiscount"></el-input> 元
          </el-form-item>
        </el-col> -->
      </el-row>
      <el-form-item label="排序：">
        <el-input-number style="width: 300px;" v-model="formData.sort" @change="handleChange" :min="1"></el-input-number>
      </el-form-item>
      <el-form-item label="课程简介:">
         <div id="introduce"></div>
      </el-form-item>
      <el-row style="margin-top:17px; ">
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确定</el-button>
      </el-row>
    </el-form>
  </el-dialog>
</template>
<script>
import * as api from '@/api/course'
import * as commonalityApi from '@/api/commonality'
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
          this.editor.create();
          this.editor.customConfig.customUploadImg = this.editorUpload
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
    handleChange(value) {
      this.formData.sort = value
    },
    createEdit() {
      const E = require('wangeditor')
      this.editor = new E('#introduce')
    },
    submitForm(formData) {
      if (parseInt(this.formData.isFree) !== 1) {
        if (!this.formData.courseOriginal) {
          this.$alert('请输入课程原价')
          return false;
        }
        if (this.formData.courseOriginal <= 0) {
          this.$alert('请输入正确的课程原价')
          return false;
        }
      } else {
        this.formData.courseOriginal = 0;
      }
      this.$refs[formData].validate((valid) => {
        if (valid) {
          if (this.formData.id === undefined) {
            this.$message({
              type: 'error',
              message: "更新失败"
            });
          }
          this.loading.show()
          this.formData.introduce = this.editor.txt.html()
          api.courseUpdate(this.formData).then(res => {
            this.loading.hide()
            if (res.code === 200 && res.data > 0) {
              // 提交成功, 关闭窗口, 刷新列表
              this.tips('更新成功', 'success')
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
          this.$message({
            type: 'error',
            message: "更新失败"
          });
        }
      })
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
    handleClose(done) {
      this.editor.txt.clear()
      this.$emit('close-callback')
    }
  }
}
</script>

