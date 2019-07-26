<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
      <el-form-item label="操作人">
        <el-input v-model="map.realName"></el-input>
      </el-form-item>
      <el-form-item label="时间">
        <el-date-picker
          v-model="gmtCreate"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          align="center"
          @change="changeTime">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button icon='el-icon-search' type="primary" @click="handleCheck">查询</el-button>
        <el-button icon='el-icon-refresh' class="filter-item" @click="handleReset">重置</el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="40"></el-table-column>
        <el-table-column width="100" prop="realName" label="操作人"></el-table-column>
        <el-table-column width="200" prop="ip" label="ip"></el-table-column>
        <el-table-column label="请求路径" width="300">
        <template slot-scope="scope">
        {{scope.row.operation}} 【{{scope.row.method}}】
        <br/>{{scope.row.path}}
        </template>
      </el-table-column>

        <el-table-column  prop="content" label="内容"></el-table-column>
        <el-table-column width="200" prop="gmtCreate" label="时间"></el-table-column>
      </el-table>
    </div>
      <el-pagination
        background
        style="float: right;margin-top: 20px; margin-bottom: 22px"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :page-size="page.pageSize"
        :page-sizes="[20, 50, 100, 200, 500, 1000]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.totalCount">
      </el-pagination>
  </div>
</template>
<script>
  import * as api from '@/api/sys'
  export default {
    data() {
      return {
        ctrl: {
          load: false
        },
        textProfitStatus: {
          1: '确认中',
          2: '成功',
          3: '失败'
        },
        timeData: {
          shortcuts: [{
            text: '今天',
            onClick: picker => {
              const now = this.getNow('now')
              picker.$emit('pick', now)
            }
          }, {
            text: '昨天',
            onClick: picker => {
              const now = this.getNow('before')
              picker.$emit('pick', now)
            }
          }]
        },
        formData: {},
        map: {},
        list: [],
        profitStatusList: [],
        id: [],
        gmtCreate: '',
        page: {
          beginPageIndex: 1,
          pageCurrent: 1,
          endPageIndex: 8,
          pageSize: 20,
          totalCount: 0,
          totalPage: 0
        }
      }
    },
    mounted() {
      this.$store.dispatch('GetOpts', { enumName: "ProfitStatusEnum", type: 'arr' }).then(res => {
        this.profitStatusList = res
      })
      this.listForPage()
    },
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.listForPage()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
        this.listForPage()
        // console.log(`当前页: ${val}`)
      },
      // 注册时间段查询条件
      changeTime() {
        if (this.gmtCreate !== null && this.gmtCreate.length) {
          this.map.beginDate = this.dateToString(this.gmtCreate[0])
          this.map.endDate = this.dateToString(this.gmtCreate[1])
        } else {
          this.map.beginDate = ''
          this.map.endDate = ''
        }
      },
      dateToString(date) {
        const year = date.getFullYear()
        const month = (date.getMonth() + 1).toString().padStart(2, '0')
        const day = date.getDate().toString().padStart(2, '0')
        const timeString = `${year}-${month}-${day}`
        return timeString
      },
      // 查询条件
       handleCheck() {
        this.page.pageCurrent = 1
        this.listForPage()
      },
      // 重置查询条件
      handleReset() {
        this.map = {}
        this.formData = {}
        this.gmtCreate = ''
        this.listForPage()
      },
      // 后台操作日志分页列表接口
      listForPage() {
        this.ctrl.load = true
        api.sysLogList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      }
    }
  }
</script>
