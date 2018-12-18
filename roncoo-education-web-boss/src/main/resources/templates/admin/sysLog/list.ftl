<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/admin/sysLog/list" method="post">
		<@pageHeadr/>
		<div class="bjui-searchBar">
			<label>操作人：</label>
			<input type="text" name="realName" value="${bean.realName!}" class="form-control" size="12">&nbsp;

			<label>时间：</label>
			<input type="text" name="beginDate" value="${bean.beginDate!''}" data-toggle="datepicker" data-rule="date" size="12">-<input type="text" name="endDate" value="${bean.endDate!''}" data-toggle="datepicker" data-rule="date" size="12">&nbsp;
			
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;&nbsp;<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>操作人</th>
				<th>ip</th>
				<th>请求路径</th>
				<th >内容</th>
				<th>时间</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td>${bean.realName!}</td>
				<td>${bean.ip!}</td>
				<td>${bean.operation!} 【${bean.method!}】<div style="margin-top: 5px;"></div>${bean.path!}</td>
				<td style="max-width:400px;">${bean.content!}</td>
				<td>${bean.gmtCreate?string('yyyy-MM-dd HH:mm:ss')}</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
