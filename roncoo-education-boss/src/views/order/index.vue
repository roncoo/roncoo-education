<template >
  <div class="pad20">
    <el-form :inline="true" size="mini">
      <el-form-item label="手机号码:">
        <el-input v-model="map.mobile"></el-input>
      </el-form-item>
      <el-form-item label="课程名称:">
        <el-input v-model="map.courseName"></el-input>
      </el-form-item>
      <el-form-item label="订单编号:">
        <el-input v-model="map.orderNo"></el-input>
      </el-form-item>
      <el-form-item label="后台备注:">
        <el-input v-model="map.remark"></el-input>
      </el-form-item>
      <el-form-item label="讲师名称:">
        <el-input v-model="map.lecturerName"></el-input>
      </el-form-item>
      <el-form-item label="日期:">
        <el-date-picker
        v-model="gmtCreate"
        type="daterange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        align="center">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="订单状态:">
        <el-select v-model="map.orderStatus" class="auto-width" clearable filterable placeholder="订单状态" style="width: 100px">
          <el-option
            v-for="item in opts.orderStatusList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="购买渠道:">
        <el-select v-model="map.channelType" class="auto-width" clearable filterable placeholder="购买渠道" style="width: 100px">
          <el-option
            v-for="item in opts.channelTypeList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="支付方式:">
        <el-select v-model="map.payType" class="auto-width" clearable filterable placeholder="购买渠道" style="width: 100px">
          <el-option
            v-for="item in opts.payTypeList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="float:right" >
          <el-button type="primary" :loading="ctrl.loading" @click="handleCheck">查询</el-button>
          <el-button class="filter-item" @click="handleReset">重置</el-button>
        </el-form-item>
    </el-form>
    <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
      <el-table-column type="index" label="序号" width="40">
      </el-table-column>
      <el-table-column label="订单号" width="157">
        <template slot-scope="scope">
          <el-button type="text" @click="handleView(scope.row.id)">{{scope.row.orderNo}}</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="courseName" label="课程信息">
      </el-table-column>
      <el-table-column label="讲师信息">
        <template slot-scope="scope">
          <el-button type="text" @click="handleLecturerView(scope.row.lecturerUserNo)">{{scope.row.lecturerName}}</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="mobile" label="用户信息">
      </el-table-column>
      <el-table-column label="交易类型" width="83">
        <template slot-scope="scope">
          <span>{{textTradeType[scope.row.tradeType]}}</span>
        </template>
      </el-table-column>
      <el-table-column label="支付方式 / 价格(元)" width="146">
        <template slot-scope="scope">
          <el-row>{{textPayType[scope.row.payType]}}</el-row>
          <el-row>价格:【{{scope.row.pricePaid.toFixed(2)}}】</el-row>
        </template>
      </el-table-column>
      <el-table-column label="购买渠道" width="83">
        <template slot-scope="scope">
          <el-row>{{textChannelType[scope.row.channelType]}}</el-row>
        </template>
      </el-table-column>
      <el-table-column label="订单状态">
        <template slot-scope="scope">
          <el-row>{{textOrderStatus[scope.row.orderStatus]}}</el-row>
        </template>
      </el-table-column>
      <el-table-column prop="remarkCus" label="客户备注">
      </el-table-column>
      <el-table-column prop="payTime" label="支付时间" width="153">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handleRemark(scope.row)" size="mini">备注</el-button>
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
    <remark :visible="ctrl.remarkVisible" :formData="formdata" :title="ctrl.dialogTitle" @close-cllback="closeCllback"></remark>
    <view-lecturer :visible="ctrl.lecturerViewVisible" :formData="formdata" :lecturerExt="lecturerExt" :title="ctrl.dialogTitle" @close-cllback="closeCllback"></view-lecturer>
    <order-view :visible="ctrl.viewVisible" :formData="formdata" :title="ctrl.dialogTitle" @close-cllback="closeCllback"></order-view>
  </div>
</template>
<script>
import * as courseApis from '@/api/course'
import * as userApi from '@/api/user'
import Remark from './remark'
import ViewLecturer from '@/views/lecturer/lecturer/view'
import OrderView from './view'
export default {
  components: { Remark, ViewLecturer, OrderView },
  data() {
    return {
      ctrl: {
        load: false,
        lecturerViewVisible: false,
        remarkVisible: false,
        viewVisible: false
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
      gmtCreate: [],
      formdata: {},
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
    getList() {
      this.ctrl.load = true
      courseApis.orderList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
        this.ctrl.load = false
        this.list = res.data.list
        this.page.pageSize = res.data.pageSize
        this.page.totalCount = res.data.totalCount
      }).catch(() => {
        this.ctrl.load = false
      })
    },
    handleCheck() {
      this.getList()
    },
    //跳转订单备注页面
    handleRemark(data) {
      this.ctrl.load = true
      courseApis.orderView(data.id).then(res => {
        this.ctrl.load = false
        this.formdata = res.data
        this.ctrl.dialogTitle = "订单备注"
        this.ctrl.remarkVisible = true
      }).catch(() => {
        this.ctrl.load = false
      })
    },
    //跳转讲师详情页面
    handleLecturerView(lecturerUserNo) {
      this.ctrl.load = true
      userApi.lecturerView({ lecturerUserNo: lecturerUserNo }).then(res => {
        this.ctrl.load = false
        this.formdata = res.data
        this.lecturerExt = res.data.lecturerExt
        this.ctrl.dialogTitle = res.data.lecturerName + '-' + '查看详情'
        this.ctrl.lecturerViewVisible = true
      }).catch(() => {
        this.ctrl.load = false
      })
    },
    //跳转订单详情页面
    handleView(id) {
      this.ctrl.load = true
      courseApis.orderView(id).then(res => {
        this.ctrl.load = false
        this.formdata = res.data
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
      this.reload()
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
      this.getList()
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
