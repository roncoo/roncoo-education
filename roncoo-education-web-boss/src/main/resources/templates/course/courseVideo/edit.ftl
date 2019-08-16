<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/course/courseVideo/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="${bean.gmtCreate!}" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="${bean.gmtModified!}" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">状态(1:正常，0:禁用)：</label>
            <input type="text" name="statusId" value="${bean.statusId!}" placeholder="状态(1:正常，0:禁用)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师用户编号：</label>
            <input type="text" name="lecturerUserNo" value="${bean.lecturerUserNo!}" placeholder="讲师用户编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课程ID：</label>
            <input type="text" name="courseId" value="${bean.courseId!}" placeholder="课程ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">章节ID：</label>
            <input type="text" name="chapterId" value="${bean.chapterId!}" placeholder="章节ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课时ID：</label>
            <input type="text" name="periodId" value="${bean.periodId!}" placeholder="课时ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">视频名称：</label>
            <input type="text" name="videoName" value="${bean.videoName!}" placeholder="视频名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">视频状态(1待上传，2上传成功，3上传失败)：</label>
            <input type="text" name="videoStatus" value="${bean.videoStatus!}" placeholder="视频状态(1待上传，2上传成功，3上传失败)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">时长：</label>
            <input type="text" name="videoLength" value="${bean.videoLength!}" placeholder="时长" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">视频ID：</label>
            <input type="text" name="videoVid" value="${bean.videoVid!}" placeholder="视频ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">阿里云oas：</label>
            <input type="text" name="videoOasId" value="${bean.videoOasId!}" placeholder="阿里云oas" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>