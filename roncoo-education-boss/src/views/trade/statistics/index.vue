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
      <el-form-item label="交易状态" value="">
        <el-select placeholder="请选择" value="">
          <el-option label="交易完成" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="支付方式" value="">
        <el-select placeholder="请选择" value="">
          <el-option label="交易完成" value=""></el-option>
        </el-select>
      </el-form-item>
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
      <br>
      <el-form-item label="通道名称" value="">
        <el-select placeholder="请选择" value="">
          <el-option label="交易完成" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="渠道名称" value="">
        <el-select placeholder="请选择" value="" filterable>
          <el-option label="交易完成" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="支付类型" value="">
        <el-select placeholder="请选择" value="" filterable>
          <el-option label="交易完成" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="" value="">
        <el-button type="primary">查询</el-button>
      </el-form-item>
    </el-form>
    <div class="b_999 font_12">
      【总记录： <span class="c_red">0</span>&nbsp;条 】&nbsp;
      【总金额： <span class="c_red">0</span>&nbsp;元 】&nbsp;
      【平台毛利： <span class="c_red">0</span>&nbsp;元 】&nbsp;
      【平台成本： <span class="c_red">0</span>&nbsp;元 】&nbsp;
      【平台利润： <span class="c_red">0</span>&nbsp;元 】
    </div>
    <div class="mgt20">
      <el-table
        :data="tableData"
        stripe
        border
        style="width: 220px;">
        <el-table-column
          label="类目"
          prop="name"
          width="110">
        </el-table-column>
        <el-table-column
          width="109"
          prop="date"
          label="数据">
        </el-table-column>
      </el-table>
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
    }
  },
  mounted() {
    this.orderTime = this.getNow()
  }
}
</script>

<style lang="scss">
  .b_999 {
    background: #f5f7f8;
    line-height: 36px;
  }
</style>
