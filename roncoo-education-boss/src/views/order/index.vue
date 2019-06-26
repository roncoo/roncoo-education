<template >
  <div class="pad20">
    <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
      <el-table-column type="index" label="序号" width="40">
      </el-table-column>
      <el-table-column prop="orderNo" label="订单号">
      </el-table-column>
      <el-table-column prop="courseName" label="课程信息">
      </el-table-column>
      <el-table-column prop="mobile" label="用户信息">
      </el-table-column>
      <el-table-column prop="tradeType" label="交易类型">
      </el-table-column>
      <el-table-column prop="courseName" label="支付方式/价格(元)">
      </el-table-column>
      <el-table-column prop="courseName" label="购买渠道">
      </el-table-column>
      <el-table-column prop="courseName" label="订单状态">
      </el-table-column>
      <el-table-column prop="courseName" label="客户备注">
      </el-table-column>
      <el-table-column prop="courseName" label="支付时间">
      </el-table-column>
      <el-table-column label="操作">
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
  </div>
</template>
<script>
import * as courseApis from '@/api/course'
export default {
  data() {
    return {
      ctrl: {
        load: false
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
      list: []
    }
  },
  mounted() {
    this.getList()
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
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`)
      this.page.pageSize = val
      this.getList()
    },
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`)
      this.page.pageCurrent = val
      this.getList()
    }
  }
}
</script>
