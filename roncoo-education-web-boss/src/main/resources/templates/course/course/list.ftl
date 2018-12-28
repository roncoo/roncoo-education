<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/course/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>课程名称：</label><input type="text" name="courseName" value="${bean.courseName!''}" class="form-control" size="12">&nbsp;
			<label>状态：</label>
			<select name="statusId" data-toggle="selectpicker">
				<option value="">全部</option>
				<#list statusIdEnums as b>
				<option value="${b.code}"<#if bean.statusId?? && bean.statusId == b.code>selected="selected"</#if>>${b.desc!}</option>
				</#list>
			</select>&nbsp;
			<label>上下架：</label>
			<select name="isPutaway" data-toggle="selectpicker" data-width="50">
				<option value="">全部</option>
				<#list isPutawayEnums as b>
				<option value="${b.code}"<#if bean.isPutaway?? && bean.isPutaway == b.code>selected="selected"</#if>>${b.desc!}</option>
				</#list>
			</select>&nbsp;
			<label>是否收费：</label>
			<select name="isFree" data-toggle="selectpicker" data-width="50">
				<option value="">全部</option>
				<#list isFreeEnums as b>
				<option value="${b.code}"<#if bean.isFree?? && bean.isFree == b.code>selected="selected"</#if>>${b.desc!}</option>
				</#list>
			</select>&nbsp;
			<br />
			<br />
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>课程名称</th>
				<th>课程分类</th>
				<th>是否收费</th>
				<th>原价</th>
				<th>上下架</th>
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
					<a href="${base}/course/course/view?id=${bean.id!}" data-toggle="dialog" data-options="{mask:true,title:'${bean.courseName}-详细信息',width:900,height:600}">${bean.courseName!}</a>
					<div style="margin-top: 5px;"></div>
                                    讲师:<#if lecturerList??>【<#list lecturerList as lecturer><#if lecturer.lecturerUserNo == bean.lecturerUserNo><a href="${base}/user/lecturer/get?lecturerUserNo=${bean.lecturerUserNo!}" data-toggle="dialog" data-width="800" data-height="618">${lecturer.lecturerName!}</a></#if></#list>】</#if>
				</td>
				<td>
					<#list courseCategoryEnums as em><#if bean.courseCategory?? && bean.courseCategory == em.code> <span class="${em.color}">${em.desc}</span></#if></#list>
				    <div style="margin-top: 5px;"></div>
				    ${bean.categoryName1!} / ${bean.categoryName2!} / ${bean.categoryName3!}
				</td>
				<td>
					<#list isFreeEnums as em><#if bean.isFree?? && bean.isFree == em.code> <span class="${em.color}">${em.desc}</span></#if></#list>
				</td>
				<td>${bean.courseOriginal!}</td>
				<td>
					<#list isPutawayEnums as em><#if bean.isPutaway?? && bean.isPutaway == em.code><span class="${em.color}">${em.desc}</span></#if></#list>
				</td>
				<td><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>${bean.sort!}</td>
				<td>
					<a href="${base}/course/course/edit?id=${bean.id}" class="btn btn-green" data-toggle="navtab" data-options="{title:'${bean.courseName}',id:'${bean.courseName}'}">修改</a>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
