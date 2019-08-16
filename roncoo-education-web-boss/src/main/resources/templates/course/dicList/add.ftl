<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/course/dicList/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">状态(1:正常，0:禁用)：</label>
            <input type="text" name="statusId" value="" placeholder="状态(1:正常，0:禁用)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">字段ID：</label>
            <input type="text" name="fieldId" value="" placeholder="字段ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">字段KEY：</label>
            <input type="text" name="fieldKey" value="" placeholder="字段KEY" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">字段VALUE：</label>
            <input type="text" name="fieldValue" value="" placeholder="字段VALUE" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>