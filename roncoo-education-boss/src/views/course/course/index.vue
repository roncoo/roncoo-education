<template>
  <div class="pad20">
    <div>
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
    </div>
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      style="float: right;margin-top: 20px"
      :current-page="page.currentPage"
      :page-sizes="page.pageSizesArr"
      :page-size="page.pageNum"
      layout="total, sizes, prev, pager, next, jumper"
      :total="page.totalNum">
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
      formLabelWidth: '120px',
      list: [],
      page: {
        pageNow: 0,
        pages: 0, //总页数
        pageNum: 20, //每页显示的个数
        totalNum: 0, //总条目数
        pageSizesArr: [20, 50, 100, 200],
        currentPage: 1 //当前页数
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
    getList(page) {
      this.ctrl.load = true
      courseApis.getCourseAuditList(page, this.page.pageNum).then(res => {
      console.log("=========" + res)
      this.ctrl.load = false
      this.list = res.data.list
      this.page.currentPage = res.data.currentPage
      this.page.totalNum = res.data.totalCount
      }).catch(() => {
        this.ctrl.load = false
      })
    },
    // currentPage 当前页数改变时会触发
    handleCurrentChange(page) {
      this.getList(page);
    },
    // pageSize 每页条数改变时会触发
    handleSizeChange(pageSize) {
      this.page.pageNum = pageSize;
      this.getList(this.page.currentPage);
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
