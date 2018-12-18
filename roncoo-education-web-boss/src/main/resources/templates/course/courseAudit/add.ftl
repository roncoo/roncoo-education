<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/course/courseAudit/save" data-toggle="validate" method="post">
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
            <label class="control-label x85">审核状态(0:待审核,1:审核通过,2:审核不通过)：</label>
            <input type="text" name="auditStatus" value="" placeholder="审核状态(0:待审核,1:审核通过,2:审核不通过)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">审核意见：</label>
            <input type="text" name="auditOpinion" value="" placeholder="审核意见" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师用户编码：</label>
            <input type="text" name="lecturerUserNo" value="" placeholder="讲师用户编码" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">一级分类ID：</label>
            <input type="text" name="categoryId1" value="" placeholder="一级分类ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">二级分类ID：</label>
            <input type="text" name="categoryId2" value="" placeholder="二级分类ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">三级分类ID：</label>
            <input type="text" name="categoryId3" value="" placeholder="三级分类ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课程名称：</label>
            <input type="text" name="courseName" value="" placeholder="课程名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课程封面：</label>
            <input type="text" name="courseLogo" value="" placeholder="课程封面" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课程介绍ID：</label>
            <input type="text" name="introduceId" value="" placeholder="课程介绍ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否免费：1免费，0收费：</label>
            <input type="text" name="isFree" value="" placeholder="是否免费：1免费，0收费" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">原价：</label>
            <input type="text" name="courseOriginal" value="" placeholder="原价" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">优惠价：</label>
            <input type="text" name="courseDiscount" value="" placeholder="优惠价" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否上架(1:上架，0:下架)：</label>
            <input type="text" name="isPutaway" value="" placeholder="是否上架(1:上架，0:下架)" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>