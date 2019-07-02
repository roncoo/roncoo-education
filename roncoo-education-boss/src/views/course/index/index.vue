<template>
  <div class="pad20">
      <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="50">
        </el-table-column>
        <el-table-column prop="courseName" label="课程名称">
        </el-table-column>
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
  </div>
</template>
<script>
import * as courseApis from '@/api/course'
export default {
  data() {
    return {
      ctrl: {
        load: false
      },
      map: {
        isFree: ''
      },
      formLabelWidth: '120px',
      list: [],
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
      }
    }
  },
  mounted() {
    this.getList()
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
    }
  }
}
</script>
