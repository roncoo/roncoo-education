<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/course/zone/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">名称：</label>
            <input type="text" name="zoneName" value="${bean.zoneName!}" placeholder="名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">描述：</label>
            <textarea name="zoneDesc" cols="20" rows="3" data-toggle="autoheight" class="form-control autosize">${bean.zoneDesc!}</textarea>
        </div>
        <div class="form-group">
            <label class="control-label x85">排序：</label>
            <input type="text" name="sort" value="${bean.sort!}" size="20" data-toggle="spinner" data-min="0" data-max="100" data-step="1" data-rule="integer">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>