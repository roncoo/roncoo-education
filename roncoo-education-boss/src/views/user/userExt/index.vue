<template>
	<el-table :data="list" style="width: 100%">
      <el-table-column prop="center" label="序号" width="180">
      </el-table-column>
      <el-table-column prop="courseName" label="课程信息" width="180">
      </el-table-column>
      <el-table-column prop="lecturer" label="讲师信息">
      </el-table-column>
      <el-table-column prop="user" label="用户信息">
      </el-table-column>
      <el-table-column prop="tradeType" label="交易类型">
      </el-table-column>
      <el-table-column prop="payType" label="支付方式 / 价格(元)">
      </el-table-column>
      <el-table-column prop="channelType" label="购买渠道">
      </el-table-column>
      <el-table-column prop="remarkCus" label="客户备注">
      </el-table-column>
      <el-table-column prop="payTime" label="支付时间">
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
</template>
<script>
import * as userApi from '@/api/user'
	export default {
      data() {
        return {
          list: []
        }
      }
      watch: {
        created() {
          this.userExtList()
        }
      },
      methods: {
        userExtList() {
          console.log('请求')
          userApi.userExtList().then(response => {
            console.log('请求返回状态码'+response.code)
            if (response.code === 200) {
              this.list = response.data
            }
          })
        }
      }
    }
</script>