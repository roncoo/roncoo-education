<template>
  <div class="app-container">
    <div class="filter-container">

      <el-input v-model="map.productCode" clearable class="filter-item" style="width: 200px;" placeholder="支付产品编码"></el-input>
      <el-input v-model="map.productName" clearable class="filter-item" style="width: 200px;" placeholder="支付产品名称"></el-input>

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
        prop="productName"
        label="产品名称">
      </el-table-column>
      <el-table-column
        prop="productCode"
        label="产品编号">
      </el-table-column>
      <el-table-column
        prop="editTime"
        label="修改时间"
        align="center">
      </el-table-column>
      <el-table-column
        width="80"
        prop="auditStatus"
        label="是否生效"
        align="center">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.auditStatus"
            @change="handleStatus($event, scope.row, $event)"
            active-value="YES"
            inactive-value="NO"
            active-color="#13ce66"
            inactive-color="#ff4949">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="190">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="primary" @click="handleAddWay(scope.row)" size="mini">设置支付方式</el-button>
              <el-button v-if="scope.row.auditStatus === 'NO'" title="删除此项" type="danger" @click="handleDelRow(scope.row)"  icon="el-icon-delete" size="mini" circle></el-button>
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
    <edit :visible="ctrl.dialogVisible" :data="productObj" :title="ctrl.dialogTitle" @close-callback="closeCallback"></edit>
  </div>
</template>

<script>
  import * as apis from '@/api/pay'
  import Edit from './edit'
  export default {
    name: "PayProduct",
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
        productObj: {},
        tableData: []
      }
    },
    mounted() {
      this.getList(1)
    },
    methods: {
      handleAddRow() {
        this.ctrl.dialogVisible = true;
      },
      handleAddWay(row) {
        console.log(row)
        this.$router.push(`/pay/way/list?pc=${row.productCode}`)
      },
      handleSearch() {
        this.getList(1)
      },
      closeCallback() {
        this.ctrl.dialogVisible = false;
      },
      // 删除支付产品
      handleDelRow(row) {
        this.$confirm(`确定删除:${row.productName}?`, '删除支付产品', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          apis.deleteProduct({ productCode: row.productCode }).then(res => {
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
      // 审核支付产品
      handleStatus(command, row) {
        console.log('handleCommand ------>> ', row, command)
        const title = { NO: '关闭', YES: '开启' }
        this.$confirm(`确定${title[command]}：${row.productName}?`, title[command] + '支付产品', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          apis.auditProduct({ productCode: row.productCode, auditStatus: command }).then(res => {
            this.$message({
              type: 'success',
              message: title[command] + '成功'
            });
          }).catch(() => {
            row.auditStatus = row.auditStatu === 'YES' ? 'NO' : 'YES'
          })
        }).catch(() => {
          this.getList(1)
        })
      },
      getList(page) {
        this.ctrl.loading = true
        apis.productList(this.map, page, this.page.pageNum).then(res => {
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
      }
    }
  }
</script>

<style scoped>

</style>
