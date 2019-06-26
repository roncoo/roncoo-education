<template>
  <div class="pad20">
      <div>
        <el-form :inline="true" size="mini">
        <el-form-item label="名称">
          <el-input v-model="map.roleName"></el-input>
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
          <el-button type="primary" :loading="ctrl.load" @click="handleCheck">查询</el-button>
          <el-button class="filter-item" @click="handleReset">重置</el-button>
          <el-button type="primary" icon="el-icon-circle-plus-outline" size="mini" @click="handleAdd()">添加</el-button>
        </el-form-item>
        </el-form>
      </div>
      <div>
        <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
          <el-table-column type="index" label="序号">
          </el-table-column>
          <el-table-column prop="roleName" label="名称">
          </el-table-column>
          <el-table-column prop="remark" label="备注">
          </el-table-column>
          <el-table-column prop="sort" label="排序">
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
          <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <ul class="list-item-actions">
              <li>
              </li>
            </ul>
          </template>
          </el-table-column>
        </el-table>
        </div>
        <div>
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
          <add :visible="ctrl.addDialogVisible" :title="ctrl.dialogTitle" @close-cllback="closeCllback"></add>
        </div>
    </div>
</template>
<script>
  import * as api from '@/api/system'
  import Add from './add'
  export default {
    components: { Add },
    data() {
      return {
        map: {},
        formData: {},
        list: [],
        ctrl: {
          load: false,
          addDialogVisible: false
        },
        opts: {
          statusIdList: []
        },
        page: {
          beginPageIndex: 1,
          pageCurrent: 1,
          endPageIndex: 8,
          pageSize: 20,
          totalCount: 0,
          totalPage: 0
        },
        textuStatusId: {
          0: '禁用',
          1: '正常'
        }
      }
    },
    props: {
      visible: {
        type: Boolean,
        default: false
      },
      title: {
        type: String,
        default: ''
      }
    },
    mounted() {
      this.$store.dispatch('GetOpts', { enumName: "StatusIdEnum", type: 'arr' }).then(res => {
        this.opts.statusIdList = res
      })
      this.roleList(1)
    },
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.roleList()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
        this.roleList()
        // console.log(`当前页: ${val}`)
      },
      // 查询条件
       handleCheck() {
        this.page.pageCurrent = 1
        this.roleList()
      },
      // 重置查询条件
      handleReset() {
        this.reload()
      },
      // 角色分页列表信息
      roleList() {
        this.ctrl.load = true
        api.roleList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
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
        console.log(id)
        api.roleUpdate({ id: id, statusId: statusId }).then(res => {
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
      // 角色添加弹窗
      handleAdd() {
        this.ctrl.addDialogVisible = true
        this.dialogTitle = "添加"
      },
      // 关闭弹窗回调
      closeCllback() {
        this.ctrl.addDialogVisible = false
        this.reload()
      },
      // 刷新当前页面
      reload() {
        this.map = {}
        this.roleList()
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
<style scoped>
  .cancel {
    text-align: right;
  }
  .button {
    padding: 5px 10px;
  }
</style>
