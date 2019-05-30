<template>
  <div class="app-container" v-if="edit">
    <el-form ref="form" :model="form" :rules="rules" label-width="150px">
      <el-alert class="title" :closable="false" title="设置支付方式" type="info" />
      <el-row>
        <el-col :span="12">
          <el-form-item label="支付方式" prop="payWay">
            <el-select v-model="form.payWay" @change="changePayWay" placeholder="请选择">
              <el-option
                v-for="item in opts.payWay"
                :key="item.name"
                :label="item.desc"
                :value="item.name+'=='+item.desc">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="支付类型" prop="payType">
            <el-select v-model="form.payType" @change="changePayType"  placeholder="请选择">
              <el-option
                v-for="item in opts.payType"
                :key="item.name"
                :label="item.desc"
                :value="item.name+'=='+item.desc">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="支付费率" prop="payRate">
            <el-input v-model="form.payRate">
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
          <el-form-item label="计算方式" required>
            <el-select v-model="form.calculateType" filterable>
              <el-option
                v-for="item in opts.calculateType"
                :key="item.name"
                :label="item.desc"
                :value="item.name">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="贷记卡费率" prop="payCreditRate">
            <el-input v-model="form.payCreditRate" type="number"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="最低手续费" prop="lowestRate">
            <el-input v-model="form.lowestRate" type="number"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="代付费率" prop="proxyRate">
            <el-input v-model="form.proxyRate" type="number">
              <el-select v-model="form.proxyRateType" class="auto-width" style="width: 120px;" slot="prepend" placeholder="请选择">
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
          <el-form-item label="代付最低手续费" prop="proxyLowestRate">
            <el-input v-model="form.proxyLowestRate" type="number"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-alert class="title" :closable="false" title="渠道分配" type="info" />
      <el-row>
        <el-col :span="12">
          <el-form-item label="支付渠道分流名称">
            <el-input v-model="form.channelFlowName" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="支付渠道分流编号" required>
            <el-input v-model="form.channelFlowCode" readonly></el-input>
            <el-button @click="findFlow" v-if="!form.id" title="查找" icon="el-icon-search" circle size="mini"></el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="代付渠道名称">
            <el-input v-model="form.proxyWayName" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="代付渠道编号" required>
            <el-input v-model="form.proxyWayCode" readonly></el-input>
            <el-button @click="findBankWay" v-if="!form.id" title="查找" icon="el-icon-search" circle size="mini"></el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <el-alert class="title" :closable="false" type="info" title="交易限制"><b class="font_12 c-danger">(为空不限制)</b></el-alert>
      <el-row>
        <el-col :span="12">
          <el-form-item label="单笔金额">
            <el-input-number v-model="form.payMin" :min="0" :max="form.payMax" :controls="false" class="auto-width" style="width: 100px;"></el-input-number>——
            <el-input-number v-model="form.payMax" :min="form.payMin" :controls="false" class="auto-width" style="width: 100px;"></el-input-number> （元）
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="交易时间">
            <el-time-picker
              is-range
              v-model="form.payTime"
              value-format="HH:mm:ss"
              range-separator="至"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
              placeholder="选择时间范围">
            </el-time-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-alert class="title" :closable="false" title="代付限制" type="info" ><b class="font_12 c-danger">(为空不限制)</b></el-alert>
      <el-row>
        <el-col :span="12">
          <el-form-item label="单笔金额">
            <el-input-number :controls="false" v-model="form.proxyPayMin" :min="0" class="auto-width" style="width: 100px;"></el-input-number>——
            <el-input-number :controls="false" v-model="form.proxyPayMax" :min="form.proxyPayMin" class="auto-width" style="width: 100px;"></el-input-number> （元）
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="代付时间">
            <el-time-picker
              is-range
              value-format="HH:mm:ss"
              v-model="form.proxyTime"
              range-separator="至"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
              placeholder="选择时间范围">
            </el-time-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button v-if="form.id" type="primary" @click="submitForm">确定提交</el-button>
        <el-button v-else type="primary" @click="submitForm">立即创建</el-button>
        <el-button @click="closeView">取消</el-button>
      </el-form-item>
    </el-form>
    <find-channel :visible="ctrl.dialogVisible" :title="ctrl.dialogTitle" @close-callback="closeCallback"></find-channel>
    <find-flow :visible="ctrl.flowVisible" :formData="form" @close-callback="closeFlowFind"></find-flow>
  </div>
  <div class="" v-else>
  </div>
