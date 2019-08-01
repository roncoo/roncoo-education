<template>
  <div>
    <div>
      <el-form ref="formData" :model="formData" label-width="150px">
        <el-alert class="title" :closable="false" title="视频存储信息" type="info" />
          <br/>
          <el-form-item label="视频存储平台:">
            <el-radio-group v-model="formData.videoType">
              <el-radio :label="1">保利威视</el-radio>
              <el-radio :label="0">七牛</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-row>
            <el-col :span="11"><div>
              <el-form-item label="useid:">
                <el-input style="width:450px;" v-model="formData.polyvUseid" placeholder="请输入useid"></el-input>
              </el-form-item>
            </div></el-col>
            <el-col :span="11"><div>
              <el-form-item label="secretkey:">
                <el-input style="width:450px;" v-model="formData.polyvSecretkey" placeholder="请输入secretkey" ></el-input>
              </el-form-item>
            </div></el-col>
          </el-row>
          <el-row>
            <el-col :span="11"><div>
              <el-form-item label="readtoken:">
                <el-input style="width:450px;" v-model="formData.polyvReadtoken" placeholder="请输入readtoken"></el-input>
              </el-form-item>
            </div></el-col>
            <el-col :span="11"><div>
              <el-form-item label="writetoken:">
                <el-input style="width:450px;" v-model="formData.polyvWritetoken" placeholder="请输入writetoken"></el-input>
              </el-form-item>
            </div></el-col>
          </el-row>
        <el-alert class="title" :closable="false" title="文件存储信息" type="info" />
          <br/>
            <el-form-item label="文件存储类型:">
              <el-radio-group v-model="formData.fileType">
                <el-radio :label="1">阿里云</el-radio>
                <el-radio :label="2">七牛</el-radio>
                <el-radio :label="3">本地</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-row>
              <el-col :span="11"><div>
                <el-form-item label="accessKeyId:">
                  <el-input style="width:450px;" v-model="formData.aliyunAccessKeyId" placeholder="请输入accessKeyId"></el-input>
                </el-form-item>
              </div></el-col>
              <el-col :span="11"><div>
                <el-form-item label="accessKeySecret:">
                  <el-input style="width:450px;" v-model="formData.aliyunAccessKeySecret" placeholder="请输入accessKeySecret"></el-input>
                </el-form-item>
              </div></el-col>
            </el-row>
            <el-row>
              <el-col :span="11"><div>
                <el-form-item label="ossUrl:">
                  <el-input style="width:450px;" v-model="formData.aliyunOssUrl" placeholder="请输入ossUrl"></el-input>
                </el-form-item>
              </div></el-col>
              <el-col :span="11"><div>
                <el-form-item label="ossBucket:">
                  <el-input style="width:450px;" v-model="formData.aliyunOssBucket" placeholder="请输入ossBucket"></el-input>
                </el-form-item>
              </div></el-col>
            </el-row>
            <el-row>
              <el-col :span="11"><div>
                <el-form-item label="smsCode:">
                  <el-input style="width:450px;" v-model="formData.smsCode" placeholder="请输入smsCode"></el-input>
                </el-form-item>
              </div></el-col>
              <el-col :span="11"><div>
                <el-form-item label="短信签名:">
                  <el-input style="width:450px;" v-model="formData.signName" placeholder="请输入短信签名"></el-input>
                </el-form-item>
              </div></el-col>
          </el-row>
        <el-alert class="title" :closable="false" title="支付通道信息" type="info" />
          <br/>
          <el-form-item label="支付通道:">
            <el-radio-group v-model="formData.payType">
              <el-radio :label="1">龙果支付</el-radio>
              <el-radio :label="0">其他</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-row>
            <el-col :span="11"><div>
              <el-form-item label="payKey:">
                <el-input style="width:450px;" v-model="formData.payKey" placeholder="请输入payKey"></el-input>
              </el-form-item>
            </div></el-col>
            <el-col :span="11"><div>
              <el-form-item label="paySecret:">
                <el-input style="width:450px;" v-model="formData.paySecret" placeholder="请输入paySecret"></el-input>
              </el-form-item>
            </div></el-col>
          </el-row>
          <el-row>
            <el-col :span="11"><div>
              <el-form-item label="支付请求:">
                <el-input style="width:450px;" v-model="formData.payUrl" placeholder="请输入支付请求"></el-input>
              </el-form-item>
            </div></el-col>
            <el-col :span="11"><div>
              <el-form-item label="回调地址:">
                <el-input style="width:450px;" v-model="formData.notifyUrl" placeholder="请输入回调地址"></el-input>
              </el-form-item>
            </div></el-col>
          </el-row>
        <br/>
      </el-form>
    </div>
      <el-row style="margin-top:17px; ">
          <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确 定</el-button>
      </el-row>
      <br/>
      <br/>
  </div>
</template>
<script>
  import * as api from '@/api/sys'
  export default {
    data() {
      return {
        formData: {}
      }
    },
    mounted() {
      this.getSys()
    },
    methods: {
      getSys() {
        this.loading.show()
        api.sysView({}).then(res => {
          this.loading.hide()
          if (res.code === 200) {
            this.formData = res.data
          }
        }).catch(() => {
          this.loading.hide()
          })
      },
      submitForm(formData) {
        this.$refs[formData].validate((valid) => {
          if (valid) {
            this.loading.show()
            api.sysUpdate(this.formData).then(res => {
              this.loading.hide()
              if (res.code === 200 && res.data > 0) {
                this.tips('操作成功', 'success')
              } else {
                this.$message({
                  type: 'error',
                  message: "更新失败"
                });
              }
            }).catch(() => {
              this.loading.hide()
            })
          } else {
            this.$message({
              type: 'error',
              message: "更新失败"
            });
          }
        })
      }
    }
  }
</script>
