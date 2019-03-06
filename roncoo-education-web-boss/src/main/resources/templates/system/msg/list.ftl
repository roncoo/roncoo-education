<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/system/msg/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>活动标题：<label>
			<input size="15" name="msgTitle" value="${bean.msgTitle!}"/>&nbsp;
			
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			
			<@shiro.hasPermission name="/system/msg/add">
				<a type="button" class="btn btn-default" href="${base}/system/msg/add" data-icon="plus" data-toggle="navtab" data-options="{title:'添加站内信',id:'msg-add'}">添加</a>
			</@shiro.hasPermission>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>短信标题</th>
				<th>是否置顶</th>
				<th>发送状态</th>
				<th>定时发送</th>
				<th>发送时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td>
					<a href="${base}/system/msg/view?id=${bean.id}" data-title="消息详情" data-id="order-view" data-toggle="dialog" data-width="600" data-height="350">${bean.msgTitle!}</a>
					<div style="margin-top: 5px;"></div>
				</td>
				<td><#list isDefaultEnums as em2><#if bean.isTop?? && bean.isTop==em2.code>${em2.desc}</#if></#list></td>
				<td><#list isSendEnums as em><#if bean.isSend?? && bean.isSend==em.code>${em.desc}</#if></#list></td>
				<td><#list isTimeSendEnums as em><#if bean.isTimeSend?? && bean.isTimeSend==em.code>${em.desc}</#if></#list></td>
				<td>
					<#if bean.isSend == 1><#if bean.gmtModified??>${bean.gmtModified?string('yyyy-MM-dd HH:mm:ss')}</#if></#if>
				</td>
				<td>
					<a href="${base}/system/msg/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="msgUser-delete" data-confirm-msg="确定要删除吗？">删除</a>
					<a href="${base}/system/msg/edit?id=${bean.id}" class="btn btn-green" data-toggle="navtab" data-id="msg-edit" data-options="{title:'修改', height:500}">修改</a>
					<#if bean.isTimeSend == 0 && bean.isSend == 0><a href="${base}/system/msg/push?id=${bean.id}" class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定要发送吗？">发送</a></#if> 
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
