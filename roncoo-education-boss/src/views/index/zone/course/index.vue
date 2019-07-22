<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
        <el-form-item>
        </el-form-item>
      <el-form-item label="专区名称">
        <el-input v-model="map.courseName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon='el-icon-search' type="primary" @click="handleCheck">查询</el-button>
        <el-button icon='el-icon-refresh' class="filter-item" @click="handleReset">重置</el-button>
        <el-button type="primary" icon="el-icon-circle-plus-outline" size="mini" @click="handleAddRow()">添加</el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table v-loading="ctrl.loading" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="40">
        </el-table-column>
        <el-table-column prop="courseName" label="课程名称" width="250">
        </el-table-column>
        <el-table-column label="所属分类" width="200">
        <template slot-scope="scope">
          {{scope.row.categoryName1}}/{{scope.row.categoryName2}}/{{scope.row.categoryName3}}
          </template>
        </el-table-column>
        <el-table-column label="展示平台" width="100">
          <template slot-scope="scope">
          <span>{{textZoneLocation[scope.row.zoneLocation]}}</span>
        </template>
        </el-table-column>
        <el-table-column
          width="170"
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
        <el-table-column prop="sort" label="排序" width="100">
        </el-table-column>
        <el-table-column prop="gmtCreate" label="添加时间">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="250">
          <template slot-scope="scope">
            <ul class="list-item-actions">
              <li>
                <el-button type="danger" @click="handleDelRow(scope.row)" size="mini">删除</el-button>
                <el-button type="primary" @click="handleUpdateRow(scope.row)" size="mini">排序</el-button>
              </li>
            </ul>
          </template>
        </el-table-column>
      </el-table>
      <div class="mgt20">
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
        <add :visible="ctrl.addDialogVisible" :zoneId="zoneId" :title="ctrl.dialogTitle" @close-callback="closeCallback"></add>
        <edit :visible="ctrl.editDialogVisible" :formData="formData" :title="ctrl.dialogTitle" @close-callback="closeCallback"></edit>
      </div>
    </div>
  </div>
</template>
<script>
  import * as api from '@/api/course'
  import Add from './add'
  import Edit from './edit'
  export default {
    components: { Add, Edit },
    data() {
      return {
        ctrl: {
          loading: false,
          addDialogVisible: false,
          editDialogVisible: false
        },
        map: {},
        formData: {},
        // 表单数据, 例如新增编辑子项，页面表单
        zoneId: '',
        list: [],
        textZoneLocation: {
          0: 'PC端',
          1: '移动端'
        },
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
      this.map.zoneId = this.$route.query.zoneId
      this.getList(this.map)
    },
    methods: {
      handleChangeStatus(id, statusId) {
        const title = { 0: '禁用', 1: '启用' }
        this.$confirm(`确定要${title[statusId]}吗?`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.ctrl.loading = true
          this.changeStatus(id, statusId)
        }).catch(() => {
          this.reload()
        })
      },
      //改变状态
      changeStatus(id, statusId) {
        api.zoneCourseUpdate({ id, statusId }).then(res => {
          this.ctrl.loading = false
          const msg = { 0: '禁用成功', 1: '启用成功' }
          this.$message({
            type: 'success',
            message: msg[statusId]
          });
          this.reload()
        })
      },
      //新增
      handleAddRow(zoneId) {
        this.zoneId = this.$route.query.zoneId
        this.ctrl.dialogTitle = '新增'
        this.ctrl.addDialogVisible = true
      },
      //删除
      handleDelRow(data) {
        this.$confirm(`确定删除这条数据?`, '我要删除', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.map = {
            id: data.id
          }
          this.ctrl.loading = true
          api.zoneCourseDelete(this.map).then(res => {
            this.ctrl.loading = false
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
                this.reload()
            }
          })
        }).catch(() => {
        })
      },
      // 关闭编辑弹窗回调
      closeCallback() {
        this.ctrl.dialogVisible = false
        this.ctrl.addDialogVisible = false
        this.ctrl.editDialogVisible = false
        this.reload()
      },
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.map.pageSize = val
        this.getList()
      },
      handleCurrentChange(val) {
        this.map.pageCurrent = val
        this.getList()
        // console.log(`当前页: ${val}`)
      },
      // 查询
       handleCheck() {
        this.map.pageNum = 1
        this.getList()
      },
      // 重置查询条件
      handleReset() {
        this.reload()
      },
      reload() {
        this.formData = {}
        this.map = {}
        this.map.zoneId = this.$route.query.zoneId
        this.getList()
      },
      //编辑
      handleUpdateRow(data) {
       this.formData = data
       this.ctrl.dialogTitle = data.courseName + '——编辑'
       this.ctrl.editDialogVisible = true
      },
      getList() {
        this.ctrl.loading = true
        api.zoneCourselist(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.page = res.data
          this.page.numPerPage = res.data.pageSize
          this.list = res.data.list
          this.ctrl.loading = false
        }).catch(() => {
          this.ctrl.loading = false
        })
      }
    }
  }
</script>
