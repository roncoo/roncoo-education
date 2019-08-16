<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/course/courseCategory/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">分类名称：</label>
            <input type="text" name="categoryName" value="${bean.categoryName!}" placeholder="分类名称" size="20" required="required">
        </div>
         <div class="form-group">
            <label class="control-label x85">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序：</label>
            <input type="text" name="sort" value="${bean.sort!}" placeholder="排序" data-toggle="spinner" size="20">
        </div>
        <div class="form-group">
            <label class="control-label x85">备注：</label>
            <textarea name="remark" cols="20" rows="3" data-toggle="autoheight" class="form-control autosize">${bean.remark!}</textarea>
        </div>
        <br />
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>