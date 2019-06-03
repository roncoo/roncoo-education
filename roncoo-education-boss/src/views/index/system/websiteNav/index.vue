<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
        <el-form-item>
          <el-button class="filter-item" type="success" @click="handleAddRow">
            新增
          </el-button>
        </el-form-item>
      <el-form-item label="分类名称">
        <el-input v-model="map.navName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :loading="ctrl.loading" @click="handleCheck">查询</el-button>
        <el-button class="filter-item" @click="handleReset">重置
        </el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table v-loading="ctrl.loading" size="medium" :data="page.list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="40">
        </el-table-column>
        <el-table-column prop="navName" label="导航名称">
        </el-table-column>
        <el-table-column prop="sort" label="排序">
        </el-table-column>
        <el-table-column
          label="状态"
          prop="statusId"
          align="center"
          width="200">
          <template slot-scope="scope">
            <el-switch
              @change="handleChangeStatus(scope.$index, scope.row, $event)"
              v-model="scope.row.statusId"
              active-value="1"
              inactive-value="0"
              active-text="启用"
              inactive-text="禁用">
            </el-switch>
          </template>
        </el-table-column>
       <el-table-column
        fixed="right"
        label="操作"
        width="340">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="primary" @click="handleUpdateRow(scope.row)" size="mini">修改</el-button>
              <el-button type="warning" @click="handleDelRow(scope.row)" size="mini">删除</el-button>
              <el-button type="warning" @click="handleAddSubclass(scope.row)" size="mini">添加</el-button>
            </li>
          </ul>
        </template>
      </el-table-column>
      </el-table>
      <div class="mgt20">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :page-size="page.numPerPage"
          :page-sizes="[20, 50, 100, 200, 500, 1000]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="page.totalCount">
        </el-pagination>
    </div>
    </div>
    <edit :visible="ctrl.dialogVisible" :formData="form" :title="ctrl.dialogTitle" @close-callback="closeCallback"></edit>
</div>
</template>
<script>
  import * as apis from '@/api/system'
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
        // 条件筛选参数
        map: {
          id: undefined,
          navName: undefined,
          statusId: undefined,
          parentId: undefined
        },
        params: {
          PerPage: 20
        },
        // 页面控制数据，例如形式弹窗，显示加载中等
        ctrl: {
          loading: false,
          remoteAuthorLoading: false,
          dialogVisible: false
        },
        // 表单数据, 例如新增编辑子项，页面表单
        form: {},
        tableData: [],
        page: {
          beginPageIndex: 1,
          currentPage: 1,
          endPageIndex: 8,
          numPerPage: 20,
          totalCount: 0,
          totalPage: 0,
          list: []
        }
      }
    },
    mounted() {
      this.getList()
    },
    methods: {
      getList() {
        this.ctrl.load = true
        apis.websiteNavList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.ctrl.load = false
          this.page.list = res.data.list
          this.page.pageSize = res.data.pageSize
          this.page.totalCount = res.data.totalCount
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.params.pageSize = val
        this.websiteNavList()
      },
      handleCurrentChange(val) {
        this.params.pageCurrent = val
        this.websiteNavList()
        // console.log(`当前页: ${val}`)
      },
       handleCheck() {
        this.params.pageNum = 1
        this.websiteNavList()
      },
      // 关闭编辑弹窗回调
      closeCallback() {
        this.ctrl.dialogVisible = false
        this.reload()
      },
      reload() {
        this.getList(this.currentPage)
      },
      //新增
      handleAddRow() {
        this.form = {}
        this.ctrl.dialogTitle = '新增'
        this.ctrl.dialogVisible = true
      },
      // 重置查询条件
      handleReset() {
        this.params = {}
        this.websiteNavList()
      },
      //删除
      handleDelRow(data) {
        console.log('handleCommand ------>> ', data)
        this.$confirm(`确定删除这条数据?`, '我要删除', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.map = {
            id: data.id
          }
          this.loading.show()
          apis.websiteNavDelete(this.map).then(res => {
            console.log(res)
            if (res.code === 200) {
              this.$message({
                type: 'success',
                message: '删除成功'
              });
              //刪除成功后刷新列表
              this.closeCallback()
            } else {
              this.$alert(res.msg)
            }
            this.loading.hide()
          })
        }).catch(() => {
        })
      },
      handleChangeStatus(index, row, command) {
        console.log('handleCommand ------>> ', index, row, command)
        const txt = { 0: '禁用', 1: '启用' }
        this.$confirm(`确定${txt[command]}底部导航：${row.navName}?`, txt[command] + '此项', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.changeStatus(row.id, command)
        }).catch(() => {
          this.reload()
        })
      },
      //改变状态
      changeStatus(id, statusId) {
        apis.websiteNavUpate({ id, statusId }).then(res => {
          const msg = { 0: '禁用成功', 1: '启用成功' }
          this.$message({
            type: 'success',
            message: msg[statusId]
          });
          this.reload()
        })
      },
      //编辑
      handleUpdateRow(data) {
       this.form = data
       this.ctrl.dialogTitle = '编辑权限'
       this.ctrl.dialogVisible = true
      },
      websiteNavList() {
        this.ctrl.loading = true
        console.log(this.params)
        apis.websiteNavList(this.params).then(res => {
          this.page = res.data
          this.page.numPerPage = res.data.pageSize
          console.log(this.page)
          this.ctrl.loading = false
        }).catch(() => {
          this.ctrl.loading = false
        })
      }
    }
  }
</script>
