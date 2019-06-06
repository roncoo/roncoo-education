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
        <el-table-column
          label="状态"
          prop="statusId"
          align="center"
          width="200">
          <template slot-scope="scope">
            <el-switch
              @change="handleChangeStatus(scope.row, $event)"
              v-model="scope.row.statusId"
              :inactive-value="1"
              :active-value="0"
              inactive-text="正常"
              active-text="禁用">
            </el-switch>
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
      // 查询条件
       handleCheck() {
        this.page.pageCurrent = 1
        this.userExtList()
      },
      // 重置查询条件
      handleReset() {
        this.params = {}
        this.userExtList()
      },
      // 分页列出用户信息
      userExtList() {
        this.ctrl.loading = true
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
        this.ctrl.dialogVisible = false;
        this.ctrl.viewDialogVisible = false;
        this.reload()
      },
      // 跳修改页面操作
      handleEdit(row) {
        this.formdata = row
        this.ctrl.dialogTitle = '编辑'
        this.ctrl.dialogVisible = true
      },
      // 修改状态
      handleChangeStatus(row, command) {
        const title = { 0: '禁用', 1: '启用' }
        this.$confirm(`确定${title[command]},名称为${row.mobile}?`, title[command] + '此项', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.changeStatus(row, command)
          this.reload()
        }).catch(() => {
          this.reload()
        })
      },
      // 请求更新用户方法
      changeStatus(row, command) {
        this.params.id === row.id
        this.params.statusId === command
        userApi.userExtUpdate({ id: row.id, statusId: command }).then(() => {
          const msg = { 0: '禁用成功', 1: '启用成功' }
          this.$message({
            type: 'success',
            message: msg[command]
          });
            this.reload()
        })
      },
      // 刷新当前页面
      reload() {
        this.userExtList()
      }
    }
  }
</script>
