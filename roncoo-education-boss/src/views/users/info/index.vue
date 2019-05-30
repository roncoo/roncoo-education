<template>
  <div class="pad20">
    <el-form :inline="true" size="mini">
      <el-form-item label="用户编号">
        <el-input v-model="params.userNo"></el-input>
      </el-form-item>
      <el-form-item label="登录账号">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="params.userName"></el-input>
      </el-form-item>
      <el-form-item label="企业全称">
        <el-input v-model="params.fullName"></el-input>
      </el-form-item>
      <el-form-item label="支付key">
        <el-input v-model="params.rpUserPayConfig.payKey"></el-input>
      </el-form-item>
      <el-form-item label="用户类型">
        <el-select placehorder="请选择" v-model="params.userType" clearable>
          <el-option :label="item.desc" :value="item.name" v-for="item in option.userTyoeList" :key="item.name"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="用户状态">
        <el-select placehorder="请选择" v-model="params.status" clearable>
          <el-option :label="item.desc" :value="item.name" v-for="item in option.merchantStatusList" :key="item.name"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="产品名称">
        <el-input v-model="params.rpUserPayConfig.productName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :loading="ctrl.load" @click.native.prevent="handleCheck">查询</el-button>
      </el-form-item>
    </el-form>
    <div>
      <el-button type="primary" size="mini" icon="el-icon-circle-plus-outline" @click.native="addUser(false)">添加用户</el-button>
      <el-button type="primary" size="mini">批量更新</el-button>
    </div>
    <div class="mgt20">
      <el-table
        :data="page.recordList"
        border
        v-loading="ctrl.load"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="40">
        </el-table-column>
        <el-table-column
          label="序号"
          type="index"
          width="50">
        </el-table-column>
        <el-table-column
          label="创建时间"
          prop="createTime">
        </el-table-column>
        <el-table-column
          label="用户名称"
          prop="userName">
          <template slot-scope="scope">
            <span class="c_blue">
              {{scope.row.userName}}
            </span>
          </template>
        </el-table-column>
        <el-table-column
          label="用户类型"
          prop="userTypeDesc">
        </el-table-column>
        <el-table-column
          label="状态">
          <template slot-scope="scope">
            <span :class="statusClass(scope.row.status)">
              {{option.merchantStatusObj && option.merchantStatusObj[scope.row.status]}}
            </span>
            <el-button @click.native="changeStatus(scope.row)" size="mini" type="danger" class="mgl10" v-if="scope.row.status !== 'ACTIVE'">激活</el-button>
            <el-button @click.native="changeStatus(scope.row)" size="mini" type="primary" class="mgl10" v-else>冻结</el-button>
          </template>
        </el-table-column>
        <el-table-column
          label="代付路由">
          <template slot-scope="scope">
            <span class="c_red" :class="{c_blue: scope.row.rpUserPayConfig && scope.row.rpUserPayConfig.isOpenProxyPayRule === 'YES'}">
              {{option.isOpenProxyPayRule[scope.row.rpUserPayConfig && scope.row.rpUserPayConfig.isOpenProxyPayRule]}}
            </span>
          </template>
        </el-table-column>
        <el-table-column
          label="支付产品名称">
          <template slot-scope="scope">
            {{scope.row.rpUserPayConfig && scope.row.rpUserPayConfig.productName}}
          </template>
        </el-table-column>
        <!-- <el-table-column
          label="联系邮箱"
          prop="email">
        </el-table-column> -->
        <el-table-column
          label="操作"
          fixed="right"
          width="180">
          <template slot-scope="scope">
            <el-button type="text" @click.native="showDetail(scope.row)">查看</el-button>
            <el-button type="text" @click.native="addUser(scope.row.userNo)">修改</el-button>
            <el-dropdown trigger="click" size="mini" class="mgl10" @command="handleCommand($event, scope.row)">
              <span class="el-dropdown-link">
                更多<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="editPwd">重置登录密码</el-dropdown-item>
                <div v-if="scope.row.userType === 'MERCHANT' || scope.row.userType === 'NOT_DIRECT_MERCHANT'">
                  <el-dropdown-item command="editPayPwd">重置支付密码</el-dropdown-item>
                  <el-dropdown-item command="sendPayKeyToEmail">发送秘钥</el-dropdown-item>
                  <el-dropdown-item command="resetPaySecret">重置秘钥</el-dropdown-item>
                  <el-dropdown-item>查看二维码</el-dropdown-item>
                </div>
                <div v-if="scope.row.userType === 'NOT_DIRECT_MERCHANT' || scope.row.userType === 'NOT_DIRECT_AGENT'">
                  <el-dropdown-item command="lookAgentInfo">查看代理信息</el-dropdown-item>
                </div>
                <el-dropdown-item>设置通道附属信息</el-dropdown-item>
                <el-dropdown-item>查看二级商户成功率</el-dropdown-item>
                <div v-if="scope.row.userType === 'MERCHANT' || scope.row.userType === 'NOT_DIRECT_MERCHANT'">
                  <el-dropdown-item command="PAY">交易开关</el-dropdown-item>
                  <el-dropdown-item command="PROXY">代付开关</el-dropdown-item>
                </div>
                <div v-if="scope.row.rpUserPayConfig && scope.row.rpUserPayConfig.proxyPayCheckType === 'PASSWORDANDGOOGLEAUTH'">
                  <el-dropdown-item command="resetGoogleSecret">重置google密钥</el-dropdown-item>
                  <el-dropdown-item command="sendGoogleSecretToEmail">发送google密钥</el-dropdown-item>
                </div>
                <el-dropdown-item v-if="scope.row.rpUserPayConfig && scope.row.rpUserPayConfig.isOpenProxyPayRule === 'YES'" command="updateProxyPayRuleStatus">
                  关闭代付路由
                </el-dropdown-item>
                <el-dropdown-item v-else command="updateProxyPayRuleStatus">开启代付路由</el-dropdown-item>
                <el-dropdown-item>代付路由设置</el-dropdown-item>
                <div v-if="scope.row.userType === 'NOT_DIRECT_MERCHANT'">
                  <el-dropdown-item v-if="scope.row.rpUserPayConfig && scope.row.rpUserPayConfig.affiliateWithAgent === 'NO'" command="editAffiliateWithAgent">挂靠代理商关系</el-dropdown-item>
                  <el-dropdown-item v-else command="editAffiliateWithAgent">解挂代理商关系</el-dropdown-item>
                </div>
              </el-dropdown-menu>
            </el-dropdown>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="mgt20">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :page-size="page.numPerPage"
        :page-sizes="[20, 50, 100, 200, 500, 1000]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.totalCount">
      </el-pagination>
    </div>
    <el-dialog width="70%" top="5vh" :title="rowInfo.userName" :visible.sync="ctrl.detailOn">
      <el-form :inline="true">
        <el-form-item label="创建时间">
          <span class="inline_text">{{rowInfo.createTime}}</span>
        </el-form-item>
        <el-form-item label="用户编号">
          <span class="inline_text">{{rowInfo.userNo}}</span>
        </el-form-item>
        <el-form-item label="登录账号">
          <span class="inline_text">{{rowInfo.mobile || rowInfo.email}}</span>
        </el-form-item>
        <el-form-item label="用户名称">
          <span class="inline_text">{{rowInfo.userName}}</span>
        </el-form-item>
        <el-form-item label="用户类型">
          <span class="inline_text">{{rowInfo.userTypeDesc}}</span>
        </el-form-item>
        <el-form-item label="状态">
          <span class="inline_text" :class="statusClass(rowInfo.status)">{{rowInfo.statusDesc}}</span>
        </el-form-item>
        <el-form-item label="代付路由">
          <span class="inline_text c_red" :class="{c_blue: rowInfo.rpUserPayConfig && rowInfo.rpUserPayConfig.isOpenProxyPayRule === 'YES'}">
            {{this.option.isOpenProxyPayRule[rowInfo.rpUserPayConfig && rowInfo.rpUserPayConfig.isOpenProxyPayRule]}}
          </span>
        </el-form-item>
        <el-form-item label="支付产品名称">
          <span class="inline_text">{{rowInfo.rpUserPayConfig && rowInfo.rpUserPayConfig.productName}}</span>
        </el-form-item>
        <el-form-item label="联系邮箱">
          <span class="inline_text">{{rowInfo.email}}</span>
        </el-form-item>
      </el-form>
    </el-dialog>
    <ListPayTypeBox
      :togglePayTypeBox="ctrl.togglePayTypeBox"
      @closePayTypeBox="ctrl.togglePayTypeBox = false"
      :payTypeList="payTypeList"
      @togglePayTypeStatus="togglePayTypeStatus"
      :payTypeTitle="payTypeTitle">
    </ListPayTypeBox>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import ListPayTypeBox from './components/ListPayTypeBox.vue'
