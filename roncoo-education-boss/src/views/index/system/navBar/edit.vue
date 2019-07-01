<template>
  <!--弹窗-->
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :before-close="handleClose">

    <el-form :model="formData" :rules="rules" ref="formData">
      <el-form-item label="广告图片" prop="permissionName">
        <el-input v-model="formData.permissionName"></el-input>
      </el-form-item>
      <el-form-item label="广告标题" prop="permission">
        <el-input v-model="formData.permission" :disabled="!!formData.id"></el-input>
        <el-tag type="danger" v-if="!formData.id">提示：权限标识添加后将不可修改，请确保准确性！</el-tag>
      </el-form-item>
      <el-form-item label="权限描述" prop="remark">
        <el-input v-model="formData.remark" type="textarea"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="submitForm('formData')">确 定</el-button>
    </div>
  </el-dialog>
</template>
