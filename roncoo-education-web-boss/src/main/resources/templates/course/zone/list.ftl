<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/zone/list?zoneLocation=${bean.zoneLocation}" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
		    <label>专区名称：</label><input type="text" name="zoneName" value="${bean.zoneName!}" class="form-control" size="10">&nbsp;
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			<br />
			<br />
			<a href="${base}/course/zone/add" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="zone-add" data-options="{title:'添加', height:300}">添加 </a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>专区名称</th>
				<th>专区描述</th>
				<th>排序</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>				
				<td>
				    <a href="${base}/course/zone/view?id=${bean.id}" data-toggle="dialog" data-options="{mask:true,title:'${bean.zoneName!}-详细信息',height:300}">${bean.zoneName!}
				</td>
				<td>${bean.zoneDesc!}</td>
				<td>${bean.sort!}</td>
				<td><#list statusIdEnums as em><#if bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>
                    <a href="${base}/course/zone/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="zone-delete" data-confirm-msg="确定要删除吗？">删除</a>
					<a href="${base}/course/zone/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-id="zone-edit" data-options="{title:'修改', height:300}">修改</a>
					<#if bean.statusId == 1>
					<a href="${base}/course/zone/status?id=${bean.id}&statusId=0" class="btn btn-red" data-toggle="doajax" data-id="zone-update" data-confirm-msg="确定要禁用吗？">禁用</a>
					</#if>
					<#if bean.statusId == 0>
					<a href="${base}/course/zone/status?id=${bean.id}&statusId=1" class="btn btn-green" data-toggle="doajax" data-id="zone-update" data-confirm-msg="确定要启用吗？">启用</a>
					</#if>
                    <a href="${base}/course/zoneCourse/list?zoneId=${bean.id}&zoneLocation=${bean.zoneLocation}" class="btn btn-orange" data-toggle="navtab" data-id="course-zoneCourse">专区课程</a>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
