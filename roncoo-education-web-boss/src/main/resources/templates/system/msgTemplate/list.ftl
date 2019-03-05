<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/system/msgTemplate/list" method="post">
		<@pageHeadr />
		
		<a href="${base}/system/msgTemplate/add" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="msgTemplate-add" data-options="{title:'添加',  height:343,width:545}">添加 </a>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>模板</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td style="max-width: 250px">${bean.remark!}</td>
				<td><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>
					<a href="${base}/system/msgTemplate/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-id="msgTemplate-edit" data-options="{title:'修改', height:300}">修改</a>
					<a href="${base}/system/msgTemplate/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="msgTemplate-delete" data-confirm-msg="确定要删除吗？">删除</a>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
