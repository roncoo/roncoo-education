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
        <el-form-item label="用户手机">
          <el-input v-model="map.mobile"></el-input>
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
          <el-table-column type="index" label="序号">
          </el-table-column>
          <el-table-column prop="mobile" label="电话">
          </el-table-column>
          <el-table-column prop="userNo" label="用户编号">
          </el-table-column>
          <el-table-column prop="gmtCreate" label="注册时间">
          </el-table-column>
          <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <ul class="list-item-actions">
              <li>
                <el-button type="success" @click="handleChoice(scope.row)" size="mini">选择</el-button>
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
        </div>
        <br/>
        <br/>
    </div>
  </el-dialog>
</template>
<script>
  import * as userApi from '@/api/user'
  export default {
    data() {
      return {
        map: {},
        list: [],
        ctrl: {
          load: false
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
        textuUserType: {
          1: '用户',
          2: '讲师'
        },
        textuStatusId: {
          0: '禁用',
          1: '正常'
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
        gmtCreate: ''
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
      this.userExtList(1)
    },
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.userExtList()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
        this.userExtList()
        // console.log(`当前页: ${val}`)
      },
      // 注册时间段查询条件
      changeTime() {
        if (this.gmtCreate !== null && this.gmtCreate.length) {
          this.map.beginGmtCreate = this.dateToString(this.gmtCreate[0])
          this.map.endGmtCreate = this.dateToString(this.gmtCreate[1])
        } else {
          this.map.beginGmtCreate = ''
          this.map.endGmtCreate = ''
        }
      },
      dateToString(date) {
        const year = date.getFullYear()
        const month = (date.getMonth() + 1).toString().padStart(2, '0')
        const day = date.getDate().toString().padStart(2, '0')
        const hours = date.getHours().toString().padStart(2, '0')
        const minutes = date.getMinutes().toString().padStart(2, '0')
        const seconds = date.getSeconds().toString().padStart(2, '0')
        const timeString = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`
        return timeString
      },
      // 查询条件
       handleCheck() {
        this.page.pageCurrent = 1
        this.userExtList()
      },
      // 重置查询条件
      handleReset() {
        this.reload()
      },
      // 分页列出用户信息
      userExtList() {
        this.ctrl.load = true
        userApi.userList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
          this.list = res.data.list
          this.page.pageCurrent = res.data.pageCurrent
          this.page.totalCount = res.data.totalCount
          this.page.pageSize = res.data.pageSize
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      // 刷新当前页面
      reload() {
        this.map = {}
        this.gmtCreate = ''
        this.userExtList()
      },
      handleChoice(res) {
        this.formData.userNo = res.userNo
        this.formData.mobile = res.mobile
        this.handleClose(this.formData)
      },
      handleClose(done) {
        this.$emit('close-callback', done)
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
