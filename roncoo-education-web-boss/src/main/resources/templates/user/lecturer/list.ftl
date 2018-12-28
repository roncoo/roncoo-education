<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/user/lecturer/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
		 <label>讲师名称：</label>
            <input type="text" name="lecturerName" value="${bean.lecturerName!''}" class="form-control" size="12">
            &nbsp;<label>手机号码：</label>
            <input type="text" name="lecturerMobile" value="${bean.lecturerMobile!''}" class="form-control" size="12">
            &nbsp;<label>状态：</label>
            <select name="statusId" data-toggle="selectpicker" data-width="50">
                <option value="">全部</option>
                <#list statusIdEnums as b>
				<option value="${b.code}"<#if bean.statusId?? && b.code == bean.statusId>selected="selected"</#if>>${b.desc!}</option>
				</#list>
            </select>
            <br />
			<br />
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
				<th>手机号</th>
				<th>讲师名称</th>
				<th>邮箱</th>
				<th>状态</th>
				<th>分成比例</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td><a href="${base}/user/lecturer/view?id=${bean.id}" data-toggle="dialog" data-id="lecturer-view" data-options="{title:'查看', width:800, height:618}">${bean.lecturerMobile!}</td>
				<td>${bean.lecturerName!}</a></td>
				<td>${bean.lecturerEmail!}</td>
				<td><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>【讲师：${bean.lecturerProportion*100}%】</td>
				<td>
					<a href="${base}/user/lecturer/edit?id=${bean.id}&lecturerUserNo=${bean.lecturerUserNo}" class="btn btn-green" data-toggle="dialog" data-id="lecturerAudit-edit" data-options="{title:'${bean.lecturerName}-信息修改', width:800, height:450}">修改</a>
					<#if bean.statusId == 1>
    					<a href="${base}/user/lecturer/status?id=${bean.id}&statusId=0" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要禁用吗？">禁用</a>
					</#if >
					<#if bean.statusId = 0>
    					<a href="${base}/user/lecturer/status?id=${bean.id}&statusId=1" class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定要启用吗？">启用</a>
					</#if >
				    <a href="${base}/user/lecturer/proportion?lecturerUserNo=${bean.lecturerUserNo}" class="btn btn-orange" data-toggle="dialog" data-id="lecturerAudit-proportion" data-options="{title:'设置分成',height:230, width:450}">设置分成 </a>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
