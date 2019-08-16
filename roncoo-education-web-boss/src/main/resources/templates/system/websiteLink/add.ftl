<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/system/websiteLink/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">链接名称：</label>
            <input type="text" name="linkName" value="" placeholder="链接名称" size="20" data-rule="required">
        </div>
        <br/>
		<div class="form-group">
            <label class="control-label x85">友情链接：</label>
            <input type="text" name="linkUrl" value="" placeholder="友情链接" size="20" data-rule="required">
        </div>
        <br/>
        <div class="form-group">
			<label class="control-label x85">跳转方式：</label>
			<select name="linkTarget" data-toggle="selectpicker">
				<option value="_blank">新窗口打开</option>
				<option value="_self">同窗口打开</option>
			</select>
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>