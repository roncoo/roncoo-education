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
			<label class="control-label">1 正常 2 冻结 9 注销：</label>${bean.statusId!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">父ID：</label>${bean.parentId!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">菜单名称：</label>${bean.menuName!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">菜单路径：</label>${bean.menuUrl!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">目标名称：</label>${bean.targetName!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">菜单图标：</label>${bean.menuIcon!}
		</div>
	</div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
    </ul>
</div>