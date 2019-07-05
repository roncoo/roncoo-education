<template>
  <div class="pad20">
    <div>
      <el-form :inline="true" size="mini">
        <el-form-item>
          <el-button class="filter-item" type="success" @click="handleAddRow">
            新增
          </el-button>
        </el-form-item>
      <el-form-item label="分类12">
        <el-input v-model="map.navName"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <template>
          <el-select v-model="map.statusId" placeholder="全部">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </template>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :loading="ctrl.loading" @click="handleCheck">查询</el-button>
        <el-button class="filter-item" @click="handleReset">重置
        </el-button>
      </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table v-loading="ctrl.loading" size="medium" :data="page.list" stripe border style="width: 100%">
        <el-table-column type="index" label="序号" width="40">
        </el-table-column>
        <el-table-column prop="navName" label="导航名称">
        </el-table-column>
        <el-table-column prop="sort" label="排序">
        </el-table-column>
        <el-table-column
          label="状态"
          prop="statusId"
          align="center"
          width="200">
          <template slot-scope="scope">
            <el-switch
              @change="handleChangeStatus(scope.$index, scope.row, $event)"
              v-model="scope.row.statusId"
              :active-value="1"
              :inactive-value="0"
              active-text="启用"
              inactive-text="禁用">
            </el-switch>
          </template>
        </el-table-column>
       <el-table-column
        fixed="right"
        label="操作"
        width="340">
        <template slot-scope="scope">
          <ul class="list-item-actions">
            <li>
              <el-button type="primary" @click="handleUpdateRow(scope.row)" size="mini">修改</el-button>
              <el-button type="warning" @click="handleDelRow(scope.row)" size="mini">删除</el-button>
              <el-button type="warning" @click="handleAddSubclass(scope.row)" size="mini">添加</el-button>
            </li>
          </ul>
        </template>
      </el-table-column>
      </el-table>
      <div class="mgt20">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :page-size="page.numPerPage"
          :page-sizes="[20, 50, 100, 200, 500, 1000]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="page.totalCount">
        </el-pagination>
    </div>
    </div>
    <edit :visible="ctrl.dialogVisible" :formData="formdata" :title="ctrl.dialogTitle" @close-callback="closeCallback"></edit>
</div>
</template>
