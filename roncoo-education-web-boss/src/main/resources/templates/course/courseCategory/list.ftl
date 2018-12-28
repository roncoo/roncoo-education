<#include "/macro/base.ftl" />
<#include "/macro/category.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/courseCategory/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>分类名称：</label>
			<input type="text" name="categoryName" value="${bean.categoryName!''}" class="form-control" size="12">&nbsp;
			
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			
			<br />
			<br />
			<a href="${base}/course/courseCategory/add?parentId=0&floor=1&categoryType=${bean.categoryType!'1'}" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="courseCategory-add" data-options="{title:'添加', height:250}">添加 </a>
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
					<a href="${base}/course/courseCategory/add2?parentId=${bean.id}&floor=${bean.floor}&categoryType=${bean.categoryType}" class="btn btn-default" data-toggle="dialog" data-id="sysMenu-add" data-options="{title:'添加', height:250}">添加 </a>
					<a href="${base}/course/courseCategory/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="courseCategory-delete" data-confirm-msg="确定要删除吗？">删除</a>
					<a href="${base}/course/courseCategory/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-options="{title:'修改', height:250,id:'course-courseCategory'}">修改</a>
				</td>
			</tr>
			
			<#if bean.list?? && bean.list?size gt 0>
            <@listCategory children=bean.list label="&nbsp;&nbsp;|--"/>
            </#if>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
