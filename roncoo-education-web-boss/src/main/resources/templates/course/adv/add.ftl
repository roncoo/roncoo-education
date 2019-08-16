<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/course/adv/save" data-toggle="validate" method="post" enctype="multipart/form-data">
	<input type="hidden" name="platShow" value="${bean.platShow}"/>
		<div class="form-group">
			<label class="control-label x85">广告图片：</label>
			<input type="file" id="pictureFile" name="advFile" data-rule="required"/>
		</div>
		<div class="form-group">
            <label class="control-label x85">广告标题：</label>
            <input type="text" name="advTitle" value="" placeholder="广告标题" size="20" data-rule="required" data-rule="required">
        </div>
		<div class="form-group">
            <label class="control-label x85">广告链接：</label>
            <input type="text" name="advUrl" value="" placeholder="广告链接" size="20" data-rule="required">
        </div>
        <div class="form-group">
			<label class="control-label x85">跳转方式：</label>
			<select name="target" data-toggle="selectpicker">
				<option value="_blank">新窗口打开</option>
				<option value="_self">同窗口打开</option>
			</select>
		</div>
		<div class="form-group">
			<label class="control-label x85">开始时间：</label>
			<input type="text" name="beginTimeString" data-toggle="datepicker" data-rule="dateTime" data-pattern="yyyy-MM-dd HH:mm:ss">
		</div>
		<div class="form-group">
			<label class="control-label x85">结束时间：</label>
			<input type="text" name="endTimeString" data-toggle="datepicker" data-rule="dateTime" data-pattern="yyyy-MM-dd HH:mm:ss">
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>