<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/course/courseRecommend/save" data-toggle="validate" method="post">
	    <input type="hidden" name="categoryId" value="${categoryId}"/>
		<div class="form-group">
            <label class="control-label x85">课程：</label>
            <input type="text" name="courseName" data-rule="required" value="请选择课程" size="30" data-toggle="lookup" data-url="${base}/course/courseRecommend/courseList?categoryId1=${categoryId!}" data-width="1000" data-height="800">
			<input type="hidden" id="courseId" name="courseId" data-rule="required" />
        </div>
		
		<div class="form-group">
            <label class="control-label x85">排序：</label>
            <input type="text" name="sort" value="1" placeholder="排序" size="30">
        </div>
        <div class="form-group">
            <label class="control-label x85">&nbsp;</label>
            <span class="fa fa-info-circle red"> 因为展示的限制，课程只展示最前面的5个</span>
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>