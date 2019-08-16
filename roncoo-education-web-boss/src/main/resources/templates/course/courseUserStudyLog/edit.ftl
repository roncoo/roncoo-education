<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/course/courseUserStudyLog/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="${bean.gmtCreate!}" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课程编号：</label>
            <input type="text" name="courseNo" value="${bean.courseNo!}" placeholder="课程编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">章节编号：</label>
            <input type="text" name="chapterNo" value="${bean.chapterNo!}" placeholder="章节编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课时编号：</label>
            <input type="text" name="periodNo" value="${bean.periodNo!}" placeholder="课时编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">用户编号：</label>
            <input type="text" name="userNo" value="${bean.userNo!}" placeholder="用户编号" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>