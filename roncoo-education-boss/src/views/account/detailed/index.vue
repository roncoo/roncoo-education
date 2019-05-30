<template>
  <div class="pad20 account_detail">
    <el-form :inline="true" ref="accountForm" size="mini">
      <el-form-item label="商户名称" class="par40">
        <span class="inline_box ">{{account && account.userName}}</span>
        <el-button size="mini" type="text" @click="ctrl.adjust = true">[调账]</el-button>
      </el-form-item>
      <el-form-item label="账户余额">
        <span class="inline_box par40 c-brand">{{account && account.availableBalance}}</span>
      </el-form-item>
      <el-form-item label="冻结金额">
        <span class="inline_box par40 c_red">{{account && account.unbalance}}</span>
      </el-form-item>
      <el-form-item label="可结算金额">
        <span class="inline_box par40 c-brand">{{account && account.availableSettAmount}}</span>
      </el-form-item>
    </el-form>
    <div class="filter-container">
      <el-select v-model="params.bankWayCode" filterable clearable placeholder="通道名称">
        <el-option :label="item.desc" :value="item.name" v-for="item in option.bankwayList" :key="item.name"></el-option>
      </el-select>

      <el-select v-model="params.payWayCode" filterable clearable placeholder="支付方式">
        <el-option :label="item.desc" :value="item.name" v-for="item in option.payWayList" :key="item.name"></el-option>
      </el-select>

        <el-button class="filter-item" type="primary" @click="handleCheck">查询</el-button>
        <el-button class="filter-item" @click="handleReset">
          重置
        </el-button>
    </div>
      <div>
      <el-table
        v-loading="ctrl.load"
        size="medium"
        :data="listSettAmount"
        stripe
        border
        style="width: 100%">
        <el-table-column
          type="index"
          label="序号"
          width="50">
        </el-table-column>
        <el-table-column
          prop="bankWayCode"
          label="银行名称">
          <template slot-scope="scope">
            {{option.bankwayObj && option.bankwayObj[scope.row.bankWayCode]}}
          </template>
        </el-table-column>
        <el-table-column
          label="款项名称">
          <template slot-scope="scope">
            {{scope | payWayName(listSettAmount)}}
          </template>
        </el-table-column>
        <el-table-column
          label="余额">
          <template slot-scope="scope">
            {{scope | settAmount(listSettAmount)}}
          </template>
        </el-table-column>
        <el-table-column
          prop="address"
          label="操作">
          <template slot-scope="scope" fixed="right">
            <el-button
              @click.native.prevent="makeMoney(scope.row, scope.$index)"
              type="text"
              size="small">
              手动打款
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <add-money :visible="ctrl.dialogVisible" :title="`${account && account.userName}手动打款`" :accountData="moneyData" @close-cllback="closeCllback"></add-money>
    <adjust :visible="ctrl.adjust" :bankWayObj="option.bankwayObj" :bankWayList="settList" :accountData="account" @close-cllback="adjustCollback"></adjust>
  </div>
</template>

<script>
import * as accountApis from '@/api/account'
import * as apis from '@/api/bank'
import addMoney from './addMoney'
import adjust from './adjust'
export default {
  filters: {
    payWayName(scope, listSettAmount) {
      if (scope.$index < listSettAmount.length / 2) {
        return `${scope.row.payWayName}T0可结算余额`
      }
      return `${scope.row.payWayName}T1可结算余额`
    },
    settAmount(scope, listSettAmount) {
      if (scope.$index < listSettAmount.length / 2) {
        return `￥${scope.row.t0SettAmount.toFixed(2)}`
      }
      return `￥${scope.row.t1SettAmount.toFixed(2)}`
    }
  },
  components: { addMoney, adjust },
  data() {
    return {
      formLabelWidth: '120px',
      searchData: [],
      moneyRules: {
        orderPrice: [
          { required: true, message: '请输入代付金额', trigger: 'blur' }
        ],
        bankCardRealName: [
          { required: true, message: '请输入收款人姓名', trigger: 'blur' }
        ],
        bankAccountNo: [
          { required: true, message: '请输入收款卡号', trigger: 'blur' }
        ],
        authcodePay: [
          { required: true, message: '请输入授权码', trigger: 'blur' }
        ]
      },
      moneyData: {
        bankAccountType: 'PRIVATE_DEBIT_ACCOUNT',
        bankCode: 'ICBC',
        bankCardRealName: ''
      },
      ctrl: {
        dialogVisible: false,
        load: false,
        adjust: false
      },
      option: {
        bankwayList: [],
        payWayList: [],
        bankwayObj: null
      },
      settList: [],
      listSettAmount: [],
      params: {},
      searchParams: {},
      account: {}
    }
  },
  methods: {
    makeMoney(target, index) {
      this.moneyData.userNo = this.$route.query.no
      this.moneyData.inType = 'T1'
      if (index < this.listSettAmount.length / 2) {
        this.moneyData.inType = 'T0'
      }
      this.moneyData.proxyType = target.payWayCode
      this.moneyData.bankWayCode = target.bankWayCode
      this.moneyData.productType = target.payWayCode
      this.moneyData.bankWay = this.option.bankwayObj[target.bankWayCode]
      this.$nextTick(() => {
        this.ctrl.dialogVisible = true
        // this.ctrl.moneyBox = true
      })
    },
    // 打款窗关闭回调
    closeCllback() {
      this.ctrl.dialogVisible = false
    },
    handleCheck() {
      this.params.pageNum = 1
      this.getList()
    },
    getList() {
      console.log(this.params)
      this.ctrl.load = true
      accountApis.getDetailList(this.params).then(res => {
        this.ctrl.load = false
        this.account = res.data.account
        this.settList = res.data.listSettAmount
        this.listSettAmount = res.data.listSettAmount.concat(res.data.listSettAmount)
      }).catch(() => {
        this.ctrl.load = false
      })
    },
    getAllBankway() {
      apis.bankWayAll().then(res => {
        this.option.bankwayList = res.data
        this.option.bankwayObj = res.data.reduce((target, item) => {
              target[item.name] = item.desc
              return target
            }, {})
      })
    },
    handleReset() {
      this.params = {
        userNo: this.$route.query.no
      }
      this.getList()
    },
    // 调账回调
    adjustCollback(res) {
      this.ctrl.adjust = false
      if (res) {
        this.getList()
      }
    }
  },
  created() {
    this.$store.dispatch('GetOpts', { name: 'PayWayEnum' }).then(res => {
      // console.log(res)
      this.option.payWayList = res
    })
  },
  mounted() {
    this.params.userNo = this.$route.query.no
    this.searchParams.merchantNo = this.$route.query.no
    this.getList()
    this.getAllBankway()
  }
}
</script>

<style lang="scss">
  .account_detail {
    .inline_text {
      display: inline-block;
      min-width: 300px;
    }
  }
</style>
