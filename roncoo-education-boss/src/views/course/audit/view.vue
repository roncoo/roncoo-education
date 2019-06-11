<template>
  <el-dialog
  :visible.sync="vb"
  :before-close="handleClose">
    <el-row>
      <el-col class="course_name">课程详情</el-col>
    </el-row>
    <el-container style="margin-bottom:20px;">
      <el-aside class="auto-width" :span="6" style="width: 300px; height: 155px; border-right:solid; border-color:#d0d0d0;">
        <el-image>{{formData.courseLogo}}</el-image>
      </el-aside>
      <el-aside class="auto-width" :span="6" style="width: 415px; height: 155px; margin-left:13px;">
        <el-col class="course_view">标题：{{formData.courseName}}</el-col>
        <el-col :span="12" class="course_view">原价：{{formData.courseOriginal}}</el-col>
        <el-col :span="12" class="course_view">优惠价：{{formData.courseDiscount}}</el-col>
        <el-col class="course_view">讲师名称：{{formData.lecturerName}}</el-col>
        <el-col class="course_view">所属分类：{{formData.categoryName1}} —— {{formData.categoryName2}} —— {{formData.categoryName3}}</el-col>
        <el-col :span="12" class="course_view">课程状态：{{textStatusId[formData.statusId]}}</el-col>
        <el-col :span="12" class="course_view">是否收费：{{textIsFree[formData.isFree]}}</el-col>
        <el-col class="course_view">上下架：{{textIsPutaway[formData.isPutaway]}}</el-col>
      </el-aside>
    </el-container>

    <el-row>
      <el-col class="course_name">课程章节—课时</el-col>
    </el-row>

    <el-tree default-expand-all :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>

    <el-form label-width="80px">
      <el-form-item>
        <el-button @click="handleClose">取 消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
//import * as courseApis from '@/api/course'
export default {
  name: 'ViewCourse',
  data() {
    return {
      ctrl: {
        load: false
      },
      vb: false,
      textStatusId: {
        1: '正常',
        0: '禁用'
      },
      textIsFree: {
        1: '免费',
        0: '收费'
      },
      textIsPutaway: {
        1: '上架',
        0: '下架'
      },
      data: [{
        label: '一级 1',
        children: [{
          label: '二级 1-1'
        }]
      }],
      defaultProps: {
        children: 'children',
        label: 'label'
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
      }
  },
  watch: {
    'visible': function(newValue) {
      this.vb = newValue
      console.log(this.formData)
      this.data = this.formData
    }
  },
  methods: {
    handleClose(done) {
      this.$emit('close-cllback')
    },
    handleNodeClick(data) {
      console.log(data);
    }
  }
}
</script>
<style scoped>
  .course_name {
    font-size:20px;
    margin-bottom:20px;
  }
  .course_view {
    margin-bottom:9px;
  }
</style>
