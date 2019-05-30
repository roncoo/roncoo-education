<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    v-loading="ctrl.loading"
    :before-close="handleClose">

    <el-form :model="formData" :rules="rules" ref="formData" label-width="150px">
      <el-form-item label="角色名称" prop="roleName">
        <el-input v-model="formData.roleName"></el-input>
      </el-form-item>
      <el-form-item label="角色编码" prop="roleCode">
        <el-input v-model="formData.roleCode"></el-input>
      </el-form-item>
      <el-form-item label="角色状态" prop="status">
        <el-radio v-model="formData.status" v-for="item in opts.status" :key="item.name" :label="item.name">{{item.desc}}</el-radio>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="formData.remark" type="textarea"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="submitForm('formData')">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import * as apis from '@/api/role'
  export default {
    name: "EditRole",
    data() {
      return {
        ctrl: {
          dialogVisible: true,
          loading: false
        },
        opts: {
          status: []
        },
        form: {},

        rules: {
          roleName: [
            { required: true, message: '请输入角色名称', trigger: 'blur' }
          ],
          roleCode: [
            { required: true, message: '请输入角色编码', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '请选择角色状态', trigger: 'blur' }
          ],
          remark: [
            { required: true, message: '请输入不少于三个字符的备注', trigger: 'blur' }
          ]
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
    created() {
      // this.remoteAuthors()
      this.$store.dispatch('GetOpts', { name: 'PublicStatusEnum' }).then(res => {
        this.opts.status = res;
      })
    },
    methods: {
      handleClose(done) {
        this.$emit('close-cllback')
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.handleConfirm()
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      async handleConfirm() {
        this.ctrl.loading = true
        let res = {}
        const pdata = {
          id: this.formData.id,
          roleName: this.formData.roleName,
          roleCode: this.formData.roleCode,
          status: this.formData.status,
          remark: this.formData.remark
        }
        if (this.formData.id === undefined) {
          res = await apis.addRole(pdata)
        } else {
          // 编辑
          res = await apis.updateRole(pdata)
        }
        console.log(res)
        this.ctrl.loading = false
        if (res.code === 200) {
          // 提交成功, 关闭窗口, 刷新列表
          this.$message({
            type: 'success',
            message: '编辑角色成功'
          });
          this.$emit('close-cllback')
        } else {
          this.$alert(res.msg || '提交失败')
        }
      }
    }
  }
</script>

<style scoped>

</style>
