<template>
  <div class="pad20">
    <div>
      <el-table v-loading="ctrl.load" size="medium" :data="page.recordList" stripe border style="width: 100%"> 
        <el-table-column type="index" label="序号" width="40">
        </el-table-column>
        <el-table-column prop="accountNo" label="课程名称">
        </el-table-column>
        <el-table-column prop="userNo" label="课程分类">
        </el-table-column>
        <el-table-column prop="userName" label="是否收费">
        </el-table-column>
        <el-table-column prop="balance" label="原价">
        </el-table-column>
        <el-table-column prop="settAmount" label="上下架">
        </el-table-column>
        <el-table-column prop="editTime" label="排序">
        </el-table-column>
        <el-table-column prop="address" width="120" label="操作">
           <template slot-scope="scope">
            <el-button @click.native.prevent="showDetail(scope.row)" type="text" size="small">
              查看账户明细
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="mgt20">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :page-size="page.numPerPage"
        :page-sizes="[20, 50, 100, 200, 500, 1000]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.totalCount">
      </el-pagination>
    </div>
  </div>
</template>
<script>
 
</script>