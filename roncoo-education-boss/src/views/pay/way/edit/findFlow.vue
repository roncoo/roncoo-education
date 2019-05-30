<template>
  <!--弹窗-->
  <el-dialog
    title="渠道分流"
    top="10vh"
    :visible.sync="visible"
    :before-close="handleClose">
    <div class="dialog">

      <el-form>
        <el-form-item label="">
          <el-input v-model="form.bankChannelCode" size="mini" clearable placeholder="银行渠道编码"></el-input>
          <el-input v-model="form.bankChannelName" size="mini" clearable placeholder="银行渠道名称"></el-input>
          <el-button @click="getList(1)" title="查找" icon="el-icon-search" size="mini">查找</el-button>
        </el-form-item>
      </el-form>

      <el-table
        size="mini"
        height="500"
        :data="tableData"
        v-loading="ctrl.loading"
        border
        style="width: 100%">
        <el-table-column
          width="220"
          prop="channelFlowName"
          label="渠道分流名称">
        </el-table-column>
        <el-table-column
          prop="channelFlowCode"
          label="渠道分流编码">
        </el-table-column>
        <el-table-column
          fixed="right"
          width="60"
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
    name: "FindFlow",
    data() {
      return {
        ctrl: {
          dialogVisible: true
        },
        form: {
          bankChannelType: 'ANOTHER_PAY'
        },
        tableData: [],

        pageNow: 0,
        pages: 0, //总页数
        pageNum: 20, //每页显示的个数
        totalNum: 0, //总条目数
        currentPage: 1 //当前页数

      }
    },
    props: {
      // route object
      visible: {
        type: Boolean,
        default: false
      },
      title: {
        type: String,
        default: ''
      }
    },
    mounted() {
      this.getList(1);
    },
    methods: {
      getList(page) {
        this.ctrl.loading = true
        this.currentPage = page
        apis.bankChannelFlowList(this.form, page, this.pageNum).then(res => {
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
        this.$emit('close-callback')
      },
      // currentPage 当前页数改变时会触发
      handleCurrentChange(page) {
        this.getList(page);
      },
      selectBank(item) {
        item.bankWayName = item.desc
        this.$emit('close-callback', item)
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
