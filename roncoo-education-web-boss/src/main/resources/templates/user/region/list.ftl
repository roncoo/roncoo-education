<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/user/region/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>账号：</label>
			<input type="text" name="number" value="" class="form-control" size="10">&nbsp;
			
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			
			<br />
			<br />
			<a href="${base}/user/region/add" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="region-add" data-options="{title:'添加', height:500}">添加 </a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>父id</th>
				<th>中心经度</th>
				<th>中心维度</th>
				<th>省Id</th>
				<th>区域编码（国标）</th>
				<th>省名称</th>
				<th>市Id</th>
				<th>城市编码</th>
				<th>市名称</th>
				<th>区域名称</th>
				<th>区名称</th>
				<th>全路径名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td>${bean.parentId!}</td>
				<td>${bean.centerLng!}</td>
				<td>${bean.centerLat!}</td>
				<td>${bean.provinceId!}</td>
				<td>${bean.provinceCode!}</td>
				<td>${bean.provinceName!}</td>
				<td>${bean.cityId!}</td>
				<td>${bean.cityCode!}</td>
				<td>${bean.cityName!}</td>
				<td>${bean.regionName!}</td>
				<td>${bean.districtName!}</td>
				<td>${bean.mergerName!}</td>
				<td>
					<a href="${base}/user/region/view?id=${bean.id}" class="btn btn-blue" data-toggle="dialog" data-id="region-view" data-options="{title:'查看', height:500}">查看</a>
					<a href="${base}/user/region/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-id="region-edit" data-options="{title:'修改', height:500}">修改</a>
					<a href="${base}/user/region/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="region-delete" data-confirm-msg="确定要删除吗？">删除</a>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
