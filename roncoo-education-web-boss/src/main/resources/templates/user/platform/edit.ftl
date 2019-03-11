<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/user/platform/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		
		<div class="form-group">
            <label class="control-label x85">客户端名称：</label>
            <input type="text" name="clientName" value="${bean.clientName!}" placeholder="客户端名称" size="20" data-rule="required">
        </div>
		<div class="form-group">
            <label for="statusId" class="control-label x85">状态：</label>
            <#list statusIdEnums as em>
            <input type="radio" id="statusId${em.code}" name="statusId" value="${em.code}" <#if bean.statusId?? && bean.statusId==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
            </#list>
        </div>
		<div class="form-group">
            <label class="control-label x85">排序：</label>
            <input type="text" name="sort" value="${bean.sort!}" size="5" data-toggle="spinner" data-min="0" data-max="100" data-step="1" data-rule="integer">
        </div>
        <div class="form-group">
            <label class="control-label x85">备注：</label>
            <textarea name="remark" cols="20" rows="3" data-toggle="autoheight" class="form-control autosize">${bean.remark!}</textarea>
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>