<#noparse><#include "/macro/base.ftl" /></#noparse>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${r"${base}"}/${cfg.packageName!}/${table.entityPath}/list" method="post">
        ${r"<@pageHeadr />"}
        <div class="bjui-searchBar">
            <label>账号：</label>
            <input type="text" name="number" value="" class="form-control" size="10">&nbsp;

            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>

            <br/>
            <br/>
            <a href="${r"${base!}"}/${cfg.project!}/${table.entityPath}/add" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="${table.entityPath}-add" data-options="{title:'添加', height:500}">添加 </a>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
        <thead>
        <tr>
            <th width="30">序号</th>
            <#list table.fields as field>
                <#if !field.keyFlag>
                    <th>${field.comment}</th>
                </#if>
            </#list>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#noparse><#if page??>
            <#list page.list as bean>
        <tr>
            <td align="center">${bean_index+1}</td></#noparse>
            <#list table.fields as field>
                <#if !field.keyFlag>
                    <td>${r"${base."}${field.propertyName!}${r"!}"}</td>
                </#if>
            </#list>
            <td>
                <a href="${r"${base!}"}/${cfg.project!}/${table.entityPath}/view?id=${r"${bean.id}"}" class="btn btn-blue" data-toggle="dialog" data-id="${table.entityPath}-view" data-options="{title:'查看', height:500}">查看</a>
                <a href="${r"${base!}"}/${cfg.project!}/${table.entityPath}/edit?id=${r"${bean.id}"}" class="btn btn-green" data-toggle="dialog" data-id="${table.entityPath}-edit" data-options="{title:'修改', height:500}">修改</a>
                <a href="${r"${base!}"}/${cfg.project!}/${table.entityPath}/delete?id=${r"${bean.id}"}" class="btn btn-red" data-toggle="doajax" data-id="${table.entityPath}-delete" data-confirm-msg="确定要删除吗？">删除</a>
            </td>
        </tr>
        ${r"</#list>"}
        ${r"</#if>"}
        </tbody>
    </table>
</div>
${r"<@pageFooter />"}
