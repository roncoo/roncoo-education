<template>
  <div class="app-container">
    <div class="filter-container">

      <el-input v-model="map.bankWayCode" class="filter-item" style="width: 200px;" placeholder="银行通道编码"></el-input>
      <el-input v-model="map.bankWayName" class="filter-item" style="width: 200px;" placeholder="银行通道名称"></el-input>
      <el-input v-model="map.bankChannelCode" class="filter-item" style="width: 200px;" placeholder="银行渠道编码"></el-input>
      <el-input v-model="map.bankChannelName" class="filter-item" style="width: 200px;" placeholder="银行渠道名称"></el-input>

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
        prop="bankWayName"
        label="银行通道名称">
      </el-table-column>
      <el-table-column
        prop="bankWayCode"
        label="银行通道编码">
      </el-table-column>
      <el-table-column
        prop="desc"
        label="银行渠道名称">
      </el-table-column>
      <el-table-column
        prop="bankChannelCode"
        label="银行渠道编码">
      </el-table-column>
      <el-table-column
        label="状态"
        prop="status"
        align="center"
        width="200">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status == 'ACTIVE'">激活</el-tag>
          <el-tag v-else type="info">冻结</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="170">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="primary" @click="handleUpdateRow(scope.row)" size="mini">编辑</el-button>
              <el-button type="warning" @click="handleUpdateRow(scope.row, 'copy')" size="mini">复制</el-button>
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

  </div>
</template>

<script>
  import * as apis from '@/api/bank'

  export default {
    name: 'BankChannelCodeList',
    data() {
      return {
        // 条件筛选参数
        map: {
          id: undefined,
          bankWayCode: undefined,
          bankWayName: undefined,
          checkFileType: undefined,
          isPlatSett: undefined,
          status: undefined
        },
        // 下拉等预设数据
        opts: {
          names: [{
            label: 'xxn',
            value: 'xxn'
          },
            {
              label: 'lxn',
              value: 'lxn'
            }],
          remoteTypes: []
        },
        // 页面控制数据，例如形式弹窗，显示加载中等
        ctrl: {
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
    created() {
      // this.remoteAuthors()
      this.getList(1)
    },
    methods: {
      getList(page) {
        this.ctrl.loading = true
        this.currentPage = page
        apis.bankCodeList(this.map, page, this.pageNum).then(res => {
          this.tableData = res.data.recordList;
          this.currentPage = res.data.currentPage
          this.totalNum = res.data.totalCount
          this.ctrl.loading = false
        }).catch(() => {
          this.ctrl.loading = false
        })
      },
      // 关闭编辑弹窗回调
      closeCllback() {
        this.ctrl.dialogVisible = false;
        this.reload()
      },
      reload() {
        this.getList(this.currentPage)
      },
      handleSearch() {
        this.getList(1)
      },
      handleReset() {
        this.map = {
          time: undefined,
          name: undefined,
          type: undefined,
          author: undefined
        }
        this.handleSearch()
      },
      handleAddRow() {
        this.$router.push('/bank/channelCode/edit')
      },
      handleUpdateRow(row, ty) {
        const now = new Date();
        this.$router.push({ path: '/bank/channelCode/edit', query: { code: row.bankChannelCode, t: now.getTime(), ty: ty }})
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
