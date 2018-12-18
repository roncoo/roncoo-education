<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/course/courseChapterPeriod/update" data-toggle="validate" method="post">
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
            <label class="control-label x85">课程ID：</label>
            <input type="text" name="courseId" value="${bean.courseId!}" placeholder="课程ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">章节ID：</label>
            <input type="text" name="chapterId" value="${bean.chapterId!}" placeholder="章节ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课时名称：</label>
            <input type="text" name="periodName" value="${bean.periodName!}" placeholder="课时名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课时描述：</label>
            <input type="text" name="periodDesc" value="${bean.periodDesc!}" placeholder="课时描述" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否免费：1免费，0收费：</label>
            <input type="text" name="isFree" value="${bean.isFree!}" placeholder="是否免费：1免费，0收费" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">原价：</label>
            <input type="text" name="periodOriginal" value="${bean.periodOriginal!}" placeholder="原价" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">优惠价：</label>
            <input type="text" name="periodDiscount" value="${bean.periodDiscount!}" placeholder="优惠价" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">购买人数：</label>
            <input type="text" name="countBuy" value="${bean.countBuy!}" placeholder="购买人数" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">学习人数：</label>
            <input type="text" name="countStudy" value="${bean.countStudy!}" placeholder="学习人数" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否存在文档(1存在，0否)：</label>
            <input type="text" name="isDoc" value="${bean.isDoc!}" placeholder="是否存在文档(1存在，0否)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">文档名称：</label>
            <input type="text" name="docName" value="${bean.docName!}" placeholder="文档名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">文档地址：</label>
            <input type="text" name="docUrl" value="${bean.docUrl!}" placeholder="文档地址" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否存在视频(1存在，0否)：</label>
            <input type="text" name="isVideo" value="${bean.isVideo!}" placeholder="是否存在视频(1存在，0否)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">视频编号：</label>
            <input type="text" name="videoNo" value="${bean.videoNo!}" placeholder="视频编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">视频名称：</label>
            <input type="text" name="videoName" value="${bean.videoName!}" placeholder="视频名称" size="20">
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