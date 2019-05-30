<template>
  <div class="pad20">
    <div class="filter-container">
      <el-input v-model="form.requestNo" clearable class="filter-item" style="width: 200px;" placeholder="用户编号"></el-input>
      <el-input v-model="form.requestNo" class="filter-item" style="width: 200px;" placeholder="商户打款请求号"></el-input>
      <el-input v-model="form.requestNo" class="filter-item" style="width: 200px;" placeholder="银行打款请求号"></el-input>
      <el-input v-model="form.requestNo" class="filter-item" style="width: 200px;" placeholder="代付渠道编号"></el-input>
      <el-date-picker
        v-model="form.requestNo"
        type="datetimerange"
        :picker-options="pickerOptions2"
        placeholder="创建时间"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        :default-time="['00:00:00', '23:59:59']"
        align="center"
        @change="change">
      </el-date-picker>
      <el-input v-model="form.requestNo" class="filter-item" style="width: 200px;" placeholder="收款银行卡号"></el-input>
      <el-input v-model="form.requestNo" class="filter-item" style="width: 200px;" placeholder="收款人姓名"></el-input>
      <el-input v-model="form.requestNo" class="filter-item" style="width: 200px;" placeholder="结算状态"></el-input>
      <el-input v-model="form.requestNo" class="filter-item" style="width: 200px;" placeholder="产品类型"></el-input>
    </div>
    <el-form :inline="true" size="mini">
      <el-form-item label="用户编号">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="商户打款请求号">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="银行打款请求号">
        <el-input></el-input>
      </el-form-item>
      <br>
      <el-form-item label="代付渠道编号">
        <el-input></el-input>
      </el-form-item>
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
      <br>
      <el-form-item label="收款银行卡号">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="收款人姓名">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="结算状态">
        <el-select value="">
          <el-option label="请选择" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="产品类型">
        <el-select value="">
          <el-option label="请选择" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="" class="fr">
        <el-button type="primary">查询</el-button>
      </el-form-item>
    </el-form>
    <div class="b_999 font_12">
      【结算记录总数： <span class="c_red">0</span>&nbsp;条 】&nbsp;
      【结算总金额： <span class="c_red">0</span>&nbsp;元 】&nbsp;
      【出款总金额： <span class="c_red">0</span>&nbsp;元 】&nbsp;
      【平台结算毛收益： <span class="c_red">0</span>&nbsp;元 】&nbsp;
      【平台结算成本： <span class="c_red">0</span>&nbsp;元 】&nbsp;
      【平台结算纯收入： <span class="c_red">0</span>&nbsp;元 】
    </div>
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
          label="创建时间">
        </el-table-column>
        <el-table-column
          width="230"
          prop="name"
          label="操作">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="deleteRow(scope.$index, tableData)"
              type="text"
              size="small">
              查看
            </el-button>
            <el-button
              @click.native.prevent="deleteRow(scope.$index, tableData)"
              type="text"
              size="small">
              结果
            </el-button>
            <el-button
              @click.native.prevent="deleteRow(scope.$index, tableData)"
              type="text"
              size="small">
              补查
            </el-button>
          </template>
        </el-table-column>
        <el-table-column
          width="138"
          prop="address"
          label="产品类型">
        </el-table-column>
        <el-table-column
          width="150"
          prop="address"
          label="银行打款请求号">
        </el-table-column>
        <el-table-column
          width="190"
          prop="address"
          label="用户(名称/编号)">
        </el-table-column>
        <el-table-column
          width="126"
          prop="address"
          label="代付接口">
        </el-table-column>
        <el-table-column
          width="81"
          prop="address"
          label="结算金额">
        </el-table-column>
        <el-table-column
          width="81"
          prop="address"
          label="手续费">
        </el-table-column>
        <el-table-column
          width="81"
          prop="address"
          label="到账金额">
        </el-table-column>
        <el-table-column
          width="81"
          prop="address"
          label="结算状态">
        </el-table-column>
        <el-table-column
          width="138"
          prop="address"
          label="商户打款请求号">
        </el-table-column>
        <el-table-column
          width="118"
          prop="address"
          label="收款人(姓名/手机号)">
        </el-table-column>
        <el-table-column
          width="168"
          prop="address"
          label="收款银行(名称/卡号)">
        </el-table-column>
        <el-table-column
          width="108"
          label="交易来源">
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
      form: {},
      currentPage4: 4,
      tableData: [],
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
      payTime: ''
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

<style lang="scss">
</style>
