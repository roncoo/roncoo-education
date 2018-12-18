<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/course/orderInfo/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}">
		<div class="form-group">
			<label>后台备注：</label>
			<textarea name="remark" cols="20" rows="3" data-toggle="autoheight" class="form-control autosize">${bean.remark!}</textarea>
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close">取消</button></li>
		<li><button type="submit" class="btn-default">修改</button></li>
	</ul>
</div>