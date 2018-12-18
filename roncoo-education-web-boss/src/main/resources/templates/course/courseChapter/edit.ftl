<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/course/courseChapter/update" data-toggle="validate" method="post">
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
            <label class="control-label x85">章节名称：</label>
            <input type="text" name="chapterName" value="${bean.chapterName!}" placeholder="章节名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">章节描述：</label>
            <input type="text" name="chapterDesc" value="${bean.chapterDesc!}" placeholder="章节描述" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否免费：1免费，0收费：</label>
            <input type="text" name="isFree" value="${bean.isFree!}" placeholder="是否免费：1免费，0收费" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">原价：</label>
            <input type="text" name="chapterOriginal" value="${bean.chapterOriginal!}" placeholder="原价" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">优惠价：</label>
            <input type="text" name="chapterDiscount" value="${bean.chapterDiscount!}" placeholder="优惠价" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>