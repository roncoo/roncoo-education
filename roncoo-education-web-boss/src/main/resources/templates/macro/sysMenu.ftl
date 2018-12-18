<!-- 菜单递归显示 -->
<#macro listMenu children label>
	<#if children??>
		<#list children as bean>
			<tr>
			    <td align="center"></td>
			    <td>
                    <#list oauth2ClientList as oauth2Client>
                        <#if bean.clientId?? && oauth2Client.id == bean.clientId> </#if>
                    </#list>
                </td>
                <td>${label} ${bean.menuName}</td>
                <td>${bean.menuUrl}</td>
                <td>${bean.targetName!}</td>
                <td>${bean.menuIcon!}</td>
                <td>${bean.sort}</td>
                <td>
                	<#if bean.statusId == 'Y'>可用</#if> <#if bean.statusId == 'N'>禁用</#if>
                </td>
               <td>${bean.remark!}</td>
                <td>
                	<a href="${base}/admin/sys/sysMenu/add?parentId=${bean.id}&clientId=${bean.clientId}" class="btn btn-default" data-toggle="dialog" data-id="sysMenu-add" data-options="{title:'添加', height:320, width:425}">添加 </a>
                    <a href="${base}/admin/sys/sysMenu/view?id=${bean.id}" class="btn btn-blue" data-toggle="dialog" data-id="sysMenu-view" data-options="{title:'查看', height:320}">查看 </a>
					<a href="${base}/admin/sys/sysMenu/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-id="sysMenu-edit" data-options="{title:'修改', height:320}">修改 </a>
					<a href="${base}/admin/sys/sysMenu/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="sysMenu-delete" data-confirm-msg="确定要删除吗？">删除</a>
                </td>
			</tr>
			<@listMenu children=bean.list label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+label/>
		</#list>
	</#if>
</#macro>

<!-- 菜单递归显示 -->
<#macro menuListForRole children>
    <#if children?? && children?size gt 0>
        <#list children as bean>
            <li data-id="${bean.id}" data-pid="${bean.parentId}" <#if bean.isShow == 1>data-checked="true"</#if>>${bean.menuName}</li>
            <@menuListForRole children=bean.list/>
        </#list>
    </#if>
</#macro>
