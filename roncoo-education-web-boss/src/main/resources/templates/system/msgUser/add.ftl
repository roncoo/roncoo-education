<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/system/msgUser/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">状态(1有效, 0无效)：</label>
            <input type="text" name="statusId" value="" placeholder="状态(1有效, 0无效)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">短信ID：</label>
            <input type="text" name="msgId" value="" placeholder="短信ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">用户编号：</label>
            <input type="text" name="userNo" value="" placeholder="用户编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">短信类型：</label>
            <input type="text" name="msgType" value="" placeholder="短信类型" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">短信标题：</label>
            <input type="text" name="msgTitle" value="" placeholder="短信标题" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否阅读(1是;0否)：</label>
            <input type="text" name="isRead" value="" placeholder="是否阅读(1是;0否)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否置顶(1是;0否)：</label>
            <input type="text" name="isTop" value="" placeholder="是否置顶(1是;0否)" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>