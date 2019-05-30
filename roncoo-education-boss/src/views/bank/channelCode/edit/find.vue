<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <div class="dialog">

      <el-form :model="formData">
        <el-form-item label="">
          <el-input v-model="form.bankWayCode" clearable @keyup="getList(1)" placeholder="银行通道编码" autocomplete="off"></el-input>
          <el-input v-model="form.desc" clearable placeholder="银行通道名称" autocomplete="off"></el-input>
          <el-button @click="getList(1)" title="查找" icon="el-icon-search" circle size="mini"></el-button>
        </el-form-item>
      </el-form>

      <el-table
        :data="tableData"
        v-loading="ctrl.loading"
        border
        style="width: 100%">
        <el-table-column
          prop="bankWayCode"
          label="银行通道编码">
        </el-table-column>

        <el-table-column
          prop="desc"
          label="银行通道名称">
        </el-table-column>
        <el-table-column
          prop="checkFileType"
          label="对账文件类型"
          align="center"
          width="150">
        </el-table-column>
        <el-table-column
          prop="isPlatSett"
          label="是否平台结算"
          align="center">
          <template slot-scope="sett">
            <span v-if="sett.row.isPlatSett">是</span>
            <span v-else type="gray">否</span>
          </template>
        </el-table-column>
        <el-table-column
          label="状态"
          prop="status"
          align="center">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="选择">
          <template slot-scope="scope">
            <el-button type="primary" @click="selectBank(scope.row)" title="选择" icon="el-icon-check" size="mini" circle></el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        background
        style="float: right;"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageNum"
        layout="total, prev, pager, next, jumper"
        :total="totalNum">
      </el-pagination>

    </div>
  </el-dialog>
</template>

<script>
  import * as apis from '@/api/bank'
  export default {
    name: "Find",
    data() {
      return {
        ctrl: {
          dialogVisible: true
        },
        form: {
          bankWayCode: undefined,
          desc: undefined
        },
        tableData: [],

        pageNow: 0,
        pages: 0, //总页数
        pageNum: 5, //每页显示的个数
        totalNum: 0, //总条目数
        currentPage: 1 //当前页数

      }
    },
    props: {
      // route object
      formData: {
        type: Object,
        default: () => {}
      },
      visible: {
        type: Boolean,
        default: false
      },
      title: {
        type: String,
        default: ''
      }
    },
    created() {
      this.getList(1);
    },
    methods: {
      getList(page) {
        this.ctrl.loading = true
        this.currentPage = page
        apis.bankWayList(this.form, page, this.pageNum).then(res => {
          // console.log(res)
          this.tableData = res.data.recordList;
          this.currentPage = res.data.currentPage
          this.totalNum = res.data.totalCount
          this.ctrl.loading = false
        }).catch(() => {
          this.ctrl.loading = false
        })
      },
      handleClose(done) {
        this.$emit('close-cllback')
      },
      // currentPage 当前页数改变时会触发
      handleCurrentChange(page) {
        this.getList(page);
      },
      selectBank(item) {
        // console.log(item)
        this.$emit('close-cllback', item)
      }
    }
  }
</script>

<style scoped>
  .dialog{
    margin-top: -20px;
    overflow: hidden;
  }
</style>
