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
      <el-form-item label="状态:" >
        <el-select v-model="params.statusId" class="auto-width" clearable filterable placeholder="状态" style="width: 85px">
          <el-option
            v-for="item in opts.statusIdList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="注册时间">
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
      <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="40">
        </el-table-column>
        <el-table-column prop="userNo" label="用户编号">
        </el-table-column>
        <el-table-column prop="mobile" label="用户手机">
        </el-table-column>
        <el-table-column label="用户类型"  width="120">
          <template slot-scope="scope">
            <span :class="textClass(scope.row.userType)">{{textuUserType[scope.row.userType]}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="nickname" label="昵称">
        </el-table-column>
        <el-table-column label="状态" width="120">
          <template slot-scope="scope">
            <span :class="textClass(scope.row.statusId)">{{textuStatusId[scope.row.statusId]}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="gmtCreate" label="注册时间">
        </el-table-column>
        <el-table-column
        fixed="right"
        label="操作"
        width="200">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="primary" @click="handleEdit(scope.row)" size="mini">修改</el-button>
              <el-button @click.native="changeStatus(scope.row)" size="mini" type="danger" class="mgl10" v-if="scope.row.status !== '1'">启用</el-button>
            <el-button @click.native="changeStatus(scope.row)" size="mini" type="primary" class="mgl10" v-else>禁用</el-button>
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
      <edit :visible="ctrl.dialogVisible" :formData="formdata" :title="ctrl.dialogTitle" @close-cllback="closeCllback"></edit>
  </div>
</template>
<script>
  import * as userApi from '@/api/user'
  import Edit from './edit'
  export default {
    components: { Edit },
    data() {
      return {
        params: {
        },
        ctrl: {
          load: false,
          remoteAuthorLoading: false,
          dialogVisible: false
        },
        list: [],
        opts: {
          statusIdList: []
        },
        page: {
          beginPageIndex: 1,
          pageCurrent: 1,
          endPageIndex: 8,
          pageSize: 20,
          totalCount: 0,
          totalPage: 0
        },
        textuUserType: {
          1: '用户',
          2: '讲师'
        },
        textuStatusId: {
          0: '禁用',
          1: '正常'
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
        formdata: {},
        gmtCreate: ''
      }
    },
    mounted() {
      this.$store.dispatch('GetOpts', { enumName: "StatusIdEnum", type: 'arr' }).then(res => {
        this.opts.statusIdList = res
      })
      this.userExtList(1)
    },
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.userExtList()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
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
        this.page.pageCurrent = 1
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
        userApi.userExtList(this.params, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.loading = false
        }).catch(() => {
          this.ctrl.loading = false
        })
      },
      textClass(userType) {
        return {
          c_red: userType === 0,
          c_blue: userType === 2
        }
      },
      // 关闭编辑弹窗回调
      closeCllback() {
        this.ctrl.dialogVisible = false
      },
      handleEdit(row) {
        this.formdata = row
        this.ctrl.dialogTitle = '编辑'
        this.ctrl.dialogVisible = true
      }
    }
  }
</script>
