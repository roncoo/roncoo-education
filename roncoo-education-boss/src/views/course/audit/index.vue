<template>
  <div class="pad20">
    <div class="filter-container">
      <el-form :inline="true" size="mini">
        <el-form-item label="课程名称:">
          <el-input v-model="map.courseName"></el-input>
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
        <el-form-item label="是否免费:">
          <el-select v-model="map.isFree" class="auto-width" clearable filterable placeholder="是否免费" style="width: 100px">
            <el-option
              v-for="item in opts.isFreeList"
              :key="item.code"
              :label="item.desc"
              :value="item.code">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="上下架:">
          <el-select v-model="map.isPutaway" class="auto-width" clearable filterable placeholder="上下架" style="width: 85px">
            <el-option
              v-for="item in opts.isPutawayList"
              :key="item.code"
              :label="item.desc"
              :value="item.code">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核状态:" >
          <el-select v-model="map.auditStatus" class="auto-width" clearable filterable placeholder="审核状态" style="width: 100px">
            <el-option
              v-for="item in opts.auditStatusList"
              :key="item.code"
              :label="item.desc"
              :value="item.code">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item style="float:right" >
          <el-button type="primary" :loading="ctrl.loading" @click="handleCheck">查询</el-button>
          <el-button class="filter-item" @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
   </div>

    <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
      <el-table-column type="index" label="序号" width="50">
      </el-table-column>
      <el-table-column prop="courseName" label="课程名称">
        <template slot-scope="scope">
          <el-button type="text" @click="handleView(scope.row)" >{{scope.row.courseName}}</el-button>
        </template>
      </el-table-column>>

      <el-table-column label="课程分类">
        <template slot-scope="scope">
        {{scope.row.categoryName1}}/{{scope.row.categoryName2}}/{{scope.row.categoryName3}}
        </template>
      </el-table-column>
      <el-table-column label="是否收费">
        <template slot-scope="scope">
          <span :class="textClass(scope.row.isFree)">{{textIsFree[scope.row.isFree]}}</span>
        </template>
      </el-table-column>
      <el-table-column label="原价">
        <template slot-scope="scope">
          {{scope.row.courseOriginal.toFixed(2)}}
        </template>
      </el-table-column>
      <el-table-column label="上下架">
        <template slot-scope="scope">
          <span :class="textClass(scope.row.isPutaway)">{{textIsPutaway[scope.row.isPutaway]}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="sort" label="排序">
      </el-table-column>
      <el-table-column label="状态">
        <template slot-scope="scope">
          <span :class="textClass(scope.row.statusId)">{{textStatusId[scope.row.statusId]}}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核状态">
        <template slot-scope="scope">
          <span :class="textAuditStatusClass(scope.row.auditStatus)">{{textAuditStatus[scope.row.auditStatus]}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="address" width="120" label="操作">
      </el-table-column>
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
    <view-course :visible="ctrl.dialogVisible" :formData="formdata" @close-cllback="closeCllback"></view-course>
  </div>
</template>
<script>
import * as courseApis from '@/api/course'
import ViewCourse from './view'
export default {
  components: { ViewCourse },
  data() {
    return {
      ctrl: {
        load: false,
        dialogVisible: false
      },
      map: {
        isFree: '',
        courseName: '',
        isPutaway: ''
      },
      formLabelWidth: '120px',
      formdata: {},
      list: [],
      opts: {
        isFreeList: [],
        statusIdList: [],
        isPutawayList: [],
        auditStatusList: []
      },
      page: {
        beginPageIndex: 1,
        currentPage: 1,
        endPageIndex: 8,
        pageSize: 20,
        totalCount: 0,
        totalPage: 0
      },
      textIsFree: {
        1: '免费',
        0: '收费'
      },
      textIsPutaway: {
        1: '上架',
        0: '下架'
      },
      textStatusId: {
        1: '正常',
        0: '禁用'
      },
      textAuditStatus: {
        0: '待审核',
        1: '审核通过',
        2: '审核不通过'
      }
    }
  },
  mounted() {
    this.getList()
    //this.vb = this.visible
    this.$store.dispatch('GetOpts', { enumName: "IsFreeEnum", type: 'arr' }).then(res => {
			this.opts.isFreeList = res
		})
    this.$store.dispatch('GetOpts', { enumName: "StatusIdEnum", type: 'arr' }).then(res => {
      this.opts.statusIdList = res
    })
    this.$store.dispatch('GetOpts', { enumName: "IsPutawayEnum", type: 'arr' }).then(res => {
      this.opts.isPutawayList = res
    })
    this.$store.dispatch('GetOpts', { enumName: "AuditStatusEnum", type: 'arr' }).then(res => {
      this.opts.auditStatusList = res
    })
  },
  methods: {
    getList() {
      this.ctrl.load = true
      courseApis.getCourseAuditList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
        this.ctrl.load = false
        this.list = res.data.list
        this.page.pageSize = res.data.pageSize
        this.page.totalCount = res.data.totalCount
      }).catch(() => {
        this.ctrl.load = false
      })
    },
    handleCheck() {
      this.getList()
    },
    // 关闭编辑弹窗回调
    closeCllback() {
      this.ctrl.dialogVisible = false;
      this.ctrl.viewDialogVisible = false;
    },
    handleView(data) {
      this.ctrl.load = true
      courseApis.courseAuditView(data.id).then(res => {
        this.ctrl.load = false
        this.formdata = res
        this.ctrl.dialogVisible = true
      }).catch(() => {
        this.ctrl.load = false
      })
    },
    // 重置查询条件
    handleReset() {
      this.map = {}
      this.getList()
    },
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`)
      this.page.pageSize = val
      this.getList()
    },
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`)
      this.page.pageCurrent = val
      this.getList()
    },
    textClass(isFree) {
      return {
        c_red: isFree === 0,
        c_blue: isFree === 1
      }
    },
    textAuditStatusClass(auditStatus) {
      return {
        c_red: auditStatus === 2,
        c_green: auditStatus === 1,
        c_blue: auditStatus === 0
      }
    }
  }
}
</script>
