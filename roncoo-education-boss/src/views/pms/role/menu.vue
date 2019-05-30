<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    v-loading="ctrl.loading"
    :before-close="handleClose">
    <el-row>
      <el-col :span="8">
        <el-tree
          :data="list"
          show-checkbox
          node-key="id"
          ref="tree"
          highlight-current
          class="tree-box"
          :props="defaultProps">
        </el-tree>
      </el-col>
      <el-col :span="16">
        <div class="user-box">
          <el-alert class="title" :closable="false" title="关联了此角色的操作员" type="info" />
          <el-table
            :data="userList"
            border
            fit
            highlight-current-row>
            <el-table-column
              fixed
              type="index"
              width="40">
            </el-table-column>
            <el-table-column
              prop="userNo"
              label="登录名">
            </el-table-column>
            <el-table-column
              prop="userName"
              label="用户姓名">
            </el-table-column>
          </el-table>
        </div>
      </el-col>
    </el-row>
  <!--   <div class="buttons">
      <el-button @click="getCheckedNodes">通过 node 获取</el-button>
      <el-button @click="getCheckedKeys">通过 key 获取</el-button>
      <el-button @click="setCheckedNodes">通过 node 设置</el-button>
      <el-button @click="setCheckedKeys">通过 key 设置</el-button>
      <el-button @click="resetChecked">清空</el-button>
    </div> -->

    <div slot="footer" class="dialog-footer">
      <el-button @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="handleSave">保 存</el-button>
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
        title: '编辑',
        form: {},
        list: [],
        pmsList: [],
        menuList: [],
        userList: [
          {
            userNo: 'lwb@roncoo.com',
            userName: '运营帐号'
          },
          {
            userNo: 'bill@123.com',
            userName: '推广帐号'
          }
        ],
        defaultProps: {
          children: 'menuList',
          label: 'name'
        }
      }
    },
    props: {
      // route object
      obj: {
        type: Object,
        default: () => {}
      },
      visible: {
        type: Boolean,
        default: false
      },
      type: {
        type: String,
        default: 'menu'
      }
    },
    watch: {
      obj(data) {
        console.log(this.obj)
        console.log("this.obj")
        this.setTitle()
      },
      type(type) {
        console.log(this.obj)
        this.setTitle()
      }
    },
    created() {
      this.getMenuList()
      this.getPmsList(1)
      this.$store.dispatch('GetOpts', { name: 'PublicStatusEnum' }).then(res => {
        this.opts.status = res;
      })
    },
    methods: {
      setTitle() {
        if (this.type === 'menu') {
          this.title = `为角色 [ ${this.obj.roleName} ] 分配菜单`;
          this.list = this.menuList;
          this.defaultProps.label = 'name'
        } else {
          this.title = `为角色 [ ${this.obj.roleName} ] 分配权限`;
          this.list = this.pmsList;
          this.defaultProps.label = 'permissionName'
        }
      },
      handleClose(done) {
        this.$emit('close-cllback')
      },
      // 获取权限列表
      getPmsList(page) {
        apis.pmsList(page, this.map).then(res => {
          console.log(res)
          this.pmsList = res.data.recordList
        })
      },
      // 获取菜单列表
      getMenuList() {
        apis.getMenuAll().then(response => {
          console.log(response)
          if (response.code === 200) {
            this.menuList = response.data
          }
        })
      },
      async handleSave() {
        // this.ctrl.loading = true
        console.log(this.obj)
        console.log("this.obj")
        const menus = this.$refs.tree.getCheckedNodes();
        const arr = [];
        for (var i = 0; i < menus.length; i++) {
          arr.push(menus[i].id)
        }
        console.log(arr.join(','));
        const res = await apis.assignMenu({ roleId: this.obj.id, menuIds: arr.join(',') });
        this.$message({
          type: 'success',
          message: res.msg
        });
        this.$emit('close-cllback')
      },

      getCheckedNodes() {
        console.log(this.$refs.tree.getCheckedNodes());
      },
      getCheckedKeys() {
        console.log(this.$refs.tree.getCheckedKeys());
      },
      setCheckedNodes() {
        this.$refs.tree.setCheckedNodes([{
          id: 5,
          label: '二级 2-1'
        }, {
          id: 9,
          label: '三级 1-1-1'
        }]);
      },
      setCheckedKeys() {
        this.$refs.tree.setCheckedKeys([3]);
      },
      resetChecked() {
        this.$refs.tree.setCheckedKeys([]);
      }
    }
  }
</script>

<style scoped>
  .tree-box{
    height: 400px;
    overflow: auto;
    border: 5px solid #ddd;
    padding: 8px 0;
  }
  .user-box{
    padding-left: 18px;
  }
</style>
