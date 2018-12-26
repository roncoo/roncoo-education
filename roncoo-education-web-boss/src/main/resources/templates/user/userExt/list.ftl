<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/user/userExt/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>用户编号：</label>
            <input type="text" name="userNo" value="${bean.userNo!''}" class="form-control" size="12">&nbsp;
			<label>用户手机：</label><input type="text" name="mobile" value="${bean.mobile!}" class="form-control" size="12">&nbsp;
			<label>注册时间：</label>
			<input type="text" name="beginGmtCreate" value="${bean.beginGmtCreate!''}" data-toggle="datepicker" data-minDate="2015-01-01" data-rule="date" size="12">-<input type="text" name="endGmtCreate" value="${bean.endGmtCreate!''}" data-toggle="datepicker" data-rule="date" size="12">
			<br />
			<br />
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
				<th>用户编号</th>
				<th>用户手机</th>
				<th>用户类型</th>
				<th>昵称</th>
				<th>状态</th>
				<th>注册时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td>${bean.userNo!}</td>
				<@shiro.hasPermission name="/user/userExt/view">
				<td><a href="${base}/user/userExt/view?id=${bean.id}" data-toggle="dialog" data-id="userExt-view" data-options="{title:'查看',width:650, height:450}">${bean.mobile!}</a></td>
				</@shiro.hasPermission>
				<td><#list userTypeEnums as em><#if bean.userType?? && bean.userType==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>${bean.nickname!}</td>
				<td><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
                <td>${bean.gmtCreate?string('yyy-MM-dd HH:mm:ss')!}</td>
                <td>
                	<@shiro.hasPermission name="/user/userExt/edit">
						<a href="${base}/user/userExt/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-id="userExt-edit" data-options="{title:'修改', height:450}">修改</a>
					</@shiro.hasPermission>
					<#if bean.statusId?? && bean.statusId==1>
				        <@shiro.hasPermission name="/user/userExt/updateStatusId">
                        <a href="${base}/user/userExt/updateStatusId?id=${bean.id!}&statusId=0" class="btn btn-red" data-toggle="doajax" data-id="userExt-updateStatusId" data-confirm-msg="确定要禁用吗？">禁用</a>
                        </@shiro.hasPermission>
                    </#if>
				    <#if bean.statusId?? && bean.statusId==0>
				        <@shiro.hasPermission name="/user/userExt/updateStatusId">
                        <a href="${base}/user/userExt/updateStatusId?id=${bean.id!}&statusId=1" class="btn btn-green" data-toggle="doajax" data-id="userExt-updateStatusId" data-confirm-msg="确定要启用吗？">启用</a>
                        </@shiro.hasPermission>
                    </#if>
                    <@shiro.hasPermission name="/course/courseUserStudyLog/list">
                    	<a href="${base}/course/courseUserStudyLog/list?userNo=${bean.userNo}" class="btn btn-orange" data-toggle="navtab" data-id="course-courseUserStudyLog" data-width="1100" data-height="550">学习记录</a>
					</@shiro.hasPermission>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
