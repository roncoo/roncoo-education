<template>
  <div class="container">
    <div class="text">开源协议：MIT | 官网：<el-link type="primary" href="https://www.roncoo.net/" target="_blank">https://edu.roncoo.net/</el-link></div>
    <div>
      <el-divider></el-divider>
      <div>
        <el-row :gutter="24">
          <el-col :span="12">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>商业版</span>
              </div>
              <div style="font-size: 15px;text-indent: 2em;padding: 0px;">单机构版、多机构版、定制版，详情请看各版本适用场景介绍及包含的功能明细。</div>
              <br/>
              <br/>
              <div class="text-right">
                <el-button style="float:right;margin-left:6px;" size="mini" type="primary" @click="jumpUrl('demo')">演示环境</el-button>
                <el-button style="float:right;" type="warning" size="mini" @click="jumpUrl('home')">官网</el-button>
              </div>
              <br/>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>开源版</span>
              </div>
              <div style="font-size: 15px;text-indent: 2em;padding: 0px;">领课教育系统是基于领课网络多年的在线教育平台开发和运营经验打造出来的产品，致力于打造一个全行业都适用的分布式在线教育系统。</div>
              <br/>
              <div class="text-right">
                <el-button style="float:right;margin-left:6px;" size="mini" type="primary" @click="jumpUrl('fineUI')">演示环境</el-button>
                <el-button style="float:right;" type="warning" size="mini" @click="jumpUrl('home')">官网</el-button>
              </div>
              <br/>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Dashboard',
  methods: {
    jumpUrl(type) {
      switch (type) {
        case 'home':
          window.open('https://www.roncoo.net/')
          break;
        case 'demo':
          window.open('http://edu.roncoo.com/')
          break;
        case 'fineUI':
          window.open('http://edu.os.roncoo.com/')
          break;
      }
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>
<style scoped>
.container {
  margin: 30px;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
    background: #e5e9f2;
  }
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.text {
  font-size: 20px;
  line-height: 46px;
}
</style>
