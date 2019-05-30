<template>
  <div>
    <el-form
      :inline="true"
      size="mini"
      label-position="left">
      <div class="font_14 font_b">入款配置</div>
      <div class="mgt20">
        <el-form-item label="支付产品编号" :label-width="formLabelWidth">
          <el-input v-model="params.productCode"></el-input>
        </el-form-item>
        <el-form-item label="支付产品名称" :label-width="formLabelWidth">
          <el-input v-model="params.productName"></el-input>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="收款方式" :label-width="formLabelWidth">
          <el-input v-model="params.fundIntoType"></el-input>
        </el-form-item>
        <el-form-item label="风险预存期" :label-width="formLabelWidth">
          <el-radio v-model="params.riskDay" :label="0">T+0</el-radio>
          <el-radio v-model="params.riskDay" :label="1">T+1</el-radio>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="是否自动结算" :label-width="formLabelWidth">
          <el-radio v-model="params.isAutoSett" label="1">是</el-radio>
          <el-radio v-model="params.isAutoSett" label="2">否</el-radio>
        </el-form-item>
      </div>
      <div class="font_14 font_b mgt20">支付安全配置</div>
      <div class="mgt20">
        <el-form-item label="余额充值" :label-width="formLabelWidth">
          <div class="w300">
            <el-radio v-model="params.isRecharge" label="no">关闭</el-radio>
            <el-radio v-model="params.isRecharge" label="yes">开启</el-radio>
          </div>
        </el-form-item>
        <el-form-item label="支付安全等级" :label-width="formLabelWidth">
          <el-select v-model="params.securityRating" placehorder="请选择">
            <el-option label="MD5" value=""></el-option>
          </el-select>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="支付IP白名单" :label-width="formLabelWidth">
          <el-input
            type="textarea"
            v-model="params.merchantServerIp"
            :autosize="{ minRows: 4 }">
          </el-input>
        </el-form-item>
      </div>
      <div class="font_14 font_b mgt20">代付安全配置</div>
      <div class="mgt20">
        <el-form-item label="商户出款类型" :label-width="formLabelWidth">
          <el-select placehorder="请选择" v-model="params.proxyPayType">
            <el-option label="商户后台+网关接口" value=""></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="代付扣款类型" :label-width="formLabelWidth">
          <el-radio v-model="radio.deductType" label="">内扣</el-radio>
          <el-radio v-model="radio.deductType" label="2">外扣</el-radio>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="代付安全等级" :label-width="formLabelWidth">
          <el-select placehorder="请选择" v-model="params.proxySecurityRating">
            <el-option label="MD5" value=""></el-option>
          </el-select>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="代付IP白名单" :label-width="formLabelWidth">
          <el-input
            v-model="params.merchantProxyServerIp"
            type="textarea"
            :autosize="{ minRows: 4 }">
          </el-input>
        </el-form-item>
      </div>
      <div class="pad20">
        <el-button type="" size="mini" class="fr mgl20">取消</el-button>
        <el-button type="primary" size="mini" class="fr">提交</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
export default {
  props: {
    rpUserPayConfigRespDTO: {
      type: [Object, Array],
      default() {
        return {}
      }
    }
  },
  watch: {
    rpUserPayConfigRespDTO: function(val) {
      console.log(val)
      if (val) {
        this.params = val
        console.log(this.params)
      }
    }
  },
  data() {
    return {
      formLabelWidth: '150px',
      params: {},
      radio: {
        risk: '1',
        auto: '1',
        recharge: '1',
        deduction: '1'
      }
    }
  }
}
</script>

<style lang="scss">
  .w300 {
    width: 300px;
  }
</style>
