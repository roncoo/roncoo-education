<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/course/zone/save" data-toggle="validate" method="post">
		
		<div class="form-group">
            <label class="control-label x85">专区名称：</label>
            <input type="text" name="zoneName" value="" placeholder="专区名称" size="20" data-rule="required">
        </div>
		<div class="form-group">
            <label class="control-label x85">描述：</label>
            <textarea name="zoneDesc" type="text" cols="20" rows="3"></textarea>
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>