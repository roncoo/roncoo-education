<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
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
        <el-table-column prop="courseName" label="课程名称"></el-table-column>
        <el-table-column prop="chapterName" label="章节名称"></el-table-column>
        <el-table-column prop="periodName" label="课时名称"></el-table-column>
        <el-table-column prop="gmtCreate" label="时间" width="170"></el-table-column>
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
  import * as api from '@/api/course'
  export default {
    data() {
      return {
        ctrl: {
          dialogVisible: false,
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
        map: {},
        list: [],
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
      this.map.userExtNo = this.$route.query.userNo
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
          this.map.beginGmtCreate = this.dateToString(this.gmtCreate[0])
          this.map.beginGmtCreate = this.dateToString(this.gmtCreate[1])
        } else {
          this.map.beginGmtCreate = ''
          this.map.beginGmtCreate = ''
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
        this.map.userExtNo = this.$route.query.userNo
        this.gmtCreate = ''
        this.listForPage()
      },
      // 分页列出用户学习日志信息
      listForPage() {
        this.ctrl.load = true
        api.studyLoglist(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
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
