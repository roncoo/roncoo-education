<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
        <el-form-item label="分类名称：">
          <el-input v-model.trim="map.categoryName"></el-input>
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
        <el-form-item>
          <el-button icon='el-icon-search' type="primary" @click="handleCheck">查询</el-button>
        <el-button icon='el-icon-refresh' class="filter-item" @click="handleReset">重置</el-button>
          <el-button v-has="'/course/pc/course/category/add'" type="primary" size="mini" icon="el-icon-circle-plus-outline" @click="addSubMmenu(0, 1)">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table
      :data="tableData"
      style="width: 100%"
      v-loading="ctrl.load"
      border
      row-key="id"
      :default-expand-all="false">
        <el-table-column
        type="index"
        label="序号"
        width="50">
        </el-table-column>
        <el-table-column
          prop="categoryName"
          label="分类名称"
          sortable
          width="180">
        </el-table-column>
        <el-table-column
          prop="remark"
          label="备注">
        </el-table-column>
        <el-table-column
        width="160"
        prop="statusId"
        label="状态"
        align="center">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.statusId"
            @change="handleChangeStatusId(scope.row.id, scope.row.statusId)"
            :active-value="0"
            :inactive-value="1"
            active-color="#ff4949"
            inactive-color="#13ce66"
            active-text="禁用"
            inactive-text="正常">
          </el-switch>
        </template>
      </el-table-column>
        <el-table-column
          prop="sort"
          label="排序">
        </el-table-column>
        <el-table-column label="操作" width="240">
          <template slot-scope="scope">
            <el-button v-has="'/course/pc/course/category/add'" type="primary" size="mini" icon="el-icon-circle-plus-outline" @click="addSubMmenu(scope.row.id, scope.row.floor)">添加</el-button>
            <el-button v-has="'/course/pc/course/category/delete'" type="danger" @click="handleDelete(scope.row.id)" size="mini">删除</el-button>
            <el-button v-has="'/course/pc/course/category/view'" type="success" @click="editSubMmenu(scope.row)" size="mini">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
       <add :visible="ctrl.addDialogVisible" :formData="formData" :title="ctrl.dialogTitle" @close-callback="closeCllback"></add>
      <edit :visible="ctrl.editDialogVisible" :formData="formData" :title="ctrl.dialogTitle" @close-callback="closeCllback"></edit>
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
        map: {},
        formData: {},
        tableData: [],
        expands: [],
        page: {
          beginPageIndex: 1,
          pageCurrent: 1,
          endPageIndex: 8,
          pageSize: 20,
          totalCount: 0,
          totalPage: 0
        },
        ctrl: {
          load: false,
          addDialogVisible: false
        },
        opts: {
          statusIdList: []
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
      setTimeout(() => {
        this.categoryList()
      }, 200)
      this.expands = [];
    },
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.categoryList()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
        this.categoryList()
        // console.log(`当前页: ${val}`)
      },
      // 查询条件
       handleCheck() {
        this.categoryList()
      },
      // 重置查询条件
      handleReset() {
        this.reload()
      },
      // 修改状态
      handleChangeStatusId(id, statusId) {
        const title = { 0: '正常', 1: '禁用' }
        this.$confirm(`确定要${title[statusId]}吗?`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.changeStatusId(id, statusId)
          this.reload()
        }).catch(() => {
          this.reload()
        })
      },
      // 请求更新状态方法
      changeStatusId(id, statusId) {
        this.ctrl.load = true
        api.categoryUpdate({ id: id, statusId: statusId }).then(res => {
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
            this.reload()
            this.ctrl.load = false
          })
      },
      // 课程分类分页列表接口
      categoryList() {
        this.ctrl.load = true
        api.categoryList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.tableData = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      addSubMmenu(id, floor) {
        this.formData.parentId = id
        this.formData.floor = floor
        this.ctrl.dialogTitle = "添加"
        this.ctrl.addDialogVisible = true
      },
      editSubMmenu(row) {
        this.getCategory(row.id)
        this.ctrl.editDialogVisible = true
      },
      getCategory(id) {
        this.ctrl.load = true
        api.categoryView({ id: id }).then(res => {
          this.formData = res.data
          this.ctrl.dialogTitle = res.data.categoryName + " —— 修改"
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      // 关闭弹窗回调
      closeCllback() {
        this.ctrl.addDialogVisible = false
        this.ctrl.editDialogVisible = false
        this.reload()
      },
      // 删除
      handleDelete(id) {
        this.$confirm(`确定要删除吗?`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          api.categoryDelete({ id: id }).then(res => {
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
        }).catch(() => {
          this.ctrl.load = false
        })
        }).catch(() => {
          this.reload()
        })
      },
      // 刷新当前页面
      reload() {
        this.map = {}
        this.formData = {}
        this.categoryList()
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
