<template>
  <div class="">
    <el-dialog width="80%" top="3vh" @close="resetForm()" :title="title" :visible.sync="vb">

      <el-form ref="form" :model="moneyData" :rules="moneyRules" label-width="150px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="出款银行：">
              <span class="inline_text">
                {{accountData.bankWay}}
              </span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="产品类型：">
              <span class="inline_text">{{accountData.productType}}</span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="交易类型：">
              <span class="inline_text">{{accountData.inType}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="银行卡类型：" prop="bankAccountType">
              <el-select placeholder="请选择" v-model="moneyData.bankAccountType">
                <el-option :label="item.desc" :value="item.name" v-for="item in option.bankAccountList" :key="item.name"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="收款人：" prop="bankCardRealName">
              <el-input v-model="moneyData.bankCardRealName">
                <el-button slot="append" icon="el-icon-search" @click="ctrl.showPayee = true"></el-button>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="代付金额：" prop="orderPrice">
              <el-input v-model="moneyData.orderPrice"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item  label="手机号：" prop="phoneNo" :rules="{validator: checkPhone, required: true, trigger: 'blur'}">
              <el-input maxlength="11" v-model.number="moneyData.phoneNo"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="支行行号：" prop="bankBranchNo">
              <el-input v-model="moneyData.bankBranchNo"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="清算行行号：" prop="bankClearNo">
              <el-input v-model="moneyData.bankClearNo"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="支行名称：" prop="bankBranchName">
              <el-input v-model="moneyData.bankBranchName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="收款银行：" prop="bankCode">
              <el-select placeholder="请选择" v-model="moneyData.bankCode">
                <el-option :label="item.desc" :value="item.name" v-for="item in option.bankCodeList" :key="item.name"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="省份" prop="province">
              <el-input v-model="moneyData.province"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="收款卡号" prop="bankAccountNo">
              <el-input v-model="moneyData.bankAccountNo"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="城市" prop="city">
              <el-input v-model="moneyData.city"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="身份证号" prop="certNo">
              <el-input v-model="moneyData.certNo"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12"><el-form-item label="授权码" prop="authcodePay">
            <el-input v-model="moneyData.authcodePay"></el-input>
          </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="18">
            <el-form-item label="备注">
              <el-input v-model="moneyData.remark" type="textarea"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-form :inline="true" size="mini" :model="moneyData" :rules="moneyRules" ref="moneyData">

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm()">确 定</el-button>
        <el-button @click="resetForm()">取 消</el-button>
      </div>
    </el-dialog>
    <payee :visible="ctrl.showPayee" :data="findData" @close-cllback="findCllback"></payee>
  </div>
</template>

<script>
  import * as apis from '@/api/account'
  import payee from './payee'
  export default {
    name: "AddMoney",
    components: { payee },
    data() {
      return {
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
        reqs: ['bankAccountType', 'bankBranchNo', 'bankClearNo', 'bankBranchName', 'bankCode', 'province', 'certNo', 'city'], //必填选项
        ctrl: {
          showPayee: false,
          moneyBox: true
        },
        findData: {},
        vb: false,
        authcodePay: '',
        moneyData: {},
        account: {},
        option: {}
      }
    },
    props: {
      // route object
      accountData: {
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
        // console.log(newValue)
        this.vb = newValue
      }
    },
    methods: {
      checkPhone(rule, value, callback) {
        console.log(value);
        if (!(/^1[3|4|5|8|7][0-9]\d{4,8}$/.test(value))) {
          return callback(new Error('请输入正确格式的手机'));
        }
        callback()
      },
      submitForm() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            this.moneyData.bankWayName = this.accountData.bankWay
            this.moneyData.productType = this.accountData.proxyType
            this.moneyData.proxyType = this.accountData.inType
            this.moneyData.userNo = this.accountData.userNo
            this.moneyData.bankWayCode = this.accountData.bankWayCode
            this.loading.show()
            apis.sendProxyPay(this.moneyData).then(res => {
              this.loading.hide()
              if (res.code === 200) {
                this.resetForm()
              }
              this.$alert(res.msg)
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm() {
        this.moneyData = {}
        this.$emit('close-cllback')
      },
      findCllback(data) {
        console.log(data)
        this.ctrl.showPayee = false;
        if (data) {
          this.moneyData = data
          this.moneyData.authcodePay = ''
          this.moneyData.orderPrice = ''
          this.moneyData.bankCardRealName = data.receiverName
          this.moneyData.bankAccountNo = data.receiverAccountNo
        }
      }
    },
    mounted() {
      console.log(this.accountData)
      this.vb = this.visible
      this.$store.dispatch('GetOpts', { name: 'BankAccountTypeEnum' }).then(res => {
        this.option.bankAccountList = res
      })
      this.$store.dispatch('GetOpts', { name: 'BankCodeEnum' }).then(res => {
        this.option.bankCodeList = res
      })
      // 必填校验规则
      this.reqs.map(item => {
        this.moneyRules[item] = [
          { required: true, message: '该项不可为空', trigger: 'blur' }
        ]
      })
    }
  }
</script>

<style scoped>

</style>
