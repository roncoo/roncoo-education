<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/course/courseUserStudyLog/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课程编号：</label>
            <input type="text" name="courseNo" value="" placeholder="课程编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">章节编号：</label>
            <input type="text" name="chapterNo" value="" placeholder="章节编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课时编号：</label>
            <input type="text" name="periodNo" value="" placeholder="课时编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">用户编号：</label>
            <input type="text" name="userNo" value="" placeholder="用户编号" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>