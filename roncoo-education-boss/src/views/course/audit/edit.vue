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
        <el-col :span="12">
          <el-form-item label="优惠价：">
            <el-input type="text" style="width: 120px" placeholder="请输入价格" v-model="formData.courseDiscount"></el-input> 元
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="排序：">
        <el-input v-model="formData.sort"></el-input>
      </el-form-item>

      <el-row style="margin-top:17px; ">
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('map')">确定</el-button>
      </el-row>
    </el-form>
  </el-dialog>
</template>
<script>
//import * as courseApis from '@/api/course'
export default {
  name: 'Edit',
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
    }
  }
}
</script>

