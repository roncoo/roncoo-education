<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/user/lecturerProfit/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>讲师名称：</label>
			<input type="text" name="lecturerName" value="${bean.lecturerName!''}" class="form-control" size="12">
			&nbsp;<label>状态：</label>
            <select name="profitStatus" data-toggle="selectpicker" size="9">
            <option value="">--请选择--</option> <#list profitStatusEnums as profit>
            <option value="${profit.code}"<#if bean.profitStatus?? && bean.profitStatus! == profit.code>selected="selected"</#if>>${profit.desc}</option> </#list>
            </select>
			&nbsp;<label>时间：</label>
			<input type="text" name="beginDate" value="${bean.beginDate!''}" data-toggle="datepicker" data-rule="date" size="12">
			-
			<input type="text" name="endDate" value="${bean.endDate!''}" data-toggle="datepicker" data-rule="date" size="12">
			<br />
			<br />
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			<a class="btn btn-blue" href="${base}/user/lecturerProfit/audit" data-toggle="doajaxchecked" data-confirm-msg="确定要将选中项标记为成功吗？" data-idname="ids" data-group="delids">标记为已打款</a>
            <div class="btn-group">
                <button type="button" class="btn-default dropdown-toggle" data-toggle="dropdown" data-icon="copy">报表导出<span class="caret"></span></button>
                <ul class="dropdown-menu right" role="menu">
                    <li>
                         <a href="${base}/user/lecturerProfit/export?pageSize=${page.totalCount!'0'}" data-toggle="doexport" data-confirm-msg="确定要导出信息吗？">导出<span style="color: green;">全部</span></a>
                    </li>
                </ul>
            </div>
		</div>
	</form> 
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
			<th width="26"><input type="checkbox" class="checkboxCtrl" data-group="delids" data-toggle="icheck"></th>
				<th width="30">序号</th>
				<th>讲师名称</th>
				<th>银行卡号</th>
				<th>银行名称</th>
				<th>银行开户名</th>
				<th>讲师分润</th>
				<th>平台分润</th>
				<th>状态</th>
				<th>时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
			    <td><input type="checkbox" name="delids" data-toggle="icheck" value="${bean.id}"></td>
				<td align="center">${bean_index+1}</td>
				<td>${bean.lecturerVO.lecturerName!}</td>
				<td>${bean.bankCardNo!}</td>
				<td>${bean.bankName!}</td>
				<td>${bean.bankUserName!}</td>
				<td>${bean.lecturerProfit!}</td>
				<td>${bean.platformProfit!}</td>
				<td><#list profitStatusEnums as em><#if bean.profitStatus?? && bean.profitStatus==em.code>${em.desc}</#if></#list></td>
				<td>${bean.gmtCreate?string('yyy-MM-dd HH:mm:ss')!}</td>
				<td>
					<a class="btn btn-green" data-title="操作进度" data-width="350" data-height="200" data-toggle="dialog" data-id="lecturerProfit-isStatus" href="${base}/user/lecturerProfit/isStatus?id=${bean.id}">打款</a>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
