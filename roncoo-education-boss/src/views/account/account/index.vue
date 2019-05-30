<template>
  <div class="pad20">
    <div class="filter-container">

      <el-input v-model="params.accountNo" class="filter-item" style="width: 200px;" placeholder="账户编号"></el-input>
      <el-input v-model="params.userNo" class="filter-item" style="width: 200px;" placeholder="用户编号"></el-input>
      <el-input v-model="params.userName" class="filter-item" style="width: 200px;" placeholder="用户名称"></el-input>

      <div style="float: right">
        <el-button class="filter-item" type="primary" @click="handleCheck">查询</el-button>
        <el-button class="filter-item" @click="handleReset">
          重置
        </el-button>
      </div>
    </div>
    <div>
      <el-table
        v-loading="ctrl.load"
        size="medium"
        :data="page.recordList"
        stripe
        border
        style="width: 100%">
        <el-table-column
          type="index"
          label="序号"
          width="60">
        </el-table-column>
        <el-table-column
          prop="accountNo"
          label="账户编号">
        </el-table-column>
        <el-table-column
          prop="userNo"
          label="用户编号">
        </el-table-column>
        <el-table-column
          prop="userName"
          label="用户名">
        </el-table-column>
        <el-table-column
          prop="balance"
          label="账户余额">
        </el-table-column>
        <el-table-column
          prop="settAmount"
          label="可结算余额">
        </el-table-column>
        <el-table-column
          prop="editTime"
          label="更新时间">
        </el-table-column>
        <el-table-column
          prop="address"
          width="120"
          label="操作">
           <template slot-scope="scope">
            <el-button
              @click.native.prevent="showDetail(scope.row)"
              type="text"
              size="small">
              查看账户明细
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="mgt20">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :page-size="page.numPerPage"
        :page-sizes="[20, 50, 100, 200, 500, 1000]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.totalCount">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapActions } from 'vuex'
export default {
  data() {
    return {
      ctrl: {
        load: false
      },
      page: {
        beginPageIndex: 1,
        currentPage: 1,
        endPageIndex: 8,
        numPerPage: 20,
        totalCount: 0,
        totalPage: 0,
        recordList: []
      },
      params: {
        numPerPage: 20
      }
    }
  },
  methods: {
    ...mapMutations('account', ['SET_USERNO']),
    ...mapActions('account', ['getAccountList']),
    showDetail(target) {
      // this.SET_USERNO(target.userNo)
      console.log(target)
      console.log(this.$route)
      this.$router.push({ path: '/account/detailed', query: { no: target.userNo }})
    },
    handleCheck() {
      this.params.pageNum = 1
      this.getList()
    },
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`)
      this.params.numPerPage = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.params.pageNum = val
      this.getList()
      // console.log(`当前页: ${val}`)
    },
    handleReset() {
      this.params = {}
      this.getList()
    },
    getList() {
      this.ctrl.load = true
      this.getAccountList(this.params).then(res => {
        console.log(res)
        this.ctrl.load = false
        this.page = res.data
      }).catch(() => {
        this.ctrl.load = false
      })
    }
  },
  mounted() {
    console.log(123)
    this.getList()
  }
}
</script>

<style lang="scss">
</style>
