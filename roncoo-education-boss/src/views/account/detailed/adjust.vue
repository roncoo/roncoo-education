<template>
  <!--弹窗-->
  <el-dialog
    :title="`${accountData.userName} 调账`"
    :visible.sync="visible"
    v-loading="ctrl.loading"
    :before-close="handleClose">

    <el-form :model="form" class="adjust-form" label-width="100px">
      <el-form-item label="调账类型" prop="roleName">
        <div class="" v-for="item in opts.debugType" :key="item.name">
          <el-radio v-model="form.debugType" :label="item.name" style="width: 100px" border size="small">{{item.desc}}</el-radio>
          <el-tag type="warning">{{opts.tips[item.name]}}</el-tag>
        </div>
      </el-form-item>
      <el-form-item label="加款" prop="roleCode" v-if="form.debugType == 'ADD'">
        <el-select v-model="form.bankOption" filterable clearable placeholder="通道名称">
          <el-option :label="`[${bankWayObj[item.bankWayCode]}]-[${item.payWayName}T0]-[${item.t0SettAmount.toFixed(2)}元]`" :value="'T0-' + index" v-for="(item, index) in bankWayList" :key="'to' + index"></el-option>
          <el-option :label="`[${bankWayObj[item.bankWayCode]}]-[${item.payWayName}T1]-[${item.t1SettAmount.toFixed(2)}元]`" :value="'T1-' + index" v-for="(item, index) in bankWayList" :key="'t1' + index"></el-option>
        </el-select>
        <el-input style="width: 120px" v-model="form.orderPrice" placeholder="加款金额"></el-input>
      </el-form-item>
      <el-form-item label="减款" prop="roleCode" v-else-if="form.debugType == 'SUB'">
        <el-select v-model="form.bankOption" filterable clearable placeholder="通道名称">
          <el-option :label="`[${bankWayObj[item.bankWayCode]}]-[${item.payWayName}T0]-[${item.t0SettAmount.toFixed(2)}元]`" :value="'T0-' + index" v-for="(item, index) in bankWayList" :key="'to' + index"></el-option>
          <el-option :label="`[${bankWayObj[item.bankWayCode]}]-[${item.payWayName}T1]-[${item.t1SettAmount.toFixed(2)}元]`" :value="'T1-' + index" v-for="(item, index) in bankWayList" :key="'t1' + index"></el-option>
        </el-select>
        <el-input style="width: 120px" v-model="form.orderPrice" placeholder="减款金额"></el-input>
      </el-form-item>
      <el-form-item label="冻结" prop="roleCode" v-else-if="form.debugType == 'FREEZE'">
        <el-select v-model="form.bankOption" filterable clearable placeholder="通道名称">
          <el-option :label="`[${bankWayObj[item.bankWayCode]}]-[${item.payWayName}T0]-[${item.t0SettAmount.toFixed(2)}元]`" :value="'T0-' + index" v-for="(item, index) in bankWayList" :key="'to' + index"></el-option>
          <el-option :label="`[${bankWayObj[item.bankWayCode]}]-[${item.payWayName}T1]-[${item.t1SettAmount.toFixed(2)}元]`" :value="'T1-' + index" v-for="(item, index) in bankWayList" :key="'t1' + index"></el-option>
        </el-select>
        <el-input style="width: 120px" v-model="form.orderPrice" placeholder="冻结金额"></el-input>
      </el-form-item>
      <el-form-item label="解冻" prop="roleCode" v-else-if="form.debugType == 'UNFREEZE'">
        <el-select v-model="form.bankOption" filterable clearable placeholder="通道名称">
          <el-option :label="`[${bankWayObj[item.bankWayCode]}]-[${item.payWayName}T0]-[${item.t0SettAmount.toFixed(2)}元]`" :value="'T0-' + index" v-for="(item, index) in bankWayList" :key="'to' + index"></el-option>
          <el-option :label="`[${bankWayObj[item.bankWayCode]}]-[${item.payWayName}T1]-[${item.t1SettAmount.toFixed(2)}元]`" :value="'T1-' + index" v-for="(item, index) in bankWayList" :key="'t1' + index"></el-option>
        </el-select>
        <el-input style="width: 120px" v-model="form.orderPrice" placeholder="解冻金额"></el-input>
      </el-form-item>
      <el-form-item label="加可结算" prop="roleCode" v-else-if="form.debugType == 'SETT'">
        <el-input v-model="form.orderPrice" placeholder="可结算金额"></el-input>
      </el-form-item>
      <el-form-item label="进账" prop="roleCode" v-else-if="form.debugType == 'IN'">
        <el-select v-model="form.bankWay" filterable clearable placeholder="通道名称">
          <el-option :label="item.desc" :value="item.name" v-for="(item, index) in opts.bankWayList" :key="'to' + index"></el-option>
        </el-select>
        <el-select v-model="form.payWay" filterable clearable placeholder="支付方式">
          <el-option :label="item.desc" :value="item.name" v-for="(item, index) in opts.payWayList" :key="'to' + index"></el-option>
        </el-select>
        <el-select v-model="form.type" filterable clearable placeholder="交易类型" style="width: 120px;">
          <el-option :label="item.desc" :value="item.name" v-for="(item, index) in opts.bst" :key="'to' + index"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="通道间调账" prop="roleCode" v-else-if="form.debugType == 'DEBUG'">
        <el-select v-model="form.bankOption" filterable clearable placeholder="出款通道">
          <el-option :label="`[${bankWayObj[item.bankWayCode]}]-[${item.payWayName}T0]-[${item.t0SettAmount.toFixed(2)}元]`" :value="'T0-' + index" v-for="(item, index) in bankWayList" :key="'to' + index"></el-option>
          <el-option :label="`[${bankWayObj[item.bankWayCode]}]-[${item.payWayName}T1]-[${item.t1SettAmount.toFixed(2)}元]`" :value="'T1-' + index" v-for="(item, index) in bankWayList" :key="'t1' + index"></el-option>
        </el-select>
        <el-select v-model="form.inBankOption" filterable clearable placeholder="进款通道" style="margin-top: 12px">
          <el-option :label="`[${bankWayObj[item.bankWayCode]}]-[${item.payWayName}T0]-[${item.t0SettAmount.toFixed(2)}元]`" :value="'T0-' + index" v-for="(item, index) in bankWayList" :key="'to' + index"></el-option>
          <el-option :label="`[${bankWayObj[item.bankWayCode]}]-[${item.payWayName}T1]-[${item.t1SettAmount.toFixed(2)}元]`" :value="'T1-' + index" v-for="(item, index) in bankWayList" :key="'t1' + index"></el-option>
        </el-select>
        <el-input style="width: 120px" v-model="form.orderPrice" placeholder="调整金额"></el-input>
      </el-form-item>
      <el-form-item label="备注信息" prop="remark">
        <el-input v-model="form.remark" type="textarea"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="submitForm">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import * as apis from '@/api/account'
  import * as bankApis from '@/api/bank'
  export default {
    name: "Adjust",
    data() {
      return {
        ctrl: {
          dialogVisible: true,
          loading: false
        },
        opts: {
          tips: { 'ADD': '加总余额+加进款', 'SUB': '减总余额+加出款', 'FREEZE': '加冻结款+加出款', 'UNFREEZE': '减冻结款+加进款', 'DEBUG': '从一个通道调整到另一个通道', 'IN': '选择一个通道,生成进款记录', 'SETT': '加总余额+加可结算金额' },
          debugType: [],
          bankWayList: []
        },
        form: {}

      }
    },
    props: {
      // route object
      accountData: {
        type: Object,
        default: () => {}
      },
      bankWayList: {
        type: Array,
        default: () => []
      },
      bankWayObj: {
        type: Object,
        default: () => {}
      },
      visible: {
        type: Boolean,
        default: false
      }
    },
    mounted() {
      // this.remoteAuthors()
      this.$store.dispatch('GetOpts', { name: 'AccountDebugAmountEnum' }).then(res => {
        this.opts.debugType = res;
      })
      this.$store.dispatch('GetOpts', { name: 'PayWayEnum' }).then(res => {
        this.opts.payWayList = res;
      })
      this.$store.dispatch('GetOpts', { name: 'BankSettTypeEnum' }).then(res => {
        console.log(res)
        this.opts.bst = res;
      })
      this.getBankWayAll()
    },
    methods: {
      getBankWayAll() {
        bankApis.bankWayAll().then(res => {
          console.log(res)
          if (res.code === 200) {
            this.opts.bankWayList = res.data
          }
        })
      },
      handleClose(done) {
        this.$emit('close-cllback')
      },
      submitForm() {
        console.log(this.accountData)
        if (!this.form.debugType) {
          this.tips('请选择调账类型', 'error')
          return false
        }
        if (!this.form.remark) {
          this.tips('调账备注不可为空', 'error')
          return false
        }
        const params = {
          accountNo: this.accountData.accountNo,
          userNo: this.accountData.userNo,
          debugType: this.form.debugType,
          orderPrice: this.form.orderPrice || 0,
          balance: 0,
          type: 1,
          debugBankWayCode: this.form.debugType,
          debugPayWayCode: this.form.debugType,
          debugRemark: this.form.remark
        }
        if (this.form.debugType === 'IN') {
          params.type = this.form.type
          params.debugPayWayCode = this.form.payWay
          params.debugBankWayCode = this.form.bankWay
        } else if (this.form.debugType !== 'SETT') {
          if (!this.form.bankOption) {
            this.tips('请选择银行通道', 'error')
            return false
          }
          const pd = this.form.bankOption.split('-')
          const bankObj = this.bankWayList[parseInt(pd[1])]
          params.balance = pd[0] === 'T0' ? bankObj.t0SettAmount : bankObj.t1SettAmount;
          params.type = pd[0];
          params.debugBankWayCode = bankObj.bankWayCode;
          params.debugPayWayCode = bankObj.payWayCode;
        }
        if (this.form.debugType === 'DEBUG') {
          if (!this.form.inBankOption) {
            this.tips('请选择进款通道', 'error')
            return false
          }
          const inPd = this.form.inBankOption.split('-')
          const inBankObj = this.bankWayList[parseInt(inPd[1])]
          params.inBalance = inPd[0] === 'T0' ? inBankObj.t0SettAmount : inBankObj.t1SettAmount;
          params.inBankWayCode = inBankObj.payWayCode;
          params.inPayWayCode = inBankObj.payWayCode;
          params.inType = inPd[0];
        }
        this.loading.show('正在发起请求');
        apis.debugAmount(params).then(res => {
          this.$alert(res.msg)
          this.loading.hide();
          if (res.code === 200) {
            this.form = {}
            this.$emit('close-cllback', 'ok')
          }
        }).catch(() => {
          this.loading.hide();
        })
      }
    }
  }
</script>

<style lang="scss">
  .adjust-form{
    margin: -20px 0;
    .el-select,.el-input{
      width: auto;
    }
  }
</style>
