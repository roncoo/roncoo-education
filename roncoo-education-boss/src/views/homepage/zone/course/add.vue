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
        <el-form-item label="课程名称">
          <el-input v-model="map.courseName"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button icon='el-icon-search' type="primary" @click="handleCheck">查询</el-button>
          <el-button icon='el-icon-refresh' class="filter-item" @click="handleReset">重置</el-button>
        </el-form-item>
        </el-form>
      </div>
      <div>
        <el-table v-loading="ctrl.load" size="medium" :data="list" stripe border style="width: 100%">
          <el-table-column type="index" label="序号">
          </el-table-column>
           <el-table-column prop="courseName" label="课程名称">
          </el-table-column>
          <el-table-column label="课程分类" width="180">
            <template slot-scope="scope">
            {{scope.row.categoryName1}}/{{scope.row.categoryName2}}/{{scope.row.categoryName3}}
            </template>
          </el-table-column>
          <el-table-column label="是否收费" width="90">
            <template slot-scope="scope">
              <span :class="textClass(scope.row.isFree)">{{textIsFree[scope.row.isFree]}}</span>
            </template>
          </el-table-column>
          <el-table-column label="原价" width="100">
            <template slot-scope="scope">
              {{scope.row.courseOriginal.toFixed(2)}}
            </template>
          </el-table-column>
          <el-table-column label="是否收费" width="90">
            <template slot-scope="scope">
              <span :class="textClass(scope.row.isPutaway)">{{textIsPutaway[scope.row.isPutaway]}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="sort" label="排序" width="70">
          </el-table-column>
          <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <ul class="list-item-actions">
              <li v-if="scope.row.isAddZoneCourse == 0">
                <el-button type="success" @click="handleChoice(scope.row.id)" size="mini">选择</el-button>
              </li>
              <li v-if="scope.row.isAddZoneCourse == 1">
                <el-link disabled>已添加</el-link>
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
  import * as api from '@/api/course'
  export default {
    data() {
      return {
        map: {},
        list: [],
        ctrl: {
          load: false
        },
        textIsFree: {
          1: '免费',
          0: '收费'
        },
        textIsPutaway: {
          1: '上架',
          2: '下架'
        },
        page: {
          beginPageIndex: 1,
          pageCurrent: 1,
          endPageIndex: 8,
          pageSize: 20,
          totalCount: 0,
          totalPage: 0
        },
        formData: {}
      }
    },
    props: {
      zoneId: {
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
          setTimeout(() => {
            this.map.zoneId = this.zoneId
            this.pageList()
          }, 500)
        }
      }
    },
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`)
        this.page.pageSize = val
        this.pageList()
      },
      handleCurrentChange(val) {
        this.page.pageCurrent = val
        this.pageList()
        // console.log(`当前页: ${val}`)
      },
      // 查询条件
       handleCheck() {
        this.page.pageCurrent = 1
        this.pageList()
      },
      // 重置查询条件
      handleReset() {
        this.reload()
      },
      // 分页列出课程信息
      pageList() {
        this.ctrl.load = true
        api.courseList(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
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
        this.map.zoneId = this.zoneId
        this.pageList()
      },
      handleChoice(res) {
        this.ctrl.loading = true
        this.map.courseId = res
        api.zoneCourseSave(this.map).then(res => {
          this.ctrl.loading = false
          if (res.code === 200 && res.data > 0) {
              this.$message({
                type: 'success',
                message: "保存成功"
              });
              this.handleClose()
          } else {
            this.$message({
              type: 'error',
              message: "保存失败"
            });
          }
        })
      },
      handleClose() {
        this.$emit('close-callback')
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
<style scoped>
  .cancel {
    text-align: right;
  }
  .button {
    padding: 5px 10px;
  }
</style>
