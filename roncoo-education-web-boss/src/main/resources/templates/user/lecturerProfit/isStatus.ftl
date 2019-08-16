<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/user/lecturerProfit/status" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}">
		<div class="form-group">
			<label class="control-label x85">进度状态：</label>
            <select name="profitStatus" data-toggle="selectpicker" size="9">
            <option value="">--请选择--</option> <#list profitStatusEnums as profit>
            <option value="${profit.code}"<#if bean.profitStatus?? && bean.profitStatus! == profit.code>selected="selected"</#if>>${profit.desc}</option> </#list>
            </select>
		</div>
		<br />
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