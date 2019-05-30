<template>
  <div class="pad20">
    <el-form :inline="true" size="mini">
      <el-form-item label="商户名称">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-button type="primary">查询</el-button>
      </el-form-item>
    </el-form>
    <div class="mgt20">
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
          width="230"
          prop="date"
          label="商户名称">
        </el-table-column>
        <el-table-column
          width="138"
          prop="address"
          label="商户编号">
        </el-table-column>
        <el-table-column
          width="138"
          prop="address"
          label="支付方式名称">
        </el-table-column>
        <el-table-column
          width="150"
          prop="address"
          label="支付方式编码">
        </el-table-column>
        <el-table-column
          width="190"
          prop="address"
          label="接收方类型">
        </el-table-column>
        <el-table-column
          width="126"
          prop="address"
          label="接收方名称">
        </el-table-column>
        <el-table-column
          width="101"
          prop="address"
          label="接收方账户">
        </el-table-column>
        <el-table-column
          width="81"
          prop="address"
          label="状态">
        </el-table-column>
        <el-table-column
          width="81"
          prop="address"
          label="创建时间">
        </el-table-column>
        <el-table-column
          width="104"
          prop="address"
          label="操作">
           <!-- <template slot-scope="scope">
            <el-button
              @click.native.prevent="deleteRow(scope.$index, tableData)"
              type="text"
              size="small">
              查看账户明细
            </el-button>
          </template> -->
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
      }],
      currentPage4: 4
    }
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
    deleteRow() {},
    change() {
      console.log(this.orderTime)
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
    }
  }
}
</script>

<style lang="scss"></style>
