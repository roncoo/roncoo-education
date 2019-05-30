<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="150px">
      <el-alert class="title" :closable="false" title="渠道信息" type="info" />
      <el-row>
        <el-col :span="12">
          <el-form-item label="银行通道" :required="true">
            <el-input disabled="" v-model="form.bankWayName"></el-input>
            <el-button @click="findBankWay" title="查找" icon="el-icon-search" circle size="mini"></el-button>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="银行渠道" :required="true">
            <el-input disabled="" v-model="form.bankChannelName"></el-input>
            <el-button @click="findBankCode" title="查找" icon="el-icon-search" circle size="mini"></el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="费率类型" prop="rateType">
            <el-select v-model="form.rateType" placeholder="费率类型">
              <el-option
                v-for="item in opts.rateType"
                :key="item.name"
                :label="item.desc"
                :value="item.name">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="费率" prop="rate">
            <el-input v-model="form.rate" type="number"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="银行渠道类型" prop="bankChannelType">
            <el-select v-model="form.bankChannelType" placeholder="选择银行渠道类型">
              <el-option
                v-for="item in opts.enumList"
                :key="item.desc"
                :label="item.desc"
                :value="item.name">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态" prop="status">
            <el-radio v-model="form.status" label="YES">激活</el-radio>
            <el-radio v-model="form.status" label="NO">冻结</el-radio>
          </el-form-item>
        </el-col>
      </el-row>
      <el-alert class="title" :closable="false" title="交易信息配置" type="info" />
      <el-row>
        <el-col :span="12">
          <el-form-item label="计算方式" prop="calculateType">
            <el-select v-model="form.calculateType" placeholder="选择计算方式">
              <el-option
                v-for="item in opts.calculateType"
                :key="item.name"
                :label="item.desc"
                :value="item.name">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="最低手续费" prop="limitFee">
            <el-input v-model="form.limitFee" type="number"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="结算周期" prop="riskDay">
            <el-input v-model="form.riskDay" type="number"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="交易时间" prop="tradeTime">
            <el-time-picker
              is-range
              v-model="tradeTime"
              value-format="HH:mm:ss"
              placeholder="选择时间">
            </el-time-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="最大交易金额">
            <el-input v-model="form.maxTradeAmount" type="number"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="最小交易金额">
            <el-input v-model="form.minTradeAmount" type="number"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button v-if="form.id" type="primary" @click="onSubmit('form')">确定提交</el-button>
        <el-button v-else type="primary" @click="onSubmit('form')">立即创建</el-button>
        <el-button @click="closeView">取消</el-button>
      </el-form-item>
    </el-form>

    <find :visible="ctrl.dialogVisible" :title="ctrl.dialogTitle" @close-callback="closeCllback"></find>
    <find-bank-code :visible="ctrl.bankCodeVisible" :formData="dialogData" :title="ctrl.bankCodeTitle" @close-cllback="closeCllback"></find-bank-code>
  </div>
