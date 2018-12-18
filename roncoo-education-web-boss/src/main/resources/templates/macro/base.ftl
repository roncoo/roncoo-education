<!-- 应用上下文 -->
<#assign base=request.contextPath /> 
<#macro navbar>
<ul id="bjui-hnav-navbar">
    <#list menuList as menu>
     <li <#if menu_index+1 ==1> class="active" </#if>>
        <a href="javascript:;" data-toggle="slidebar">
            <i class="fa fa-table"></i> ${menu.menuName}
        </a>
        <div class="items hide" data-noinit="true">
            <ul id="bjui-hnav-tree${menu_index+1}" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true" data-faicon="table">
                <#list menu.list as me>
                    <li data-id="${me.id}" data-pid="${me.parentId}" data-faicon="folder-open-o" data-faicon-close="folder-o">${me.menuName}</li>
                    <#list me.list as m>
                        <li data-id="${m.id}" data-pid="${m.parentId}" data-url="${base}${m.menuUrl}" data-tabid="${m.targetName}" data-faicon="list-alt">${m.menuName}</li>
                    </#list>
                </#list>
            </ul>
        </div>
    </li>
    </#list>
</ul>
</#macro>

<!-- 分页 -->
<#macro pageHeadr>
<input type="hidden" name="pageSize" value="${(page.pageSize)!'1'}">
<input type="hidden" name="pageCurrent" value="${(page.pageCurrent)!'20'}">
</#macro>

<!-- 分页 -->
<#macro pageFooter>
<div class="bjui-pageFooter">
	<div class="pages">
		<span>每页&nbsp;</span>
		<div class="selectPagesize">
			<select data-toggle="selectpicker" data-toggle-change="changepagesize">
				<#if page??>
				<option value="5"<#if page.pageSize == 5>selected="selected"</#if>>5</option>
				<option value="20"<#if page.pageSize == 20>selected="selected"</#if>>20</option>
				<option value="50"<#if page.pageSize == 50>selected="selected"</#if>>50</option>
				<option value="100"<#if page.pageSize == 100>selected="selected"</#if>>100</option>
				<option value="200"<#if page.pageSize == 200>selected="selected"</#if>>200</option>
				<option value="500"<#if page.pageSize == 500>selected="selected"</#if>>500</option>
				<option value="1000"<#if page.pageSize == 1000>selected="selected"</#if>>1000</option>
				<#else>
				<option value="0">0</option>
				</#if>
			</select>
		</div>
		<span>&nbsp;条，共 ${(page.totalCount)!'0'}条，当前第${(page.pageCurrent)!'0'}页</span>
	</div>
	<div class="pagination-box" data-toggle="pagination" data-total="${(page.totalCount)!}" data-page-size="${(page.pageSize)!}" data-page-current="${(page.pageCurrent)!}"></div>
</div>
</#macro>

