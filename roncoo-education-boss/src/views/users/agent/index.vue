<template>
  <div class="pad10">
    <div class="border mgt30" v-if="agentInfo.firstAgentRateList">
      <div class="c_red mgt_up"><span class="pad10 border">一级代理商信息</span></div>
      <el-row class="pad10 mgt10">
        <el-col :span="6"><div>一级代理商编号：</div></el-col>
        <el-col :span="6"><div>{{agentInfo.firstAgentNo}}</div></el-col>
        <el-col :span="6"><div>一级代理商名称：</div></el-col>
        <el-col :span="6"><div>{{agentInfo.firstAgentName}}</div></el-col>
      </el-row>
      <el-row class="border mgt10" v-for="item in agentInfo.firstAgentRateList" :key="item.id">
        <el-col :span="3"><div>支付方式：</div></el-col>
        <el-col :span="6">
          <div>
            {{item.payWayName}}--<span class="c_red">{{item.payTypeName}}</span>
          </div>
        </el-col>
        <el-col :span="2"><div>费率：</div></el-col>
        <el-col :span="5">
          <div>
            (支付[<span class="c_red">{{item.rateTypeDesc}}</span>] 代付[<span class="c_red">{{item.proxyRateTypeDesc}}</span>])
          </div>
        </el-col>
        <el-col :span="7">
          <div>
            支付：<span class="c_red">{{item.payRate}}</span>--代付：<span class="c_red">{{item.proxyRate}}</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="mgt40 border" v-if="agentInfo.secondAgentRateList">
      <div class="c_red mgt_up"><span class="pad10 border">二级代理商信息</span></div>
      <el-row class="pad10 mgt10">
        <el-col :span="6"><div>二级代理商编号：</div></el-col>
        <el-col :span="6"><div>{{agentInfo.secondAgentNo}}</div></el-col>
        <el-col :span="6"><div>二级代理商名称：</div></el-col>
        <el-col :span="6"><div>{{agentInfo.secondAgentName}}</div></el-col>
      </el-row>
      <el-row class="border mgt10" v-for="item in agentInfo.secondAgentRateList" :key="item.id">
        <el-col :span="3"><div>支付方式：</div></el-col>
        <el-col :span="6">
          <div>
            {{item.payWayName}}--<span class="c_red">{{item.payTypeName}}</span>
          </div>
        </el-col>
        <el-col :span="2"><div>费率：</div></el-col>
        <el-col :span="5">
          <div>&nbsp;</div>
        </el-col>
        <el-col :span="7">
          <div>
            支付：<span class="c_red">{{item.payRate}}</span>--代付：<span class="c_red">{{item.proxyRate}}</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="mgt40 border" v-if="agentInfo.thirdAgentRateList">
      <div class="c_red mgt_up"><span class="pad10 border">三级代理商信息</span></div>
      <el-row class="pad10 mgt10">
        <el-col :span="6"><div>三级代理商编号：</div></el-col>
        <el-col :span="6"><div>{{agentInfo.thirdAgentNo}}</div></el-col>
        <el-col :span="6"><div>三级代理商名称：</div></el-col>
        <el-col :span="6"><div>{{agentInfo.thirdAgentName}}</div></el-col>
      </el-row>
      <el-row class="border mgt10" v-for="item in agentInfo.thirdAgentRateList" :key="item.id">
        <el-col :span="3"><div>支付方式：</div></el-col>
        <el-col :span="6">
          <div>
            {{item.payWayName}}--<span class="c_red">{{item.payTypeName}}</span>
          </div>
        </el-col>
        <el-col :span="2"><div>费率：</div></el-col>
        <el-col :span="5">
          <div>&nbsp;</div>
        </el-col>
        <el-col :span="7">
          <div>
            支付：<span class="c_red">{{item.payRate}}</span>--代付：<span class="c_red">{{item.proxyRate}}</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="mgt40 border" v-if="agentInfo.merchantRateList">
      <div class="c_red mgt_up"><span class="pad10 border">商户已设置的费率</span></div>
      <div class="mgt10"></div>
      <el-row class="pad10 mgt10" v-for="item in agentInfo.merchantRateList" :key="item.id">
        <el-col :span="3"><div>支付方式：</div></el-col>
        <el-col :span="6">
          <div>
            {{item.payWayName}}--<span class="c_red">{{item.payTypeName}}</span>
          </div>
        </el-col>
        <el-col :span="2"><div>费率：</div></el-col>
        <el-col :span="5">
          <div>&nbsp;</div>
        </el-col>
        <el-col :span="7">
          <div>
            支付：<span class="c_red">{{item.payRate}}</span>--代付：<span class="c_red">{{item.proxyRate}}</span>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  data() {
    return {
      formLabelWidth: '120px',
      agentInfo: {}
    }
  },
  methods: {
    ...mapActions('users', ['lookAgentInfo']),
    getAgentInfo() {
      const params = {
        userNo: this.$route.query.userNo
      }
      this.lookAgentInfo(params).then(res => {
        console.log(res)
        this.agentInfo = res.data
      })
    }
  },
  mounted() {
    this.getAgentInfo()
  }
}
</script>

<style lang="scss">
  .mgt_up {
    margin-top: -20px;
  }
  .border {
    background: #fff;
    border: 1px dashed rgb(225, 225, 225);
    padding: 10px;
  }
</style>