export default {
  components: {
    ListPayTypeBox
  },
  data() {
    return {
      payTypeList: [], //交易或代付开关的列表
      payTypeTitle: '交易开关', //交易或代付开关的标题
      ctrl: {
        load: false,
        detailOn: false,
        togglePayTypeBox: false
      },
      page: {
        beginPageIndex: 1,
        currentPage: 1,
        endPageIndex: 8,
        numPerPage: 20,
        totalCount: 0,
        totalPage: 0,
        recordList: []
      },
      option: {
        isOpenProxyPayRule: {
          NO: '禁用',
          YES: '开启'
        },
        userTyoeList: [],
        merchantStatusList: [],
        merchantStatusObj: null
      },
      params: {
        rpUserPayConfig: {}
      },
      rowInfo: {},
      multipleSelection: []
    }
  },
  methods: {
    ...mapActions('users', ['getUserInfoList',
      'editStatus',
      'editPwd',
      'editPayPwd',
      'sendPayKeyToEmail',
      'resetPaySecret',
      'updateProxyPayRuleStatus',
      'listPayType',
      'sendGoogleSecretToEmail',
      'resetGoogleSecret',
      'lookAgentInfo',
      'editAffiliateWithAgent']),
    // 交易或代理开关列表
    payOrProxyList(info, messageObj, params, command) {
      params['payType'] = command
      if (command === 'PROXY') {
        this.payTypeTitle = '代付开关'
      }
      this.loading.show()
      this.listPayType(params).then(res => {
        this.loading.hide()
        console.log(res)
        this.ctrl.togglePayTypeBox = true
        this.payTypeList = res.data
      }).catch(() => {
        this.loading.hide()
        this.ctrl.togglePayTypeBox = true
      })
    },
    // 打开或关闭交易（代付）方式
    togglePayTypeStatus(params) {
      console.log(params)
      this.payTypeList.find(item => {
        return item.userNo === params.userNo
      }).status = params.status
    },
    //打开或关闭代付路由
    switchProxyPayRuleStatus(info, messageObj, params) {
      console.log(123)
      let isOpenProxyPayRule = 'NO'
      messageObj['updateProxyPayRuleStatus'] = `确定关闭${info.userName}的代付路由？`
      if (info.rpUserPayConfig.isOpenProxyPayRule === 'NO') {
        isOpenProxyPayRule = 'YES'
        messageObj['updateProxyPayRuleStatus'] = `确定开启${info.userName}的代付路由？`
      }
      params['isOpenProxyPayRule'] = isOpenProxyPayRule
      console.log(messageObj, params)
    },
    // 挂靠/解挂代理商关系
    switchAffiliateWithAgent(info, messageObj, params) {
      console.log(123)
      let isOpenProxyPayRule = 'NO'
      messageObj['editAffiliateWithAgent'] = `确定解挂${info.userName}的代理商关系？`
      if (info.rpUserPayConfig.affiliateWithAgent === 'NO') {
        isOpenProxyPayRule = 'YES'
        messageObj['editAffiliateWithAgent'] = `确定挂靠${info.userName}的代理商关系？`
      }
      params['affiliateWithAgent'] = isOpenProxyPayRule
      console.log(messageObj, params)
    },
    // 下拉操作
    handleCommand(command, info) {
      // this.$message('click on item ' + command);
      const messageObj = {
        editPwd: `即将为${info.userName}重置登录密码，并且发送到联系邮箱`,
        editPayPwd: `即将为${info.userName}重置支付密码，并且发送到联系邮箱`,
        sendPayKeyToEmail: `即将发送payKey,paySecret到${info.userName}的邮箱`,
        resetPaySecret: `确定要为${info.userName}重置秘钥？`,
        sendGoogleSecretToEmail: `确定要向${info.userName}发送google秘钥？`,
        resetGoogleSecret: `确定要为${info.userName}重置google秘钥？`
      }
      console.log(command)
      console.log(info)
      const params = {
        userNo: info.userNo
      }
      // 打开或关闭代付路由
      if (command === 'updateProxyPayRuleStatus') {
        this.switchProxyPayRuleStatus(info, messageObj, params, command)
      }
      // 挂靠/解挂代理商关系
      if (command === 'editAffiliateWithAgent') {
        this.switchAffiliateWithAgent(info, messageObj, params, command)
      }
      // 交易或代付开关
      if (command === 'PAY' || command === 'PROXY') {
        this.payOrProxyList(info, messageObj, params, command)
        return
      }
      // 查看代理信息
      if (command === 'lookAgentInfo') {
        this.$router.push({ path: '/users/agent', query: { userNo: info.userNo }})
        return
      }
      this.$confirm(messageObj[command], '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.allRequest(command, info, messageObj, params)
      }).catch(() => {
        console.log('取消')
      })
    },
    // 各种按钮请求
    allRequest(command, info, messageObj, params) {
      this.loading.show()
      this[command](params).then(res => {
        this.loading.hide()
        console.log(res)
        if (command === 'updateProxyPayRuleStatus') {
          info.rpUserPayConfig.isOpenProxyPayRule = params.isOpenProxyPayRule
        }
        if (command === 'editAffiliateWithAgent') {
          info.rpUserPayConfig.affiliateWithAgent = params.affiliateWithAgent
        }
        this.$message({
          showClose: true,
          type: 'success',
          message: '操作成功'
        })
      }).catch(() => {
        this.loading.hide()
      })
    },
    // 激活或冻结
    changeStatus(info) {
      console.log(info)
      const params = {
        userNo: info.userNo,
        status: '101'
      }
      let tipStr = `确定要冻结${info.userName}吗`
      let successStr = '冻结成功'
      if (info.status !== 'ACTIVE') {
        tipStr = `确定要激活${info.userName}吗`
        successStr = '激活成功'
        params.status = '100'
      }
      this.$confirm(tipStr, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const loading = this.$loading({
          lock: true,
          text: '请稍后...',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        })
        this.editStatus(params).then(res => {
          loading.close();
          console.log(res)
          if (info.status !== 'ACTIVE') {
            info.status = 'ACTIVE'
          } else {
            info.status = 'UNACTIVE'
          }
          this.$message({
            showClose: true,
            type: 'success',
            message: successStr
          })
        }).catch(() => {
          loading.close();
        })
      }).catch(() => {
        console.log('取消')
      })
    },
    // 添加用户
    addUser(userNo) {
      if (userNo) {
        this.$router.push({ path: '/users/add', query: { userNo }})
      } else {
        this.$router.push({ path: '/users/add' })
      }
    },
    // 状态样式
    statusClass(status) {
      return {
        c_999: status !== 'ACTIVE' && status !== 'UNACTIVE',
        c_blue: status === 'ACTIVE',
        c_red: status === 'UNACTIVE'
      }
    },
    // 查看
    showDetail(info) {
      console.log(info)
      this.rowInfo = info
      this.$nextTick(() => {
        this.ctrl.detailOn = true
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    handleCheck() {
      this.params.pageNum = 1
      this.getList()
    },
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`)
      this.params.numPerPage = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.params.pageNum = val
      this.getList()
      // console.log(`当前页: ${val}`)
    },
    getList() {
      this.ctrl.load = true
      this.getUserInfoList(this.params).then(res => {
        console.log(res)
        this.ctrl.load = false
        this.page = res.data
      }).catch(() => {
        this.ctrl.load = false
      })
    }
  },
  created() {
    this.$store.dispatch('GetOpts', { name: 'UserTypeEnum' }).then(res => {
      console.log(res)
      this.option.userTyoeList = res
    })
    this.$store.dispatch('GetOpts', { name: 'MerchantStatusEnum' }).then(res => {
      console.log(res)
      this.option.merchantStatusList = res
      this.option.merchantStatusObj = res.reduce((pre, item) => {
        pre[item.name] = item.desc
        return pre
      }, {})
    })
  },
  mounted() {
    this.getList()
  }
}
</script>

<style lang="scss">
  .el-dropdown-link {
    cursor: pointer;
  }
</style>
