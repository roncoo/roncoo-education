<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/user/sendSmsLog/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>手机号：</label><input type="text" name="mobile" value="${bean.mobile!''}" class="form-control" size="15">&nbsp;
			<label>发送时间：</label><input type="text" name="beginGmtCreate" value="${bean.beginGmtCreate!''}" data-toggle="datepicker" data-minDate="2015-01-01" data-rule="date" size="15">-<input type="text" name="endGmtCreate" value="${bean.endGmtCreate!''}" data-toggle="datepicker" data-rule="date" size="15">
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			<br/>
			<br/>
			<a href="${base}/user/sendSmsLog/add" class="btn btn-blue" data-toggle="dialog" data-width="420" data-height="180" data-options="{title:'发送验证码'}">发送验证码</a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>手机号码</th>
				<th>短信模板</th>
				<th>验证码</th>
				<th>发送状态</th>
				<th>发送时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td>${bean.mobile!}</td>
				<td>${bean.template!}</td>
				<td>${bean.smsCode!}</td>
				<td><#list isSuccessEnums as em><#if bean.isSuccess?? && bean.isSuccess==em.code><span class="${em.color} ">${em.desc}</span></#if></#list></td>
				<td>${bean.gmtCreate?string('yyy-MM-dd HH:mm:ss')!}</td>
				<td>
					<a href="${base}/user/sendSmsLog/reSend?mobile=${bean.mobile}" class="btn btn-green" data-toggle="doajax" data-id="platformInfo-delete" data-confirm-msg="确定要重发吗？手机号【${bean.mobile}】">重发</a>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
