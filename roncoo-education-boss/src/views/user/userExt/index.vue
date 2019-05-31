<template>
	<div class="pad20">
		<div>
			<el-table :data="list" style="width: 100%">
		      <el-table-column prop="center" label="序号" width="180">
		      </el-table-column>
		      <el-table-column prop="userNo" label="用户编号" width="180">
		      </el-table-column>
		      <el-table-column prop="mobile" label="用户手机">
		      </el-table-column>
		      <el-table-column prop="userType" label="用户类型">
		      </el-table-column>
		      <el-table-column prop="statusId" label="状态">
		      </el-table-column>
		      <el-table-column prop="gmtCreate" label="创建时间">
		      </el-table-column>
		      <el-table-column
		      fixed="right"
		      label="操作"
		      width="100">
		      <template slot-scope="scope">
		        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
		        <el-button type="text" size="small">编辑</el-button>
		      </template>
		    </el-table-column>
		    </el-table>
	    </div>
	    <div>
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
import * as userApi from '@/api/user'
	export default {
      data() {
        return {
          list: []
        }
      },
      watch: {
        created() {
          this.userExtList()
        }
      },
      methods: {
        userExtList() {
          userApi.userExtList().then(response => {
            console.log(response.code)
            if (response.code === 200) {
              this.list = response.data
            }
          })
        }
      }
    }
</script>