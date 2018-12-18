<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/orderInfo/course/courseList" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>课程名称：</label>
			<input type="text" name="courseName" value="${bean.courseName!}">
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
				<th>课程名称</th>
				<th>课程分类</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#list page.list as bean>
				<tr>
					<td align="center">${bean_index+1}</td>
					<td>
						<a href="${base }/course/course/view?id=${bean.id}" data-id="course-view" data-toggle="dialog" data-options="{mask:true,title:'${bean.courseName}-详细信息',width:900,height:600}">${bean.courseName}</a>
					</td>
					<td>${bean.categoryName1!}</td>
					<td><#list statusIdEnums as em><#if bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
					<td>                                               
						<a href="javascript:;" data-toggle="lookupback" data-args="{courseName:'${bean.courseName}', courseId:'${bean.id}'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
					</td>
				</tr>
			</#list>
		</tbody>
	</table>
</div>
<@pageFooter />

