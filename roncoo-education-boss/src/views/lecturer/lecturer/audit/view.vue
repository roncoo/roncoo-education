<template>
  <!--弹窗-->
  <el-dialog
     width="60%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
     <el-form :inline="true">
      <el-card >
        <el-divider style="font-weight:bold; background-color:#f4f4f5">讲师个人信息</el-divider>
        <div>
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
            <el-col :span="8"><div>
              <el-form-item label="审核状态:">
                <template slot-scope="scope">
                  <span :class="textClass(formData.auditStatus)">{{textAuditStatus[formData.auditStatus]}}</span>
                </template>
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
        </div>
      </el-card>
      <br/>
      <el-card >
        <el-divider style="font-weight:bold; background-color:#f4f4f5">简介</el-divider>
        <div v-html="formData.introduce">
        </div>
      </el-card>
      <br/>
      <el-card >
        <el-divider style="font-weight:bold; background-color:#f4f4f5">分成情况</el-divider>
        <div>
          <el-row>
            <el-col :span="8"><div>
              <el-form-item label="讲师分成情况:">
                <span>{{formData.lecturerProportion*100}}%</span>
              </el-form-item>
            </div></el-col>
            <el-col :span="8"><div>
              <el-form-item label="累计总收入:">
                <span>{{lecturerExt.totalIncome}}元</span>
              </el-form-item>
            </div></el-col>
          </el-row>
          <el-row>
            <el-col :span="8"><div>
              <el-form-item label="已提现金额:">
                <span >{{lecturerExt.historyMoney}}元</span>
              </el-form-item>
            </div></el-col>
            <el-col :span="8"><div>
              <el-form-item label="可提现金额:">
                <span >{{lecturerExt.enableBalances}}元</span>
              </el-form-item>
            </div></el-col>
            <el-col :span="8"><div>
              <el-form-item label="冻结金额:">
                <span >{{lecturerExt.freezeBalances}}元</span>
              </el-form-item>
            </div></el-col>
          </el-row>
          <el-row>
            <el-col :span="8"><div>
              <el-form-item label="银行卡号:">
                <span >{{lecturerExt.bankCardNo}}</span>
              </el-form-item>
            </div></el-col>
            <el-col :span="8"><div>
              <el-form-item label="银行名称:">
                <span >{{lecturerExt.bankName}}</span>
              </el-form-item>
            </div></el-col>
            <el-col :span="8"><div>
              <el-form-item label="支行名称:">
                <span >{{lecturerExt.bankBranchName}}</span>
              </el-form-item>
            </div></el-col>
          </el-row>
          <el-row>
            <el-col :span="8"><div>
              <el-form-item label="开户姓名:">
                <span >{{lecturerExt.bankUserName}}</span>
              </el-form-item>
            </div></el-col>
            <el-col :span="8"><div>
              <el-form-item label="身份证号:">
                <span >{{lecturerExt.bankIdCardNo}}</span>
              </el-form-item>
            </div></el-col>
          </el-row>
        </div>
      </el-card>
      <br/>
    </el-form>
    <el-row style="margin-top:17px; ">
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
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
        textAuditStatus: {
          0: '待审核',
          1: '审核通过',
          2: '审核不通过'
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
      lecturerExt: {
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
    methods: {
      textClass(isFree) {
        return {
          c_red: isFree === 0,
          c_blue: isFree === 1
        }
      },
      handleClose(done) {
        this.$emit('close-callback')
      }
    }
  }
</script>
<style scoped>
  .title {
    font-weight:bold;
    background-color:#f4f4f5
  }
  .box-card {
    font-size: 14px;
  }
</style>
