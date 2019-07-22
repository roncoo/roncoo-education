<template>
  <!--弹窗-->
  <el-dialog
    width="40%"
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">
    <el-form v-loading="ctrl.load" label-width="80px">
      <div>
        <el-tree
        ref="tree"
        :data="menus"
        :default-checked-keys="menuIds"
        :props="defaultProps"
        accordion
        show-checkbox
        highlight-current
        node-key="id"/>
      </div>
    </el-form>
    <el-row style="margin-top:17px; ">
        <el-button style="float:right" size="mini" type="primary" @click="saveRoleMenu()">确 定</el-button>
        <el-button style="float:right;margin-left:6px;" size="mini" type="danger" plain @click="handleClose">取 消</el-button>
    </el-row>
  </el-dialog>
</template>
<script>
  import * as api from '@/api/system'
  export default {
    name: 'Pms',
    data() {
      return {
        ctrl: {
          load: false
        },
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        menus: [],
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
          this.menusList()
        }
      }
    },
    methods: {
      menusList() {
        this.ctrl.load = true
        api.menuList({}).then(res => {
          this.menus = res.data.sysMenu
          this.rolemenu(this.id)
          this.ctrl.load = false
        }).catch(() => {
          this.ctrl.load = false
        })
      },
      rolemenu(roleId) {
        api.menuRoleList({ roleId: roleId }).then(res => {
          this.menuIds = res.data.list
          // 过滤非全选的父级菜单
          this.menus.forEach(item => {
            if (this.setChecked(item)) {
            this.menuIds.splice(this.menuIds.indexOf(item.id), 1)
            }
          })
        }).catch(() => {
        })
      },
       // 过滤非全选的父级菜单
      setChecked(row) {
        if (this.menuIds.indexOf(row.id) !== -1) {
          let int = 0;
          row.children.forEach(item => {
            if (this.setChecked(item)) {
              this.menuIds.splice(this.menuIds.indexOf(item.id), 1)
            }
            if (this.menuIds.indexOf(item.id) !== -1) {
              int += 1
            }
          })
          if (int < row.children.length) {
            return true
          } else {
            return false
          }
        }
       },
      // 保存角色菜单信息
      saveRoleMenu() {
        this.ctrl.load = true
        const role = { roleId: this.id, menuId: [] }
        // 获取目前被选中的节点的 key 所组成的数组 与 获取半选中的节点的 key 所组成的数组
         this.$refs.tree.getCheckedKeys().concat(this.$refs.tree.getHalfCheckedKeys()).forEach(function(data, index) {
          role.menuId.push(data)
        })
         // 保存角色信息
         api.menuRoleSave(role).then(res => {
          this.ctrl.load = false
          if (res.code === 200 && res.data > 0) {
            this.tips('保存成功', 'success')
            this.handleClose()
          } else {
            this.$alert(res.msg || '提交失败')
          }
        }).catch(() => {
        })
      },
      handleClose(done) {
        this.menuIds = []
        this.menuList = []
        this.menus = []
        this.$emit('close-callback')
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
