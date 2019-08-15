<template>
  <el-upload
    class="avatar-uploader"
    action=""
    :show-file-list="false"
    :http-request="handlePost">
    <img v-if="imageUrl" :src="imageUrl" class="avatar" alt="">
    <i v-else  class="el-icon-plus avatar-uploader-icon"></i>
  </el-upload>
</template>
<script>
  import * as api from '@/api/commonality'
  export default {
    data() {
      return {
        imageUrl: ''
      };
    },
    methods: {
      handlePost(file) {
        const fd = new FormData();
        fd.append('picFile', file.file)
        // 配置post请求的参数。参数名file,后面跟要传的文件，参数名fileType，值为category（看后端的具体要求）
        api.uploadPic(fd).then(response => {
          this.imageUrl = response.data
        }).catch(() => {
          this.$message({
            showClose: true,
            message: '上传失败',
            type: 'error'
          });
        })
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
