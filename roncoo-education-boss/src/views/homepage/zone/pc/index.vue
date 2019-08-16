<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
        <el-form-item>
        </el-form-item>
      <el-form-item label="专区名称：">
        <el-input v-model.trim="map.zoneName"></el-input>
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
        <el-button v-has="'/course/pc/zone/add'" type="primary" icon="el-icon-circle-plus-outline" size="mini" @click="handleAddRow()">添加</el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table v-loading="ctrl.loading" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="50">
        </el-table-column>
        <el-table-column prop="zoneName" label="专区名称">
        </el-table-column>
        <el-table-column prop="zoneDesc" label="专区描述">
        </el-table-column>
        <el-table-column prop="sort" label="排序">
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
       <el-table-column
        fixed="right"
        label="操作"
        width="340">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="danger" @click="handleDelRow(scope.row)" size="mini">删除</el-button>
              <el-button v-has="'/course/pc/zone/view'" type="success" @click="handleUpdateRow(scope.row.id)" size="mini">修改</el-button>
              <el-button v-has="'/course/pc/zone/course/list'" type="primary" @click="handleCourseRow(scope.row.id)" size="mini">专区课程</el-button>
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
    </div>
    </div>
    <edit :visible="ctrl.dialogVisible" :formData="formData" :title="ctrl.dialogTitle" @close-callback="closeCallback"></edit>
</div>
</template>
<script>
  import * as api from '@/api/homepage'
  import Edit from './edit'
  export default {
   components: { Edit },
    data() {
      return {
        opts: {
          statusIdList: []
        },
        ctrl: {
          loading: false
        },
        map: {},
        // 表单数据, 例如新增编辑子项，页面表单
        formData: {},
        list: [],
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
      this.$store.dispatch('GetOpts', { enumName: "StatusIdEnum", type: 'arr' }).then(res => {
        this.opts.statusIdList = res
      })
      this.getList()
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
        api.pcZoneUpdate({ id, statusId }).then(res => {
          this.ctrl.loading = false
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
          }
        }).catch(() => {
          this.ctrl.loading = false
          this.reload()
        })
      },
      //新增
      handleAddRow() {
        this.formData = {}
        this.ctrl.dialogTitle = '新增'
        this.ctrl.dialogVisible = true
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
          api.pcZoneDelete(this.map).then(res => {
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
            }
          }).catch(() => {
            this.ctrl.loading = false
            })
        })
      },
      // 关闭编辑弹窗回调
      closeCallback() {
        this.ctrl.dialogVisible = false;
        this.reload()
      },
      //编辑
      handleUpdateRow(id) {
        this.ctrl.loading = true
        api.pcZoneView({ id: id }).then(res => {
          this.formData = res.data
          this.ctrl.dialogTitle = '编辑'
          this.ctrl.dialogVisible = true
        }).catch(() => {
          this.ctrl.loading = false
          })
      },
      handleCourseRow(id) {
        this.$router.push({ path: '/homepage/zone/course', query: { zoneId: id }});
      },
      // 查询条件
       handleCheck() {
        this.page.pageCurrent = 1
        this.getList()
      },
      // 重置查询条件
      handleReset() {
        this.reload()
      },
       // 刷新当前页面
      reload() {
        this.map = {}
        this.formdata = {}
        this.getList()
      },
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.getList()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
        this.getList()
        // console.log(`当前页: ${val}`)
      },
      getList() {
        this.ctrl.loading = true
        api.pcZoneList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.loading = false
        }).catch(() => {
          this.ctrl.loading = false
        })
      }
    }
  }
</script>
