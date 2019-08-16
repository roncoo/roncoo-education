<template>
  <!--弹窗-->
  <el-dialog
    width="35%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form :model="formData" :rules="rules" ref="formData" label-width="100px">
      <el-form-item label="广告图片:" prop="advImg">
        <div>
          <el-upload
            class="avatar-uploader"
            action=""
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :http-request="handlePost">
            <img v-if="formData.advImg" :src="formData.advImg" class="avatar" alt="">
            <i v-else  class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
      </el-form-item>
      <el-form-item label="广告标题:" prop="advTitle">
        <el-input v-model="formData.advTitle" ></el-input>
      </el-form-item>
      <el-form-item label="广告链接:" prop="advUrl">
        <el-input v-model="formData.advUrl" ></el-input>
      </el-form-item>
      <el-form-item label="广告排序:" prop="sort">
        <el-input-number style="width: 300px;" v-model="formData.sort" @change="handleChange" :min="1" :max="1000" label="描述文字"></el-input-number>
      </el-form-item>
      <el-form-item label="跳转方式:" >
        <el-select v-model="formData.advTarget" class="auto-width" clearable filterable placeholder="跳转方式" style="width: 300px">
          <el-option
            v-for="item in opts.advTargetList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间:">
        <el-date-picker
          v-model="formData.beginTime"
          type="datetime"
          placeholder="选择日期时间"
          align="right"
          value-format="yyyy-MM-dd HH:mm:ss"
          :picker-options="pickerOptions">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间:">
        <el-date-picker
          v-model="formData.endTime"
          type="datetime"
          placeholder="选择日期时间"
          align="right"
          value-format="yyyy-MM-dd HH:mm:ss"
          :picker-options="pickerOptions">
        </el-date-picker>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px; ">
      <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
      <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确定</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
  import * as api from '@/api/homepage'
  import * as apia from '@/api/commonality'
  export default {
    name: 'Edit',
    data() {
      return {
        opts: {
          advTargetList: []
        },
        pickerOptions: {
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
        rules: {
          advImg: [
            { required: true, message: '请输入广告图片', trigger: 'blur', autocomplete: 'on' }
          ],
          advTitle: [
            { required: true, message: '请输入广告标题', trigger: 'blur' }
          ],
          advUrl: [
            { required: true, message: '请输入广告链接', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '请输入广告排序', trigger: 'blur' }
          ],
          beginTime: [
            { required: true, message: '请输入开始时间', trigger: 'blur' }
          ],
          endTime: [
            { required: true, message: '请输入结束时间', trigger: 'blur' }
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
      this.$store.dispatch('GetOpts', { enumName: "AdvTargetEnum", type: 'arr' }).then(res => {
        this.opts.advTargetList = res
      })
    },
    methods: {
      handleChange(value) {
        this.formData.sort = value
      },
      handleClose(done) {
        this.$refs['formData'].resetFields()
        this.$emit('close-callback')
      },
      handleAvatarSuccess(res, file) {
        this.formData.advImg = URL.createObjectURL(file.raw);
      },
      handlePost(file) {
        console.log(file);
        const fd = new FormData();
        fd.append('picFile', file.file)
        // 配置post请求的参数。参数名file,后面跟要传的文件，参数名fileType，值为category（看后端的具体要求）
        apia.uploadPic(fd).then(response => {
          this.formData.advImg = response.data
          console.log(this.formData.advImg);
        }).catch(() => {
          this.$message({
            showClose: true,
            message: '上传失败',
            type: 'error'
          });
        })
      },
      submitForm(formName) {
        if (this.formData === undefined) {
          this.$message({
            showClose: true,
            message: '提交失败',
            type: 'error'
          });
        } else {
          if (!this.formData.advImg) {
            this.$message({
              type: 'error',
              message: '请添加广告图片'
            });
            return false
          }
          if (!this.formData.advTitle) {
            this.$message({
              type: 'error',
              message: '请输入广告标题'
            });
            return false
          }
          if (!this.formData.advUrl) {
            this.$message({
              type: 'error',
              message: '请输入广告链接'
            });
            return false
          }
          if (!this.formData.sort) {
            this.$message({
              type: 'error',
              message: '请输入广告排序'
            });
            return false
          }
          if (!this.formData.beginTime) {
            this.$message({
              type: 'error',
              message: '请输入开始时间'
            });
            return false
          }
          if (!this.formData.endTime) {
            this.$message({
              type: 'error',
              message: '请输入结束时间'
            });
            return false
          }
          this.loading.show()
          if (this.formData.id === undefined) {
            // 新增
            api.advSave(this.formData).then(res => {
              this.loading.hide()
              if (res.code === 200 && res.data > 0) {
                // 提交成功, 关闭窗口, 刷新列表
                this.handleClose('close-callback')
              } else {
                this.$message({
                  showClose: true,
                  message: '提交失败',
                  type: 'error'
                });
              }
            }).catch(() => {
              this.loading.hide()
              })
          } else {
            // 编辑
            this.loading.show()
            api.advUpdate(this.formData).then(res => {
              this.loading.hide()
              this.load = false
              if (res.code === 200 && res.data > 0) {
                // 提交成功, 关闭窗口, 刷新列表
                this.handleClose('close-callback')
              } else {
                this.$message({
                  showClose: true,
                  message: '提交失败',
                  type: 'error'
                });
              }
            }).catch(() => {
              this.loading.hide()
              })
          }
        }
      }
    }
  }
</script>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 150px;
    height: 150px;
    line-height: 150px;
    text-align: center;
  }
  .avatar {
    width: 150px;
    height: 150px;
    display: block;
  }
</style>
