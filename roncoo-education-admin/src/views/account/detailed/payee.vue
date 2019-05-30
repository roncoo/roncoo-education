<template>
  <el-dialog title="搜索收款人" width="60%" :visible.sync="vb" @close="resetSearch()">
    <el-form :inline="true" ref="searchForm">
      <el-form-item label="收款人">
        <el-input v-model="searchParams.receiverName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSearch" :loading="ctrl.searchLoad">搜索</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="recordList"
      size="mini"
      height="260"
      border
      v-loading="ctrl.searchLoad"
      @row-dblclick="checkSearch">
      <el-table-column type="index" label="序号"></el-table-column>
      <el-table-column property="receiverName" label="收款人"></el-table-column>
      <el-table-column property="bankName" label="银行名称"></el-table-column>
      <el-table-column property="receiverAccountNo" label="收款卡号"></el-table-column>
      <el-table-column property="phoneNo" label="手机号"></el-table-column>
      <el-table-column property="phoneNo" label="操作">
        <template slot-scope="scope" fixed="right">
          <el-button
            @click.native.prevent="checkSearch(scope.row, scope.$index)"
            type="primary"
            size="small">
            选择
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="mgt20">
      <el-pagination
        :page-size="30"
        :page-sizes="[20, 50, 100, 200, 500, 1000]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.totalCount">
      </el-pagination>
    </div>
  </el-dialog>
</template>

<script>
  import * as apis from '@/api/account'
  export default {
    name: "Payee",
    data() {
      return {
        ctrl: {},
        page: {},
        vb: false,
        recordList: [],
        searchParams: {}
      }
    },
    props: {
      // route object
      visible: {
        type: Boolean,
        default: false
      }
    },
    watch: {
      'visible': function(newValue) {
        // console.log(newValue)
        this.vb = newValue
      }
    },
    methods: {
      checkSearch(data) {
        console.log(data)
        this.$emit('close-cllback', data)
      },
      resetSearch() {
        this.$emit('close-cllback')
      },
      handleSearch() {
        apis.getDrawBank(this.searchParams).then(res => {
          if (res.code === 200) {
            this.recordList = res.data.recordList
          } else {
            this.tips(res.msg, 'error')
          }
        })
      }
    },
    mounted() {
      this.searchParams.merchantNo = this.$route.query.no
      this.handleSearch()
    }
  }
</script>

<style scoped>

</style>
