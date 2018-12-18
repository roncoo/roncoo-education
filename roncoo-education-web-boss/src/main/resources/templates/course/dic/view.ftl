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
			<label class="control-label">字段名：</label>${bean.fieldName!}
		</div>
	</div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
    </ul>
</div>