<template>
  <div class="" v-loading="ctrl.loading">
    <el-dialog width="80%" @close="resetForm()" :title="title" :visible.sync="vb">

      <el-form ref="form" :model="form" :rules="moneyRules" label-width="160px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="银行通道：" prop="bankWayName">
              <el-input v-model="form.bankWayName">
                <el-button slot="append" icon="el-icon-search" @click="ctrl.dialogVisible = true"></el-button>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="银行渠道：" prop="bankChannelCode">
              <el-select placeholder="请选择" v-model="form.bankChannelCode">
                <el-option :label="item.desc" :value="item.bankChannelCode" v-for="item in opts.bankCodeList" :key="item.bankChannelCode"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item  label="银行渠道类型：" prop="bankChannelType">
              <el-select placeholder="请选择" v-model="form.bankChannelType">
                <el-option :label="item.desc" :value="item.name" v-for="item in opts.enumList" :key="item.name"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="精度保留方式：" prop="calculateType">
              <el-select placeholder="请选择" v-model="form.calculateType">
                <el-option :label="item.desc" :value="item.name" v-for="item in opts.calculateType" :key="item.name"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="渠道费率：" prop="rate">
              <el-input v-model="form.rate">
                <el-select v-model="form.rateType" class="auto-width" style="width: 120px;" slot="prepend" placeholder="请选择">
                  <el-option
                    v-for="item in opts.rateType"
                    :key="item.name"
                    :label="item.desc"
                    :value="item.name">
                  </el-option>
                </el-select>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="渠道费率(贷记卡)：" prop="bankCreditRate">
              <el-input v-model="form.bankCreditRate"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="最低手续费：" prop="lowestRate">
              <el-input v-model="form.lowestRate"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态：" prop="status">
              <el-radio v-model="form.status" border size="small" label="YES">激活</el-radio>
              <el-radio v-model="form.status" border size="small" label="NO">冻结</el-radio>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="18">
            <el-form-item label="备注：">
              <el-input v-model="form.remark" type="textarea"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm()">确 定</el-button>
        <el-button @click="resetForm()">取 消</el-button>
      </div>
    </el-dialog>
    <find :visible="ctrl.dialogVisible" :title="ctrl.dialogTitle" @close-callback="closeCallback"></find>
  </div>
</template>

<script>
  import * as apis from '@/api/bank'
  import Find from './findBankWay'
  export default {
    name: "AddMoney",
    components: { Find },
    data() {
      return {
        moneyRules: {
          calculateType: [
            { required: true, message: '请输入代付金额', trigger: 'blur' }
          ],
          bankChannelType: [
            { required: true, message: '请输入收款人姓名', trigger: 'blur' }
          ],
          bankAccountNo: [
            { required: true, message: '请输入收款卡号', trigger: 'blur' }
          ],
          bankCreditRate: [
            { required: true, message: '请输入授权码', trigger: 'blur' }
          ]
        },
        reqs: ['rate'], //必填选项
        ctrl: {
          loading: false,
          dialogVisible: false,
          showPayee: false,
          moneyBox: true
        },
        opts: {
          rateType: [],
          bankCodeList: [],
          calculateType: [],
          enumList: []
        },
        form: {
          status: 'YES',
          rateType: 'PERCENT'
        },
        vb: false,
        authcodePay: ''
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
      resetForm() {
        this.form = {}
        this.$emit('close-callback')
      },
      closeCallback(data) {
        console.log(data)
        this.ctrl.dialogVisible = false
        if (data) {
          this.form.bankWayName = data.desc
          this.getBankCode()
        }
      },
      getBankCode() {
        this.ctrl.loading = true
        apis.bankCodeList(this.form).then(res => {
          console.log(res)
          this.opts.bankCodeList = res.data.recordList;
          this.ctrl.loading = false
        }).catch(() => {
          this.ctrl.loading = false
        })
      }
    },
    mounted() {
      this.$store.dispatch('GetOpts', { name: 'RateTypeEnum' }).then(res => {
        this.opts.rateType = res
      })
      this.$store.dispatch('GetOpts', { name: 'CalculateTypeEnum' }).then(res => {
        this.opts.calculateType = res
      })
      // 获取银行渠道类型枚举
      this.$store.dispatch('GetOpts', { name: 'BankChannelTypeEnum' }).then(res => {
        // this.opts.status = res
        this.opts.enumList = res
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
