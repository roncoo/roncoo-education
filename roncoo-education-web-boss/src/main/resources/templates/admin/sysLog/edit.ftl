<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/admin/operationLog/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="${bean.gmtCreate!}" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">机构号：</label>
            <input type="text" name="orgNo" value="${bean.orgNo!}" placeholder="机构号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">操作人：</label>
            <input type="text" name="sysUserId" value="${bean.sysUserId!}" placeholder="操作人" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">真实姓名：</label>
            <input type="text" name="realName" value="${bean.realName!}" placeholder="真实姓名" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>