</template>
<script>
  import * as apis from '@/api/pay'
  import * as role from '@/api/role'
  import FindChannel from './findChannel'
  import FindFlow from './findFlow'
  export default {
    name: 'EditPayWay',
    components: { FindChannel, FindFlow },
    data() {
      return {
        formdata: {
          payTypeCode: undefined,
          needOrderPolling: 'YES',
          status: 'ACTIVE',
          proxyRateType: 'PERCENT',
          rateType: 'PERCENT',
          calculateType: 'ROUND_UP_DOWN',
          communicationType: 'HTTP_CLIENT'
        },
        form: {},
        edit: true,
        ctrl: {
          flowVisible: false,
          dialogTitle: '查询代付渠道',
          dialogVisible: false
        },
        opts: {
          payWay: [],
          payType: [],
          calculateType: [],
          rateType: []
        },
        rules: {
          payWay: [
            { required: true, message: '请选择支付方式', trigger: 'blur' }
          ],
          payType: [
            { required: true, message: '请选择支付类型', trigger: 'blur' }
          ],
          payRate: [
            { required: true, message: '请输入支付费率', trigger: 'blur' }
          ],
          payCreditRate: [
            { required: true, message: '请输入贷记卡费率', trigger: 'blur' }
          ],
          lowestRate: [
            { required: true, message: '请输入最低手续费', trigger: 'blur' }
          ],
          proxyRate: [
            { required: true, message: '请输入代付费率', trigger: 'blur' }
          ],
          proxyLowestRate: [
            { required: true, message: '请输入代付最低手续费', trigger: 'blur' }
          ]
        }
      }
    },
    mounted() {
      const view = this.$route
      this.form = this.formdata
      this.$store.dispatch('GetOpts', { name: 'PayWayEnum' }).then(res => {
        console.log(res)
        this.opts.payWay = res;
      })
      this.$store.dispatch('GetOpts', { name: 'CalculateTypeEnum' }).then(res => {
        this.opts.calculateType = res;
      })
      this.$store.dispatch('GetOpts', { name: 'RateTypeEnum' }).then(res => {
        this.opts.rateType = res;
      })
      if (view.query.id) {
        this.edit = false;
        this.getPayWayInfo(view.query.id)
      }
    },
    methods: {
      changePayType(e) {
        console.log(e)
      },
      changePayWay(e) {
        const arr = e.split('==')
        role.linkageEnumList({ enumName: 'PayTypeEnum', inParam: arr[0], methodName: 'getWayList' }).then(res => {
          if (res.code === 200) {
            this.form.payTypeCode = undefined
            this.opts.payType = res.data
          } else {
            this.$message({
              type: 'error',
              message: res.msg
            });
          }
        })
      },
      submitForm() {
        if (this.form.channelFlowCode === undefined) {
          this.$message({
            type: 'error',
            message: '银行渠道分流不得为空'
          });
          return false
        }
        if (this.form.proxyWayCode === undefined) {
          this.$message({
            type: 'error',
            message: '代付渠道不得为空'
          });
          return false
        }
        console.log(this.form)
        this.$refs.form.validate((valid) => {
          if (valid) {
            this.onSubmit()
          } else {
            console.log('error submit!!');
            return false;
          }
        })
      },
      onSubmit() {
        console.log(this.form)
        const arr1 = this.form.payWay.split('==');
        this.form.payWayCode = arr1[0]
        this.form.payWayName = arr1[1]
        const arr2 = this.form.payType.split('==');
        this.form.payTypeCode = arr2[0]
        this.form.payTypeName = arr2[1]
        if (this.form.payTime) {
          this.form.payBeginTimeStr = this.form.payTime[0]
          this.form.payEndTimeStr = this.form.payTime[1]
        }
        if (this.form.proxyTime) {
          this.form.proxyPayBeginTimeStr = this.form.proxyTime[0]
          this.form.proxyPayEndTimeStr = this.form.proxyTime[1]
        }
        this.loading.show()
        if (this.form.id === undefined) {
          // 新增
          this.form.payProductCode = this.$route.query.pc;
          apis.addPayWay(this.form).then(res => {
            this.form = this.formdata
            this.loading.hide()
            this.$confirm(`添加支付方式成功`, {
              confirmButtonText: '继续添加',
              cancelButtonText: '关闭',
              type: 'warning'
            }).then(() => {
              this.form = this.formdata
            }).catch(() => {
              this.closeView()
            })
          }).catch(() => {
            this.loading.hide()
          })
        } else {
          // 编辑
          apis.updatePayWay(this.form).then(res => {
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
      getPayWayInfo(id) {
        this.loading.show()
        apis.getPayWay({ id: id }).then(res => {
          this.loading.hide()
          if (res.code === 200) {
            this.edit = true;
            this.form = res.data
            this.form.payWay = res.data.payWayCode + '==' + res.data.payWayName
            this.form.payType = res.data.payTypeCode + '==' + res.data.payTypeName
            if (res.data.payBeginTime) {
              this.form.payTime = [res.data.payBeginTime, res.data.payEndTime]
              this.form.payBeginTime = undefined;
              this.form.payEndTime = undefined;
            }
            if (res.data.proxyPayBeginTime) {
              this.form.proxyTime = [res.data.proxyPayBeginTime, res.data.proxyPayEndTime]
              this.form.proxyPayBeginTime = undefined;
              this.form.proxyPayEndTime = undefined;
            }
            this.changePayWay(this.form.payWay)
          } else {
            this.closeView()
            this.$alert(res.msg || '系统错误')
          }
        }).catch(() => {
          this.loading.hide()
          this.closeView()
        })
      },
      findBankWay() {
        this.ctrl.dialogVisible = true;
      },
      findFlow() {
        this.ctrl.flowVisible = true;
      },
      // 关闭渠道分流查询回调
      closeFlowFind(res) {
        this.ctrl.flowVisible = false;
        console.log(res)
        if (res) {
          this.form.channelFlowCode = res.channelFlowCode
          this.form.channelFlowName = res.channelFlowName
        }
      },
      // 关闭代付渠道回调
      closeCallback(res) {
        this.ctrl.dialogVisible = false;
        console.log(res)
        if (res) {
          this.form.proxyWayCode = res.bankChannelCode
          this.form.proxyWayName = res.bankChannelName
        }
      },
      closeView() {
        const view = {
          key: this.$route.fullPath
        }
        this.$store.dispatch('delView', view).then(({ visitedViews }) => {
          console.log(visitedViews)
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
