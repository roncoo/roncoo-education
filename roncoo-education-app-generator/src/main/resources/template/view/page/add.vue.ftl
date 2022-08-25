<template>
    <div class="app-container">
        <el-form ref="form" :model="form" :rules="rules" label-width="150px">
            <el-alert class="title" :closable="false" title="基础信息" type="info"/>
            <el-row>
                <#list table.fields as field>
                <#if !field.keyFlag>
                <el-col :span="12">
                    <el-form-item label="${field.comment}" prop="${field.propertyName!}">
                        <el-input v-model="form.${field.propertyName!}" class="form-group"/>
                    </el-form-item>
                </el-col>
                <#if (field_index) % 2 == 0 && field_has_next>
            </el-row>

            <el-row>
                </#if>
                </#if>
                </#list>
            </el-row>

            <el-form-item>
                <el-button @click="handleClose">关 闭</el-button>
                <el-button type="primary" plain @click="submitForm('form')">立即创建</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import {${table.entityName?uncap_first}Save} from '@/api/${cfg.packageName!}'

    export default {
        name: 'Add${table.entityName}',
        data() {
            return {
                form: {},
                opts: {},
                rules: {
                    <#list table.fields as field>
                    <#if !field.keyFlag>
                    <#if field_has_next>
                    ${field.propertyName!}: [
                        {required: true, message: '请输入${field.comment}', trigger: 'blur'}
                    ],
                    <#else>
                    ${field.propertyName!}: [
                        {required: true, message: '请输入${field.comment}', trigger: 'blur'}
                    ]
                    </#if>
                    </#if>
                    </#list>
                }
            }
        },
        mounted() {
        },
        methods: {
            handleClose() {
                const view = {
                    key: this.$route.fullPath
                }
                this.$store.dispatch('delView', view).then(() => {
                    this.$router.push('/${cfg.packageName!}/${table.name?replace("_","/")}/list');
                })
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.onSubmit()
                    } else {
                        return false;
                    }
                })
            },
            onSubmit() {
                this.loading.show()
                // 新增
                ${table.entityName?uncap_first}Save(this.form).then(res => {
                    this.loading.hide()
                    this.tips(res.data, 'success');
                    this.handleClose();
                }).catch(() => {
                    this.loading.hide()
                })
            }
        }
    }
</script>
<style scoped>
    .title {
        margin-bottom: 20px;
    }

    .form-group {
        width: 100%;
        max-width: 300px;
    }
</style>
