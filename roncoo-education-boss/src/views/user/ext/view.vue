<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
     <el-form :inline="true">
      <el-card >
        <el-divider style="font-weight:bold; background-color:#f4f4f5">基础信息</el-divider>
        <el-row>
          <el-col :span="12"><div>
            <el-form-item label="用户编号:">
              <span >{{formData.userNo}}</span>
            </el-form-item>
          </div></el-col>
          <el-col :span="12"><div>
            <el-form-item label="用户类型:">
              <template slot-scope="scope">
                <span :class="textClass(formData.userType)">{{textUserType[formData.userType]}}</span>
              </template>
            </el-form-item>
          </div></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><div>
            <el-form-item label="手机号:">
              <span >{{formData.mobile}}</span>
            </el-form-item>
          </div></el-col>
          <el-col :span="12"><div>
            <el-form-item label="昵称:">
              <span>{{formData.nickname}}</span>
            </el-form-item>
          </div></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><div>
            <el-form-item label="注册时间:">
              <span >{{formData.gmtCreate}}</span>
            </el-form-item>
          </div></el-col>
          <el-col :span="12"><div>
            <el-form-item label="状态:">
              <template slot-scope="scope">
                <span :class="textClass(formData.statusId)">{{textStatusId[formData.statusId]}}</span>
              </template>
            </el-form-item>
          </div></el-col>
        </el-row>
      </el-card>
      <br/>
       <el-card >
        <el-divider style="font-weight:bold; background-color:#f4f4f5">基础信息</el-divider>
        <div>
          <el-row>
            <el-col :span="12"><div>
              <el-form-item label="用户头像:">
                <div class="demo-image">
                  <div class="block">
                    <el-image style="width: 100px; height: 100px" :src="formData.headImgUrl"></el-image>
                  </div>
                </div>
              </el-form-item>
            </div></el-col>
          </el-row>
          <el-row>
            <el-col :span="12"><div>
              <el-form-item label="性别:">
                <template slot-scope="scope">
                  <span :class="textClass(formData.sex)">{{textUex[formData.sex]}}</span>
                </template>
              </el-form-item>
            </div></el-col>
            <el-col :span="12"><div>
              <el-form-item label="年龄:">
                <span>{{formData.age}}</span>
              </el-form-item>
            </div></el-col>
          </el-row>
          <el-form-item label="备注:">
            <span >{{formData.remark}}</span>
          </el-form-item>
        </div>
      </el-card>
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
        textUserType: {
          1: '用户',
          2: '讲师'
        },
        textUex: {
          1: '男',
          2: '女',
          3: '保密'
        }
      }
    },
     props: {
      // route object
      formData: {
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
          c_blue: isFree === 2
        }
      },
      handleClose(done) {
        this.$emit('close-callback')
      }
    }
  }
</script>
