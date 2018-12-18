<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/user/platform/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>客户端名称：</label><input type="text" name="clientName" value="${bean.clientName!}" class="form-control" size="10">&nbsp;
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			<br />
			<br />
			<a href="${base}/user/platform/add" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="platform-add" data-options="{title:'添加', height:200}">添加 </a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>客户端名称</th>
				<th>客户端ID</th>
				<th>备注</th>
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
				<td>${bean.clientName!}</td>
				<td>${bean.clientId!}</td>
				<td>${bean.remark!}</td>
				<td>${bean.sort!}</td>
				<td><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>
					<a href="${base}/user/platform/view?id=${bean.id}" class="btn btn-blue" data-toggle="dialog" data-id="platform-view" data-options="{title:'查看', height:300}">查看</a>
					<a href="${base}/user/platform/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-id="platform-edit" data-options="{title:'修改', height:300}">修改</a>
					<a href="${base}/user/platform/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="platform-delete" data-confirm-msg="确定要删除吗？">删除</a>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
