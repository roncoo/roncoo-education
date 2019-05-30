<template>
  <div class="pad20">
    <div class="filter-container">
      <el-input v-model="params.requestNo" class="filter-item" style="width: 200px;" placeholder="请求号"></el-input>
      <el-input v-model="params.accountNo" class="filter-item" style="width: 200px;" placeholder="账户编号"></el-input>
      <el-input v-model="params.userNo" class="filter-item" style="width: 200px;" placeholder="用户编号"></el-input>
      <br>
      <el-date-picker
        v-model="payTime"
        type="datetimerange"
        :picker-options="timeData"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        :default-time="['00:00:00', '23:59:59']"
        align="center"
        @change="changeTime">
      </el-date-picker>

      <el-select value="" v-model="params.fundDirection" placeholder="变动方向">
        <el-option label="请选择" value=""></el-option>
        <el-option label="加款" value="ADD"></el-option>
        <el-option label="减款" value="SUB"></el-option>
      </el-select>
      <div style="float: right">
        <el-button class="filter-item" type="primary" @click="handleCheck">查询</el-button>
        <el-button class="filter-item" @click="handleReset">
          重置
        </el-button>
      </div>
    </div>
    <div>
      <el-table
        v-loading="ctrl.load"
        size="medium"
        :data="page.recordList"
        stripe
        border
        style="width: 100%">
        <el-table-column
          type="index"
          label="序号"
          width="60">
        </el-table-column>
        <el-table-column
          prop="requestNo"
          label="请求号">
        </el-table-column>
        <el-table-column
          width="230"
          prop="createTime"
          label="创建时间">
        </el-table-column>
        <el-table-column
          align="center"
          label="业务类型">
          <template slot-scope="scope">
            {{option.trxTypeObj && option.trxTypeObj[scope.row.trxType]}}
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          prop="amount"
          label="变动金额">
          <template slot-scope="scope">
            <span class="c-brand" :class="{ 'c-warning': scope.row.fundDirection === 'SUB'}">{{scope.row.amount | fitPrice}}</span>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="变动方向">
          <template slot-scope="scope">
            <span class="c-brand" :class="{ 'c-warning': scope.row.fundDirection === 'SUB'}">{{scope.row.fundDirection | fundDirection}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="balance"
          align="center"
          label="余额">
          <template slot-scope="scope">
            <span class="c-danger">{{scope.row.balance | fitPrice}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="userName"
          label="用户名">
        </el-table-column>
        <!--<el-table-column-->
          <!--label="操作"-->
          <!--fixed="right">-->
          <!--<template slot-scope="scope">-->
            <!--<el-button type="text" @click.native="showDetail(scope.row)">查看</el-button>-->
          <!--</template>-->
        <!--</el-table-column>-->
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
        <el-form-item label="请求号">
          <span class="inline_text">{{rowInfo.requestNo}}</span>
        </el-form-item>
        <el-form-item label="账户编号">
          <span class="inline_text">{{rowInfo.accountNo}}</span>
        </el-form-item>
        <el-form-item label="用户编号">
          <span class="inline_text">{{rowInfo.userNo}}</span>
        </el-form-item>
        <el-form-item label="业务类型">
          <span class="inline_text">{{option.trxTypeObj && option.trxTypeObj[rowInfo.trxType]}}</span>
        </el-form-item>
        <el-form-item label="变动金额">
          <span class="inline_text">{{rowInfo.amount}}</span>
        </el-form-item>
        <el-form-item label="变动方向">
          <span class="inline_text c_blue" :class="{c_orange: rowInfo.fundDirection === 'SUB'}">{{rowInfo.fundDirection | fundDirection}}</span>
        </el-form-item>
        <el-form-item label="余额">
          <span class="inline_text">{{rowInfo.balance}}</span>
        </el-form-item>
        <el-form-item label="用户名">
          <span class="inline_text">{{rowInfo.userName}}</span>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  filters: {
    fundDirection(value) {
      const fundType = {
        ADD: '加款',
        SUB: '减款'
      }
      return fundType[value]
    },
    fitPrice(val = 0) {
      return `¥ ${val.toFixed(2)}`
    }
  },
  data() {
    return {
      ctrl: {
        load: false,
        detailOn: false
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
        trxTypeObj: null
      },
      params: {
        numPerPage: 20
      },
      timeData: {
        shortcuts: [{
          text: '今天',
          onClick: picker => {
            const now = this.getNow('now')
            picker.$emit('pick', now)
          }
        }, {
          text: '昨天',
          onClick: picker => {
            const now = this.getNow('before')
            picker.$emit('pick', now)
          }
        }]
      },
      payTime: '',
      rowInfo: {}
    }
  },
  methods: {
    ...mapActions('account', ['getHistoryList']),
    showDetail(info) {
      this.rowInfo = info
      this.$nextTick(() => {
        this.ctrl.detailOn = true
      })
    },
    getNow(dateType) {
      const now = new Date()
      if (dateType === 'before') {
        now.setTime(now.getTime() - 3600 * 1000 * 24)
      }
      const year = now.getFullYear()
      const month = now.getMonth()
      const day = now.getDate()
      return [new Date(year, month, day), new Date(year, month, day, 23, 59, 59)]
    },
    changeTime() {
      if (this.payTime.length) {
        this.params.beginTime = this.dateToString(this.payTime[0])
        this.params.endTime = this.dateToString(this.payTime[1])
      } else {
        this.params.beginTime = ''
        this.params.endTime = ''
      }
    },
    dateToString(date) {
      const year = date.getFullYear()
      const month = (date.getMonth() + 1).toString().padStart(2, '0')
      const day = date.getDate().toString().padStart(2, '0')
      const hours = date.getHours().toString().padStart(2, '0')
      const minutes = date.getMinutes().toString().padStart(2, '0')
      const seconds = date.getSeconds().toString().padStart(2, '0')
      const timeString = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`
      return timeString
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
    handleReset() {
      this.params = {}
      this.getList()
    },
    getList() {
      this.ctrl.load = true
      this.getHistoryList(this.params).then(res => {
        console.log(res)
        this.ctrl.load = false
        this.page = res.data
      }).catch(() => {
        this.ctrl.load = false
      })
    }
  },
  created() {
    this.$store.dispatch('GetOpts', { name: 'TrxTypeEnum', type: 'obj' }).then(res => {
      this.option.trxTypeObj = res
    })
  },
  mounted() {
    this.getList()
  }
}
</script>

<style lang="scss">
  .c_blue {
    color: blue;
  }
  .c_orange {
    color: rgb(255, 165, 0);
  }
</style>
