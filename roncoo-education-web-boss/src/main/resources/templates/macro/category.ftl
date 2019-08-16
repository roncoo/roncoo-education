<!-- 分类递归显示 -->
<#macro listCategory children label>
	<#if children??>
		<#list children as bean>
			<tr>
			    <td align="center"></td>
                <td>${label} 
                    <a href="${base}/course/courseCategory/view?id=${bean.id}" data-toggle="dialog" data-id="courseCategory-view" data-options="{title:'查看', height:250}">${bean.categoryName!} </a>
                </td>
                <td>${bean.remark!}</td>
                <td><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code>${em.desc}</#if></#list></td>
                <td>${bean.sort}</td>
                <td>
                    <a href="${base}/course/courseCategory/add2?parentId=${bean.id}&floor=${bean.floor}&categoryType=${bean.categoryType}" class="btn btn-default" data-toggle="dialog" data-id="courseCategory-add" data-options="{title:'添加', height:250}">添加 </a>
                    <a href="${base}/course/courseCategory/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="courseCategory-delete" data-confirm-msg="确定要删除吗？">删除</a>
                    <a href="${base}/course/courseCategory/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-options="{title:'修改', height:250,id:'course-courseCategory'}">修改 </a>
                </td>
			</tr>
			<@listCategory children=bean.list label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+label/>
		</#list>
	</#if>
</#macro>

<!-- 分类递归显示 -->
<#macro categoryListForRole children>
    <#if children?? && children?size gt 0>
        <#list children as bean>
            <li data-id="${bean.id}" data-pid="${bean.parentId}" <#if bean.isShow == 1>data-checked="true"</#if>>${bean.categoryName}</li>
            <@categoryListForRole children=bean.list/>
        </#list>
    </#if>
</#macro>
