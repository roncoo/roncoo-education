<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
      <el-form-item label="标题">
        <el-input v-model="map.title"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon='el-icon-search' type="primary" @click="handleCheck">查询</el-button>
        <el-button icon='el-icon-refresh' class="filter-item" @click="handleReset">重置</el-button>
        <el-button type="primary" icon="el-icon-circle-plus-outline" size="mini" @click="handleAdd()">添加</el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="40">
        </el-table-column>
        <el-table-column prop="title" label="标题" width="200">
        </el-table-column>
        <el-table-column prop="remark" label="模板">
        </el-table-column>
        <el-table-column label="状态" width="100">
          <template slot-scope="scope">
            <span :class="textClass(scope.row.statusId)">{{textStatusId[scope.row.statusId]}}</span>
          </template>
        </el-table-column>
        <el-table-column
        fixed="right"
        label="操作"
        width="200">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="danger" @click="handleDelete(scope.row.id)" size="mini">删除</el-button>
              <el-button type="success" @click="handleEdit(scope.row)" size="mini">修改</el-button>
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
  </div>
</template>
<script>
  import * as api from '@/api/sys'
  import Edit from './edit'
  import Add from './add'
  export default {
    components: { Edit, Add },
    data() {
      return {
        map: {},
        formData: {},
        list: [],
        ctrl: {
          load: false,
          addDialogVisible: false,
          dialogVisible: false
        },
        page: {
          beginPageIndex: 1,
          pageCurrent: 1,
          endPageIndex: 8,
          pageSize: 20,
          totalCount: 0,
          totalPage: 0
        },
        textStatusId: {
          1: '正常',
          0: '禁用'
        }
      }
    },
    mounted() {
      this.templateList(1)
    },
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.templateList()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
        this.templateList()
        // console.log(`当前页: ${val}`)
      },
      // 查询条件
       handleCheck() {
        this.page.pageCurrent = 1
        this.templateList()
      },
      // 重置查询条件
      handleReset() {
        this.reload()
      },
      // 后台管理员分页列表接口
      templateList() {
        this.ctrl.load = true
        api.templateList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      // 删除
      handleDelete(id) {
        this.$confirm(`确定要删除吗?`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.ctrl.load = true
          api.templateDelete({ id: id }).then(res => {
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
              this.reload()
          }
        })
        }).catch(() => {
          this.ctrl.load = false
          this.reload()
        })
      },
      handleAdd() {
        this.ctrl.addDialogVisible = true
        this.dialogTitle = '添加'
      },
      handleEdit(id) {
        this.ctrl.load = true
        api.templateView({ id: id }).then(res => {
          this.formData = res
          this.dialogTitle = res.title + '编辑'
          this.ctrl.dialogVisible = true
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      // 关闭弹窗回调
      closeCallback() {
        this.ctrl.addDialogVisible = false
        this.ctrl.dialogVisible = false
        this.reload()
      },
      // 刷新当前页面
      reload() {
        this.map = {}
        this.formData = {}
        this.templateList()
      },
      textClass(isFree) {
        return {
          c_red: isFree === 0,
          c_blue: isFree === 1
        }
      }
    }
  }
</script>
