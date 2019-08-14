<template >
  <div class="pad20">
    <el-form :inline="true" size="mini">
      <el-form-item label="手机号码：">
        <el-input v-model.trim="map.mobile"></el-input>
      </el-form-item>
      <el-form-item label="课程名称：">
        <el-input v-model.trim="map.courseName"></el-input>
      </el-form-item>
      <el-form-item label="支付时间：" >
        <div>
          <datePicker style="width: 357px" v-model="payTime" ref="dataRange" type="daterange"></datePicker>
        </div>
      </el-form-item>
      <el-form-item label="订单状态：">
        <el-select v-model="map.orderStatus" class="auto-width" clearable filterable placeholder="订单状态" style="width: 100px">
          <el-option
            v-for="item in opts.orderStatusList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="订单编号：">
        <el-input v-model.trim="map.orderNo"></el-input>
      </el-form-item>
      <el-form-item label="后台备注：">
        <el-input v-model.trim="map.remark"></el-input>
      </el-form-item>
      <el-form-item label="讲师名称：">
        <el-input v-model.trim="map.lecturerName"></el-input>
      </el-form-item>
      <el-form-item label="购买渠道：">
        <el-select v-model="map.channelType" class="auto-width" clearable filterable placeholder="购买渠道" style="width: 100px">
          <el-option
            v-for="item in opts.channelTypeList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="支付方式：">
        <el-select v-model="map.payType" class="auto-width" clearable filterable placeholder="购买渠道" style="width: 100px">
          <el-option
            v-for="item in opts.payTypeList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <br/>
        <el-form-item >
          <el-button icon='el-icon-search' type="primary" @click="handleCheck">查询</el-button>
          <el-button icon='el-icon-refresh' class="filter-item" @click="handleReset">重置</el-button>
          <div style="display:inline" v-has="'/course/pc/order/info/statistical'">
            <el-form-item >
              <div>
                <span>平台收入: 【{{platformProfit}}元】</span>
              </div>
            </el-form-item>
            <el-form-item >
              <el-divider direction="vertical"></el-divider>
            </el-form-item>
            <el-form-item >
              <span>订单收入: 【{{totalProfit}}元】</span>
            </el-form-item>
            <el-form-item >
              <el-divider direction="vertical"></el-divider>
            </el-form-item>
            <el-form-item >
              <span>讲师收入: 【{{lecturerProfit}}元】</span>
            </el-form-item>
          </div>
        </el-form-item>
    </el-form>
    <el-table v-loading="ctrl.load" border size="medium" :data="list" stripe style="width: 100%">
      <el-table-column type="index" label="序号" width="50">
      </el-table-column>
      <el-table-column label="订单号" width="170">
        <template slot-scope="scope">
          <el-button v-has="'/course/pc/order/info/view'" type="text" @click="handleView(scope.row.id)">{{scope.row.orderNo}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="课程信息" width="200">
        <template slot-scope="scope">
           <el-row>{{scope.row.courseName}}</el-row>
          <!-- <el-button v-has="'/user/pc/lecturer/view'" type="text" @click="handleOrderList(scope.row.courseId)">{{scope.row.courseName}}</el-button> -->
          <el-row>【<el-button type="text" @click="handleOrderList(scope.row.courseId, scope.row.courseName, 1)">详情</el-button>】</el-row>
        </template>
      </el-table-column>
      <el-table-column label="讲师信息" width="150">
        <template slot-scope="scope">
          <el-button v-has="'/user/pc/lecturer/view'" type="text" @click="handleLecturerView(scope.row.lecturerUserNo)">{{scope.row.lecturerName}}</el-button>
          <el-row>【<el-button type="text" @click="handleOrderList(scope.row.lecturerUserNo, scope.row.lecturerName, 2)">详情</el-button>】</el-row>
        </template>
      </el-table-column>
      <el-table-column label="用户信息" width="120">
         <template slot-scope="scope">
          <el-button v-has="'/user/pc/user/ext/view'" type="text" @click="handleUserView(scope.row.userNo)">{{scope.row.mobile}}</el-button>
          <el-row>【<el-button type="text" @click="handleOrderList(scope.row.userNo, scope.row.mobile, 3)">详情</el-button>】</el-row>
        </template>
      </el-table-column>
      <el-table-column
        label="交易类型"
        prop="tradeType"
        align="center"
        width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.tradeType === 1" type="success">线上支付</el-tag>
          <el-tag v-if="scope.row.tradeType === 2" type="brandColor">线下支付</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="支付方式 / 价格(元)" width="140">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.payType === 1" type="success">微信支付</el-tag>
          <el-tag v-if="scope.row.payType === 2" type="brandColor">支付宝支付</el-tag>
          <el-row>价格:【{{scope.row.pricePaid.toFixed(2)}}】</el-row>
        </template>
      </el-table-column>
      <el-table-column
        label="购买渠道"
        prop="channelType"
        align="center"
        width="80">
        <template slot-scope="sett">
          <el-tag v-if="sett.row.channelType === 1" type="success">PC端</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="订单状态"
        prop="orderStatus"
        align="center"
        width="100">
        <template slot-scope="sett">
          <el-tag v-if="sett.row.orderStatus === 1" type="warning">待支付</el-tag>
          <el-tag v-if="sett.row.orderStatus === 2" type="success">支付成功</el-tag>
          <el-tag v-if="sett.row.orderStatus === 3" type="danger">支付失败</el-tag>
          <el-tag v-if="sett.row.orderStatus === 4" type="brandColor">已关闭</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="remarkCus" label="客户备注" width="100">
      </el-table-column>
      <el-table-column prop="payTime" label="支付时间" width="160">
      </el-table-column>
      <el-table-column label="操作" fixed="right" width="100">
        <template slot-scope="scope">
          <el-button v-has="'/course/pc/order/info/edit'" type="success" @click="handleRemark(scope.row)" size="mini">备注</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      style="float: right;margin-top: 20px; margin-bottom: 22px"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :page-size="page.pageSize"
      :page-sizes="[20, 50, 100, 200, 500, 1000]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="page.totalCount">
    </el-pagination>
    <remark :visible="ctrl.remarkVisible" :formData="formData" :title="ctrl.dialogTitle" @close-callback="closeCllback"></remark>
    <view-lecturer :visible="ctrl.lecturerViewVisible" :formData="formData" :lecturerExt="lecturerExt" :title="ctrl.dialogTitle" @close-callback="closeCllback"></view-lecturer>
    <view-user :visible="ctrl.userViewVisible" :formData="formData" :title="ctrl.dialogTitle" @close-callback="closeCllback"></view-user>
    <order-view :visible="ctrl.viewVisible" :formData="formData" :title="ctrl.dialogTitle" @close-callback="closeCllback"></order-view>
  </div>
</template>
<script>
import * as orderApi from '@/api/order'
import * as userApi from '@/api/user'
import * as lecturerApi from '@/api/lecturer'
import Remark from './remark'
import ViewLecturer from '@/views/lecturer/lecturer/lecturer/view'
import viewUser from '@/views/user/ext/view'
import OrderView from './view'
import datePicker from '@/components/DateRange/datePicker';
export default {
  components: { Remark, ViewLecturer, OrderView, viewUser, datePicker },
  data() {
    return {
      ctrl: {
        load: false,
        lecturerViewVisible: false,
        remarkVisible: false,
        viewVisible: false,
        userViewVisible: false,
        listVisible: false
      },
      page: {
        beginPageIndex: 1,
        currentPage: 1,
        endPageIndex: 8,
        pageSize: 20,
        totalCount: 0,
        totalPage: 0
      },
      map: {},
      payTime: '',
      totalProfit: '', // 订单收入
      platformProfit: '', // 平台收入
      lecturerProfit: '', //讲师收入
      formData: {},
      list: [],
      lecturerExt: {},
      opts: {
        orderStatusList: [],
        channelTypeList: [],
        payTypeList: []
      },
      textTradeType: {
        1: '线上支付',
        2: '线下支付'
      },
      textPayType: {
        1: '微信支付',
        2: '支付宝支付'
      },
      textChannelType: {
        1: 'PC端'
      },
      textOrderStatus: {
        1: '待支付',
        2: '支付成功',
        3: '支付失败',
        4: '已关闭'
      }
    }
  },
  watch: {
    // 注册时间段查询条件
   'payTime': function(payTime) {
      if (this.payTime !== null && this.payTime.length) {
        this.map.beginPayTime = this.payTime[0]
        this.map.endPayTime = this.payTime[1]
      } else {
        this.map.beginPayTime = ''
        this.map.endPayTime = ''
      }
      console.log(this.map)
    }
  },
  mounted() {
    this.getList()
    this.$store.dispatch('GetOpts', { enumName: "OrderStatusEnum", type: 'arr' }).then(res => {
      this.opts.orderStatusList = res
    })
    this.$store.dispatch('GetOpts', { enumName: "ChannelTypeEnum", type: 'arr' }).then(res => {
      this.opts.channelTypeList = res
    })
    this.$store.dispatch('GetOpts', { enumName: "PayTypeEnum", type: 'arr' }).then(res => {
      this.opts.payTypeList = res
    })
  },
  methods: {
    // 列出
    handleOrderList(res, name, type) {
      if (type === 1) {
        this.map = {}
        this.map.courseId = res
        this.map.courseName = name
      } else if (type === 2) {
        this.map = {}
        this.map.lecturerUserNo = res
        this.map.lecturerName = name
      } else if (type === 3) {
        this.map = {}
        this.map.userNo = res
        this.map.mobile = name
      }
      this.handleCheck(this.map)
    },
    //跳转订单备注页面
    handleRemark(data) {
      this.ctrl.load = true
      orderApi.orderView(data.id).then(res => {
        this.ctrl.load = false
        this.formData = res.data
        this.ctrl.dialogTitle = "订单备注"
        this.ctrl.remarkVisible = true
      }).catch(() => {
        this.ctrl.load = false
      })
    },
    handleUserView(userNo) {
      this.ctrl.load = true
      userApi.userExtView({ orderUserNo: userNo }).then(res => {
        this.formData = res.data
        this.ctrl.dialogTitle = res.data.nickname + '-' + '查看详情'
        this.ctrl.userViewVisible = true
        this.ctrl.load = false
      }).catch(() => {
        this.ctrl.load = false
      })
    },
    //跳转讲师详情页面
    handleLecturerView(lecturerUserNo) {
      this.ctrl.load = true
      lecturerApi.lecturerView({ lecturerUserNo: lecturerUserNo }).then(res => {
        this.formData = res.data
        this.lecturerExt = res.data.lecturerExt
        this.ctrl.dialogTitle = res.data.lecturerName + '-' + '查看详情'
        this.ctrl.lecturerViewVisible = true
        this.ctrl.load = false
      }).catch(() => {
        this.ctrl.load = false
      })
    },
    //跳转订单详情页面
    handleView(id) {
      this.ctrl.load = true
      orderApi.orderView(id).then(res => {
        this.ctrl.load = false
        this.formData = res.data
        this.ctrl.dialogTitle = '订单详情'
        this.ctrl.viewVisible = true
      }).catch(() => {
        this.ctrl.load = false
      })
    },
    // 关闭编辑弹窗回调
    closeCllback() {
      this.ctrl.dialogVisible = false;
      this.ctrl.remarkVisible = false;
      this.ctrl.lecturerViewVisible = false;
      this.ctrl.viewVisible = false;
      this.ctrl.userViewVisible = false;
      this.ctrl.listVisible = false;
      this.handleReset()
    },
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`)
      this.page.pageSize = val
      this.getList()
    },
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`)
      this.page.pageCurrent = val
      this.getList()
    },
    // 刷新当前页面
    reload() {
      this.getList()
    },
    // 重置查询条件
    handleReset() {
      this.map = {}
      this.formData = {}
      this.lecturerExt = {}
      this.getList()
    },
    // 查询
    handleCheck() {
      this.page.pageCurrent = 1
      this.getList()
    },
    // 订单统计
    statistical() {
      orderApi.orderStatistical(this.map).then(res => {
        this.totalProfit = res.data.totalProfit.toFixed(2)
        this.platformProfit = res.data.platformProfit.toFixed(2)
        this.lecturerProfit = res.data.lecturerProfit.toFixed(2)
        this.ctrl.load = false
      }).catch(() => {
        this.ctrl.load = false
      })
    },
    getList() {
      this.ctrl.load = true
      orderApi.orderList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
        this.list = res.data.list
        this.page.pageSize = res.data.pageSize
        this.page.totalCount = res.data.totalCount
        this.statistical()
      }).catch(() => {
        this.ctrl.load = false
      })
    },
    textClass(code) {
      return {
        c_red: code === 0,
        c_blue: code === 1
      }
    }
  }
}
</script>
