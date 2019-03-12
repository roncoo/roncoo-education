<#include "/macro/base.ftl" />
<#include "/macro/category.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/courseCategory/listFloor" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>分类名称：</label>
			<input type="text" name="categoryName" value="${bean.categoryName!''}" class="form-control" size="12">&nbsp;
			
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			
			<br />
			<br />
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>分类名称</th>
				<th>备注</th>
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
					<a href="${base}/course/courseCategory/view?id=${bean.id}" data-toggle="dialog" data-options="{title:'查看', height:250}">${bean.categoryName!}</a>
				</td>
				<td>${bean.remark!}</td>
				<td><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>${bean.sort!}</td>
				<td>
					 <a href="${base}/course/courseRecommend/list?categoryId=${bean.id}" class="btn btn-orange" data-toggle="navtab" data-id="course-courseRecommend">推荐课程</a>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
