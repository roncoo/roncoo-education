<#list tableList as table>
    <#if table_has_next>
        {
        path: '/${cfg.packageName!}/${table.name?replace("_", "/")}',
        component: Layout,
        children: [
        {
        path: 'list',
        component: () => import('@/views/${cfg.packageName!}/${table.entityName?uncap_first}/list'),
        meta: { title: '${table.comment}列表' }
        },
        {
        path: 'add',
        component: () => import('@/views/${cfg.packageName!}/${table.entityName?uncap_first}/add'),
        meta: { title: '添加${table.comment}' }
        },
        {
        path: 'edit',
        component: () => import('@/views/${cfg.packageName!}/${table.entityName?uncap_first}/edit'),
        meta: { title: '编辑${table.comment}' }
        }
        ]
        },
    <#else>
        {
        path: '${table.name?replace("_", "/")}',
        meta: { title: '${table.comment}' },
        component: () => import('@/views/${cfg.packageName!}/${table.entityName?uncap_first}'),
        children: [
        {
        path: 'list',
        component: () => import('@/views/${cfg.packageName!}/${table.entityName?uncap_first}/list'),
        meta: { title: '${table.comment}列表' }
        },
        {
        path: 'add',
        component: () => import('@/views/${cfg.packageName!}/${table.entityName?uncap_first}/add'),
        meta: { title: '添加${table.comment}' }
        },
        {
        path: 'edit',
        component: () => import('@/views/${cfg.packageName!}/${table.entityName?uncap_first}/edit'),
        meta: { title: '编辑${table.comment}' }
        }
        ]
        }
    </#if>
</#list>
