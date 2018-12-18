<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/course/dicList/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="${bean.gmtCreate!}" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="${bean.gmtModified!}" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">状态(1:正常，0:禁用)：</label>
            <input type="text" name="statusId" value="${bean.statusId!}" placeholder="状态(1:正常，0:禁用)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">字段ID：</label>
            <input type="text" name="fieldId" value="${bean.fieldId!}" placeholder="字段ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">字段KEY：</label>
            <input type="text" name="fieldKey" value="${bean.fieldKey!}" placeholder="字段KEY" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">字段VALUE：</label>
            <input type="text" name="fieldValue" value="${bean.fieldValue!}" placeholder="字段VALUE" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>