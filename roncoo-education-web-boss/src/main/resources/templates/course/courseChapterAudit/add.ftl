<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/course/courseChapterAudit/save" data-toggle="validate" method="post">
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
            <label class="control-label x85">审核状态(0:待审核;1:审核通过;2:审核不通过)：</label>
            <input type="text" name="auditStatus" value="" placeholder="审核状态(0:待审核;1:审核通过;2:审核不通过)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">审核意见：</label>
            <input type="text" name="auditOpinion" value="" placeholder="审核意见" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课程ID：</label>
            <input type="text" name="courseId" value="" placeholder="课程ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">章节名称：</label>
            <input type="text" name="chapterName" value="" placeholder="章节名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">章节描述：</label>
            <input type="text" name="chapterDesc" value="" placeholder="章节描述" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否免费：1免费，0收费：</label>
            <input type="text" name="isFree" value="" placeholder="是否免费：1免费，0收费" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">原价：</label>
            <input type="text" name="chapterOriginal" value="" placeholder="原价" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">优惠价：</label>
            <input type="text" name="chapterDiscount" value="" placeholder="优惠价" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>