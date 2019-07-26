<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
      <el-form-item label="短信标题">
        <el-input v-model="map.msgTitle"></el-input>
      </el-form-item>
      <el-form-item label="是否发送:">
        <el-select v-model="map.isSend" class="auto-width" clearable filterable placeholder="是否发送" style="width: 100px">
          <el-option
            v-for="item in opts.isSendList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否置顶:">
        <el-select v-model="map.isTop" class="auto-width" clearable filterable placeholder="是否置顶" style="width: 100px">
          <el-option
            v-for="item in opts.isTopList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
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
        <el-table-column prop="msgTitle" label="短信标题">
        </el-table-column>
        <el-table-column label="是否置顶" width="90">
          <template slot-scope="scope">
            <span :class="textClass(scope.row.isTop)">{{textIsTop[scope.row.isTop]}}</span>
          </template>
        </el-table-column>
        <el-table-column label="发送状态" width="90">
          <template slot-scope="scope">
            <span :class="textClass(scope.row.isSend)">{{textIsSend[scope.row.isSend]}}</span>
          </template>
        </el-table-column>
        <el-table-column label="定时发送" width="90">
          <template slot-scope="scope">
            <span :class="textClass(scope.row.isTimeSend)">{{textIsTimeSend[scope.row.isTimeSend]}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="sendTime" label="发送时间">
        </el-table-column>
        <el-table-column
        fixed="right"
        label="操作"
        width="370">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="danger" @click="handleDelete(scope.row.id)" size="mini">删除</el-button>
              <el-button type="success" @click="handleEdit(scope.row.id)" size="mini">修改</el-button>
              <el-button type="success" @click="handleSend(scope.row.id)" size="mini">发送</el-button>
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
          dialogVisible: false,
          addDialogVisible: false,
          passwordDialogVisible: false,
          viewVisible: false
        },
        page: {
          beginPageIndex: 1,
          pageCurrent: 1,
          endPageIndex: 8,
          pageSize: 20,
          totalCount: 0,
          totalPage: 0
        },
        opts: {
          isSendList: [],
          isTopList: []
        },
        textIsSend: {
          1: '是',
          0: '否'
        },
        textIsTop: {
          1: '是',
          0: '否'
        },
        textIsTimeSend: {
          1: '是',
          0: '否'
        }
      }
    },
    mounted() {
      this.$store.dispatch('GetOpts', { enumName: "IsSendEnum", type: 'arr' }).then(res => {
        this.opts.isSendList = res
      })
      this.$store.dispatch('GetOpts', { enumName: "IsTopEnum", type: 'arr' }).then(res => {
        this.opts.isTopList = res
      })
      this.msgList(1)
    },
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.msgList()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
        this.msgList()
        // console.log(`当前页: ${val}`)
      },
      // 查询条件
       handleCheck() {
        this.page.pageCurrent = 1
        this.msgList()
      },
      // 重置查询条件
      handleReset() {
        this.reload()
      },
      // 后台管理员分页列表接口
      msgList() {
        this.ctrl.load = true
        api.msgList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      // 添加管理员
      handleAdd() {
        this.ctrl.addDialogVisible = true
        this.ctrl.dialogTitle = '添加'
      },
      // 发送消息
      handleSend(row) {
        this.$confirm(`确定要发送吗?`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          api.msgPush({ id: row }).then(res => {
          if (res.code === 200 && res.data > 0) {
            this.$message({
              type: 'success',
              message: "发送成功"
            });
              this.reload()
          } else {
            this.$message({
              type: 'error',
              message: "发送失败"
            });
              this.reload()
          }
        })
        }).catch(() => {
          this.reload()
        })
      },
      // 跳修改弹窗页面
      handleEdit(row) {
        this.load === true
        api.msgView({ id: row }).then(res => {
          this.formData = res.data
          this.ctrl.dialogTitle = res.data.msgTitle + '——编辑'
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = true
        })
        this.ctrl.dialogVisible = true
      },
      // 关闭弹窗回调
      closeCallback() {
        this.ctrl.addDialogVisible = false
        this.ctrl.dialogVisible = false
        this.reload()
      },
      // 删除
      handleDelete(id) {
        this.$confirm(`确定要删除吗?`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          api.msgDelete({ id: id }).then(res => {
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
        this.msgList()
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
