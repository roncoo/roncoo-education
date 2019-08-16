<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/admin/sysMenu/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="${bean.gmtCreate!}" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="${bean.gmtModified!}" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">1 正常 2 冻结 9 注销：</label>
            <input type="text" name="statusId" value="${bean.statusId!}" placeholder="1 正常 2 冻结 9 注销" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">父ID：</label>
            <input type="text" name="parentId" value="${bean.parentId!}" placeholder="父ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">菜单名称：</label>
            <input type="text" name="menuName" value="${bean.menuName!}" placeholder="菜单名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">菜单路径：</label>
            <input type="text" name="menuUrl" value="${bean.menuUrl!}" placeholder="菜单路径" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">目标名称：</label>
            <input type="text" name="targetName" value="${bean.targetName!}" placeholder="目标名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">菜单图标：</label>
            <input type="text" name="menuIcon" value="${bean.menuIcon!}" placeholder="菜单图标" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>