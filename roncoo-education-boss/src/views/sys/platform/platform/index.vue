  <template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
      <el-form-item label="客户端名称">
        <el-input v-model="map.clientName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon='el-icon-search' type="primary" @click="handleCheck">查询</el-button>
        <el-button icon='el-icon-refresh' class="filter-item" @click="handleReset">重置</el-button>
        <el-button type="primary" size="mini" icon="el-icon-circle-plus-outline" @click.native="add(false)">新增</el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="40"></el-table-column>
        <el-table-column label="客户端名称">
           <template slot-scope="scope">
            <el-button type="text" @click="handleView(scope.row.id)">{{scope.row.clientName}}</el-button>
          </template>
        </el-table-column>
        <el-table-column width="300" prop="clientId" label="客户端ID"></el-table-column>
        <el-table-column prop="remark" label="备注"></el-table-column>
        <el-table-column prop="sort" label="排序"></el-table-column>
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
        width="200">
          <template slot-scope="scope">
            <ul class="list-item-actions">
              <li>
                <el-button type="danger" @click="handleDelete(scope.row.id)" size="mini">删除</el-button>
                <el-button type="success" @click="handleEdit(scope.row)" size="mini">编辑</el-button>
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
      <add-platform :visible="ctrl.addDialogVisible" :title="ctrl.dialogTitle" @close-callback="closeCallback"></add-platform>
      <edit :visible="ctrl.dialogVisible" :formData="formData" :title="ctrl.dialogTitle" @close-callback="closeCallback"></edit>
      <view-platform :visible="ctrl.viewVisible" :formData="formData" :title="ctrl.dialogTitle" @close-callback="closeCallback"></view-platform>
  </div>
</template>
<script>
  import * as api from '@/api/sys'
  import Edit from './edit'
  import ViewPlatform from './view'
  import AddPlatform from './add'
  export default {
    components: { Edit, ViewPlatform, AddPlatform },
    data() {
      return {
        ctrl: {
          dialogVisible: false,
          viewVisible: false,
          addDialogVisible: false,
          load: false
        },
        textOrderStatus: {
          1: '待支付',
          2: '支付成功',
          3: '支付失败',
          4: '已关闭'
        },
        textPayType: {
          1: '微信支付',
          2: '支付宝支付'
        },
        timeData: {
          shortcuts: [{
            text: '今天',
            onClick: picker => {
              const now = this.getNow('now')
              picker.$emit('pick', now)
            }
          }, {
            text: '昨天',
            onClick: picker => {
              const now = this.getNow('before')
              picker.$emit('pick', now)
            }
          }]
        },
        formData: {},
        map: {},
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
      this.listForPage()
    },
    methods: {
      // 删除
      handleDelete(id) {
        this.$confirm(`确定要删除吗?`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.ctrl.load = true
          api.platformDelete({ id: id }).then(res => {
            this.ctrl.load = false
          if (res.code === 200 && res.data > 0) {
            this.$message({
              type: 'success',
              message: "删除成功"
            });
              this.handleReset()
          } else {
            this.$message({
              type: 'error',
              message: "删除失败"
            });
              this.handleReset()
          }
        }).catch(() => {
          this.ctrl.load = false
        })
        }).catch(() => {
          this.handleReset()
        })
      },
      // 跳添加弹窗
      add() {
        this.ctrl.dialogTitle = '添加'
        this.ctrl.addDialogVisible = true
      },
      // 查看弹窗
      handleView(id) {
        this.ctrl.load = true
        api.platformView({ id: id }).then(res => {
          this.formData = res.data
          this.ctrl.load = false
        }).catch(() => {
            this.ctrl.load = false
          })
        this.ctrl.viewVisible = true
      },
       // 修改弹窗
      handleEdit(res) {
        this.formData = res
        this.ctrl.dialogVisible = true
      },
      // 关闭弹窗回调
      closeCallback() {
        this.formData = {}
        this.ctrl.dialogVisible = false
        this.ctrl.viewVisible = false
        this.ctrl.addDialogVisible = false
        this.handleReset()
      },
      // 修改状态
      handleChangeStatus(id, statusId) {
        const title = { 0: '禁用', 1: '启用' }
        this.$confirm(`确定要${title[statusId]}吗?`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.ctrl.load = true
          this.changeStatus(id, statusId)
          this.handleReset()
        }).catch(() => {
          this.handleReset()
        })
      },
      // 请求更新用户方法
      changeStatus(id, statusId) {
        api.platformUpdate({ id: id, statusId: statusId }).then(res => {
          this.ctrl.load = false
          if (res.code === 200 && res.data > 0) {
            const msg = { 0: '禁用成功', 1: '启用成功' }
            this.$message({
              type: 'success',
              message: msg[statusId]
            });
              this.handleReset()
          } else {
            const msg = { 0: '禁用失败', 1: '启用失败' }
            this.$message({
              type: 'error',
              message: msg[statusId]
            });
              this.handleReset()
          }
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      // 注册时间段查询条件
      changeTime() {
        if (this.gmtCreate !== null && this.gmtCreate.length) {
          this.map.beginDate = this.dateToString(this.gmtCreate[0])
          this.map.endDate = this.dateToString(this.gmtCreate[1])
        } else {
          this.map.beginDate = ''
          this.map.endDate = ''
        }
      },
      dateToString(date) {
        const year = date.getFullYear()
        const month = (date.getMonth() + 1).toString().padStart(2, '0')
        const day = date.getDate().toString().padStart(2, '0')
        const timeString = `${year}-${month}-${day}`
        return timeString
      },
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.listForPage()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
        this.listForPage()
        // console.log(`当前页: ${val}`)
      },
      // 查询条件
       handleCheck() {
        this.page.pageCurrent = 1
        this.listForPage()
      },
      // 重置查询条件
      handleReset() {
        this.map = {}
        this.formData = {}
        this.listForPage()
      },
      // 平台信息分页列表接口
      listForPage() {
        this.ctrl.load = true
        api.platformList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      }
    }
  }
</script>
