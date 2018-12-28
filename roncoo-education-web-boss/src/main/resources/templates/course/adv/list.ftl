<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/adv/list?platShow=${bean.platShow}" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>广告标题：</label>
			<input type="text" name="advTitle" value="${bean.advTitle!}" class ="form-control" size="12" />&nbsp;
			<label>状态：</label>
			<select name="statusId" data-toggle="selectpicker" data-width="50">
				<option value="">全部</option>
				<#list statusIdEnums as em>
				<option value="${em.code}"<#if bean.statusId?? && em.code == bean.statusId>selected="selected"</#if>>${em.desc!}</option>
				</#list>
			</select>&nbsp;&nbsp;
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			
			<br />
			<br />
			<a href="${base}/course/adv/add?platShow=${bean.platShow}" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="adv-add" data-options="{title:'添加', height:343,width:545}">添加 </a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>广告图片</th>
				<th>广告标题/广告链接</th>	
				<th>排序</th>
				<th>状态</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td><img alt="${bean.advTitle!}" src="${bean.advImg!}" width="150"></td>
				<td>${bean.advTitle!}<br/><a href="${bean.advUrl}" target="_blank" style="max-width:100px;">${bean.advUrl}</a></td>
				<td>${bean.sort!}</td>
				<td><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>${bean.beginTime!?string('yyyy-MM-dd HH:mm:ss')}</td>
				<td>${bean.endTime!?string('yyyy-MM-dd HH:mm:ss')}</td>
				<td>
					<a href="${base}/course/adv/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="adv-delete" data-confirm-msg="确定要删除吗？">删除</a>
					<a href="${base}/course/adv/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-id="adv-edit" data-options="{title:'修改', height:343,width:545}">修改</a>
					<#if bean.statusId == 1>
					<a href="${base}/course/adv/status?id=${bean.id}&statusId=0" class="btn btn-red" data-toggle="doajax" data-id="adv-update" data-confirm-msg="确定要禁用吗？">禁用</a>
					</#if>
					<#if bean.statusId == 0>
					<a href="${base}/course/adv/status?id=${bean.id}&statusId=1" class="btn btn-green" data-toggle="doajax" data-id="adv-update" data-confirm-msg="确定要启用吗？">启用</a>
					</#if>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
