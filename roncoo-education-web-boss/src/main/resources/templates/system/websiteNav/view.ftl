<div class="bjui-pageContent">
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">导航名称：</label>${bean.navName!}
		</div>
	</div>
	<br />
	<br />
	<div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序：</label>${bean.sort!}
		</div>
		<div class="col-lg-6">
			<label class="control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list>
		</div>
	</div>
	<br />
	<br />
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">创建时间：</label>${bean.gmtCreate?string('yyyy-MM-dd HH:mm:ss')!}
		</div>
	</div>
	<br />
	<br />
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">修改时间：</label>${bean.gmtModified?string('yyyy-MM-dd HH:mm:ss')!}
		</div>
	</div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
    </ul>
</div>