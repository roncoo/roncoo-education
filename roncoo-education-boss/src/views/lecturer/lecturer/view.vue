<template>
  <!--弹窗-->
  <el-dialog
     width="70%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
     <el-form :inline="true">
      <el-row>
        <el-col :span="8"><div>
          <el-form-item label="头像:">
            <div class="demo-image">
              <div class="block">
                <el-image style="width: 100px; height: 100px" :src="formData.headImgUrl"></el-image>
              </div>
            </div>
          </el-form-item>
        </div></el-col>
        <el-col :span="8"><div>
          <el-form-item label="讲师编号:">
            <span >{{formData.lecturerUserNo}}</span>
          </el-form-item>
          <br/>
          <el-form-item label="讲师名称:">
            <span >{{formData.lecturerName}}</span>
          </el-form-item>
        </div></el-col>
        <br/><br/><br/><br/>
        <el-col :span="8"><div>
          <el-form-item label="状态:">
            <template slot-scope="scope">
              <span :class="textClass(formData.statusId)">{{textStatusId[formData.statusId]}}</span>
            </template>
          </el-form-item>
        </div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div>
          <el-form-item label="手机号:">
            <span >{{formData.lecturerMobile}}</span>
          </el-form-item>
        </div></el-col>
        <el-col :span="8"><div>
          <el-form-item label="邮箱:">
            <span >{{formData.lecturerEmail}}</span>
          </el-form-item>
        </div></el-col>
        <el-col :span="8"><div>
          <el-form-item label="职位:">
            <span >{{formData.position}}</span>
          </el-form-item>
        </div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div>
          <el-form-item label="注册时间:">
            <span >{{formData.gmtCreate}}</span>
          </el-form-item>
        </div></el-col>
        <el-col :span="8"><div>
          <el-form-item label="排序:">
            <span >{{formData.sort}}</span>
          </el-form-item>
        </div></el-col>
      </el-row>
      <el-form-item class="button" label="简介:">
        <span >{{formData.introduce}}</span>
      </el-form-item>
      <el-row>
        <el-col :span="8"><div>
          <el-form-item label="讲师分成情况:">
            <span>{{formData.lecturerProportion*100}}%</span>
          </el-form-item>
        </div></el-col>
        <el-col :span="8"><div>
          <el-form-item label="累计总收入:">
            <span>{{lecturerView.totalIncome}}元</span>
          </el-form-item>
        </div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div>
          <el-form-item label="已提现金额:">
            <span >{{lecturerView.historyMoney}}元</span>
          </el-form-item>
        </div></el-col>
        <el-col :span="8"><div>
          <el-form-item label="可提现金额:">
            <span >{{lecturerView.enableBalances}}元</span>
          </el-form-item>
        </div></el-col>
        <el-col :span="8"><div>
          <el-form-item label="冻结金额:">
            <span >{{lecturerView.freezeBalances}}元</span>
          </el-form-item>
        </div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div>
          <el-form-item label="银行卡号:">
            <span >{{lecturerView.bankCardNo}}</span>
          </el-form-item>
        </div></el-col>
        <el-col :span="8"><div>
          <el-form-item label="银行名称:">
            <span >{{lecturerView.bankName}}</span>
          </el-form-item>
        </div></el-col>
        <el-col :span="8"><div>
          <el-form-item label="支行名称:">
            <span >{{lecturerView.bankBranchName}}</span>
          </el-form-item>
        </div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div>
          <el-form-item label="开户姓名:">
            <span >{{lecturerView.bankUserName}}</span>
          </el-form-item>
        </div></el-col>
        <el-col :span="8"><div>
          <el-form-item label="身份证号:">
            <span >{{lecturerView.bankIdCardNo}}</span>
          </el-form-item>
        </div></el-col>
      </el-row>
    </el-form>
    <el-row class="cancel">
      <el-button class="button" type="danger" plain @click="handleClose">取 消</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
  export default {
    data() {
      return {
        textStatusId: {
          1: '正常',
          0: '禁用'
        },
        opts: {
          statusIdList: []
        }
      }
    },
    props: {
      // route object
      formData: {
        type: Object,
        default: () => {}
      },
      lecturerView: {
        type: Object,
        default: () => {}
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
    mounted() {
      if (JSON.stringify(this.formData.lecturerExt) === '{}') {
        this.lecturerExt = this.formData.lecturerExt
      }
    },
    methods: {
      textClass(isFree) {
        return {
          c_red: isFree === 0,
          c_blue: isFree === 1
        }
      },
      handleClose(done) {
        this.$emit('close-cllback')
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
