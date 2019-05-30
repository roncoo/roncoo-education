<template>
  <div class="" v-loading="ctrl.loading">
    <el-dialog width="80%" @close="resetForm()" :title="title" :visible.sync="vb">

      <el-form class="edit-form" ref="form" :model="form" :rules="rules" label-width="160px">
        <el-alert class="alert" title="分流信息" :closable="false"/>
        <el-row>
          <el-col :span="12">
            <el-form-item label="渠道分流名称：" prop="bankWayName">
              <el-input v-model="form.bankWayName">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="渠道分流编码：" prop="bankChannelCode">
              <el-input v-model="form.bankWayName">
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item  label="支付(方式-类型)：" prop="bankChannelType">
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
        <el-alert title="主从渠道配置" class="alert" :closable="false"/>
        <el-row>
          <el-col :span="12">
            <el-form-item label="主支付渠道名称：" prop="rate">
              <el-input v-model="form.masterBankChannelName" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="主支付渠道编码：" prop="bankCreditRate">
              <el-input v-model="form.masterBankChannelCode" :readonly="true">
                <el-button slot="append" icon="el-icon-search" @click="findChannel('master')"></el-button>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="从支付渠道名称：" prop="lowestRate">
              <el-input v-model="form.slaveBankChannelName" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="从支付渠道编码：" prop="status">
              <el-input v-model="form.slaveBankChannelCode" :readonly="true">
                <el-button slot="append" icon="el-icon-search" @click="findChannel('slave')"></el-button>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-alert title="备注信息" class="alert" :closable="false"/>
        <el-row>
          <el-col :span="18">
            <el-form-item label="备注：">
              <el-input v-model="form.remark" type="textarea"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleSubmit()">确 定</el-button>
        <el-button @click="resetForm()">取 消</el-button>
      </div>
    </el-dialog>
    <find :visible="ctrl.dialogVisible" :title="ctrl.dialogTitle" @close-callback="closeCallback"></find>
  </div>
</template>

<script>
  import * as apis from '@/api/bank'
  import Find from './findChannel'
  export default {
    name: "AddMoney",
    components: { Find },
    data() {
      return {
        rules: {
        },
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
        this.ctrl.dialogVisible = false
        if (data) {
          if (this.ctrl.findType === 'master') {
            this.form.masterBankChannelCode = data.bankChannelCode
            this.form.masterBankChannelName = data.bankChannelName
          } else {
            this.form.slaveBankChannelCode = data.bankChannelCode
            this.form.slaveBankChannelName = data.bankChannelName
          }
          this.form.bankWayName = data.desc
        }
      },
      findChannel(ty) {
        this.ctrl.dialogVisible = true;
        this.ctrl.findType = ty;
      },
      submitForm() {
        console.log(this.form)
        apis.addChannelFlow(this.form).then(res => {
          console.log(res)
        })
      },
      handleSubmit() {
        this.$refs.form.validate((valid) => {
          if (valid) {
            this.submitForm()
          } else {
            console.log('error submit!!');
            return false;
          }
        });
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
    }
  }
</script>

<style scoped>
  .edit-form{
    margin: -20px 0;
  }
  .alert{
    margin-bottom: 20px;
  }
</style>
