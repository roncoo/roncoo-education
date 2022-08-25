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
                <#if (field_index+1) % 2 == 0 && field_has_next>
            </el-row>

            <el-row>
                </#if>
                </#if>
                </#list>
            </el-row>

            <el-form-item>
                <el-button @click="handleClose">关 闭</el-button>
                <el-button type="primary" plain @click="submitForm('form')">更新数据</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import {${table.entityName?uncap_first}View, ${table.entityName?uncap_first}Edit} from '@/api/${cfg.packageName!}'

    export default {
        name: 'Edit${table.entityName}',
        data() {
            return {
                form: {},
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
            const view = this.$route
            this.getViewInfo(view.query.id)
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
            getViewInfo(id) {
                this.loading.show()
                ${table.entityName?uncap_first}View(id).then(res => {
                    this.loading.hide()
                    this.form = res.data
                }).catch(() => {
                    this.loading.hide()
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
                // 编辑
                ${table.entityName?uncap_first}Edit(this.form).then(res => {
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
