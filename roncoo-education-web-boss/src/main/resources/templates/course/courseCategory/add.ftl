<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/course/courseCategory/save" data-toggle="validate" method="post">
		<input type="hidden" name="parentId" value="${bean.parentId}"/>
		<input type="hidden" name="floor" value="${bean.floor}"/>
		<input type="hidden" name="categoryType" value="${bean.categoryType}"/>
		<div class="form-group">
            <label class="control-label x85">分类名称：</label>
            <input type="text" name="categoryName" value="" placeholder="分类名称" size="20" data-rule="required">
        </div>
        <div class="form-group">
            <label class="control-label x85">备注：</label>
            <textarea name="remark" cols="20" rows="3" data-toggle="autoheight" class="form-control autosize"></textarea>
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>