<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/orderInfo/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
		    <label>手机号码：</label><input type="text" name="mobile" value="${bean.mobile!''}" class="form-control" size="10">&nbsp;
			<label>课程名称：</label><input type="text" name="courseName" value="${bean.courseName!}" class="form-control" size="10">&nbsp;
			<label>支付时间：</label><input type="text" name="beginPayTime" value="${bean.beginPayTime!''}" data-toggle="datepicker" data-rule="date" size="12"> - <input type="text" name="endPayTime" value="${bean.endPayTime!''}" data-toggle="datepicker" data-rule="date" size="12">&nbsp;
			<label>订单状态：</label>
			<select name="orderStatus" data-toggle="selectpicker">
				<option value="">全部</option>
				<#list orderStatusEnums as b>
				<option value="${b.code}"<#if bean.orderStatus?? && b.code == bean.orderStatus>selected="selected"</#if>>${b.desc!}</option>
				</#list>
			</select>	
			<br/>
			<br/>
			<label>订单编号：</label><input type="text" name="orderNo" value="${bean.orderNo!}" class="form-control" size="10">&nbsp;
			<label>后台备注：</label><input type="text" id="remark" value="${bean.remark!''}" name="remark" class="form-control" size="10">&nbsp;
			<label>平台讲师：</label><input type="text" name="lecturerName" value="${bean.lecturerName!''}" class="form-control" size="10">&nbsp;
			<label>购买渠道：</label>
            <select name="channelType" data-toggle="selectpicker">
                <option value="">全部</option>
                <#list channelTypeEnums as b>
                <option value="${b.code}"<#if bean.channelType?? && b.code == bean.channelType>selected="selected"</#if>>${b.desc!}</option>
                </#list>
            </select>&nbsp;
			<label>支付方式：</label>
			<select name="payType" data-toggle="selectpicker">
				<option value="">全部</option>
				<#list payTypeEnums as b>
				<option value="${b.code}"<#if bean.payType?? && b.code == bean.payType>selected="selected"</#if>>${b.desc!}</option>
				</#list>
			</select>
			<br />
            <br />
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
            <div class="btn-group">
                <button type="button" class="btn-default dropdown-toggle" data-toggle="dropdown" data-icon="copy">报表导出<span class="caret"></span></button>
                <ul class="dropdown-menu right" role="menu">
                    <li>
                         <a href="${base}/course/orderInfo/export" data-toggle="doexport" data-confirm-msg="确定要导出信息吗？">导出<span style="color: green;">全部</span></a>
                    </li>
                </ul>
            </div>
                     【平台收入:<span class="red">${countMap.platformProfit?string('0.00')}</span>元】=【订单收入: <span class="red">${countMap.totalProfit?string('0.00')}</span>元】-【讲师收入:<span class="red">${countMap.lecturerProfit?string('0.00')}</span>元】
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>订单号</th>
				<th>课程信息</th>
				<th>讲师信息</th>
				<th>用户信息</th>
				<th>交易类型</th>
				<th>支付方式 / 价格(元)</th>
				<th>购买渠道</th>
				<th>订单状态</th>
				<th>客户备注</th>
				<th>支付时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<@shiro.hasRole name="admin">
				<td>${bean.orgName!}【${bean.orgNo!}】</td>
				</@shiro.hasRole>
				<td>
					<a href="${base}/course/orderInfo/view?id=${bean.id}" data-title="订单详情" data-id="order-view" data-toggle="dialog" data-width="600" data-height="480">${bean.orderNo!}</a>
				</td>
				<td>
					<a href="${base}/course/course/view?id=${bean.courseId!}" data-id="order-course-view" data-toggle="dialog" data-options="{mask:true,title:'${bean.courseName!}-详细信息',width:900,height:600}">${bean.courseName!}</a>
					<div style="margin-top: 5px;"></div>
					【<a href="${base}/course/orderInfo/list?courseId=${bean.courseId!}&orderStatus=2" data-toggle="dialog" data-width="1200" data-height="500">明细</a>】
				</td>
				<td>
                    <a href="${base}/user/lecturer/get?lecturerUserNo=${bean.lecturerUserNo!}" data-id="order-lecturer-get" data-toggle="dialog" data-width="800" data-height="618">${bean.lecturerName!}</a>
                    <div style="margin-top: 5px;"></div>
                                   【<a href="${base}/course/orderInfo/list?lecturerUserNo=${bean.lecturerUserNo!}&orderStatus=2" data-id="order-lecturer-list" data-toggle="dialog" data-width="1200" data-height="500">明细</a>】
                 </td>
				<td>
				    <a href="${base}/user/userExt/get?userNo=${bean.userNo!}" data-toggle="dialog" data-id="order-userExt-view" data-options="{title:'查看' ,width:650, height:450}">${bean.mobile!}</a>
				    <div style="margin-top: 5px;"></div>
				      【<a href="${base}/course/orderInfo/list?mobile=${bean.mobile!}&orderStatus=2" data-id="order-list" data-toggle="dialog" data-width="1200" data-height="500">明细</a>】
				</td>
				<td><#list tradeTypeEnums as b> <#if b.code == bean.tradeType>${b.desc!}</#if> </#list></td>
				<td>
					<#list payTypeEnums as b> <#if b.code == bean.payType>${b.desc!}</#if> </#list>
					<div style="margin-top: 5px;"></div>
					价格:【${bean.pricePaid!}】 
				</td>
				<td><#list channelTypeEnums as b> <#if b.code == bean.channelType>${b.desc!}</#if> </#list></td>
				<td><#list orderStatusEnums as b> <#if b.code == bean.orderStatus>${b.desc!}</#if> </#list></td>
				<td>${bean.remarkCus!}</td>
				<td style="width:60px;"><#if bean.payTime??>${bean.payTime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
				<td>
				   <a class="btn btn-blue" data-title="备注订单" data-width="350" data-height="200" data-toggle="dialog" data-id="order-edit" href="${base}/course/orderInfo/edit?id=${bean.id}&remark=${bean.remark!}">备注 </a>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
