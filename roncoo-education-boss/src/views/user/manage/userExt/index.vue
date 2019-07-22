<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
      <el-form-item label="用户手机">
        <el-input v-model="map.mobile"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="map.nickname"></el-input>
      </el-form-item>
      <el-form-item label="状态:" >
        <el-select v-model="map.statusId" class="auto-width" clearable filterable placeholder="状态" style="width: 85px">
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
        <el-table-column type="index" label="序号" width="40">
        </el-table-column>
        <el-table-column prop="userNo" label="用户编号">
        </el-table-column>
        <el-table-column label="用户手机">
           <template slot-scope="scope">
            <el-button type="text" @click="handleView(scope.row.id)">{{scope.row.mobile}}</el-button>
          </template>
        </el-table-column>
        <el-table-column label="用户类型"  width="120">
          <template slot-scope="scope">
            <span :class="textClass(scope.row.userType)">{{textuUserType[scope.row.userType]}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="nickname" label="昵称">
        </el-table-column>
        <el-table-column
          width="150"
          prop="statusId"
          label="状态"
          align="center">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.statusId"
              @change="handleChangeStatus(scope.row.id, scope.row.statusId)"
              :active-value="0"
              :inactive-value="1"
              active-color="#ff4949"
              inactive-color="#13ce66"
              active-text="禁用"
              inactive-text="正常">
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
              <el-button type="success" @click="handleEdit(scope.row)" size="mini">编辑</el-button>
              <el-button type="success" @click="handleStudy(scope.row.userNo)" size="mini">学习记录</el-button>
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
      <edit :visible="ctrl.dialogVisible" :formData="formData" :title="ctrl.dialogTitle" @close-callback="closeCallback"></edit>
      <view-user :visible="ctrl.viewVisible" :formData="formData" @close-callback="closeCallback"></view-user>
      <study :visible="ctrl.studyVisible" :userNo="userNo" :title="ctrl.dialogTitle" @close-callback="closeCallback"></study>
  </div>
</template>
<script>
  import * as userApi from '@/api/user'
  import Edit from './edit'
  import viewUser from './view'
  import study from './listStudy'
  export default {
    components: { Edit, viewUser, study },
    data() {
      return {
        list: [],
        map: {},
        formData: {},
        gmtCreate: '',
        userNo: '',
        ctrl: {
          load: false,
          remoteAuthorLoading: false,
          dialogVisible: false,
          viewVisible: false,
          studyVisible: false
        },
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
        }
      }
    },
    mounted() {
      this.$store.dispatch('GetOpts', { enumName: "StatusIdEnum", type: 'arr' }).then(res => {
        this.opts.statusIdList = res
      })
      this.userExtList(1)
    },
    methods: {
      handleStudy(row) {
        this.userNo = row
        this.ctrl.studyVisible = true
        this.ctrl.dialogTitle = '学习记录'
      },
      // 跳修改弹窗页面
      handleEdit(row) {
        this.formData = row
        this.ctrl.dialogTitle = '编辑'
        this.ctrl.dialogVisible = true
      },
      // 查看弹窗
      handleView(id) {
        this.ctrl.load = true
        userApi.userExtView({ id: id }).then(res => {
          this.formData = res.data
          this.ctrl.load = false
        }).catch(() => {
            this.ctrl.load = false
          })
        this.ctrl.viewVisible = true
      },
      // 关闭弹窗回调
      closeCallback() {
        this.formData = {}
        this.ctrl.dialogVisible = false
        this.ctrl.viewVisible = false
        this.ctrl.studyVisible = false
        this.reload()
      },
      // 修改状态
      handleChangeStatus(id, statusId) {
        const title = { 0: '禁用', 1: '启用' }
        this.$confirm(`确定要${title[statusId]}吗?`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.ctrl.load = true
          this.changeStatus(id, statusId)
          this.reload()
        }).catch(() => {
          this.reload()
        })
      },
      // 请求更新用户方法
      changeStatus(id, statusId) {
        userApi.userExtUpdate({ id: id, statusId: statusId }).then(res => {
          this.ctrl.load = false
          if (res.code === 200 && res.data > 0) {
            const msg = { 0: '禁用成功', 1: '启用成功' }
            this.$message({
              type: 'success',
              message: msg[statusId]
            });
              this.reload()
          } else {
            const msg = { 0: '禁用失败', 1: '启用失败' }
            this.$message({
              type: 'error',
              message: msg[statusId]
            });
              this.reload()
          }
        })
      },
      // 注册时间段查询条件
      changeTime() {
        if (this.gmtCreate !== null && this.gmtCreate.length) {
          this.map.beginGmtCreate = this.dateToString(this.gmtCreate[0])
          this.map.endGmtCreate = this.dateToString(this.gmtCreate[1])
        } else {
          this.map.beginGmtCreate = ''
          this.map.endGmtCreate = ''
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
        this.userExtList()
      },
      // 重置查询条件
      handleReset() {
        this.reload()
      },
       // 刷新当前页面
      reload() {
        this.map = {}
        this.gmtCreate = ''
        this.userExtList()
      },
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
      // 分页列出用户信息
      userExtList() {
        this.ctrl.load = true
        userApi.userExtList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      textClass(userType) {
        return {
          c_red: userType === 0,
          c_blue: userType === 2
        }
      }
    }
  }
</script>
