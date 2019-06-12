<template>
  <el-dialog
  :title="this.head"
  :visible.sync="vb"
  :before-close="handleClose"
  width="30%"
  top="30vh">
  <el-form ref="map" :model="map">
    <el-row>
      审核状态：
      <el-radio v-model="map.auditSatusId" label="1">通过</el-radio>
      <el-radio v-model="map.auditSatusId" label="2">不通过</el-radio>
    </el-row>
    <el-row style="margin-top:15px;">
      <el-col>审核意见：</el-col>
    </el-row>
    <el-row style="margin-left:70px;">
      <el-input type="textarea" :rows="3" placeholder="系统默认通过" v-model="map.auditOpinion"> </el-input>
    </el-row>
    <el-row style="margin-top:17px; ">
      <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
      <el-button style="float:right" size="mini" type="primary" @click="submitForm('map')">确定</el-button>
    </el-row>
  </el-form>
  </el-dialog>
</template>
<script>
import * as courseApis from '@/api/course'
export default {
  name: 'CourseAudit',
  data() {
    return {
      ctrl: {
        load: false
      },
      map: {
        id: '',
        auditSatusId: '1',
        auditOpinion: ''
      },
      head: '',
      vb: false
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
    'visible': function(newValue) {
      this.vb = newValue
      this.head = this.title + " —— 信息审核"
    }
  },
  methods: {
    handleClose(done) {
      this.$emit('close-cllback')
    },
    submitForm(map) {
      this.$refs[map].validate((valid) => {
        if (valid) {
          this.handleConfirm()
        } else {
          return false;
        }
      })
    },
    async handleConfirm() {
      if (this.map.auditOpinion === '') {
        this.map.auditOpinion = "系统默认通过"
      }
      this.map.id = this.formData.data.id
      console.log(this.map)
      this.ctrl.load = true
      courseApis.courseAudit(this.map).then(res => {
        console.log("审核状态============" + res.code)
        this.tips('审核成功', 'success')
        this.$emit('close-cllback')
        this.ctrl.load = false
      }).catch(() => {
        this.$alert('审核失败')
      })
    }
  }
}
</script>
<style scoped>
</style>
