<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/admin/sysMenu/save" data-toggle="validate" method="post">
	    <input name="parentId" type="hidden" value="${bean.parentId}"/>
		<div class="form-group">
            <label class="control-label x85">菜单名称：</label>
            <input type="text" name="menuName" value="" placeholder="菜单名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">菜单路径：</label>
            <input type="text" name="menuUrl" value="" placeholder="菜单路径" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">目标名称：</label>
            <input type="text" name="targetName" value="" placeholder="目标名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">菜单图标：</label>
            <input type="text" name="menuIcon" value="" placeholder="菜单图标" size="20">
        </div>
        <div class="form-group">
            <label class="control-label x85">排序：</label>
            <input type="text" name="sort" value="1" placeholder="排序" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>