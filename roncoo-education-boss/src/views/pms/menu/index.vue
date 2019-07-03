<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
        <el-form-item label="菜单名称">
          <el-input v-model="map.menuName"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :loading="ctrl.load" @click="handleCheck">查询</el-button>
          <el-button class="filter-item" @click="handleReset">重置</el-button>
          <el-button type="primary" size="mini" icon="el-icon-circle-plus-outline" @click="addSubMmenu(0)">添加</el-button>
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
          prop="menuName"
          label="菜单名称"
          sortable
          width="180">
        </el-table-column>
        <el-table-column
          prop="menuIcon"
          label="菜单图标">
        </el-table-column>
        <el-table-column
          prop="menuUrl"
          label="菜单路径"
          sortable
          width="180">
        </el-table-column>
        <el-table-column
          prop="targetName"
          label="目标名称">
        </el-table-column>
        <el-table-column
          prop="remark"
          label="备注">
        </el-table-column>
        <el-table-column label="操作" width="240">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" icon="el-icon-circle-plus-outline" @click="addSubMmenu(scope.row.id)">添加</el-button>
            <el-button type="danger" @click="handleDelete(scope.row.id)" size="mini">删除</el-button>
            <el-button type="success" @click="editSubMmenu(scope.row)" size="mini">更新</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <add :visible="ctrl.addDialogVisible" :formData="formData" :title="ctrl.dialogTitle" @close-cllback="closeCllback"></add>
    <edit :visible="ctrl.editDialogVisible" :formData="formData" :title="ctrl.dialogTitle" @close-cllback="closeCllback"></edit>
  </div>
</template>
<script>
  import * as api from '@/api/system'
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
      this.menuList()
      this.expands = [];
    },
    methods: {
      // 查询条件
       handleCheck() {
        this.menuList()
      },
      // 重置查询条件
      handleReset() {
        this.reload()
      },
      // 后台管理员分页列表接口
      menuList() {
        this.ctrl.load = true
        api.menuList(this.map).then(res => {
          this.tableData = res.data.sysMenu
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      addSubMmenu(row) {
        this.formData.parentId = row
        this.ctrl.dialogTitle = "添加"
        this.ctrl.addDialogVisible = true
      },
      editSubMmenu(row) {
        this.formData = row
        this.ctrl.dialogTitle = row.menuName + "修改"
        this.ctrl.editDialogVisible = true
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
          api.menuDelete({ id: id }).then(res => {
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
          this.reload()
        })
      },
      // 刷新当前页面
      reload() {
        this.map = {}
        this.formData = {}
        this.menuList()
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
