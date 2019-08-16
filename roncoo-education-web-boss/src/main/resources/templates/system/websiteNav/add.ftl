<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/system/websiteNav/save" data-toggle="validate" method="post">
	<input type="hidden" name="parentId" value="${parentId}"/>
		<div class="form-group">
            <label class="control-label x85">导航名称：</label>
            <input type="text" name="navName" value="" placeholder="导航名称" size="20" data-rule="required">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>