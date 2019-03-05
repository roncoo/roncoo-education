<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/system/msgTemplate/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">模&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;板：</label>
            <textarea name="remark" cols="20" rows="3" data-toggle="autoheight" class="form-control autosize">${bean.remark!}</textarea>
        </div>        
        
         <div class="form-group">
            <label class="control-label x85">&nbsp;</label>
            <span class="fa fa-info-circle red">{{name}}=用户名称</span>
        </div>
        <div class="form-group">
            <label class="control-label x85">&nbsp;</label>
            <span class="fa fa-info-circle red">{{courseName}}=课程名称</span>
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>