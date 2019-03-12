<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/user/platform/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">客户端名称：</label>
            <input type="text" name="clientName" value="" placeholder="客户端名称" size="20" data-rule="required">
        </div>
        <div class="form-group">
            <label class="control-label x85">备注：</label>
            <textarea name="remark" cols="20" rows="3" data-toggle="autoheight" class="form-control autosize"></textarea>
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>