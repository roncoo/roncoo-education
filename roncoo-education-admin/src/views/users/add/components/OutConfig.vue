<template>
  <div class="">
    <el-form
      :inline="true"
      size="mini"
      label-position="left"
      :model="params">
      <div class="font_14 font_b">出款配置</div>
      <div class="mgt20">
        <el-form-item label="开户银行" :label-width="formLabelWidth">
          <el-input v-model="params.bankChannelName"></el-input>
        </el-form-item>
        <el-form-item label="银行账户类型" :label-width="formLabelWidth">
          <el-select v-model="params.bankAccountType">
            <el-option value="" label="对公账户"></el-option>
          </el-select>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="银行开户名" :label-width="formLabelWidth">
          <el-input v-model="params.bankAccountName"></el-input>
        </el-form-item>
        <el-form-item label="银行账号" :label-width="formLabelWidth">
          <el-input v-model="params.bankAccountNo"></el-input>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="证件类型" :label-width="formLabelWidth">
          <el-select v-model="params.cardType">
            <el-option value="" label="身份证"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="证件号码" :label-width="formLabelWidth">
          <el-input v-model="params.cardNo"></el-input>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="手机号" :label-width="formLabelWidth">
          <el-input v-model="params.mobileNo"></el-input>
        </el-form-item>
        <el-form-item label="开户行详细地址" :label-width="formLabelWidth">
          <el-input v-model="params.street"></el-input>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="出款限额百分比" :label-width="formLabelWidth">
          <el-input></el-input>
        </el-form-item>
        <el-form-item label="代付检验方式" :label-width="formLabelWidth">
          <el-select value="">
            <el-option value="" label="支付密码"></el-option>
          </el-select>
        </el-form-item>
      </div>
      <div class="font_14 font_b mgt20">
        银行卡正面照
        <span class="c_red">（不能大于2M）</span>
      </div>
      <div class="mgt20 upload_box">
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          :file-list="fileList"
          list-type="picture"
          :on-success="uploadSuccess">
          <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </div>
      <div class="font_14 font_b mgt20">
        银行卡反面照
        <span class="c_red">（不能大于2M）</span>
      </div>
      <div class="mgt20 upload_box">
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          :file-list="fileList"
          list-type="picture"
          :on-success="uploadSuccess">
          <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </div>
      <div class="pad20">
        <el-button type="" size="mini" class="fr mgl20">取消</el-button>
        <el-button type="primary" size="mini" class="fr">提交</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
export default {
  props: {
    rpUserBankAccountRespDTO: {
      type: [Object, Array],
      default() {
        return {}
      }
    }
  },
  watch: {
    rpUserBankAccountRespDTO: function(val) {
      console.log(val)
      if (val) {
        this.params = val
        console.log(this.params)
      }
    }
  },
  data() {
    return {
      formLabelWidth: '150px',
      fileList: [],
      params: {},
      option: {
        userTyoeList: []
      }
    }
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    uploadSuccess(response, file, fileList) {
      console.log(response)
      console.log(file)
      console.log(fileList)
    }
  },
  created() {
    this.$store.dispatch('GetOpts', { name: 'UserTypeEnum' }).then(res => {
      console.log(res)
      this.option.userTyoeList = res
    })
  }
}
</script>

<style lang="scss">
  .upload_box {
    width: 300px;
  }
</style>
