<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
      <el-form-item label="讲师名称">
        <el-input v-model="map.lecturerName"></el-input>
      </el-form-item>
      <el-form-item label="分润状态:" >
        <el-select v-model="map.profitStatus" class="auto-width" clearable filterable placeholder="分润状态" style="width: 85px">
          <el-option
            v-for="item in profitStatusList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="时间">
        <el-date-picker
        v-model="gmtCreate"
        type="datetimerange"
        :picker-options="timeData"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        :default-time="['00:00:00', '23:59:59']"
        align="center"
        @change="changeTime">
      </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :loading="ctrl.load" @click="handleCheck">查询</el-button>
        <el-button class="filter-item" @click="handleReset">重置
        </el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table @selection-change="handleSelectionChange" v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column type="index" label="序号" width="40"></el-table-column>
        <el-table-column width="100" prop="lecturerName" label="讲师名称"></el-table-column>
        <el-table-column width="150" prop="lecturerMobile" label="讲师手机"></el-table-column>
        <el-table-column width="170" prop="bankCardNo" label="银行卡号"></el-table-column>
        <el-table-column width="150" prop="bankName" label="银行名称"></el-table-column>
        <el-table-column prop="bankUserName" label="开户名"></el-table-column>
        <el-table-column prop="lecturerProfit" label="讲师收入"></el-table-column>
        <el-table-column prop="platformProfit" label="平台收入"></el-table-column>
        <el-table-column label="审核状态">
          <template slot-scope="scope">
            <span>{{textProfitStatus[scope.row.profitStatus]}}</span>
          </template>
        </el-table-column>
        <el-table-column width="170" prop="gmtCreate" label="时间"></el-table-column>
        <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <!-- <el-button type="success" @click="handleEdit(scope.row.id)" size="mini">修改</el-button> -->
            </li>
          </ul>
        </template>
        </el-table-column>
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
  import * as userApi from '@/api/user'
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
        map: {},
        list: [],
        profitStatusList: [],
        multipleSelection: [],
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
      this.listForPage(1)
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
        const hours = date.getHours().toString().padStart(2, '0')
        const minutes = date.getMinutes().toString().padStart(2, '0')
        const seconds = date.getSeconds().toString().padStart(2, '0')
        const timeString = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`
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
        this.gmtCreate = ''
        this.listForPage()
      },
      // 分页列出用户信息
      listForPage() {
        this.ctrl.load = true
        userApi.lecturerProfitList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      handleSelectionChange(val) {
        console.log('id集合', val)
        this.multipleSelection = val
      }
    }
  }
</script>
