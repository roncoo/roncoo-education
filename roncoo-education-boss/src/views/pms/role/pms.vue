<template>
  <!--弹窗-->
  <el-dialog
    width="40%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form label-width="80px">
      <div>
        <el-tree
        ref="tree"
        :data="menus"
        :default-checked-keys="menuIds"
        :props="defaultProps"
        accordion
        show-checkbox
        :check-strictly="true"
        node-key="id"/>
      </div>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button class="button" type="primary" @click="saveRoleMenu()">确 定</el-button>
      <el-button class="button" type="danger" plain @click="handleClose">取 消</el-button>
    </div>
  </el-dialog>
</template>
<script>
  import * as api from '@/api/system'
  export default {
    name: 'Pms',
    data() {
      return {
        menus: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        menuIds: []
      }
    },
    props: {
      // route object
      id: {
        type: String,
        default: ''
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
    watch: {
      visible: function(val) {
        if (val) {
          setTimeout(() => {
            this.menusList()
          }, 500)
        }
      }
    },
    methods: {
      menusList() {
        this.load = true
        api.menuList({}).then(res => {
          this.menus = res.data.sysMenu
          this.rolemenu(this.id)
          console.log(this.menus)
        }).catch(() => {
          this.load = false
        })
      },
      rolemenu(roleId) {
        this.load = true
        api.menuRoleList({ roleId: roleId }).then(res => {
          console.log(res)
          this.menuIds = res.data.list
          console.log('关联集合', this.menuIds)
        }).catch(() => {
          this.load = false
        })
      },
      // 保存角色菜单信息
      saveRoleMenu() {
        this.load = true
        const role = { roleId: this.id, menuId: [] }
        this.$refs.tree.getCheckedKeys().forEach(function(data, index) {
          role.menuId.push(data)
        })
        console.log(role)
        this.handleConfirm(role)
      },
      //异步保存角色信息
      async handleConfirm(role) {
        api.menuRoleSave(role).then(res => {
          if (res.code === 200 && res.data > 0) {
            this.$emit('close-cllback')
          } else {
            this.$alert(res.msg || '提交失败')
          }
          this.load = false
        }).catch(() => {
          this.load = false
        })
      },
      handleClose(done) {
        this.menuIds = []
        this.menus = []
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
