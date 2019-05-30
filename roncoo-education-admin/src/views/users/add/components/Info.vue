<template>
  <div class="">
    <el-form
      :inline="true"
      size="mini"
      label-position="left"
      :model="params">
      <div class="font_14 font_b">基本信息</div>
      <div class="mgt20">
        <el-form-item label="用户类型" :label-width="formLabelWidth">
          <el-select v-model="params.userType" :disabled="ctrl.edit" placehorder="请选择" clearable>
            <el-option v-for="item in option.userTyoeList" :key="item.name" :label="item.desc" :value="item.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户名称" :label-width="formLabelWidth">
          <el-input v-model="params.userName"></el-input>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="企业全称" :label-width="formLabelWidth">
          <el-input v-model="params.fullName"></el-input>
        </el-form-item>
        <el-form-item label="企业地址" :label-width="formLabelWidth">
          <el-input v-model="params.address"></el-input>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="合同编号" :label-width="formLabelWidth">
          <el-input v-model="params.contractNo"></el-input>
        </el-form-item>
        <el-form-item label="法人代表姓名" :label-width="formLabelWidth">
          <el-input v-model="params.legalPerson"></el-input>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="法人身份证号" :label-width="formLabelWidth">
          <el-input v-model="params.cardNo"></el-input>
        </el-form-item>
        <el-form-item label="营业执照号" :label-width="formLabelWidth">
          <el-input v-model="params.licenseNo"></el-input>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="业务联系人" :label-width="formLabelWidth">
          <el-input v-model="params.busiContactName"></el-input>
        </el-form-item>
        <el-form-item label="业务联系人手机号码" :label-width="formLabelWidth">
          <el-input v-model="params.busiContactMobile"></el-input>
        </el-form-item>
      </div>
      <div>
        <el-form-item label="联系邮箱" :label-width="formLabelWidth">
          <el-input v-model="params.email" :disabled="ctrl.edit"></el-input>
        </el-form-item>
        <el-form-item label="登录手机号" :label-width="formLabelWidth">
          <el-input v-model="params.mobile" :disabled="ctrl.edit"></el-input>
        </el-form-item>
      </div>
      <div class="font_14 font_b mgt20">店铺信息</div>
      <div class="mgt20">
        <el-form-item label="店铺名称" :label-width="formLabelWidth">
          <el-input v-model="params.shopName"></el-input>
        </el-form-item>
        <el-form-item label="注册号" :label-width="formLabelWidth">
          <el-input v-model="params.registNo"></el-input>
        </el-form-item>
      </div>
      <div class="font_14 font_b mgt20">
        营业执照
        <span class="c_red">（不能大于3M）</span>
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
        身份证正面照
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
        身份证反面照
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
        手持身份证正面照
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
        手持身份证反面照
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
    rpUserInfoRespDTO: {
      type: [Object, Array],
      default() {
        return {}
      }
    }
  },
  watch: {
    rpUserInfoRespDTO: function(val) {
      console.log(val)
      if (val) {
        this.params = val
        console.log(this.params)
      }
    }
  },
  data() {
    return {
      ctrl: {
        edit: false
      },
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
  },
  mounted() {
    if (this.$route.query.userNo) {
      this.ctrl.edit = true
    }
  }
}
</script>

<style lang="scss">
  .upload_box {
    width: 300px;
  }
</style>
