<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/course/adv/update" data-toggle="validate" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
			<label class="control-label x85">广告图片：</label>
			<input type="file" id="pictureFile" name="advFile"/>
		</div>
		<div class="form-group">
            <label class="control-label x85">广告标题：</label>
            <input type="text" name="advTitle" value="${bean.advTitle!}" placeholder="广告标题" size="20" data-rule="required">
        </div>
		<div class="form-group">
            <label class="control-label x85">广告链接：</label>
            <input type="text" name="advUrl" value="${bean.advUrl!}" data-rule="required" size="20">
        </div>
        <div class="form-group">
			<label class="control-label x85">跳转方式：</label>
			<select name="advTarget" data-toggle="selectpicker">
				<option value="_blank" <#if bean.advTarget=='_blank'>selected</#if>>新窗口打开</option>
				<option value="_self" <#if bean.advTarget=='_self'>selected</#if>>同窗口打开</option>
			</select>
		</div>
		<div class="form-group">
            <label class="control-label x85">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序：</label>
            <input name="sort" value="${bean.advLocation}" type="text" placeholder="排序" size="20" data-rule="required" data-toggle="spinner"/>
        </div>
        <div class="form-group">
			<label class="control-label x85">开始时间：</label>
			<input type="text" name="beginTimeString" value="${bean.beginTime?string('yyyy-MM-dd HH:mm:ss')}" data-toggle="datepicker" size="20" data-pattern="yyyy-MM-dd HH:mm:ss">
		</div>
		<div class="form-group">
			<label class="control-label x85">结束时间：</label>
			<input type="text" name="endTimeString" value="${bean.endTime?string('yyyy-MM-dd HH:mm:ss')}" data-toggle="datepicker" size="20" data-pattern="yyyy-MM-dd HH:mm:ss">
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>