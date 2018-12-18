<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/user/lecturerAudit/audit" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}">
		<div class="form-group">
			<label class="control-label x85">审核结果：</label>
			<input type="radio" name="auditStatus" value=1 data-toggle="icheck" data-label="通过" checked="checked">
			&nbsp;&nbsp;
			<input type="radio" name="auditStatus" value=2 data-toggle="icheck" data-label="不通过">
		</div>
		<br />
		<div class="form-group">
			<label class="control-label x85">审核意见：</label>
			<textarea cols="20" rows="3" name="auditOpinion" data-toggle="autoheight" class="form-control" >系统默认通过</textarea>
		</div>
		<br />
	</form>
</div>

<div class="bjui-pageFooter">
	<ul>
		<li>
			<button type="button" class="btn-close" data-icon="close">关闭</button>
		</li>
		<li>
			<button type="submit" class="btn-default" data-icon="save">审核</button>
		</li>
	</ul>
</div>