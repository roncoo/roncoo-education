<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/system/msgUser/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>活动标题：<label>
			<input size="15" name="msgTitle" value="${bean.msgTitle!}"/>&nbsp;
			<label>用户手机：<label>
			<input size="15" name="mobile" value="${bean.mobile!}"/>&nbsp;
			
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>用户手机</th>
				<th>短信类型</th>
				<th>短信标题</th>
				<th>是否已阅读</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td>${bean.mobile!}</td>
				<td><#list msgTypeEnums as mm><#if bean.msgType?? && bean.msgType==mm.code>${mm.desc}</#if></#list></td>
				<td>${bean.msgTitle!}</td>
				<td><#list isDefaultEnums as em><#if bean.isRead?? && bean.isRead==em.code>${em.desc}</#if></#list></td>
				<td>
					<a href="${base}/system/msgUser/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="msgUser-delete" data-confirm-msg="确定要删除吗？">删除</a>
					<@shiro.hasPermission name="/system/msg/view">
						<a href="${base}/system/msg/view?id=${bean.msgId}&mobile=${bean.mobile}" class="btn btn-blue" data-toggle="dialog" data-id="msgUser-view" data-options="{title:'查看',  height:250,width:650}">查看</a>
					</@shiro.hasPermission>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
