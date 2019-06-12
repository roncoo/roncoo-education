<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
      <el-form-item label="讲师名称">
        <el-input v-model="map.lecturerName"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="map.lecturerMobile"></el-input>
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
        <el-button class="filter-item" @click="handleReset">重置
        </el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="40">
        </el-table-column>
        <el-table-column label="手机号">
           <template slot-scope="scope">
            <el-button type="text" @click="handleView(scope.row.id)">{{scope.row.lecturerMobile}}</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="lecturerName" label="讲师名称">
        </el-table-column>
        <el-table-column prop="lecturerEmail" label="邮箱">
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
        <el-table-column label="分成比例">
          <template slot-scope="scope">
             [ 讲师: {{scope.row.lecturerProportion*100}}%]
          </template>
        </el-table-column>
        <el-table-column
        fixed="right"
        label="操作"
        width="200">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="primary" @click="handleEdit(scope.row.id)" size="mini">修改</el-button>
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
      <edit :visible="ctrl.dialogVisible" :formData="formData" :title="ctrl.dialogTitle" @close-cllback="closeCllback"></edit>
  </div>
</template>
<script>
  import * as userApi from '@/api/user'
  import Edit from './edit'
  export default {
    components: { Edit },
    data() {
      return {
        map: {},
        formData: {},
        ctrl: {
          load: false,
          dialogVisible: false
        },
        opts: {
          statusIdList: []
        },
        list: [],
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
    mounted() {
      this.$store.dispatch('GetOpts', { enumName: "StatusIdEnum", type: 'arr' }).then(res => {
        this.opts.statusIdList = res
      })
      this.lecturerList(1)
    },
    methods: {
       handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.lecturerList()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
        this.lecturerList()
        // console.log(`当前页: ${val}`)
      },
      // 查询条件
       handleCheck() {
        this.page.pageCurrent = 1
        this.lecturerList()
      },
      // 重置查询条件
      handleReset() {
        this.params = {}
        this.lecturerList()
      },
      lecturerList() {
        this.load === true
        userApi.lecturerList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.loading = false
        }).catch(() => {
          this.ctrl.loading = false
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
        userApi.lecturerUpdate({ id: id, statusId: statusId }).then(() => {
          const msg = { 0: '禁用成功', 1: '启用成功' }
          this.$message({
            type: 'success',
            message: msg[statusId]
          });
            this.reload()
        })
      },
       // 关闭编辑弹窗回调
      closeCllback() {
        this.ctrl.dialogVisible = false;
        this.reload()
      },
      // 跳页面操作
      handleEdit(id) {
        this.load === true
        console.log("修改查询讲师id", id)
        userApi.lecturerView({ id: id }).then(row => {
          console.log("查询讲师信息", row.data)
          this.formData = row.data
          this.ctrl.loading = false
        }).catch(() => {
          this.ctrl.loading = false
        })
        this.ctrl.dialogTitle = '编辑'
        this.ctrl.dialogVisible = true
      },
      // 刷新当前页面
      reload() {
        this.lecturerList()
      }
    }
  }
</script>
