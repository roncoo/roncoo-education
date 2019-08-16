<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/user/userLogModified/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">用户编号：</label>
            <input type="text" name="userNo" value="" placeholder="用户编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">原手机号：</label>
            <input type="text" name="mobileOld" value="" placeholder="原手机号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">新手机：</label>
            <input type="text" name="mobileNew" value="" placeholder="新手机" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>