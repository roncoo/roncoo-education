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
			<label class="control-label">状态(1有效, 0无效)：</label>${bean.statusId!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">站点标题：</label>${bean.websiteTitle!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">站点关键词：</label>${bean.websiteKeyword!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">站点描述：</label>${bean.websiteDesc!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">是否开启统计：</label>${bean.isEnableStatistics!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">统计代码：</label>${bean.statisticsCode!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">是否显示客服信息：</label>${bean.isShowService!}
		</div>
	</div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
    </ul>
</div>