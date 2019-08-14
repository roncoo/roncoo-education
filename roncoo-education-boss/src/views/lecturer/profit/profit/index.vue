<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
      <el-form-item label="讲师名称：">
        <el-input v-model.trim="map.lecturerName"></el-input>
      </el-form-item>
      <el-form-item label="分润状态：" >
        <el-select v-model="map.profitStatus" class="auto-width" clearable filterable placeholder="分润状态" style="width: 100px">
          <el-option
            v-for="item in profitStatusList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="时间：">
        <div>
          <datePicker v-model="gmtCreate" ref="dataRange" type="daterange"></datePicker>
        </div>
      </el-form-item>
      <el-form-item>
        <el-button icon='el-icon-search' type="primary" @click="handleCheck">查询</el-button>
        <el-button icon='el-icon-refresh' class="filter-item" @click="handleReset">重置</el-button>
        <el-button type="success" @click="handleBatch()" size="mini">标记为已打款</el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table @selection-change="handleSelectionChange" v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="selection" width="40"></el-table-column>
        <el-table-column type="index" label="序号" width="50"></el-table-column>
        <el-table-column width="100" prop="lecturerName" label="讲师名称"></el-table-column>
        <el-table-column width="120" prop="lecturerMobile" label="讲师手机"></el-table-column>
        <el-table-column width="200" prop="bankCardNo" label="银行卡号"></el-table-column>
        <el-table-column prop="bankName" label="银行名称"></el-table-column>
        <el-table-column prop="bankUserName" label="开户名"></el-table-column>
        <el-table-column prop="lecturerProfit" label="讲师收入"></el-table-column>
        <el-table-column prop="platformProfit" label="平台收入"></el-table-column>
        <el-table-column label="审核状态" width="80">
          <template slot-scope="scope">
            <span>{{textProfitStatus[scope.row.profitStatus]}}</span>
          </template>
        </el-table-column>
        <el-table-column width="170" prop="gmtCreate" label="时间"></el-table-column>
        <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="success" @click="handleEdit(scope.row)" size="mini">打款</el-button>
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
      <edit :visible="ctrl.dialogVisible" :formData="formData" :title="ctrl.dialogTitle" @close-callback="closeCllback"></edit>
  </div>
</template>
<script>
  import * as api from '@/api/lecturer'
  import Edit from './edit'
  import datePicker from '@/components/DateRange/datePicker';
  export default {
    components: { Edit, datePicker },
    data() {
      return {
        ctrl: {
          dialogVisible: false,
          load: false
        },
        textProfitStatus: {
          1: '确认中',
          2: '成功',
          3: '失败'
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
        profitStatusList: [],
        id: [],
        gmtCreate: '',
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
    watch: {
      // 注册时间段查询条件
     'gmtCreate': function(gmtCreate) {
        if (this.gmtCreate !== null && this.gmtCreate.length) {
          this.map.beginDate = this.gmtCreate[0]
          this.map.endDate = this.gmtCreate[1]
        } else {
          this.map.beginDate = ''
          this.map.endDate = ''
        }
      }
    },
    mounted() {
      this.$store.dispatch('GetOpts', { enumName: "ProfitStatusEnum", type: 'arr' }).then(res => {
        this.profitStatusList = res
      })
      this.listForPage(1)
    },
    methods: {
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
        this.gmtCreate = ''
        this.listForPage()
      },
      // 分页列出打款信息
      listForPage() {
        this.ctrl.load = true
        api.lecturerProfitList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      // 打款跳页面操作
      handleEdit(row) {
        this.formData = row
        this.ctrl.dialogTitle = '打款进度'
        this.ctrl.dialogVisible = true
      },
      // 关闭弹窗回调
      closeCllback() {
        this.ctrl.dialogVisible = false;
        this.handleReset()
      },
      // 选择标记打款讲师
      handleSelectionChange(list) {
        const sort = []
        for (var i = 0; i < list.length; i++) {
          sort.push(this.list[i].id)
        }
        this.id = sort
      },
      // 标记为已打款
      handleBatch() {
        this.$confirm(`确定要将选中项标记为成功吗？`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.changeStatus()
        }).catch(() => {
          this.handleReset()
        })
      },
      // 请求批量更新标记为已打款
      changeStatus() {
        this.ctrl.load = true
        api.lecturerProfitBatch({ id: this.id, profitStatus: 2 }).then(res => {
          this.ctrl.load = false
          if (res.code === 200 && res.data > 0) {
              this.$message({
                type: 'success',
                message: '操作成功'
              });
                this.handleReset()
          } else {
              this.$message({
                type: 'error',
                message: '操作失败'
              });
                this.handleReset()
          }
        }).catch(() => {
          this.ctrl.load = false
        })
      }
    }
  }
</script>
