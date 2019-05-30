<template>
  <div class="app-container">
    <div class="filter-container">

      <el-input v-model="map.permissionName" class="filter-item" style="width: 200px;" placeholder="请输入权限名称"></el-input>
      <el-input v-model="map.permission" class="filter-item" style="width: 200px;" placeholder="请输入权限标识"></el-input>
      <div style="float: right">
        <el-button class="filter-item" type="primary" @click="handleSearch">搜索</el-button>

        <el-button
          class="filter-item"
          type="success"
          @click="handleAddRow">
          新增
        </el-button>
      </div>

    </div>
    <el-table
      v-loading="ctrl.loading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column
        fixed
        type="index"
        width="60">
      </el-table-column>
      <el-table-column
        prop="permissionName"
        label="权限名称">
      </el-table-column>
      <el-table-column
        prop="permission"
        label="权限标识">
      </el-table-column>
      <el-table-column
        prop="remark"
        label="描述">
      </el-table-column>

      <el-table-column
        prop="createTime"
        label="创建时间">
      </el-table-column>

      <el-table-column
        fixed="right"
        label="操作"
        width="200">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdateRow(scope.row)">编辑</el-button>
              <el-button type="danger" size="mini" icon="el-icon-delete" @click="handleDeleteRow(scope.$index, scope.row, $event)">删除</el-button>
            </li>
          </ul>
        </template>
      </el-table-column>
    </el-table>
  <edit :visible="ctrl.dialogVisible" :formData="formdata" :title="ctrl.dialogTitle" @close-cllback="closeCllback"></edit>
  </div>
</template>

<script>
import * as apis from '@/api/role'
import Edit from './edit'

export default {
  components: { Edit },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      map: {},
      list: null,
      // 页面控制数据，例如形式弹窗，显示加载中等
      ctrl: {
        loading: false,
        remoteAuthorLoading: false,
        dialogVisible: false
      },
      formdata: {}
    }
  },
  created() {
    this.fetchData(1)
  },
  methods: {
    fetchData(page) {
      this.ctrl.loading = true
      apis.pmsList(page, this.map).then(res => {
        console.log(res)
        this.list = res.data.recordList
        this.ctrl.loading = false
      })
    },
    handleSearch() {
      this.fetchData(1)
    },
    handleAddRow() {
      this.formdata = {}
      this.ctrl.dialogTitle = '新增权限'
      this.ctrl.dialogVisible = true
    },
    closeCllback() {
      this.ctrl.dialogVisible = false
    },
    handleDeleteRow(index, row, command) {
      this.$confirm(`确定删除权限：${row.permissionName}?`, '删除此项', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deletePms(row.id)
      }).catch(() => {
      })
    },
    deletePms(id) {
      this.loading.show()
      apis.deletePms(id).then(res => {
        console.log(res)
        this.loading.hide()
        this.fetchData(1)
      })
    },
    handleUpdateRow(data) {
      this.formdata = data
      this.ctrl.dialogTitle = '编辑权限'
      this.ctrl.dialogVisible = true
    }
  }
}
</script>
