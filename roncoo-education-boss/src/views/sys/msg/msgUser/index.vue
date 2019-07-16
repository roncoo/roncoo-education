<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
      <el-form-item label="短信标题">
        <el-input v-model="map.msgTitle"></el-input>
      </el-form-item>
      <el-form-item label="用户手机">
        <el-input v-model="map.mobile"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon='el-icon-search' type="primary" @click="handleCheck">查询</el-button>
        <el-button icon='el-icon-refresh' class="filter-item" @click="handleReset">重置</el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="40">
        </el-table-column>
        <el-table-column label="用户手机">
           <template slot-scope="scope">
            <el-button type="text" @click="handleView(scope.row.msgId, scope.row.mobile)">{{scope.row.mobile}}</el-button>
          </template>
        </el-table-column>
        <el-table-column label="是否置顶" width="90">
          <template slot-scope="scope">
            <span :class="textClass(scope.row.msgType)">{{textMsgType[scope.row.msgType]}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="msgTitle" label="短信标题">
        </el-table-column>
        <el-table-column label="定时发送" width="90">
          <template slot-scope="scope">
            <span :class="textClass(scope.row.isRead)">{{textIsRead[scope.row.isRead]}}</span>
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
      <view-user :visible="ctrl.dialogVisible" :formData="formData" :title="ctrl.dialogTitle" @close-cllback="closeViewFind"></view-user>
  </div>
</template>
<script>
  import * as api from '@/api/system'
  import viewUser from './view'
  export default {
    components: { viewUser },
    data() {
      return {
        map: {},
        formData: {},
        list: [],
        ctrl: {
          load: false,
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
        opts: {
        },
        textMsgType: {
          1: '系统消息',
          2: '其他'
        },
        textIsRead: {
          1: '是',
          0: '否'
        }
      }
    },
    mounted() {
      this.msgUserList(1)
    },
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.msgUserList()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
        this.msgUserList()
        // console.log(`当前页: ${val}`)
      },
      // 查询条件
       handleCheck() {
        this.page.pageCurrent = 1
        this.msgUserList()
      },
      // 重置查询条件
      handleReset() {
        this.reload()
      },
      // 后台管理员分页列表接口
      msgUserList() {
        this.ctrl.load = true
        api.msgUserList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
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
          api.msgUserDelete({ id: id }).then(res => {
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
      handleView(row, mobile) {
        api.msgView({ id: row }).then(res => {
          if (res.code === 200) {
            this.formData.msgTitle = res.data.msgTitle
            this.ctrl.dialogTitle = mobile + '——查看'
            this.ctrl.dialogVisible = true
          }
          })
      },
      // 关闭查看弹窗回调
      closeViewFind() {
        this.formData = {}
        this.ctrl.dialogVisible = false
      },
      // 刷新当前页面
      reload() {
        this.map = {}
        this.formData = {}
        this.msgUserList()
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
