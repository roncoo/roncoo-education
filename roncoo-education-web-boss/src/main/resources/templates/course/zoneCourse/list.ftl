<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/zoneCourse/list?zoneId=${bean.zoneId}&zoneLocation=${bean.zoneLocation}" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>课程名称：</label><input type="text" name="courseName" value="${bean.courseName!}" class="form-control" size="10">&nbsp;			
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>			
			<br />
			<br />
			<button type="button" data-icon="plus" class="btn btn-default" data-toggle="dialog" data-width="1100" data-height="550" data-id="dialog-mask" data-mask="true" data-options="{url:'${base}/course/zoneCourse/courseList?zoneId=${bean.zoneId}&zoneLocation=${bean.zoneLocation}', title:'新增课程信息'}">添加</button>
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
				<th>展示平台</th>
				<th>状态</th>
				<th>排序</th>
				<th>添加时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>						
				<td>
				    <a href="${base}/course/zoneCourse/view?zoneId=${bean.zoneId}&courseId=${bean.courseId}" data-toggle="dialog" data-options="{mask:true,title:'${bean.courseName!}-详细信息',width:750,height:650}">${bean.courseName!}
				</td>
				<td>${bean.categoryName1!} / ${bean.categoryName2!} / ${bean.categoryName3!}</td>
				<td><#list zoneLocationEnums as em><#if bean.zoneLocation==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td><#list statusIdEnums as em><#if bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>${bean.sort!}</td>
				<td>${bean.gmtCreate?string('yyy-MM-dd HH:MM:ss')!}</td>
				<td>
                	<a href="${base}/course/zoneCourse/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="zoneCourse-delete" data-confirm-msg="确定要删除吗？">删除</a>
					<#if bean.statusId == 1>
					<a href="${base}/course/zoneCourse/status?id=${bean.id}&statusId=0" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要禁用吗？">禁用</a>
					</#if>
					<#if bean.statusId == 0>
					<a href="${base}/course/zoneCourse/status?id=${bean.id}&statusId=1" class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定要启用吗？">启用</a>
					</#if>
					<a href="${base}/course/zoneCourse/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-id="zoneCourse-edit" data-width="400" data-height="140" data-options="{title:'排序'}">排序</a>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
