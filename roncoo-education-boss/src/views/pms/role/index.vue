<template>
  <div class="app-container">
    <div class="filter-container">

      <el-input v-model="map.roleName" class="filter-item" style="width: 200px;" placeholder="请输入角色名称"></el-input>
      <div style="float: right">
        <el-button class="filter-item" type="primary" @click="handleSearch">
          搜索
        </el-button>

        <el-button
          class="filter-item"
          type="success"
          @click="handleAddRow">
          新增
        </el-button>
      </div>

    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column align="center" label="序号" width="65">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="角色名称">
        <template slot-scope="scope">
          {{ scope.row.roleName }}
        </template>
      </el-table-column>
      <el-table-column label="角色编码" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.roleCode }}</span>
        </template>
      </el-table-column>
      <el-table-column label="描述" align="center">
        <template slot-scope="scope">
          {{ scope.row.remark }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="130">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="text" @click="handleUpdateRow(scope.row)">编辑</el-button>
            </li>
            <li>
              <el-dropdown @command="handleCommand(scope.$index, scope.row, $event)">
                <span class="el-dropdown-link">
                  更多<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="del">删除</el-dropdown-item>
                  <el-dropdown-item command="setMenu">分配菜单</el-dropdown-item>
                  <el-dropdown-item command="setPms">分配权限</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </li>
          </ul>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      style="float: right;margin-top: 20px"
      :current-page="currentPage"
      :page-sizes="pageSizesArr"
      :page-size="pageNum"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalNum">
    </el-pagination>

  <edit :visible="ctrl.dialogVisible" :formData="form" :title="ctrl.dialogTitle" @close-cllback="closeCllback"></edit>
  <menu1 :visible="ctrl.menuVisible" :obj="ctrl.data" :type="ctrl.type" @close-cllback="closeCllback"></menu1>

  </div>
</template>

<script>
  import * as apis from '@/api/role'
  import Edit from './edit'
  import Menu1 from './menu'

  export default {
    components: { Edit, Menu1 },
    data() {
      return {
        map: {
          roleName: ''
        },
        ctrl: {
          dialogVisible: false,
          dialogTitle: '添加角色',
          menuVisible: false,
          data: {},
          type: 'menu'
        },
        form: {},
        list: null,
        listLoading: true,
        menuList: null,

        pageNow: 0,
        pages: 0, //总页数
        pageNum: 20, //每页显示的个数
        totalNum: 0, //总条目数
        pageSizesArr: [20, 50, 100, 200],
        currentPage: 1, // 当前页数
        downloadLoading: false
      }
    },
    created() {
      this.getList()
    },
    methods: {
      // 角色列表
      getList() {
        this.listLoading = true
        apis.roleList(this.map).then(response => {
          console.log(response)
          this.listLoading = false
          if (response.code === 200) {
            this.list = response.data.recordList
          }
        })
      },
      // 权限列表
      getPmsList() {

      },
      handleSearch() {
        this.getList()
      },
      handleAddRow() {
        this.form = {}
        this.ctrl.dialogTitle = '添加角色';
        this.ctrl.dialogVisible = true;
      },
      handleUpdateRow(row) {
        console.log(row)
        this.form = row
        this.ctrl.dialogTitle = '编辑角色信息';
        this.ctrl.dialogVisible = true;
      },
      closeCllback() {
        this.getList()
        this.ctrl.dialogVisible = false;
        this.ctrl.menuVisible = false;
      },
      // 修改状态
      handleCommand(index, row, command) {
        console.log('handleCommand ------>> ', index, row, command)

        if (command === 'del') {
          this.$confirm(`确定删除角色：${row.roleName}?`, '删除此项', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.handleDelete(row.id)
          })
        } else if (command === 'setMenu') {
          console.log('分配菜单')
          this.ctrl.type = `menu`
          this.ctrl.menuVisible = true
          this.ctrl.data = row
        } else if (command === 'setPms') {
          this.ctrl.type = 'pms'
          this.ctrl.menuVisible = true
          this.ctrl.data = row
        }
        console.log(this.ctrl)
      },
      handleDelete(id) {
        apis.deleteRole({ id: id }).then(res => {
          if (res.code === 200) {
            this.getList()
            this.$message({
              type: 'success',
              message: '角色删除成功'
            });
          } else {
            this.$message({
              type: 'error',
              message: res.msg
            });
          }
        })
      },
      // currentPage 当前页数改变时会触发
      handleCurrentChange(page) {
        this.getList(page);
      },
      // pageSize 每页条数改变时会触发
      handleSizeChange(pageSize) {
        this.pageNum = pageSize;
        this.getList(this.currentPage);
      }
    }
  }
</script>
