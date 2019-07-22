<template>
  <!--弹窗-->
  <el-dialog
     width="30%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form :model="formData" :rules="rules" ref="formData" label-width="100px">
      <el-form-item label="导航标题:">
        <el-select v-model="formData.navUrl" @change="changeValue" class="auto-width" clearable filterable placeholder="导航标题" style="width: 200px">
          <el-option
            v-for="item in opts.navList"
            :key="item.code"
            :label="item.desc"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="打开方式:" width="200">
        <template>
          <el-radio-group v-model="formData.tradeType">
            <el-radio
            v-for="item in opts.targetList"
            :key="item.code"
            :label="item.desc"
            :value="item.code"></el-radio>
          </el-radio-group>
        </template>
      </el-form-item>
    </el-form>
    <el-row style="margin-top:17px;">
        <el-button style="float:right" size="mini" type="primary" @click="submitForm('formData')">确 定</el-button>
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
  import * as api from '@/api/system'
  export default {
    name: 'Edit',
    data() {
      return {
        opts: {
          navList: [],
          targetList: []
        },
        ada: {},
        navTitle: '',
        formData: {},
        rules: {
          target: [
            { required: true, message: '请选择打开方式', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '请输入导航排序', trigger: 'blur' }
          ]
        }
      }
    },
    props: {
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
      this.$store.dispatch('GetOpts', { enumName: "NavEnum", type: 'arr' }).then(res => {
        this.opts.navList = res
      })
      this.$store.dispatch('GetOpts', { enumName: "TargetEnum", type: 'arr' }).then(res => {
        this.opts.targetList = res
      })
    },
    methods: {
      handleClose(done) {
        this.$emit('close-callback')
      },
      // 获取选中的导航标题
      changeValue(value) {
        let obj = {};
        obj = this.opts.navList.find((item) => {
          return item.code === value;
        });
        if (obj === undefined) {
          this.formData.navTitle = ''
        } else {
          this.formData.navTitle = obj.desc
        }
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            //新增底部导航栏，给父ID赋值
            this.formData.parentId = 0
            console.log("value", this.formData);
            api.navBarSave().then(res => {
              this.loading.hide()
              if (res.code === 200 && res.data > 0) {
                // 提交成功, 关闭窗口, 刷新列表
                this.tips('操作成功', 'success')
                this.handleClose()
              } else {
                this.$message({
                  type: 'error',
                  message: "提交失败"
                });
              }
            })
          } else {
            this.$message({
              type: 'error',
              message: "提交失败"
            });
          }
        });
      }
    }
  }
</script>
