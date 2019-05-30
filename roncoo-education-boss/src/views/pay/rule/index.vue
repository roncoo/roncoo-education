<template>
  <div class="app-container">
    <div class="filter-container">

      <el-input v-model="map.bankChannelName" clearable class="filter-item" style="width: 200px;" placeholder="银行渠道名称"></el-input>

      <div style="float: right">
        <el-button class="filter-item" type="primary" @click="handleSearch">
          搜索
        </el-button>

        <el-button class="filter-item" type="success" @click="handleAddRow">
          新增
        </el-button>
      </div>
    </div>
    <el-table
      :data="tableData"
      v-loading="ctrl.loading"
      border
      style="width: 100%">
      <el-table-column
        type="index"
        label="序号"
        width="50">
      </el-table-column>
      <el-table-column
        prop="bankChannelName"
        label="银行渠道">
        <template slot-scope="scope">
          {{scope.row.bankChannelName}} <br>{{scope.row.bankChannelCode}}
        </template>
      </el-table-column>
      <el-table-column
        width="80"
        align="center"
        prop="channelPriority"
        label="渠道权重">
      </el-table-column>
      <el-table-column
        width="180"
        prop="editTime"
        label="支付金额限制（元）"
        align="center">
        <template slot-scope="scope">
          {{scope.row.minPayAmount}} - {{scope.row.maxPayAmount}}
        </template>
      </el-table-column>
      <el-table-column
        width="200"
        prop="editTime"
        label="支付时间限制"
        align="center">
        <template slot-scope="scope">
          {{scope.row.beginPayTime}} - {{scope.row.endPayTime}}
        </template>
      </el-table-column>
      <el-table-column
        width="80"
        prop="status"
        label="路由状态"
        align="center">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            @change="changeStatus(scope.row, $event)"
            active-value="YES"
            inactive-value="NO"
            active-color="#13ce66"
            inactive-color="#ff4949">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column
        width="80"
        prop="field1"
        label="额度状态"
        align="center">
        <template slot-scope="scope">
          <!--{{scope.row.field1}}-->
          <el-tag v-if="scope.row.field1 == 'CLOSE'" type="danger">满额</el-tag>
          <el-tag v-else-if="scope.row.field1 == 'OPEN'" type="success">未满额</el-tag>
          <el-tag v-else>未设置</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="170">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="primary" @click="handleAddWay(scope.row)" size="mini">修改</el-button>
              <el-button type="warning" @click="handleDelRow(scope.row)" size="mini">删除</el-button>
            </li>
          </ul>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      style="float: right;margin-top: 20px"
      :current-page="page.currentPage"
      :page-sizes="page.pageSizesArr"
      :page-size="page.pageNum"
      layout="total, sizes, prev, pager, next, jumper"
      :total="page.totalNum">
    </el-pagination>
    <edit :visible="ctrl.dialogVisible" :formData="ruleObj" :title="ctrl.dialogTitle" @close-callback="closeCallback"></edit>
  </div>
</template>

<script>
  import * as apis from '@/api/pay'
  import Edit from './edit'
  export default {
    name: "PayRuleList",
    components: { Edit },
    data() {
      return {
        map: {},
        ctrl: {
          loading: false,
          dialogVisible: false,
          dialogTitle: '编辑支付产品'
        },
        page: {
          pageNow: 0,
          pages: 0, //总页数
          pageNum: 20, //每页显示的个数
          totalNum: 0, //总条目数
          pageSizesArr: [20, 50, 100, 200],
          currentPage: 1 //当前页数
        },
        ruleObj: {},
        tableData: []
      }
    },
    mounted() {
      const view = this.$route.query
      this.map.payWayId = view.id
      this.getList(1)
    },
    methods: {
      handleAddRow() {
        this.ruleObj = undefined
        this.ctrl.dialogVisible = true;
      },
      handleAddWay(row) {
        console.log(row)
        row.payTime = [row.beginPayTime, row.endPayTime]
        this.ruleObj = row;
        this.ctrl.dialogVisible = true;
      },
      handleSearch() {
        this.getList(1)
      },
      closeCallback() {
        this.getList(1)
        this.ctrl.dialogVisible = false;
      },
      // 修改路由状态
      changeStatus(row, command) {
        console.log('handleCommand ------>> ', row, command)
        const title = { NO: '关闭', YES: '开启' }
        this.$confirm(`确定${title[command]}名称为${row.bankChannelName}的路由?`, title[command] + '路由', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          apis.updatePayRuleStatus({ id: row.id, status: command }).then(res => {
            this.$message({
              type: 'success',
              message: title[command] + '成功'
            });
          })
        }).catch(() => {
          this.getList(1)
        })
      },
      handleDelRow(row) {
        this.$confirm(`确定删除名称为${row.bankChannelName}的路由?`, '删除路由', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          apis.deletePayRule({ id: row.id, payWayId: this.map.payWayId }).then(res => {
            this.getList(1)
            this.$message({
              type: 'success',
              message: '删除成功'
            });
          })
        }).catch(() => {
          this.getList(1)
        })
      },
      getList(page) {
        this.ctrl.loading = true
        apis.ruleList(this.map, page, this.page.pageNum).then(res => {
          // console.log(res)
          this.tableData = res.data.recordList
          this.page.currentPage = res.data.currentPage
          this.page.totalNum = res.data.totalCount
          this.ctrl.loading = false
        }).catch(() => {
          this.ctrl.loading = false
        })
      },
      // currentPage 当前页数改变时会触发
      handleCurrentChange(page) {
        this.getList(page);
      },
      // pageSize 每页条数改变时会触发
      handleSizeChange(pageSize) {
        this.page.pageNum = pageSize;
        this.getList(this.page.currentPage);
      }
    }
  }
</script>

<style scoped>

</style>
