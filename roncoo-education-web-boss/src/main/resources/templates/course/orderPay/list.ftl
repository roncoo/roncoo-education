<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/orderPay/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>订单编号：</label>
			<input type="text" name="orderNo" value="${bean.orderNo!''}" class="form-control" size="10">
			&nbsp;
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
				<th>订单号</th>
				<th>流水号</th>
				<th>订单状态</th>
				<th>支付方式</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td>${bean.orderNo!}</td>
				<td>${bean.serialNumber!}</td>
				<td><#list orderStatusEnums as em> <#if em.code == bean.orderStatus>${em.desc!}</#if> </#list></td>
				<td><#list payTypeEnums as em> <#if em.code == bean.payType>${em.desc!}</#if> </#list></td>
				<td>${bean.gmtCreate?string('yyyy-MM-dd HH:mm:ss')}</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
