<template>
  <div class="pad20">
    <el-tabs v-model="activeName" @tab-click="changeTab">
      <el-tab-pane label="基本信息" name="first">
        <add-info :rpUserInfoRespDTO="rpUserInfoRespDTO"></add-info>
      </el-tab-pane>
      <el-tab-pane label="入款配置" name="second">
        <in-config :rpUserPayConfigRespDTO="rpUserPayConfigRespDTO"></in-config>
      </el-tab-pane>
      <el-tab-pane label="出款配置" name="third">
        <out-config :rpUserBankAccountRespDTO="rpUserBankAccountRespDTO"></out-config>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import AddInfo from './components/Info.vue'
import InConfig from './components/InConfig.vue'
import OutConfig from './components/OutConfig.vue'
export default {
  components: {
    AddInfo,
    InConfig,
    OutConfig
  },
  data() {
    return {
      rpUserInfoRespDTO: null,
      rpUserPayConfigRespDTO: null,
      rpUserBankAccountRespDTO: null,
      ctrl: {
        edit: false
      },
      activeName: 'first'
    }
  },
  methods: {
    ...mapActions('users', ['editUI']),
    changeTab(tab, event) {
      console.log(tab, event);
    },
    getEditInfo() {
      const params = {
        userNo: this.$route.query.userNo
      }
      this.editUI(params).then(res => {
        console.log(res)
        this.rpUserInfoRespDTO = res.data.rpUserInfoRespDTO
        this.rpUserPayConfigRespDTO = res.data.rpUserPayConfigRespDTO
        this.rpUserBankAccountRespDTO = res.data.rpUserBankAccountRespDTO
      })
    }
  },
  mounted() {
    if (this.$route.query.userNo) {
      this.ctrl.edit = true
      this.getEditInfo()
    }
  }
}
</script>

<style lang="scss"></style>
