<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
      <el-form-item label="讲师名称">
        <el-input v-model="map.lecturerName"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="map.lecturerMobile"></el-input>
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
      <el-form-item label="审核情况:" >
        <el-select v-model="map.auditStatus" class="auto-width" clearable filterable placeholder="审核情况" style="width: 85px">
          <el-option
            v-for="item in opts.auditStatusList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :loading="ctrl.load" @click="handleCheck">查询</el-button>
        <el-button class="filter-item" @click="handleReset">重置</el-button>
        <el-button type="primary" size="mini" icon="el-icon-circle-plus-outline" @click.native="add(false)">添加讲师</el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="40">
        </el-table-column>
        <el-table-column label="手机号">
           <template slot-scope="scope">
            <el-button type="text" @click="handleView(scope.row.id)">{{scope.row.lecturerMobile}}</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="lecturerName" label="讲师名称">
        </el-table-column>
        <el-table-column prop="lecturerEmail" label="邮箱">
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
        <el-table-column label="分成比例">
          <template slot-scope="scope">
             [ 讲师: {{scope.row.lecturerProportion*100}}%]
          </template>
        </el-table-column>
        <el-table-column label="审核状态">
          <template slot-scope="scope">
            <span :class="textAuditStatusClass(scope.row.auditStatus)">{{textAuditStatus[scope.row.auditStatus]}}</span>
          </template>
        </el-table-column>
        <el-table-column
        fixed="right"
        label="操作"
        width="200">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="success" @click="handleEdit(scope.row.id)" size="mini">修改</el-button>
              <el-button type="primary" @click="handleAudit(scope.row)" size="mini">审核</el-button>
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
      <add :visible="ctrl.addDialogVisible" :title="ctrl.addDialogTitle" @close-cllback="addCloseCllback"></add>
      <edit :visible="ctrl.editDialogVisible" :formData="formData" :title="ctrl.editDialogTitle" @close-cllback="editCloseCllback"></edit>
      <view-lecturer :visible="ctrl.viewVisible" :formData="viewData" :title="ctrl.ViewDialogTitle" :lecturerView="lecturerView" @close-cllback="viewCloseCllback"></view-lecturer>
      <audit :visible="ctrl.auditDialogVisible" :formData="auditMap" :title="ctrl.auditDialogTitle" @close-cllback="auditCloseCllback"></audit>
    </div>
</template>
<script>
  import * as userApi from '@/api/user'
  import Add from './add'
  import Edit from './edit'
  import viewLecturer from './view'
  import Audit from './audit'
  export default {
    components: { Add, Edit, viewLecturer, Audit },
    data() {
      return {
        map: {},
        viewData: {},
        formData: {},
        lecturerView: {},
        auditMap: {
          id: '',
          auditStatus: 1
        },
        ctrl: {
          load: false,
          addDialogVisible: false,
          editDialogVisible: false,
          auditDialogVisible: false,
          viewVisible: false
        },
        opts: {
          statusIdList: [],
          auditStatusList: []
        },
        list: [],
        page: {
          beginPageIndex: 1,
          pageCurrent: 1,
          endPageIndex: 8,
          pageSize: 20,
          totalCount: 0,
          totalPage: 0
        },
        textAuditStatus: {
        0: '待审核',
        1: '审核通过',
        2: '审核不通过'
      }
      }
    },
    mounted() {
      this.$store.dispatch('GetOpts', { enumName: "StatusIdEnum", type: 'arr' }).then(res => {
        this.opts.statusIdList = res
      })
      this.$store.dispatch('GetOpts', { enumName: "AuditStatusEnum", type: 'arr' }).then(res => {
        this.opts.auditStatusList = res
      })
      this.lecturerAuditList(1)
    },
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.lecturerAuditList()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
        this.lecturerAuditList()
        // console.log(`当前页: ${val}`)
      },
      // 查询条件
      handleCheck() {
        this.page.pageCurrent = 1
        this.lecturerAuditList()
      },
      // 重置查询条件
      handleReset() {
        this.map = {}
        this.lecturerAuditList()
      },
      lecturerAuditList() {
        this.load === true
        userApi.lecturerAuditList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
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
          this.changeStatus(id, statusId)
          this.reload()
        }).catch(() => {
          this.reload()
        })
      },
      // 请求更新用户方法
      changeStatus(id, statusId) {
        userApi.lecturerAuditUpdate({ id: id, statusId: statusId }).then(res => {
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
      // 跳添加讲师弹窗
      add() {
        this.ctrl.addDialogTitle = '添加'
        this.ctrl.addDialogVisible = true
      },
      // 关闭添加讲师弹窗回调
      addCloseCllback() {
        this.ctrl.addDialogVisible = false;
        this.reload()
      },
      // 修改跳页面操作
      handleEdit(id) {
        this.load === true
        userApi.lecturerAuditView({ id: id }).then(row => {
          this.formData = row.data
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
        this.ctrl.editDialogTitle = '编辑'
        this.ctrl.editDialogVisible = true
      },
      // 关闭编辑弹窗回调
      editCloseCllback() {
        this.formData = {}
        this.ctrl.editDialogVisible = false
        this.reload()
      },
      // 审核页面弹窗
      handleAudit(row) {
        this.auditMap.id = row.id
        this.ctrl.auditDialogTitle = row.lecturerMobile
        this.ctrl.auditDialogVisible = true
      },
      // 关闭编辑弹窗回调
      auditCloseCllback() {
        this.ctrl.auditDialogVisible = false
        this.reload()
      },
      // 跳查看讲师弹窗
      handleView(id) {
        this.load === true
        userApi.lecturerAuditView({ id: id }).then(res => {
          this.viewData = res.data
          if (JSON.stringify(res.data.lecturerExt) !== '{}') {
            this.lecturerView = res.data.lecturerExt
            this.ctrl.ViewDialogTitle = res.data.lecturerMobile
          }
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = true
        })
        this.ctrl.viewVisible = true
      },
      // 关闭查看讲师弹窗回调
      viewCloseCllback() {
        this.ctrl.viewVisible = false;
        this.reload()
      },
      textAuditStatusClass(auditStatus) {
        return {
          c_red: auditStatus === 2,
          c_green: auditStatus === 1,
          c_blue: auditStatus === 0
        }
      },
      // 刷新当前页面
      reload() {
        this.lecturerAuditList()
      }
    }
  }
</script>
