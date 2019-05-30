<template>
  <div class="app-container pms-menu-container" v-loading="ctrl.loading">
    <div class="tree-box">
      <el-tree
        ref="tree2"
        :data="list"
        :props="defaultProps"
        icon-class="el-icon-share"
        class="filter-tree"
        default-expand-all>
        <div class="custom-tree-node" slot-scope="{ node, data }">
          <i class="el-icon-share"></i>
          <span @click="handleClick(data)" :ref="`ref${data.id}`">{{ node.label }}</span>
          <span class="tree-btn-box">
            <el-button
              type="text"
              title="添加"
              icon="el-icon-circle-plus-outline"
              @click="() => handleAddRow(data)">
            </el-button>
            <el-button
              type="text"
              title="删除"
              class="delete-btn"
              icon="el-icon-remove-outline"
              @click="() => handleRemove(data)">
            </el-button>
          </span>
        </div>
      </el-tree>
    </div>
    <div class="edit-box">
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="上级菜单" prop="pName">
          <el-input v-model="form.pName" :disabled="!!form.id"></el-input>
        </el-form-item>
        <el-form-item label="菜单名称" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="菜单编号" prop="number">
          <el-input v-model="form.number"></el-input>
        </el-form-item>
        <el-form-item label="请求URL" prop="url">
          <el-input v-model="form.url"></el-input>
        </el-form-item>
        <div class="" style="margin-left: 80px;">
          <el-button class="btn-item" type="success" size="mini" icon="el-icon-edit" @click="submitForm">确定提交</el-button>
          <el-button class="btn-item" type="primary" size="mini" icon="el-icon-refresh" @click="handleReset">重置</el-button>
        </div>

      </el-form>
    </div>
    <div class="btn-box clearfix">
      <el-button class="btn-item" type="success" size="mini" icon="el-icon-edit" @click="handleAddRow">添加</el-button>
      <el-button class="btn-item" type="primary" size="mini" icon="el-icon-refresh" @click="getMenuList">刷新</el-button>
    </div>

  </div>
</template>

<script>
import * as apis from '@/api/role'
export default {

  data() {
    return {
      ctrl: {
        loading: false
      },
      list: [],
      form: {
        pName: '无',
        level: 1,
        parentId: 0
      },
      filterText: '',
      defaultProps: {
        children: 'menuList',
        label: 'name'
      },
      rules: {
        pName: [
          { required: true, message: '请输入上级菜单', trigger: 'blur', autocomplete: 'on' }
        ],
        name: [
          { required: true, message: '请输入菜单名称', trigger: 'blur' }
        ],
        number: [
          { required: true, message: '请输入菜单编号', trigger: 'blur' }
        ],
        url: [
          { required: true, message: '请输入请求URL', trigger: 'blur' }
        ]
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val)
    }
  },
  created() {
    this.getMenuList()
  },
  methods: {
    getMenuList() {
      this.ctrl.loading = true
      apis.getMenuAll().then(response => {
        console.log(response)
        this.ctrl.loading = false
        if (response.code === 200) {
          this.list = response.data
        }
      })
    },
    handleAddRow(data) {
      const pdata = {
        pName: '无',
        level: 1,
        parentId: 0
      }
      if (data.name) {
        pdata.pName = data.name;
        pdata.level = data.level + 1;
        pdata.parentId = data.id;
      }
      this.form = pdata
    },
    handleRemove(data) {
      console.log(data)
      this.$confirm(`确认删除 [${data.name}]?`).then(() => {
        apis.removeMenu(data.id).then(res => {
          console.log(res)
        })
      })
    },
    submitForm() {
      this.$refs.form.validate((valid) => {
        console.log(valid)
        if (valid) {
          this.handleSubmit()
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    handleSubmit() {
      console.log(this.form)
      if (!this.form.parentId) {
        this.form.parentId = 0
        this.form.isLeaf = 1
      }
      // return false
      this.loading.show();
      if (this.form.id) {
        apis.updateMenu(this.form).then(res => {
          // console.log(res)
          this.loading.hide();
          if (res.code === 200) {
            this.tips('修改成功', 'success')
            this.getMenuList()
          } else {
            this.tips(res.msg, 'error')
          }
        })
      } else {
        apis.addMenu(this.form).then(res => {
          // console.log(res)
          this.loading.hide();
          if (res.code === 200) {
            this.tips('添加成功', 'success')
            this.getMenuList()
          } else {
            this.tips(res.msg, 'error')
          }
        })
      }
    },
    handleClick(item) {
      // console.log(item)
      if (item.parentId) {
        item.pName = this.$refs[`ref${item.parentId}`].innerHTML;
      } else {
        item.pName = '无'
      }
      this.form = item;
    },
    handleReset() {
      this.form = {}
    }
  }
}
</script>
<style lang="scss">
  .pms-menu-container {
    .tree-box {
      float: left;
      width: 300px;
      height: 500px;
      overflow: auto;
      border: 3px solid #ddd;
      padding: 5px 0;

    }

    .edit-box {
      width: 600px;
      padding: 20px;
      float: left;
    }

    .btn-box {
      clear: both;
      width: 240px;
      padding-top: 8px;
      text-align: right;
    }

    .custom-tree-node {
      width: 100%;
      font-size: 14px;
      .tree-btn-box {
        display: none;
        margin-left: 12px;

        .el-button {
          margin: 0;
        }
      }
    }

    .custom-tree-node:hover {
      .tree-btn-box {
        display: inline;
      }
    }
    .is-current {
      &>.el-tree-node__content{
        background-color: #eee;
        .tree-btn-box {
          display: inline;
        }
      }
    }

    .delete-btn {
      color: #f00;
    }
  }
</style>

