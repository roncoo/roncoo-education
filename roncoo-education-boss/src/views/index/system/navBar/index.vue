<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
      <el-form-item label="导航标题">
        <el-input v-model="map.navTitle"></el-input>
      </el-form-item>
        <el-form-item label="状态">
          <template>
            <el-select v-model="map.statusId" placeholder="全部">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </template>
        </el-form-item>
      <el-form-item>
        <el-button type="primary" :loading="ctrl.loading" @click="handleCheck">查询</el-button>
        <el-button class="filter-item" @click="handleReset">重置
        </el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table v-loading="ctrl.loading" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="40">
        </el-table-column>
        <el-table-column prop="navTitle" label="导航标题">
        </el-table-column>
        <el-table-column label="跳转方式" >
          <template slot-scope="scope">
            <p v-if="scope.row.target=='_self'">
              当前页面打开
            </p>
            <p v-else-if="scope.row.target=='_blank'">
              新开窗口打开
            </p>
          </template>
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
              :active-value="1"
              :inactive-value="0"
              active-text="启用"
              inactive-text="禁用">
            </el-switch>
          </template>
        </el-table-column>
       <el-table-column
        fixed="right"
        label="操作"
        width="170">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="primary" @click="handleUpdateRow(scope.row)" size="mini">修改</el-button>
              <el-button type="warning" @click="handleDelRow(scope.row)" size="mini">删除</el-button>
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
    <edit :visible="ctrl.dialogVisible" :formData="form" :title="ctrl.dialogTitle" @close-cllback="closeCallback"></edit>
</div>
</template>
<script>
  import * as apis from '@/api/system'
  import Edit from './edit'
  export default {
   components: { Edit },
    data() {
      return {
        //状态
        options: [{
          value: '1',
          label: '启用'
        }, {
          value: '0',
          label: '禁用'
        }],
        map: {
          id: undefined,
          navTitle: undefined
        },
        ctrl: {
          loading: false
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
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.params.pageSize = val
        this.getList()
      },
      // 关闭编辑弹窗回调
      closeCallback() {
        this.ctrl.dialogVisible = false;
        this.reload()
      },
      reload() {
        this.getList(this.currentPage)
      },
      handleCurrentChange(val) {
        this.params.pageCurrent = val
        this.getList()
        // console.log(`当前页: ${val}`)
      },
      handleChangeStatus(index, row, command) {
        console.log('handleCommand ------>> ', index, row, command)
        const txt = { 0: '启用', 1: '禁用' }
        this.$confirm(`确定${txt[command]}头部导航：${row.linkName}?`, txt[command] + '此项', {
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
        apis.websiteLinkUpdate({ id, statusId }).then(res => {
          const msg = { 0: '禁用成功', 1: '启用成功' }
          this.$message({
            type: 'success',
            message: msg[statusId]
          });
          this.reload()
        })
      },
       handleCheck() {
        this.map.pageNum = 1
        this.getList()
      },
      // 重置查询条件
      handleReset() {
        this.map = {}
        this.getList()
      },
      //编辑
      handleUpdateRow(row) {
        console.log(row)
       this.formdata = row
       this.ctrl.dialogTitle = '编辑'
       this.ctrl.dialogVisible = true
      },
      getList() {
        this.ctrl.loading = true
        console.log(this.params)
          apis.navBarList(this.params).then(res => {
          this.page = res.data
          this.page.numPerPage = res.data.pageSize
          this.list = res.data.list
          console.log(this.page)
          this.ctrl.loading = false
        }).catch(() => {
          this.ctrl.loading = false
        })
      }
    }
  }
</script>
