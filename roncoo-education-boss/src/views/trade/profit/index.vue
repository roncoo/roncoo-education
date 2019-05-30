<template>
  <div class="pad20">
    <el-form :inline="true" size="mini">
      <el-form-item label="商户编号">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="商户名称">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="支付方式" value="">
        <el-select placeholder="请选择" value="">
          <el-option label="交易完成" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="支付类型" value="">
        <el-select placeholder="请选择" value="" filterable>
          <el-option label="交易完成" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="展示方式" value="">
        <el-select placeholder="请选择" value="">
          <el-option label="列表" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="通道名称" value="">
        <el-select placeholder="请选择" value="" filterable>
          <el-option label="交易完成" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="渠道名称" value="">
        <el-select placeholder="请选择" value="" filterable>
          <el-option label="交易完成" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间">
        <el-date-picker
          v-model="value2"
          align="right"
          type="date"
          placeholder="选择日期"
          :picker-options="pickerOptions1">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间">
        <el-date-picker
          v-model="value1"
          align="right"
          type="date"
          placeholder="选择日期"
          :picker-options="pickerOptions1">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="" value="">
        <el-button type="primary">查询</el-button>
      </el-form-item>
    </el-form>
    <div class="b_999 font_12">
      【总成功金额: <span class="c_red">0</span>&nbsp;元 】&nbsp;
      【总成功笔数: <span class="c_red">0</span>&nbsp;笔 】&nbsp;
      【总平台收入: <span class="c_red">0</span>&nbsp;元 】&nbsp;
      【总平台成本: <span class="c_red">0</span>&nbsp;元 】&nbsp;
      【总平台利润: <span class="c_red">0</span>&nbsp;元 】&nbsp;
      【总未支付金额: <span class="c_red">0</span> 元】
    </div>
    <div class="mgt20">
      <el-table
        :data="tableData"
        stripe
        border
        height="400"
        style="width: 100%">
        <el-table-column
          label="日期"
          prop="date"
          width="104">
        </el-table-column>
        <el-table-column
          width="200"
          prop="date"
          label="商户名称">
        </el-table-column>
        <el-table-column
          width="85"
          prop="name"
          label="成功金额">
        </el-table-column>
        <el-table-column
          width="85"
          prop="address"
          label="成功笔数">
        </el-table-column>
        <el-table-column
          width="85"
          prop="address"
          label="平台收入">
        </el-table-column>
        <el-table-column
          width="85"
          prop="address"
          label="平台成本">
        </el-table-column>
        <el-table-column
          width="85"
          prop="address"
          label="平台利润">
        </el-table-column>
        <el-table-column
          width="95"
          prop="address"
          label="未支付金额">
        </el-table-column>
        <el-table-column
          width="95"
          prop="address"
          label="未支付笔数">
        </el-table-column>
        <el-table-column
          width="155"
          prop="address"
          label="支付方式">
        </el-table-column>
        <el-table-column
          width="155"
          prop="address"
          label="银行通道">
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
      pickerOptions1: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }]
      },
      value1: '',
      value2: '',
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
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
    }
  }
}
</script>

<style lang="scss">
  .b_999 {
    background: #f5f7f8;
    line-height: 36px;
  }
</style>
