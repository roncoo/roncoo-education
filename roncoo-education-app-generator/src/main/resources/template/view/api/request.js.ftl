import request from '@/utils/request'
<#assign profile="pc">

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list tableList as table>
    // ${table.comment}--添加
    export function ${table.entityName?uncap_first}Save(data = {}) {
    return request({
    url: '/${cfg.packageName!}/${profile}/${table.name?replace("_", "/")}/save',
    method: 'post',
    data: data
    })
    }

    // ${table.comment}--分页
    export function ${table.entityName?uncap_first}Page(params, pageCurrent = 1, pageSize = 20) {
    return request({
    url: '/${cfg.packageName!}/${profile}/${table.name?replace("_", "/")}/list',
    method: 'post',
    data: { pageCurrent: pageCurrent, pageSize: pageSize, ...params }
    })
    }

    // ${table.comment}--查看
    export function ${table.entityName?uncap_first}View(id) {
    return request.get(`/${cfg.packageName!}/${profile}/${table.name?replace("_", "/")}/view?id=<#noparse>${id}</#noparse>`)
    }

    // ${table.comment}--删除
    export function ${table.entityName?uncap_first}Delete(id) {
    return request.delete(`/${cfg.packageName!}/${profile}/${table.name?replace("_", "/")}/delete?id=<#noparse>${id}</#noparse>`)
    }

    // ${table.comment}--修改
    export function ${table.entityName?uncap_first}Edit(data = {}) {
    return request({
    url: '/${cfg.packageName!}/${profile}/${table.name?replace("_", "/")}/edit',
    method: 'put',
    data: data
    })
    }

</#list>
<#-- ----------  END 字段循环遍历  ---------->

