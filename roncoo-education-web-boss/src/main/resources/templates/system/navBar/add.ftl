<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/system/navBar/save" data-toggle="validate" method="post">
		<div class="form-group">
			<label class="control-label x85">导航标题：</label>
			<select name="navUrl" data-toggle="selectpicker">
				<option value="">请选择</option>
				<#list navEnum as bean>
				<option value="${bean.code!}">${bean.desc!}</option> 
				</#list>
			</select>
		</div>
		<br/ >
		<div class="form-group">
			<label class="control-label x85">跳转方式：</label>
			<select name="target" data-toggle="selectpicker">
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