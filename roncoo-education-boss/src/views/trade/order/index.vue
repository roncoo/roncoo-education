<template>
  <div class="order">
    <div class="filter-container">

      <el-input v-model="map.permissionName" class="filter-item" style="width: 200px;" placeholder="商户编号"></el-input>
      <el-input v-model="map.permission" class="filter-item" style="width: 200px;" placeholder="商户订单号"></el-input>
      <el-select v-model="map.receiverAccountType" placeholder="订单状态">
        <el-option
          label="---全部---"
          value="">
        </el-option>
        <el-option label="交易完成" value="1"></el-option>
        <el-option label="交易成功" value="2"></el-option>
      </el-select>
      <el-select v-model="map.receiverAccountType" placeholder="支付方式">
        <el-option
          label="---全部---"
          value="">
        </el-option>
        <el-option label="交易完成" value="1"></el-option>
        <el-option label="交易成功" value="2"></el-option>
      </el-select>
      <el-select v-model="map.receiverAccountType" placeholder="支付类型">
        <el-option
          label="---全部---"
          value="">
        </el-option>
        <el-option label="交易完成" value="1"></el-option>
        <el-option label="交易成功" value="2"></el-option>
      </el-select>
      <br>
      <el-date-picker
        v-model="orderTime"
        type="datetimerange"
        :picker-options="pickerOptions2"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        :default-time="['00:00:00', '23:59:59']"
        :default-value = "new Date()"
        align="center"
        @change="change">
      </el-date-picker>

      <div style="float: right">
        <el-button class="filter-item" type="primary" @click="handleSearch">查询</el-button>

      </div>

    </div>
    <div>
      <el-table
        size="medium"
        :data="tableData"
        stripe
        border
        height="400"
        style="width: 100%">
        <el-table-column
          type="index"
          label="序号"
          width="50">
        </el-table-column>
        <el-table-column
          width="120"
          prop="date"
          label="下单时间">
        </el-table-column>
        <el-table-column
          width="168"
          prop="name"
          label="商户编号">
        </el-table-column>
        <el-table-column
          width="168"
          prop="address"
          label="商户订单号">
        </el-table-column>
        <el-table-column
          width="88"
          prop="address"
          label="订单金额">
        </el-table-column>
        <el-table-column
          width="68"
          prop="address"
          label="状态">
        </el-table-column>
        <el-table-column
          width="88"
          prop="address"
          label="支付方式">
        </el-table-column>
        <el-table-column
          width="110"
          prop="address"
          label="支付类型">
        </el-table-column>
        <el-table-column
          width="211"
          prop="address"
          label="商户名称">
        </el-table-column>
      </el-table>
    </div>
    <div class="mgt20">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage4"
        :page-sizes="[20, 50, 100, 200, 500, 1000]"
        :page-size="20"
        layout="total, sizes, prev, pager, next, jumper"
        :total="400">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      map: {
      },
      pickerOptions2: {
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
      orderTime: '',
      currentPage4: 4,
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }]
    }
  },
  created() {
    this.$store.dispatch('GetOpts', { name: 'PublicStatusEnum' }).then(res => {
      console.log(res)
    })
  },
  methods: {
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
    change() {
      console.log(this.orderTime)
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
    },
    handleSearch() {
      console.log(this.map)
    }
  },
  mounted() {
    this.orderTime = this.getNow()
  }
}
</script>

<style lang="scss">
  .order {
    padding: 20px;
  }
</style>
