<template>
	<el-table :data="tableData" style="width: 100%">
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
	export default {
      data() {
        return {
          tableData: [{
            center: '1',
            courseName: '课程信息',
            lecturer: '讲师信息',
            user: '用户信息',
            tradeType: '交易类型',
            payType: '微信/200元',
            channelType: 'WEB',
            remarkCus: '支付成功',
            payTime: '2016-05-04'
          }, {
            center: '2',
           courseName: '课程信息',
            lecturer: '讲师信息',
            user: '用户信息',
            tradeType: '交易类型',
            payType: '微信/200元',
            channelType: 'WEB',
            remarkCus: '支付成功',
            payTime: '2016-05-04'
          }, {
            center: '3',
           courseName: '课程信息',
            lecturer: '讲师信息',
            user: '用户信息',
            tradeType: '交易类型',
            payType: '微信/200元',
            channelType: 'WEB',
            remarkCus: '支付成功',
            payTime: '2016-05-04'
          }, {
            center: '3',
           courseName: '课程信息',
            lecturer: '讲师信息',
            user: '用户信息',
            tradeType: '交易类型',
            payType: '微信/200元',
            channelType: 'WEB',
            remarkCus: '支付成功',
            payTime: '2016-05-04'
          }]
        }
      }
    }
</script>