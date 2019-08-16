<template>
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose"
    width="50%">
    <el-form :inline="true">
      <el-alert class="title" :closable="false" title="订单信息" type="info" />
      <el-row>
        <el-col :span="12">
          <el-form-item label="用户编号:">
            <span >{{formData.userNo}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="注册时间:">
            <span >{{formData.registerTime}}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="课程名称:">
            <span >{{formData.courseName}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="讲师名称:">
            <span >{{formData.lecturerName}}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="订单号:">
            <span >{{formData.orderNo}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="流水号:">
            <span >{{formData.serialNumber}}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="交易类型:">
            <template slot-scope="scope">
              <span>{{textTradeType[formData.tradeType]}}</span>
            </template>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="支付时间:">
            <span >{{formData.payTime}}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="客户备注:">
            <span >{{formData.remarkCus}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="后台备注:">
            <span >{{formData.remark}}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-alert class="title" :closable="false" title="金额分成" type="info" />

      <el-row>
        <el-col :span="8">
          <el-form-item label="支付方式:">
            <template slot-scope="scope">
              <el-row>{{textPayType[formData.payType]}}</el-row>
            </template>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="购买渠道:">
            <template slot-scope="scope">
              <el-row>{{textChannelType[formData.channelType]}}</el-row>
            </template>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <template slot-scope="scope">
            <el-row>{{textOrderStatus[formData.orderStatus]}}</el-row>
          </template>
        </el-col>
      </el-row>
    </el-form>
  </el-dialog>
</template>
<script>
  export default {
    name: 'OrderView',
    data() {
      return {
        textTradeType: {
          1: '线上支付',
          2: '线下支付'
        },
        textPayType: {
          1: '微信支付',
          2: '支付宝支付'
        },
        textChannelType: {
          1: 'PC端'
        },
        textOrderStatus: {
          1: '待支付',
          2: '支付成功',
          3: '支付失败',
          4: '已关闭'
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
    methods: {
      handleClose(done) {
        this.$emit('close-callback')
      }
    }
  }
</script>
