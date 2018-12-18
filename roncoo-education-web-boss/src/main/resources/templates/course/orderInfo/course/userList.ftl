<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/orderInfo/course/userList" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>手机号码：</label>
			<input type="text" name="email" value="${bean.mobile!}">
			<button type="submit" class="btn-green" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed"  data-selected-multi="true">
		<thead>
			<tr>
				<th align="center">序号</th>
				<@shiro.hasRole name="admin">
                <th>所属机构</th>
                </@shiro.hasRole>
				<th>手机</th>
				<th>账号</th>
				<th>注册时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#list page.list as bean>
				<tr>
					<td align="center">${bean_index+1}</td>
					<@shiro.hasRole name="admin">
                    <td>${bean.orgNo!}</td>
                    </@shiro.hasRole>
					<td>${bean.mobile}</td>
					<td>${bean.userNo}</td>
					<td>
						<#if bean.gmtCreate??>${bean.gmtCreate?string('yyyy-MM-dd HH:mm:ss')}</#if>
					</td>
					<td>
						<a href="javascript:;" data-toggle="lookupback" data-args="{userNo:'${bean.userNo}',  mobile:'${bean.mobile}'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
					</td>
				</tr>
			</#list>
		</tbody>
	</table>
</div>
<@pageFooter />

