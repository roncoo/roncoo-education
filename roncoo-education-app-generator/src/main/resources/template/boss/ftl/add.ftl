<#noparse><#assign base=request.contextPath /></#noparse>
<div class="bjui-pageContent">
    <form action="${r"${base}"}/${cfg.packageName!}/${table.entityPath}/save" data-toggle="validate" method="post">
        <#list table.commonFields as field>
            <div class="form-group">
                <label class="control-label x85">${field.comment!}：</label>
                <input type="text" name="${field.propertyName!}" value="" placeholder="${field.comment!}" size="20">
            </div>
        </#list>
        <#list table.fields as field>
            <#if !field.keyFlag>
                <div class="form-group">
                    <label class="control-label x85">${field.comment!}：</label>
                    <input type="text" name="${field.propertyName!}" value="" placeholder="${field.comment!}" size="20">
                </div>
            </#if>
        </#list>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li>
            <button type="button" class="btn-close">取消</button>
        </li>
        <li>
            <button type="submit" class="btn-default">添加</button>
        </li>
    </ul>
</div>