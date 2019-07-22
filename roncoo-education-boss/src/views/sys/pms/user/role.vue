<template>
  <!--弹窗-->
  <el-dialog
    width="90%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <div class="pad20">
      <div>
        <el-form :inline="true" size="mini">
        <el-form-item label="角色">
          <el-input v-model="map.roleName"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :loading="ctrl.load" @click="handleCheck">查询</el-button>
          <el-button class="filter-item" @click="handleReset">重置</el-button>
          <el-button type="success" @click="handleBatch()" size="mini">设置角色</el-button>
        </el-form-item>
        </el-form>
      </div>
      <div>
        <el-table
        size="medium"
        :data="list"
        tooltip-effect="dark"
        v-loading="ctrl.load"
        ref="multipleTable"
        :row-key="getRowKeys"
        :reserve-selection="true"
        @row-dblclick="handleClickRow"
        @selection-change="handleSelectionChange"
        border
        style="width: 100%">
          <el-table-column type="index" label="序号">
          </el-table-column>
          <el-table-column prop="roleName" label="名称">
          </el-table-column>
          <el-table-column prop="id" label="备注">
          </el-table-column>
          <el-table-column prop="sort" label="排序">
          </el-table-column>
          <el-table-column label="状态">
            <template slot-scope="scope">
              <span :class="textClass(scope.row.statusId)">{{textuStatusId[scope.row.statusId]}}</span>
            </template>
          </el-table-column>
          <el-table-column :reserve-selection="true" type="selection" prop="id"></el-table-column>
        </el-table>
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
        <br/>
        <br/>
    </div>
  </el-dialog>
</template>
<script>
  import * as api from '@/api/system'
  export default {
    name: 'Role',
    data() {
      return {
        map: {},
        list: [],
        idList: [], // 选中的数据id数组
        userRoleList: [], // 选中的数组
        ctrl: {
          load: false
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
      userId: {
        type: String,
        default: ''
      },
      visible: {
        type: Boolean,
        default: false
      },
      title: {
        type: String,
        default: ''
      }
    },
    watch: {
      visible: function(val) {
        if (val) {
          this.idList = [];
          this.roleList(1)
          // this.roleIdList()
        }
      }
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
        this.map = {}
        this.list = []
        this.idList = []
        this.roleList()
      },
      // 分页列出用户信息
      roleList() {
        this.ctrl.load = true
        api.roleList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.load = false
          this.roleIdList()
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      // 获取用户管理角色集合
      roleIdList() {
        api.roleUserList({ userId: this.userId }).then(res => {
          this.$nextTick(() => {
            this.$refs.multipleTable.clearSelection();
            if (res.data.list !== []) {
              this.idlist = [];
              res.data.list.map(item => {
                this.idlist.push(item.id);
              })
              this.setSelect();
            }
          })
        }).catch(() => {
          })
      },
      handleClickRow(row) {
        console.log(row)
      },
      setSelect() {
        this.list.map(item => {
          if (this.idlist.indexOf(item.id) !== -1) { // 校验是否已选中
            this.$refs.multipleTable.toggleRowSelection(item, true)
          }
        })
      },
      getRowKeys(row) {
        return row.id;
      },
      // 选择添加的角色
      handleSelectionChange(val) {
        this.userRoleList = val;
      },
      // 用户添加角色
      handleBatch() {
        this.$confirm(`确定要添加选中的角色？`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loading.show()
          this.changeStatus()
        }).catch(() => {
          })
      },
      changeStatus() {
        const idlist = [];
        this.userRoleList.map(item => { // 遍历获取已选中的角色id
          idlist.push(item.id)
        })
        api.roleUserSave({ userId: this.userId, roleId: idlist }).then(res => {
          this.loading.hide()
          if (res.code === 200 && res.data > 0) {
             this.tips('操作成功', 'success')
              this.handleClose()
          } else {
            this.$message({
              type: 'error',
              message: "提交失败"
            });
          }
        })
      },
      handleClose(done) {
        this.$emit('close-callback')
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
