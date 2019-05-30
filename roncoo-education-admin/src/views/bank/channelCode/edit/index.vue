<template>
  <div class="app-container" v-if="edit">
    <el-form ref="form" :model="form" :rules="rules" label-width="150px">
      <el-alert class="title" :closable="false" title="通道信息" type="info" />
      <el-row>
        <el-col :span="12">
          <el-form-item label="银行通道名称" :required="true">
            <el-input v-model="form.bankWayName" :disabled="true"></el-input>
            <el-button @click="findBankWay" v-if="!form.id" title="查找" icon="el-icon-search" circle size="mini"></el-button>

          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="银行通道编码" :required="true">
            <el-input v-model="form.bankWayCode" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="银行渠道名称" prop="desc">
            <el-input v-model="form.desc" :disabled="!!form.id"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="银行渠道编码" prop="bankChannelCode">
            <el-input v-model="form.bankChannelCode" :disabled="!!form.id"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="银行实现类" prop="bankObjectCode">
            <el-select v-model="form.bankObjectCode" filterable>
              <el-option
                v-for="item in opts.enumList"
                :key="item.name"
                :label="item.desc"
                :value="item.name">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="日交易限额" prop="status">
            <el-input v-model="form.dayMaxAmount"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="银行结算类型" prop="bankObjectCode">
            <el-radio v-model="form.bankSettType" border size="small" :label="item.name" v-for="item in opts.settType" :key="item.name">{{item.desc}}</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="接口通讯类型" prop="status">
            <el-radio v-model="form.communicationType" border size="small" :label="item.name" v-for="item in opts.communicationType" :key="item.name">{{item.desc}}</el-radio>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="是否需要订单轮询" prop="bankObjectCode">
            <el-radio v-model="form.needOrderPolling" border size="small" label="YES">是</el-radio>
            <el-radio v-model="form.needOrderPolling" border size="small" label="NO">否</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态" prop="status">
            <el-radio v-model="form.status" border size="small" label="ACTIVE">激活</el-radio>
            <el-radio v-model="form.status" border size="small" label="UNACTIVE">冻结</el-radio>
          </el-form-item>
        </el-col>
      </el-row>
      <el-alert class="title" :closable="false" title="通道配置" type="info" />
      <el-row>
        <el-col :span="12">
          <el-form-item label="银行商户编号" prop="bankMerchantNo">
            <el-input v-model="form.bankMerchantNo"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="银行商户密钥" prop="bankMerchantSecret">
            <el-input v-model="form.bankMerchantSecret"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="交易请求地址" prop="tradeRequestUrl">
            <el-input v-model="form.tradeRequestUrl"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="交易查询地址" prop="tradeQueryUrl">
            <el-input v-model="form.tradeQueryUrl"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="对账文件下载地址" prop="checkFileDownUrl">
            <el-input v-model="form.checkFileDownUrl"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="备用字段1">
            <el-input v-model="form.field1"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="备用字段2">
            <el-input v-model="form.field2"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="备用字段3">
            <el-input v-model="form.field3"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-alert class="title" :closable="false" title="通道备注" type="info" />
      <el-form-item label="备注信息">
        <el-input type="textarea" v-model="form.remark"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button v-if="form.id" type="primary" @click="submitForm('form')">确定提交</el-button>
        <el-button v-else type="primary" @click="submitForm('form')">立即创建</el-button>
        <el-button @click="closeView">取消</el-button>
      </el-form-item>
    </el-form>
    <find :visible="ctrl.dialogVisible" :formData="form" :title="ctrl.dialogTitle" @close-cllback="closeCllback"></find>
  </div>
  <div class="" v-else>
  </div>
</template>
<script>
  import * as apis from '@/api/bank'
  import Find from './find'
  export default {
    name: 'EditBankCode',
    components: { Find },
    data() {
      return {
        form: {
          bankSettType: 'T0',
          needOrderPolling: 'YES',
          status: 'ACTIVE',
          communicationType: 'HTTP_CLIENT'
        },
        edit: true,
        ctrl: {
          dialogTitle: '查询银行通道',
          dialogVisible: false
        },
        opts: {
          enumList: [],
          settType: [],
          communicationType: []
        },
        rules: {
          desc: [
            { required: true, message: '请输入银行渠道名称', trigger: 'blur' }
          ],
          bankChannelCode: [
            { required: true, message: '请输入银行渠道编码', trigger: 'blur' }
          ],
          bankObjectCode: [
            { required: true, message: '请选择银行实现类', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '请选择状态', trigger: 'blur' }
          ],
          bankMerchantNo: [
            { required: true, message: '请输入银行商户编号', trigger: 'blur' }
          ],
          bankMerchantSecret: [
            { required: true, message: '请输入银行商户密钥', trigger: 'blur' }
          ],
          tradeRequestUrl: [
            { required: true, message: '请输入交易请求地址', trigger: 'blur' }
          ],
          tradeQueryUrl: [
            { required: true, message: '请输入交易查询地址', trigger: 'blur' }
          ],
          checkFileDownUrl: [
            { required: true, message: '请输入对账文件下载地址', trigger: 'blur' }
          ]
        }
      }
    },
    mounted() {
      const view = this.$route
      this.$store.dispatch('updateVisitedView', { key: view.path, title: '添加银行渠道编码' }).then(() => {
      })

      this.$store.dispatch('GetOpts', { name: 'BankObjectCodeEnum' }).then(res => {
        this.opts.enumList = res;
      })
      this.$store.dispatch('GetOpts', { name: 'BankSettTypeEnum' }).then(res => {
        this.opts.settType = res;
      })
      this.$store.dispatch('GetOpts', { name: 'BankCommunicationTypeEnum' }).then(res => {
        this.opts.communicationType = res;
      })
      if (view.query.code) {
        this.edit = false;
        const googleCode = prompt("请输入谷歌支付授权码");
        if (googleCode) {
          this.getBankChannelCode({ bankChannelCode: view.query.code, payGoogleCode: googleCode })
        } else {
          this.closeView()
        }
      }
    },
    methods: {
      submitForm(formName) {
        if (this.form.bankWayName === undefined) {
          this.$message({
            type: 'error',
            message: '请选择银行渠道'
          });
          return false
        }
        this.$refs[formName].validate((valid) => {
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
        this.loading.show()
        if (this.form.id === undefined) {
          // 新增
          apis.addBankCode(this.form).then(res => {
            this.loading.hide()
            this.$confirm(`添加银行渠道编码成功`, {
              confirmButtonText: '继续添加',
              cancelButtonText: '关闭',
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
          apis.updateBankCode(this.form).then(res => {
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
      getBankChannelCode(params) {
        this.loading.show()
        apis.getBankChannelCode(params).then(res => {
          this.loading.hide()
          if (res.code === 200) {
          this.edit = true;
          this.form = res.data
          if (this.$route.query.ty === 'copy') {
            this.form.id = undefined
          }
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
      // 关闭编辑弹窗回调
      closeCllback(res) {
        this.ctrl.dialogVisible = false;
        console.log(res)
        if (res) {
          this.form.bankWayCode = res.bankWayCode
          this.form.bankWayName = res.desc
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
