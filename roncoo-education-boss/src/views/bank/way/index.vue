<template>
  <div class="app-container">
    <div class="filter-container">

      <el-input v-model="map.bankWayCode" clearable class="filter-item" style="width: 200px;" placeholder="银行通道编码"></el-input>
      <el-input v-model="map.desc" clearable class="filter-item" style="width: 200px;" placeholder="银行通道名称"></el-input>

      <div style="float: right">
        <el-button class="filter-item" type="primary" @click="handleSearch">
          搜索
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
        prop="desc"
        label="银行通道名称">
      </el-table-column>
      <el-table-column
        prop="bankWayCode"
        label="银行通道编码">
      </el-table-column>

      <el-table-column
        prop="checkFileType"
        label="对账文件类型"
        align="center"
        width="150">
        <template slot-scope="sett">
          <el-tag v-if="sett.row.checkFileType == 'ALL'">{{opts.fileType[sett.row.checkFileType]}}</el-tag>
          <el-tag v-else type="warning">{{opts.fileType[sett.row.checkFileType]}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="isPlatSett"
        label="是否平台结算"
        align="center"
        width="120">
        <template slot-scope="sett">
          <el-tag v-if="sett.row.isPlatSett == 'YES'">是</el-tag>
          <el-tag v-else type="warning">否</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="状态"
        prop="status"
        align="center"
        width="200">
        <template slot-scope="scope">
          <el-switch
            @change="handleChangeStatus(scope.$index, scope.row, $event)"
            v-model="scope.row.status"
            active-value="YES"
            inactive-value="NO"
            active-text="激活"
            inactive-text="冻结">
          </el-switch>
        </template>
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

    <edit :visible="ctrl.dialogVisible" :formData="form" :title="ctrl.dialogTitle" @close-cllback="closeCllback"></edit>
  </div>
</template>

<script>
  import * as apis from '@/api/bank'
  import Edit from './edit'

  export default {
    name: 'BankWayList',
    components: { Edit },
    data() {
      return {
        // 条件筛选参数
        map: {
          bankWayCode: undefined,
          desc: undefined
        },
        // 下拉等预设数据
        opts: {
          fileType: {
            SPLIT: '分开',
            ALL: '全部'
          }
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
        apis.bankWayList(this.map, page, this.pageNum).then(res => {
          console.log(res)
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
      // 修改状态
      handleChangeStatus(index, row, command) {
        console.log('handleCommand ------>> ', index, row, command)

        const txt = { NO: '冻结', YES: '激活' }

        this.$confirm(`确定${txt[command]}银行通道：${row.desc}?`, txt[command] + '此项', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.changeStatus(row.id, command)
        }).catch(() => {
          this.reload()
        })
      },
      handleAddRow() {
        this.form = {}
        this.ctrl.dialogTitle = '新增'
        this.ctrl.dialogVisible = true
      },
      changeStatus(id, status) {
        apis.changeBankWay({ id, status }).then(res => {
          const msg = { NO: '冻结成功', YES: '激活成功' }
          this.$message({
            type: 'success',
            message: msg[status]
          });
          // this.reload()
        })
      },
      handleUpdateRow(row) {
        console.log(row)
        this.form = row
        this.ctrl.dialogTitle = '编辑'
        this.ctrl.dialogVisible = true
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
