<template>
  <div class="app-container">
    <div class="filter-container">

      <el-input v-model="map.payWayName" clearable class="filter-item" style="width: 200px;" placeholder="支付方式名称"></el-input>

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
      size="mini"
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
        min-width="100"
        prop="payWayName"
        label="支付方式">
        <template slot-scope="scope">
          {{scope.row.payWayName}}<br>
          {{scope.row.payWayCode}}
        </template>
      </el-table-column>
      <el-table-column
        min-width="140"
        prop="payTypeName"
        label="支付类型">
        <template slot-scope="scope">
          {{scope.row.payTypeName}}<br>
          {{scope.row.payTypeCode}}
        </template>
      </el-table-column>
      <el-table-column
        prop="isOpenRule"
        label="开启路由"
        align="center">
        <template slot-scope="scope">
          <el-switch
            @change="handleStatus($event, scope.row, $event)"
            v-model="scope.row.isOpenRule"
            active-value="YES"
            inactive-value="NO"
            active-color="#13ce66"
            inactive-color="#ff4949">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column
        min-width="160"
        prop="channelFlowCode"
        label="渠道分流编号">
      </el-table-column>
      <el-table-column
        min-width="160"
        prop="proxyWayName"
        label="代付渠道">
        <template slot-scope="scope">
          {{scope.row.proxyWayName}}<br>
          {{scope.row.proxyWayCode}}
        </template>
      </el-table-column>
      <el-table-column
        min-width="130"
        prop="payRate"
        label="费率(工作日|周末)"
        align="center">
        <template slot-scope="scope">
          {{scope.row.payRate}} | {{scope.row.weekendPayRate}}({{rateType[scope.row.rateType]}})
        </template>
      </el-table-column>
      <el-table-column
        width="80"
        prop="proxyRate"
        label="代付费率"
        align="center">
        <template slot-scope="scope">
          {{scope.row.proxyRate}}({{rateType[scope.row.proxyRateType]}})
        </template>
      </el-table-column>
      <el-table-column
        width="90"
        prop="lowestRate"
        label="最低手续费"
        align="center">
        <template slot-scope="scope">
          {{scope.row.lowestRate}}(元)
        </template>
      </el-table-column>
      <el-table-column
        min-width="200"
        prop="payMax"
        label="支付限制"
        align="center">
        <template slot-scope="scope">
          <span>{{scope.row.payMin}}[元] -- {{scope.row.payMax}}[元]</span>
          <div v-if="scope.row.payBeginTime">{{scope.row.payBeginTime}} -- {{scope.row.payEndTime}}</div>
        </template>
      </el-table-column>
      <el-table-column
        min-width="200"
        prop="payMax"
        label="代付限制"
        align="center">
        <template slot-scope="scope">
          <span>{{scope.row.payMin}}[元] -- {{scope.row.payMax}}[元]</span>
          <div v-if="scope.row.proxyPayBeginTime">{{scope.row.proxyPayBeginTime}} -- {{scope.row.proxyPayEndTime}}</div>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="180">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="primary" @click="handleAddRule(scope.row)" size="mini">路由设置</el-button>
              <el-button v-if="scope.row.auditStatus === 'NO'" title="删除此项" type="danger" @click="handleUpdateRow(scope.row)"  icon="el-icon-delete" size="mini" circle></el-button>
            </li>
            <li>
              <el-dropdown @command="handleCommand($event, scope.row )">
                <span class="el-dropdown-link">
                  更多<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="edit">编辑</el-dropdown-item>
                  <el-dropdown-item command="del">删除</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
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
  </div>
</template>

<script>
  import * as apis from '@/api/pay'
  export default {
    name: "PayProduct",
    data() {
      return {
        map: {},
        ctrl: {
          loading: false
        },
        rateType: [],
        page: {
          pageNow: 0,
          pages: 0, //总页数
          pageNum: 20, //每页显示的个数
          totalNum: 0, //总条目数
          pageSizesArr: [20, 50, 100, 200],
          currentPage: 1 //当前页数
        },
        tableData: []
      }
    },
    mounted() {
      const view = this.$route.query
      this.$store.dispatch('GetOpts', { name: 'RateTypeEnum', type: 'obj' }).then(res => {
        this.rateType = res
      })
      this.map.payProductCode = view.pc
      this.getList(1)
    },
    methods: {
      handleSearch() {
        this.getList(1)
      },
      handleAddRow() {
        this.$router.push(`/pay/way/edit?pc=${this.map.payProductCode}`)
      },
      handleAddWay(row) {
        this.$router.push(`/pay/way/list?pc=${row.productCode}`)
      },
      handleAddRule(row) {
        console.log(row)
        this.$router.push(`/pay/rule?id=${row.id}`)
      },
      handleCommand(command, row) {
        console.log(command, row)
        if (command === 'del') {
          this.$confirm(`确定删除名称为${row.payWayName}的路由?`, '删除支付方式', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.deleteRow(row.id)
          })
        } else if (command === 'edit') {
          this.$router.push(`/pay/way/edit?pc=${this.map.payProductCode}&id=${row.id}`)
        }
      },
      deleteRow(id) {
        apis.deletePay(id).then(res => {
          console.log(res)
          this.getList(1);
        })
      },
      getList(page) {
        this.ctrl.loading = true
        apis.wayList(this.map, page, this.page.pageNum).then(res => {
          console.log(res)
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
      },
      // 修改状态
      handleStatus(command, row) {
        console.log('handleCommand ------>> ', row, command)
        const title = { NO: '关闭', YES: '开启' }

        this.$confirm(`确定${title[command]}名称为${row.payWayName}的路由?`, title[command] + '路由', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.changeStatus(row.id, command)
        }).catch(() => {
          this.getList(1)
        })
      },
      changeStatus(id, status) {
        apis.updateRuleStatus({ id, isOpenRule: status }).then(res => {
          const msg = { NO: '关闭成功', YES: '开启成功' }
          this.$message({
            type: 'success',
            message: msg[status]
          });
        }).catch(() => {
          this.getList(1)
        })
      }
    }
  }
</script>

<style scoped>

</style>
