<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
      <el-form-item label="用户手机">
        <el-input v-model="params.mobile"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="params.nickname"></el-input>
      </el-form-item>
      <el-date-picker
        label="注册时间"
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
      <el-form-item>
        <el-button type="primary" :loading="ctrl.loading" @click="handleCheck">查询</el-button>
        <el-button class="filter-item" @click="handleReset">重置
        </el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table v-loading="ctrl.loading" size="medium" :data="page.list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="40">
        </el-table-column>
        <el-table-column prop="userNo" label="用户编号">
        </el-table-column>
        <el-table-column prop="mobile" label="用户手机">
        </el-table-column>
        <el-table-column prop="userType" label="用户类型">
        </el-table-column>
        <el-table-column prop="nickname" label="昵称">
        </el-table-column>
        <el-table-column prop="statusId" label="状态">
        </el-table-column>
        <el-table-column prop="gmtCreate" label="注册时间">
        </el-table-column>
         <el-table-column
        fixed="right"
        label="操作"
        width="170">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="primary" @click="handleAddWay(scope.row)" size="mini">修改</el-button>
              <el-button type="warning" @click="handleDelRow(scope.row)" size="mini">删除</el-button>
            </li>
          </ul>
        </template>
      </el-table-column>
      </el-table>
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
</div>
</template>
<script>
  import * as userApi from '@/api/user'
  export default {
    data() {
      return {
        params: {
          PerPage: 20
        },
        ctrl: {
          loading: false
        },
        page: {
          beginPageIndex: 1,
          currentPage: 1,
          endPageIndex: 8,
          numPerPage: 20,
          totalCount: 0,
          totalPage: 0,
          list: []
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
        gmtCreate: ''
      }
    },
    mounted() {
      this.userExtList(1)
    },
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.params.pageSize = val
        this.userExtList()
      },
      handleCurrentChange(val) {
        this.params.pageCurrent = val
        this.userExtList()
        // console.log(`当前页: ${val}`)
      },
      // 注册时间段查询条件
      changeTime() {
        if (this.gmtCreate.length) {
          this.params.beginGmtCreate = this.dateToString(this.gmtCreate[0])
          this.params.endGmtCreate = this.dateToString(this.gmtCreate[1])
        } else {
          this.params.beginGmtCreate = ''
          this.params.endTime = ''
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
       handleCheck() {
        this.params.pageNum = 1
        this.userExtList()
      },
      // 重置查询条件
      handleReset() {
        this.params = {}
        this.userExtList()
      },
      userExtList() {
        this.ctrl.loading = true
        console.log(this.params)
        userApi.userExtList(this.params).then(res => {
          this.page = res.data
          this.page.numPerPage = res.data.pageSize
          console.log(this.page)
          this.ctrl.loading = false
        }).catch(() => {
          this.ctrl.loading = false
        })
      }
    }
  }
</script>
