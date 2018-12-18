<div class="bjui-pageContent">
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">专区名称：</label>${bean.zoneName!}
		</div>
		<div class="col-lg-6">
			<label class="control-label">平台显示：</label>
			<#list zoneLocationEnums as em><#if bean.zoneLocation?? && bean.zoneLocation==em.code><span class="${em.color}">${em.desc}</span></#if></#list>
		</div>
	</div>
   	<br/>
    <br/>
    <div class="form-group">
        <div class="col-lg-6">
            <label class="control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label>
            <#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list>
        </div>
        <div class="col-lg-6">
            <label class="control-label">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序：</label>${bean.sort!}
        </div>
    </div>
    <br/>
    <br/>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">专区描述：</label>${bean.zoneDesc!}
		</div>
	</div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
    </ul>
</div>