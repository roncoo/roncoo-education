<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/courseRecommend/courseList?categoryNo1=${bean.categoryNo1!}" method="post">
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
				<th width="30">序号</th>
				<th>课程名称</th>
				<th>所属分类</th>
				<th>是否免费</th>
				<th>原价</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#list page.list as bean>
				<tr>
					<td align="center">${bean_index+1}</td>
				    <td>${bean.courseName!}</td>
				    <td>${bean.categoryName1!} / ${bean.categoryName2!} / ${bean.categoryName3!}</td>
				    <td><#list isFreeEnums as em><#if bean.isFree?? && bean.isFree == em.code> <span class="${em.color}">${em.desc}</span></#if></#list></td>
				    <td>${bean.orgPrice!}</td>
					<td>
						<a href="javascript:;" data-toggle="lookupback" data-args="{courseId:'${bean.id}',courseName:'${bean.courseName}'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
					</td>
				</tr>
			</#list>
		</tbody>
	</table>
</div>
<@pageFooter />

