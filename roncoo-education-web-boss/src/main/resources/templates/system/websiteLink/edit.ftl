<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/system/websiteLink/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">链接名称：</label>
            <input type="text" name="linkName" value="${bean.linkName!}" placeholder="链接名称" size="20" data-rule="required">
        </div>
        <br/>
		<div class="form-group">
			<label class="control-label x85">友情链接：</label>
			<input name="linkUrl" type="text" value="${bean.linkUrl}" placeholder="友情链接" size="20" data-rule="required"/>
		</div>
		<br/>
		<div class="form-group">
			<label class="control-label x85">跳转方式：</label>
			<select name="linkTarget" data-toggle="selectpicker">
				<option value="_blank" <#if bean.linkTarget=='_blank'>selected</#if>>新窗口打开</option>
				<option value="_self" <#if bean.linkTarget=='_self'>selected</#if>>同窗口打开</option>
			</select>
		</div>
		<br/>
		<div class="form-group">
            <label class="control-label x85">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序：</label>
            <input name="sort" value="${bean.sort}" type="text" placeholder="排序" size="20" data-toggle="spinner" data-rule="required" />
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>