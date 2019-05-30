<template>
  <div class="app-container">
    <div class="filter-container">

      <el-input v-model="map.channelFlowCode" clearable class="filter-item" style="width: 200px;" placeholder="渠道分流编码"></el-input>
      <el-select v-model="map.payWayCode" clearable filterable placeholder="支付方式">
        <el-option
          v-for="item in opts.payWay"
          :key="item.name"
          :label="item.desc"
          :value="item.name">
        </el-option>
      </el-select>
      <el-select v-model="map.payTypeCode" clearable filterable placeholder="支付类型">
        <el-option
          v-for="item in opts.payType"
          :key="item.name"
          :label="item.desc"
          :value="item.name">
        </el-option>
      </el-select>
      <el-input v-model="map.masterBankChannelName" class="filter-item" :readonly="true" style="width: 240px;" placeholder="主渠道">
        <el-button slot="append" icon="el-icon-search" @click="ctrl.findMaster = true"></el-button>
      </el-input>

      <div style="float: right">
        <el-button class="filter-item" type="primary" @click="handleSearch">
          搜索
        </el-button>

        <el-button class="filter-item" @click="handleReset">
          重置
        </el-button>

        <el-button class="filter-item" type="success" @click="handleAddRow">
          新增
        </el-button>
      </div>

      <!--<el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download"-->
      <!--@click="handleDownload">-->
      <!--导出-->
      <!--</el-button>-->
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
        prop="bankWayCode"
        label="渠道分流（名称/编码）">
        <template slot-scope="scope">
          {{scope.row.channelFlowName}}
          <br>
          {{scope.row.channelFlowCode}}
        </template>
      </el-table-column>
      <el-table-column
        prop="bankWayName"
        width="170"
        label="支付方式（名称/编码）">
        <template slot-scope="scope">
          {{scope.row.payWayName}}
          <br>
          {{scope.row.payWayCode}}
        </template>
      </el-table-column>
      <el-table-column
        prop="bankChannelCode"
        label="支付类型（名称/编码）"
        align="left">
        <template slot-scope="scope">
          {{scope.row.payTypeName}}
          <br>
          {{scope.row.payTypeCode}}
        </template>
      </el-table-column>
      <el-table-column
        prop="bankChannelName"
        label="主渠道（名称/编码）"
        align="left">
        <template slot-scope="scope">
          {{scope.row.masterBankChannelName}}
          <br>
          {{scope.row.masterBankChannelCode}}
        </template>
      </el-table-column>
      <el-table-column
        prop="bankChannelName"
        label="从渠道（名称/编码）">
        <template slot-scope="scope">
          {{scope.row.slaveBankChannelName}}
          <br>
          {{scope.row.slaveBankChannelCode}}
        </template>
      </el-table-column>
      <el-table-column
        label="备注"
        prop="remark"
        align="center"
        width="140">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="90">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="primary" @click="handleUpdateRow(scope.row)" size="mini">编辑</el-button>
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
      :current-page="currentPage"
      :page-sizes="pageSizesArr"
      :page-size="pageNum"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalNum">
    </el-pagination>
    <edit :visible="ctrl.dialogVisible" :title="ctrl.editTitle" @close-callback="editCallback"></edit>
    <master-channel :visible="ctrl.findMaster" @close-callback="masterCallback"></master-channel>
  </div>
</template>

<script>
  import * as apis from '@/api/bank'
  import Edit from './edit'
  import MasterChannel from './findChannel'

  export default {
    name: 'BankChannelList',
    components: { Edit, MasterChannel },
    data() {
      return {
        // 条件筛选参数
        map: {
          bankWayName: undefined,
          bankChannelCode: undefined,
          bankChannelName: undefined
        },
        // 下拉等预设数据
        opts: {
          payType: [],
          payWay: []
        },
        // 页面控制数据，例如形式弹窗，显示加载中等
        ctrl: {
          findMaster: false,
          editTitle: '添加渠道分流信息',
          loading: false,
          remoteAuthorLoading: false,
          dialogVisible: false
        },
        // 表单数据, 例如新增编辑子项，页面表单
        form: {},
        tableData: [],

        pageNow: 0,
        pages: 0, //总页数
        pageNum: 20, //每页显示的个数
        totalNum: 0, //总条目数
        pageSizesArr: [20, 50, 100, 200],
        currentPage: 1, //当前页数
        downloadLoading: false
      }
    },
    mounted() {
      // this.remoteAuthors()
      this.$store.dispatch('GetOpts', { name: 'PayTypeEnum' }).then(res => {
        this.opts.payType = res
      })
      this.$store.dispatch('GetOpts', { name: 'PayWayEnum' }).then(res => {
        this.opts.payWay = res
      })
      this.getList(1)
    },
    methods: {
      getList(page) {
        this.ctrl.loading = true
        this.currentPage = page
        apis.bankChannelFlowList(this.map, page, this.pageNum).then(res => {
          this.tableData = res.data.recordList;
          this.currentPage = res.data.currentPage
          this.totalNum = res.data.totalCount
          this.ctrl.loading = false
        }).catch(() => {
          this.ctrl.loading = false
        })
      },
      // 关闭编辑弹窗回调
      masterCallback(data) {
        console.log(data)
        this.ctrl.findMaster = false;
        if (data) {
          this.map.masterBankChannelCode = data.bankChannelCode
          this.map.masterBankChannelName = data.bankChannelName
        }
      },
      reload() {
        this.getList(this.currentPage)
      },
      handleSearch() {
        this.getList(1)
      },
      handleReset() {
        this.map = {}
        this.handleSearch()
      },
      handleAddRow() {
        this.ctrl.dialogVisible = true
      },
      editCallback(data) {
        this.ctrl.dialogVisible = false
      },
      handleUpdateRow(row) {
        this.ctrl.dialogVisible = true
        // this.$router.push({ path: '/bank/channel/edit', query: { id: row.id }})
      },
      // currentPage 当前页数改变时会触发
      handleCurrentChange(page) {
        this.getList(page);
      },
      // pageSize 每页条数改变时会触发
      handleSizeChange(pageSize) {
        this.pageNum = pageSize;
        this.getList(this.currentPage);
      },
      handleDownload() {
        //导出
        console.log('导出~~~')
      }
    }
  }
</script>
