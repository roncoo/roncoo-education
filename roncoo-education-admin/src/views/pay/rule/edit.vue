<template>
  <!--弹窗-->
  <div class="">
  <el-dialog
    title="添加支付路由"
    :visible.sync="visible"
    :before-close="handleClose">

    <el-form :model="formData" class="form" :rules="rules" label-width="180px" ref="formData">
      <el-alert :closable="false" title="支付路由限制" />
      <br>
      <el-form-item label="支付渠道" required>
        <el-input v-model="formData.bankChannelName" readonly></el-input>
        <el-button @click="findBank" title="查找" icon="el-icon-search" circle size="mini"></el-button>
      </el-form-item>
      <el-form-item label="渠道权重" prop="channelPriority" required>
        <el-input-number v-model="formData.channelPriority" :step="10" :min="0" class="auto-width" style="width: 130px;"></el-input-number>
      </el-form-item>
      <el-form-item label="路由状态" prop="status" required>
        <el-radio v-model="formData.status" label="YES" border>开启</el-radio>
        <el-radio v-model="formData.status" label="NO" border>关闭</el-radio>
      </el-form-item>
      <el-form-item label="单笔支付金额" prop="maxPayAmount">
        <el-input-number :controls="false" v-model="formData.minPayAmount" :min="0" :max="formData.maxPayAmount" class="auto-width" style="width: 130px;"></el-input-number>——
        <el-input-number :controls="false" v-model="formData.maxPayAmount" :min="formData.minPayAmount" class="auto-width" style="width: 130px;"></el-input-number> （元）
      </el-form-item>
      <el-form-item label="交易时间" prop="payTime">
        <el-time-picker
          is-range
          value-format="HH:mm:ss"
          v-model="payTime"
          range-separator="至"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          placeholder="选择时间范围">
        </el-time-picker>
      </el-form-item>
      <el-form-item label="备注">
        <el-input v-model="formData.remark" ></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="submitForm">确 定</el-button>
    </div>
  </el-dialog>
  <find-channel :visible="ctrl.dialogVisible" :title="ctrl.dialogTitle" @close-callback="closeCallback"></find-channel>
  </div>
</template>

<script>
  import FindChannel from '../way/edit/findChannel'
  import * as apis from '@/api/pay'
  export default {
    components: { FindChannel },
    data() {
      return {
        ctrl: {
          dialogVisible: false,
          dialogTitle: '查询银行渠道'
        },
        // formData: {
        //   channelPriority: 1,
        //   status: 'NO',
        //   payTime: ['0:0:0', '23:59:59']
        // },
        payTime: ['0:0:0', '23:59:59'],
        rules: {
          maxPayAmount: [
            { required: true, message: '请输入单笔支付金额限制', trigger: 'blur' }
          ],
          payTime: [
            { required: true, message: '请选择支付时间限制', trigger: 'blur' }
          ]
        }
      }
    },
    props: {
      // route object
      formData: {
        type: Object,
        default: () => {
          return {
            channelPriority: 1,
            status: 'NO'
          }
        }
      },
      visible: {
        type: Boolean,
        default: false
      }
    },
    watch: {
      formData(val) {
        if (val.beginPayTime) {
          this.payTime = [val.beginPayTime, val.endPayTime]
        }
      }
    },
    mounted() {
    },
    methods: {
      handleClose(done) {
        this.$emit('close-callback')
      },
      findBank() {
        this.ctrl.dialogVisible = true;
      },
      closeCallback(res) {
        this.ctrl.dialogVisible = false;
        if (res) {
          this.formData.bankChannelCode = res.bankChannelCode
          this.formData.bankChannelName = res.bankChannelName
        }
      },
      submitForm() {
        this.$refs.formData.validate((valid) => {
          if (valid) {
            this.handleConfirm()
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      handleConfirm() {
        this.loading.show()
        let action = 'addPayRule'
        if (this.formData.id !== undefined) {
          action = 'updatePayRule'
          this.formData.beginPayTime = undefined;
          this.formData.endPayTime = undefined;
        }
        this.formData.payWayId = this.$route.query.id;
        this.formData.beginPayTimeStr = this.payTime[0];
        this.formData.endPayTimeStr = this.payTime[1];
        apis[action](this.formData).then(res => {
          if (res.code === 200) {
            this.loading.hide()
            this.$message({
              type: 'success',
              message: '提交成功'
            });
            // 提交成功, 关闭窗口, 刷新列表
            this.$emit('close-callback')
          } else {
            this.$alert(res.msg || '提交失败')
          }
        }).catch(() => {
          this.loading.hide()
        })
      }
    }
  }
</script>

<style scoped>
  .form{
    margin: -20px 0;
  }
</style>
