<div class="bjui-pageContent">
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">创建时间：</label>${bean.gmtCreate!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">修改时间：</label>${bean.gmtModified!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">状态(1:正常，0:禁用)：</label>${bean.statusId!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">审核状态(0:待审核;1:审核通过;2:审核不通过)：</label>${bean.auditStatus!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">审核意见：</label>${bean.auditOpinion!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">课程ID：</label>${bean.courseId!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">章节名称：</label>${bean.chapterName!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">章节描述：</label>${bean.chapterDesc!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">是否免费：1免费，0收费：</label>${bean.isFree!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">原价：</label>${bean.chapterOriginal!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">优惠价：</label>${bean.chapterDiscount!}
		</div>
	</div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
    </ul>
</div>