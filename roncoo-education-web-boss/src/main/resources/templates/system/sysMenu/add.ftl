<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/system/sysMenu/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">状态(1:正常，0:禁用)：</label>
            <input type="text" name="statusId" value="" placeholder="状态(1:正常，0:禁用)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">父ID：</label>
            <input type="text" name="parentId" value="" placeholder="父ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">菜单名称：</label>
            <input type="text" name="menuName" value="" placeholder="菜单名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">菜单路径：</label>
            <input type="text" name="menuUrl" value="" placeholder="菜单路径" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">菜单图标：</label>
            <input type="text" name="menuIcon" value="" placeholder="菜单图标" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">目标名称：</label>
            <input type="text" name="targetName" value="" placeholder="目标名称" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>