<template>
  <div class="pad20">
    <el-form :inline="true" size="mini">
      <el-form-item label="商户编号">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="商户订单号">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="银行订单号">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="支付流水号">
        <el-input></el-input>
      </el-form-item>
      <br>
      <el-form-item label="下单时间">
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
      </el-form-item>
      <el-form-item label="交易状态" value="">
        <el-select placeholder="请选择" value="">
          <el-option label="交易完成" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="结算状态" value="">
        <el-select placeholder="请选择" value="">
          <el-option label="结算失败" value=""></el-option>
        </el-select>
      </el-form-item>
      <br>
      <el-form-item label="支付时间">
        <el-date-picker
          v-model="payTime"
          type="datetimerange"
          :picker-options="pickerOptions2"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="['00:00:00', '23:59:59']"
          align="center"
          @change="change">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="支付方式" value="">
        <el-select placeholder="请选择" value="">
          <el-option label="结算失败" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="支付类型" value="">
        <el-select placeholder="请选择" filterable value="">
          <el-option label="结算失败" value=""></el-option>
        </el-select>
      </el-form-item>
      <br>
      <el-form-item label="支付渠道名称">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="展示方式" value="">
        <el-select placeholder="请选择" value="1">
          <el-option label="列表" value="1"></el-option>
          <el-option label="图形" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="" value="">
        <el-button type="primary">查询</el-button>
      </el-form-item>
    </el-form>
    <div>
      <el-table
        :data="tableData"
        stripe
        border
        height="400"
        style="width: 100%">
        <el-table-column
          type="index"
          label="序号"
          width="35">
        </el-table-column>
        <el-table-column
          width="40"
          type="selection">
        </el-table-column>
        <el-table-column
          width="84"
          prop="date"
          label="查补操作">
        </el-table-column>
        <el-table-column
          width="121"
          prop="name"
          label="时间(创建/支付)">
        </el-table-column>
        <el-table-column
          width="203"
          prop="address"
          label="商户(名称/编号)">
        </el-table-column>
        <el-table-column
          width="184"
          prop="address"
          label="商户订单号/商品名称">
        </el-table-column>
        <el-table-column
          width="184"
          prop="address"
          label="银行订单号">
        </el-table-column>
        <el-table-column
          width="121"
          prop="address"
          label="金额(接收/支付)">
        </el-table-column>
        <el-table-column
          width="79"
          prop="address"
          label="支付状态">
        </el-table-column>
        <el-table-column
          width="234"
          prop="address"
          label="渠道(名称/编号)">
        </el-table-column>
        <el-table-column
          width="79"
          prop="address"
          label="平台收入">
        </el-table-column>
        <el-table-column
          width="79"
          prop="address"
          label="平台成本">
        </el-table-column>
        <el-table-column
          width="79"
          prop="address"
          label="平台利润">
        </el-table-column>
        <el-table-column
          width="184"
          prop="address"
          label="支付(方式/流水号)">
        </el-table-column>
        <el-table-column
          width="184"
          prop="address"
          label="银行流水号">
        </el-table-column>
        <el-table-column
          width="78"
          prop="address"
          label="结算状态">
        </el-table-column>
        <el-table-column
          width="90"
          prop="address"
          label="手动调单">
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
      payTime: '',
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
    }
  },
  mounted() {
    this.orderTime = this.getNow()
  }
}
</script>

<style></style>
