<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/course/courseVideo/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">状态(1:正常，0:禁用)：</label>
            <input type="text" name="statusId" value="" placeholder="状态(1:正常，0:禁用)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师用户编号：</label>
            <input type="text" name="lecturerUserNo" value="" placeholder="讲师用户编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课程ID：</label>
            <input type="text" name="courseId" value="" placeholder="课程ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">章节ID：</label>
            <input type="text" name="chapterId" value="" placeholder="章节ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课时ID：</label>
            <input type="text" name="periodId" value="" placeholder="课时ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">视频名称：</label>
            <input type="text" name="videoName" value="" placeholder="视频名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">视频状态(1待上传，2上传成功，3上传失败)：</label>
            <input type="text" name="videoStatus" value="" placeholder="视频状态(1待上传，2上传成功，3上传失败)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">时长：</label>
            <input type="text" name="videoLength" value="" placeholder="时长" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">视频ID：</label>
            <input type="text" name="videoVid" value="" placeholder="视频ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">阿里云oas：</label>
            <input type="text" name="videoOasId" value="" placeholder="阿里云oas" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>