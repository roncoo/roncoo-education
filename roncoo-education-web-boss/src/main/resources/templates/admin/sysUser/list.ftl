<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/admin/sysUser/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>手机：</label>
            <input type="text" name="phone" value="${bean.phone!}" class="form-control" size="12">&nbsp;
			
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			
			<br />
			<br />
			<a href="${base}/admin/sysUser/add" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="sysUser-add" data-options="{title:'添加', height:350}">添加 </a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>手机</th>
				<th>姓名</th>
				<th>备注</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td>${bean.mobile!}</td>
				<td>${bean.realName!}</td>
				<td>${bean.remark!}</td>
				<td><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>
					<a href="${base}/admin/sysUser/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-id="sysUser-edit" data-options="{title:'修改', height:400}">修改 </a>
					<a href="${base}/admin/sysUser/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="sysUser-delete" data-confirm-msg="确定要删除吗？">删除</a>
				    <a href="${base}/admin/sysRoleUser/set?userId=${bean.id}" class="btn btn-orange" data-toggle="dialog" data-id="sysUser-set" data-options="{title:'设置角色', height:350, width:800}">设置角色 </a>
					<a href="${base}/admin/sysUser/password?userNo=${bean.userNo}" class="btn btn-green" data-toggle="dialog" data-id="sysUser-password" data-options="{title:'修改密码', height:400}">修改密码 </a>
                	
                	<#if bean.userType?? && bean.userType == 2>
                	   <a href="${base}/admin/sysUser/proportion?id=${bean.id}" class="btn btn-red" data-toggle="dialog" data-id="sysUser-proportion" data-options="{title:'设置分成', height:200}">设置分成 </a>
                	</#if>
                	<#if bean.userType?? && bean.userType == 1>
                	   <a href="${base}/admin/sysUser/login?id=${bean.id}" class="btn btn-red">身份登录</a>
                	</#if>
                </td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
