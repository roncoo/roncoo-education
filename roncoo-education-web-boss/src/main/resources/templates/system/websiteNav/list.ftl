<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/system/websiteNav/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>分类名称：</label><input type="text" name="navName" value="${bean.navName!''}" class="form-control" size="10">&nbsp;&nbsp;
			<label>状态：</label>
			<select name="statusId" data-toggle="selectpicker">
				<option value="">全部</option>
				<#list statusIdEnums as b>
				<option value="${b.code}"<#if bean.statusId?? && bean.statusId == b.code>selected="selected"</#if>>${b.desc!}</option>
				</#list>
			</select>&nbsp;&nbsp;
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			
			<br />
			<br />
			<a href="${base}/system/websiteNav/add?parentId=0" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="websiteNav-add" data-options="{title:'添加', height:150, width:450}">添加 </a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>导航名称</th>
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
				<a href="${base}/system/websiteNav/view?id=${bean.id}" data-toggle="dialog" data-options="{title:'查看', height:250}">${bean.navName!}</a>
				</td>
				<td><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
				<td>${bean.sort!}</td>
				<td>
                    <a href="${base}/system/websiteNav/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="websiteNav-delete" data-confirm-msg="确定要删除吗？">删除</a>
						<a href="${base}/system/websiteNav/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-id="websiteNav-edit" data-options="{title:'修改', height:200, width:370}">修改</a>
					<#if bean.statusId == 1>
						<a href="${base}/system/websiteNav/updateStatusId?id=${bean.id}&statusId=0" class="btn btn-red" data-toggle="doajax" data-id="websiteNav-updateStatusId" data-confirm-msg="确定要禁用吗？">禁用</a>
					</#if>
					<#if bean.statusId == 0>
						<a href="${base}/system/websiteNav/updateStatusId?id=${bean.id}&statusId=1" class="btn btn-green" data-toggle="doajax" data-id="websiteNav-updateStatusId" data-confirm-msg="确定要启用吗？">启用</a>
					</#if>
					<#if bean.parentId == 0>
                    	<a href="${base}/system/websiteNav/add?parentId=${bean.id}" class="btn btn-default" data-toggle="dialog" data-id="websiteNav-add" data-options="{title:'添加', height:150, width:450}">添加 </a>
                	</#if>
				</td>
			</tr>
			<#if bean.list?? && bean.list?size gt 0>
                <@listCategory children=bean.list label="&nbsp;&nbsp;|--"/>
            </#if>
			<#if bean.websiteNavList?? && bean.websiteNavList?size gt 0>
                  <#list bean.websiteNavList as b>
                     <tr>
                        <td align="center"></td>
                        <td>
                    		<a href="${base}/system/websiteNav/view?id=${b.id}" data-toggle="dialog" data-options="{title:'查看', height:250}">&nbsp;&nbsp;|--${b.navName!}</a>
                        </td>
                        <td><#list statusIdEnums as em><#if b.statusId?? && b.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></td>
                        <td>${b.sort!}</td>
                        <td>
                            <a href="${base}/system/websiteNav/delete?id=${b.id}" class="btn btn-red" data-toggle="doajax" data-id="websiteNav-delete" data-confirm-msg="确定要删除吗？">删除</a>
                        	<a href="${base}/system/websiteNav/edit?id=${b.id}" class="btn btn-green" data-toggle="dialog" data-id="websiteNav-edit" data-options="{title:'修改', height:200, width:370}">修改</a>
							<#if b.statusId == 1>
								<a href="${base}/system/websiteNav/updateStatusId?id=${b.id}&statusId=0" class="btn btn-red" data-toggle="doajax" data-id="websiteNav-updateStatusId" data-confirm-msg="确定要禁用吗？">禁用</a>
							</#if>
							<#if b.statusId == 0>
								<a href="${base}/system/websiteNav/updateStatusId?id=${b.id}&statusId=1" class="btn btn-green" data-toggle="doajax" data-id="websiteNav-updateStatusId" data-confirm-msg="确定要启用吗？">启用</a>
							</#if>
	                    	<#if b.statusId == 1>
								<#if b.isArticle == 0>
	                                <a href="${base}/system/websiteNavArticle/add?navId=${b.id}" class="btn btn-blue" data-toggle="navtab" data-id="websiteNavArticle-add">文章管理</a>
	                            <#else>
	                            	<a href="${base}/system/websiteNavArticle/edit?navId=${b.id}" class="btn btn-blue" data-toggle="navtab" data-id="websiteNavArticle-edit" >文章管理</a>
	                        	</#if>
							</#if>
                        </td>
                    </tr>
                  </#list>
            </#if>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />
