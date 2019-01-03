<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/system/websiteLink/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>链接名称：</label>
			<input size="12" name="linkName" value="${bean.linkName!}" />&nbsp;
			<label>状态：</label>
			<select name="statusId" data-toggle="selectpicker" data-width="50">
				<option value="">全部</option>
				<#list statusIdEnums as b>
				<option value="${b.code}"<#if bean.statusId?? && bean.statusId == b.code>selected="selected"</#if>>${b.desc!}</option>
				</#list>
			</select>&nbsp;&nbsp;
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			
			<br />
			<br />
			<a href="${base}/system/websiteLink/add" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="websiteLink-add" data-options="{title:'添加', height:250, width:410}">添加 </a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>链接名称 / 链接地址</th>
				<th>排序</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td><a href="${bean.linkUrl!}" target="_blank">${bean.linkName!}</a>&nbsp;【${bean.linkUrl!}】</td>
				<td>${bean.sort!}</td>
				<td><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>
                    <a href="${base}/system/websiteLink/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="websiteLink-delete" data-confirm-msg="确定要删除吗？">删除</a>
					<a href="${base}/system/websiteLink/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-id="websiteLink-edit" data-options="{title:'修改', height:255, width:410}">修改</a>
					<#if bean.statusId == 1>
						<a href="${base}/system/websiteLink/updateStatusId?id=${bean.id}&statusId=0" class="btn btn-red" data-toggle="doajax" data-id="websiteLink-updateStatusId" data-confirm-msg="确定要禁用吗？">禁用</a>
					</#if>
					<#if bean.statusId == 0>
						<a href="${base}/system/websiteLink/updateStatusId?id=${bean.id}&statusId=1" class="btn btn-green" data-toggle="doajax" data-id="websiteLink-updateStatusId" data-confirm-msg="确定要启用吗？">启用</a>
					</#if>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
