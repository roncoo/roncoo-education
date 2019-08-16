<#assign base=request.contextPath /> 
<#include "/macro/sysMenu.ftl" />
<div class="bjui-pageContent">
	<form action="${base}/admin/sysMenuRole/save" data-toggle="validate" method="post">
		<input type="hidden" name="roleId" value="${bean.roleId!}">
		<input type="hidden" name="ids" value="${ids!}" id="menuIds" >
		
		<div class="form-group">
			<label class="control-label x85">权限列表：</label>
			<input type="text" name="menus" id="j_ztree_menus1" data-toggle="selectztree" size="25" data-tree="#j_select_tree1" readonly>
			<ul id="j_select_tree1" class="ztree hide" data-toggle="ztree" data-expand-all="false" data-check-enable="true" data-on-check="S_NodeCheck" data-on-click="S_NodeClick">
				<#list menuList as bean>
                     <li data-id="${bean.id}" data-pid="${bean.parentId}" <#if bean.isShow == 1>data-checked="true"</#if> >${bean.menuName}</li>
                     <#if bean.list?? && bean.list?size gt 0>
                         <@menuListForRole children=bean.list/>
                     </#if>
				</#list>
			</ul>
		</div>
	</form>
</div>

<div class="bjui-pageFooter">
	<ul>
		<li>
			<button type="button" class="btn-close" data-icon="close">关闭</button>
		</li>
		<li>
			<button type="submit" class="btn-default" data-icon="save">授权</button>
		</li>
	</ul>
</div>

<script type="text/javascript">
	//选择事件
	function S_NodeCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId), nodes = zTree.getCheckedNodes(true)
		var ids = '', names = ''
		for (var i = 0; i < nodes.length; i++) {
			ids += ',' + nodes[i].id
			names += ',' + nodes[i].name
			console.log(nodes[i].url);
		}
		if (ids.length > 0) {
			ids = ids.substr(1), names = names.substr(1)
		}
		
		var $from = $('#' + treeId).data('fromObj')
		if ($from && $from.length) {
			$from.val(names)
		}
		
		// 记录菜单ID
		$('#menuIds').val(ids);
	}
	//单击事件
	function S_NodeClick(event, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId)
		zTree.checkNode(treeNode, !treeNode.checked, true, true)
		event.preventDefault()
	}
</script>
<style>
<!--
#j_ztree_menus1{cursor:text;}
#j_select_tree1_select_box{max-height: 240px !important;}
-->
</style>