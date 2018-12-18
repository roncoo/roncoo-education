<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/courseUserStudyLog/list?userNo=${bean.userNo}" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>学习时间：</label>
			<input type="text" name="beginGmtCreate" value="${bean.beginGmtCreate!''}" data-toggle="datepicker" data-minDate="2015-01-01" data-rule="date" size="12">-<input type="text" name="endGmtCreate" value="${bean.endGmtCreate!''}" data-toggle="datepicker" data-rule="date" size="12">
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
				<th>课程名称</th>
				<th>章节名称</th>
				<th>课时名称</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td>${bean.courseName!}</td>
				<td>${bean.chapterName!}</td>
				<td>${bean.periodName!}</td>
				<td>${bean.gmtCreate?string('yyy-MM-dd HH:mm:ss')!}</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
