<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<div class="panel-body">
        <div style="mini-width:400px;height:280px" data-toggle="echarts" data-type="bar,line" data-url="${base!''}/course/courseUserStudyLog/coursePlayCount?orgNo=${bean.orgNo!''}&courseNo=${bean.courseNo!''}&beginGmtCreate=${bean.beginGmtCreate!''}&endGmtCreate=${bean.endGmtCreate!''}"></div>
    </div>
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/course/courseUserStudyLog/courseList" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<@shiro.hasRole name="admin">
       		<label>机构：</label>
       		<input type="text" name="orgNo" value="<#if bean.orgNo?? && bean.orgNo !='admin'>${bean.orgNo!''}</#if>" class="form-control" size="12">&nbsp;
        	</@shiro.hasRole>
			&nbsp;<label>统计时间：</label>
			<input type="text" name="beginGmtCreate" value="${bean.beginGmtCreate!''}" data-toggle="datepicker" data-minDate="2015-01-01" data-rule="date" size="12">-<input type="text" name="endGmtCreate" value="${bean.endGmtCreate!''}" data-toggle="datepicker" data-rule="date" size="12">
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			<@shiro.hasRole name="admin">
			【总播放次数: <span class="red">${countMap.totalInOrgNo!'0'}</span>次】 -【课程总播放次数: <span class="red">${countMap.totalInCourse!'0'}</span>次】-【试卷总播放次数:<span class="red">${countMap.totalInPaper!'0'}</span>次】
			</@shiro.hasRole>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>课程名称</th>
				<th>播放次数</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td><a href="${base}/course/courseUserStudyLog/periodList?courseId=${bean.courseId!}" data-toggle="dialog" data-options="{mask:true,title:'${bean.courseName}-详细信息',width:1400,height:600}">${bean.courseName!}</a></td>
				<td>${bean.chapterId!}</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
