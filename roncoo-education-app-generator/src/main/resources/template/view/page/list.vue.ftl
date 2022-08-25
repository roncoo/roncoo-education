<template>
    <div class="app-container">

        <#--查询条件-->
        <el-form class="filter-container" inline label-width="90px" size="mini">
            <el-form-item class="filter-item" label="">
                <el-button class="filter-item" type="primary" @click="listForPage">查询</el-button>
                <el-button class="filter-item" @click="handleReset">重置</el-button>
            </el-form-item>

            <#--新增-->
            <el-button plain type="success" size="mini" @click="handleAddRow">新增</el-button>
        </el-form>

        <#--表格数据展示-->
        <el-table
                :data="list"
                size="mini"
                element-loading-text="Loading"
                border
                fit
                highlight-current-row
        >

            <el-table-column align="center" type="index" label="序号" width="80"/>
            <#list table.fields as field>
                <#if !field.keyFlag>
                    <el-table-column label="${field.comment}" prop="${field.propertyName!}"/>
                </#if>
            </#list>
            <el-table-column fixed="right" label="操作">
                <template slot-scope="scope">
                    <el-button plain type="primary" size="mini" @click="handleUpdateRow(scope.row.id)">编辑</el-button>
                    <el-button plain type="danger" size="mini" @click="handleDeleteRow(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <#-- 分页插件-->
        <el-pagination
                :current-page="page.pageCurrent"
                :page-sizes="[10, 20, 50, 100]"
                :page-size="page.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="page.totalCount"
                background
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
        />
    </div>
</template>

<script>
    import {${table.entityName?uncap_first}Page, ${table.entityName?uncap_first}Delete} from '@/api/${cfg.packageName!}'

    export default {
        data() {
            return {
                page: {
                    beginPageIndex: 1,
                    pageCurrent: 1,
                    endPageIndex: 8,
                    pageSize: 20,
                    totalCount: 0,
                    totalPage: 0
                },
                list: [],
                map: {},
                listLoading: true
            }
        },
        created() {
            this.listForPage()
        },
        methods: {
            handleDeleteRow(row) {
                {
                    const body = '确定要删除？'
                    this.$confirm(body, '删除确认', {
                        confirmButtonText: '确认',
                        cancelButtonText: '取消'
                    }).then(() => {
                        this.loading.start();
                        ${table.entityName?uncap_first}Delete(row.id).then(res => {
                            this.$message.success(res.data);
                            this.listForPage();
                        })
                    })
                }
            },
            handleSizeChange(size) {
                this.page.pageSize = size
                this.page.pageCurrent = 1
                this.listForPage();
            },
            handleCurrentChange(current) {
                this.page.pageCurrent = current
                this.listForPage();
            },
            handleReset() {
                this.map = {}
                this.page.pageCurrent = 1
                this.listForPage();
            },
            handleAddRow() {
                this.$router.push('/${cfg.packageName!}/${table.name?replace("_","/")}/add')
            },
            handleUpdateRow(id) {
                this.$router.push({path: '/${cfg.packageName!}/${table.name?replace("_","/")}/edit', query: {id: id}})
            },
            listForPage() {
                // this.listLoading = true
                this.loading.start()
                ${table.entityName?uncap_first}Page(this.map, this.page.pageCurrent, this.page.pageSize).then(res => {
                    this.list = res.data.list
                    this.page.pageCurrent = res.data.pageCurrent
                    this.page.totalCount = res.data.totalCount
                    this.page.pageSize = res.data.pageSize
                })
            }
        }
    }
</script>
