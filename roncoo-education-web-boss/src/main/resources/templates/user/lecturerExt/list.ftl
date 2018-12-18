<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/user/lecturerExt/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>账号：</label>
			<input type="text" name="number" value="" class="form-control" size="10">&nbsp;
			
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			
			<br />
			<br />
			<a href="${base}/user/lecturerExt/add" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="lecturerExt-add" data-options="{title:'添加', height:500}">添加 </a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>状态(1:正常，0:禁用)</th>
				<th>讲师用户编号</th>
				<th>总收入</th>
				<th>已提金额</th>
				<th>可提余额</th>
				<th>冻结金额</th>
				<th>银行卡号</th>
				<th>银行名称</th>
				<th>银行支行名称</th>
				<th>银行开户名</th>
				<th>银行身份证号</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td>${bean.gmtCreate!}</td>
				<td>${bean.gmtModified!}</td>
				<td>${bean.statusId!}</td>
				<td>${bean.lecturerUserNo!}</td>
				<td>${bean.totalIncome!}</td>
				<td>${bean.historyMoney!}</td>
				<td>${bean.enableBalances!}</td>
				<td>${bean.freezeBalances!}</td>
				<td>${bean.bankCardNo!}</td>
				<td>${bean.bankName!}</td>
				<td>${bean.bankBranchName!}</td>
				<td>${bean.bankUserName!}</td>
				<td>${bean.bankIdCardNo!}</td>
				<td>
					<a href="${base}/user/lecturerExt/view?id=${bean.id}" class="btn btn-blue" data-toggle="dialog" data-id="lecturerExt-view" data-options="{title:'查看', height:500}">查看</a>
					<a href="${base}/user/lecturerExt/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-id="lecturerExt-edit" data-options="{title:'修改', height:500}">修改</a>
					<a href="${base}/user/lecturerExt/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="lecturerExt-delete" data-confirm-msg="确定要删除吗？">删除</a>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
