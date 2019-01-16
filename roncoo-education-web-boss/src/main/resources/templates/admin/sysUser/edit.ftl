<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/admin/sysUser/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
        <div class="form-group">
            <label class="control-label x85">手机：</label>
            <input type="text" name="mobile" value="${bean.mobile!}" placeholder="手机" size="20" data-rule="required,mobile">
        </div>
        <div class="form-group">
            <label class="control-label x85">姓名：</label>
            <input type="text" name="realName" value="${bean.realName!}" placeholder="真实姓名" size="20" data-rule="required">
        </div>
        <div class="form-group">
            <label for="statusId" class="control-label x85">备注：</label>
            <textarea name="remark" cols="20" rows="3" data-toggle="autoheight" class="form-control autosize">${bean.remark!}</textarea>
        </div>
        <div class="form-group">
            <label for="statusId" class="control-label x85">状态：</label>
            <#list statusIdEnums as em>
            <input type="radio" id="statusId${em.code}" name="statusId" value="${em.code}" <#if bean.statusId?? && bean.statusId==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
            </#list>
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>