</template>
<script>
  import * as apis from '@/api/bank'
  import Find from './findBankWay'
  import FindBankCode from './findBankCode'
  export default {
    components: { Find, FindBankCode },
    data() {
      return {
        ctrl: {
          dialogVisible: false,
          dialogTitle: '选择银行通道',
          bankCodeVisible: false,
          bankCodeTitle: ''
        },

        opts: {
          rateType: [],
          calculateType: [],
          enumList: []
        },

        tradeTime: ["00:00:00", "23:59:59"],
        dialogData: {},
        form: {},
        rules: {
          bankWayCode: [
            { required: true, message: '请输入银行通道编码', trigger: 'blur' }
          ],
          checkFileType: [
            { required: true, message: '请选择对账文件类型', trigger: 'blur' }
          ],
          isPlatSett: [
            { required: true, message: '请选择是否平台结算', trigger: 'change' }
          ],
          rateType: [
            { required: true, message: '请选择费率类型', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '请选择银行通道状态', trigger: 'blur' }
          ],
          rate: [
            { required: true, message: '请输入费率', trigger: 'blur' }
          ],
          bankChannelType: [
            { required: true, message: '请选择银行渠道类型', trigger: 'blur' }
          ],
          calculateType: [
            { required: true, message: '请选择计算方式', trigger: 'blur' }
          ],
          limitFee: [
            { required: true, message: '请输入最低手续费', trigger: 'blur' }
          ],
          riskDay: [
            { required: true, message: '结算周期', trigger: 'blur' }
          ]
        }
      }
    },
    mounted() {
      const view = this.$route
      // console.log(view)
      this.$store.dispatch('updateVisitedView', { key: view.path, title: '添加银行渠道' }).then(() => {
      })
      this.$store.dispatch('GetOpts', { name: 'RateTypeEnum' }).then(res => {
        this.opts.rateType = res
      })
      this.$store.dispatch('GetOpts', { name: 'CalculateTypeEnum' }).then(res => {
        console.log(res)
        this.opts.calculateType = res
      })
      // 获取银行渠道类型枚举
      this.$store.dispatch('GetOpts', { name: 'BankChannelTypeEnum' }).then(res => {
        // this.opts.status = res
        this.opts.enumList = res
      })
      if (view.query.id) {
        this.getBankChannel(view.query.id)
      }
    },
    methods: {
      onSubmit(formName) {
        if (!this.form.bankWayName) {
          this.$message({
            type: 'error',
            message: '请选择银行通道'
          });
          return false
        }
        if (!this.form.bankChannelCode) {
          this.$message({
            type: 'error',
            message: '请选择银行渠道'
          });
          return false
        }
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.submitForm()
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      submitForm() {
        console.log(this.tradeTime)
        this.loading.show()
        this.form.minTradeTimeStr = null
        this.form.maxTradeTimeStr = null

        if (this.form.id === undefined) {
          // 新增
          if (this.tradeTime !== null) {
            if (this.tradeTime[0] !== '00:00:00' || this.tradeTime[1] !== '23:59:59') {
              this.form.minTradeTimeStr = this.tradeTime[0]
              this.form.maxTradeTimeStr = this.tradeTime[1]
            }
          }
          apis.addBankChannel(this.form).then(res => {
            this.loading.hide()
            this.$confirm(`添加银行渠道成功`, {
              confirmButtonText: '继续添加',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.form = {}
            }).catch(() => {
              this.closeView()
            })
          }).catch(() => {
            this.loading.hide()
          })
        } else {
          // 编辑
          if (this.tradeTime !== null) {
            this.form.minTradeTimeStr = this.tradeTime[0]
            this.form.maxTradeTimeStr = this.tradeTime[1]
          }
          apis.updateBankChannel(this.form).then(res => {
            this.loading.hide()
            this.$message({
              type: 'success',
              message: '修改成功'
            });
            this.closeView()
          }).catch(() => {
            this.loading.hide()
          })
        }
      },
      // 获取银行渠道详情
      async getBankChannel(id) {
        this.loading.show()
        const res = await apis.getBankChannel(id)
        console.log(res)
        this.loading.hide()
        if (res.code === 0) {
          this.tradeTime = [res.data.minTradeTimeStr || '00:00:00', res.data.maxTradeTimeStr || '23:59:59']
          this.form = res.data
        } else {
          this.$alert(res.msg || '系统错误')
        }
      },
      findBankCode() {
        if (this.form.bankWayCode === undefined) {
          this.$message({
            type: 'error',
            message: '请先选择银行通道'
          });
          return false
        }
        this.dialogData = {
          bankWayCode: this.form.bankWayCode,
          bankWayName: this.form.bankWayName
        }
        this.ctrl.bankCodeVisible = true;
        this.ctrl.bankCodeTitle = this.form.bankWayName + '-> 选择银行渠道'
      },

      findBankWay() {
        this.ctrl.dialogVisible = true;
      },
      // 关闭编辑弹窗回调
      closeCllback(res) {
        this.ctrl.dialogVisible = false;
        this.ctrl.bankCodeVisible = false;
        console.log(res)
        console.log("res=======")
        if (res) {
          this.form.bankWayCode = res.bankWayCode
          this.form.bankWayName = res.bankWayName
          this.form.bankChannelCode = res.bankChannelCode
          this.form.bankChannelName = res.bankChannelName
        }
      },
      closeView() {
        const view = {
          key: this.$route.fullPath
        }
        this.$store.dispatch('delView', view).then(({ visitedViews }) => {
          // console.log(visitedViews)
          const latestView = visitedViews.slice(-1)[0]
          if (latestView) {
            this.$router.push(latestView.route.fullPath)
          } else {
            this.$router.push('/')
          }
        })
      }
    }
  }
</script>
<style scoped>
  .title{
    margin-bottom: 20px;
  }
</style>
