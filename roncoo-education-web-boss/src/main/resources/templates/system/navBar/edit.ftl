<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/system/navBar/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">导航标题：</label>
            <input name="navTitle" value="${bean.navTitle}" type="text" placeholder="导航标题" size="20" data-rule="required" />
        </div>
        <div class="form-group">
			<label class="control-label x85">跳转方式：</label>
			<select name="target" data-toggle="selectpicker">
				<option value="_blank" <#if bean.target=='_blank'>selected</#if>>新窗口打开</option>
				<option value="_self" <#if bean.target=='_self'>selected</#if>>同窗口打开</option>
			</select>
		</div>
		<div class="form-group">
            <label class="control-label x85">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序：</label>
            <input name="sort" value="${bean.sort}" type="text" placeholder="排序" data-toggle="spinner" size="20" data-rule="required" />
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>