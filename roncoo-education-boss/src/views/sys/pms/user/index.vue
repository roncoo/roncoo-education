<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
      <el-form-item label="手机号">
        <el-input v-model="map.mobile"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon='el-icon-search' type="primary" @click="handleCheck">查询</el-button>
        <el-button icon='el-icon-refresh' class="filter-item" @click="handleReset">重置</el-button>
        <el-button v-has="'/system/pc/sys/user/add'" type="primary" perms="sys:user:add" icon="el-icon-circle-plus-outline" size="mini" @click="handleAdd()">添加</el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="40">
        </el-table-column>
        <el-table-column prop="mobile" label="手机号" width="170">
        </el-table-column>
        <el-table-column prop="realName" label="名称" width="250">
        </el-table-column>
        <el-table-column prop="sort" width="100" label="排序">
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
        <el-table-column prop="remark" label="备注" width="220">
        </el-table-column>
        <el-table-column
        fixed="right"
        label="操作">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="danger" @click="handleDelete(scope.row.id)" size="mini">删除</el-button>
              <el-button v-has="'/system/pc/sys/user/view'" type="success" @click="handleEdit(scope.row.id)" size="mini">修改</el-button>
              <el-button v-has="'/system/pc/sys/role/list'" type="primary" @click="handleUserRole(scope.row.id, scope.row.realName)" size="mini">设置角色</el-button>
              <el-button v-has="'/system/pc/sys/user/password'" type="success" @click="handlePassword(scope.row.userNo, scope.row.realName)" size="mini">密码修改</el-button>
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
      <add :visible="ctrl.addDialogVisible" :title="ctrl.dialogTitle" @close-callback="closeCallback"></add>
      <edit :visible="ctrl.dialogVisible" :formData="formData" :title="ctrl.dialogTitle" @close-callback="closeCallback"></edit>
      <password :visible="ctrl.passwordDialogVisible" :formData="formData" :title="ctrl.dialogTitle" @close-callback="closeCallback"></password>
      <role :visible="ctrl.serRoleDialogVisible" :userId="id" :title="ctrl.dialogTitle" @close-callback="closeCallback"></role>
  </div>
</template>
<script>
  import * as api from '@/api/sys'
  import Edit from './edit'
  import Add from './add'
  import Password from './password'
  import Role from './role'
  export default {
    components: { Edit, Add, Password, Role },
    data() {
      return {
        map: {},
        formData: {},
        list: [],
        id: '',
        ctrl: {
          load: false,
          dialogVisible: false,
          addDialogVisible: false,
          passwordDialogVisible: false,
          viewVisible: false,
          serRoleDialogVisible: false
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
      this.adminUserList(1)
    },
    methods: {
      // 添加管理员
      handleAdd() {
        this.ctrl.addDialogVisible = true
        this.dialogTitle = '添加'
      },
      // 跳修改弹窗页面
      handleEdit(id) {
        this.ctrl.load = true
        api.userView({ id: id }).then(res => {
          this.formData = res.data
          this.ctrl.dialogVisible = true
          this.ctrl.dialogTitle = res.data.realName + ' —— 信息编辑'
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      // 跳出修改密码弹窗
      handlePassword(userNo, realName) {
        this.formData.adminUserNo = userNo
        this.ctrl.passwordDialogVisible = true
        this.ctrl.dialogTitle = realName + ' —— 密码修改'
      },
      // 跳出设置角色弹窗
      handleUserRole(id, realName) {
        this.id = id
        this.ctrl.serRoleDialogVisible = true
        this.ctrl.dialogTitle = realName + ' —— 设置角色'
      },
      // 关闭弹窗回调
      closeCallback() {
        this.ctrl.addDialogVisible = false
        this.ctrl.dialogVisible = false
        this.ctrl.passwordDialogVisible = false
        this.ctrl.serRoleDialogVisible = false
        this.id = ''
        this.reload()
      },
      // 删除
      handleDelete(id) {
        this.$confirm(`确定要删除吗?`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.ctrl.load = true
          api.userDelete({ id: id }).then(res => {
            this.ctrl.load = false
          if (res.code === 200 && res.data > 0) {
            this.$message({
              type: 'success',
              message: "删除成功"
            });
              this.reload()
          } else {
            this.$message({
              type: 'error',
              message: "删除失败"
            });
          }
        }).catch(() => {
          this.ctrl.load = false
        })
        })
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
        api.userUpdate({ id: id, statusId: statusId }).then(res => {
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
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.adminUserList()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
        this.adminUserList()
        // console.log(`当前页: ${val}`)
      },
      // 查询条件
       handleCheck() {
        this.page.pageCurrent = 1
        this.adminUserList()
      },
      // 重置查询条件
      handleReset() {
        this.reload()
      },
      // 后台管理员分页列表接口
      adminUserList() {
        this.ctrl.load = true
        api.sysUserList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      // 刷新当前页面
      reload() {
        this.map = {}
        this.formData = {}
        this.gmtCreate = ''
        this.adminUserList()
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
