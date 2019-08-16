<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/zoneCourse/courseList?zoneId=${bean.zoneId}" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>课程名称：</label><input type="text" name="courseName" value="${bean.courseName!}" class="form-control" size="10">&nbsp;
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>课程名称</th>
				<th>所属分类</th>
				<th>是否免费</th>
				<th>原价</th>
				<th>状态</th>
				<th>排序</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td>
					<a href="${base}/course/course/view?id=${bean.id}" data-toggle="dialog" data-options="{mask:true,title:'${bean.courseName}-详细信息',width:900,height:600}">${bean.courseName!}</a>
				</td>
				<td>${bean.categoryName1!} / ${bean.categoryName2!} / ${bean.categoryName3!}</td>
				<td><#list isFreeEnums as em><#if bean.isFree?? && bean.isFree == em.code> <span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>${bean.courseOriginal!}</td>				
				<td><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>${bean.sort!}</td>
				<td><a type="button" class="btn btn-blue" href="${base}/course/zoneCourse/save?zoneId=${zoneId}&courseId=${bean.id}" data-toggle="doajax" data-confirm-msg="确定要选择吗？" data-icon="check">选择</a></td>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
