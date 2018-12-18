<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/user/lecturer/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">分成：</label>
            <input type="text" name="lecturerProportion" value="${bean.lecturerProportion*100}" placeholder="讲师分成比例" size="20">%
        </div>
        <div class="form-group">
		  <label class="control-label x85">&nbsp;</label>
		  <span class="fa fa-info-circle red"> 设置讲师分成比例</span>
		</div>    
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>