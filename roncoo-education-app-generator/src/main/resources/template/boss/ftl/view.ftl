<div class="bjui-pageContent">
    <#list table.fields as field>
        <#if !field.keyFlag>
            <div class="form-group">
                <div class="col-lg-6">
                    <label class="control-label">${field.comment}：</label>${r"${base."}${field.propertyName!}${r"!}"}
                </div>
            </div>
        </#if>
    </#list>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li>
            <button type="button" class="btn-close">取消</button>
        </li>
    </ul>
</div>