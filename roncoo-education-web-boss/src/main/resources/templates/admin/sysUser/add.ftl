<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/admin/sysUser/save" data-toggle="validate" method="post">
        <div class="form-group">
            <label class="control-label x85">手机：</label>
            <input type="text" name="mobile" data-rule="required" value="请选择" size="20" data-toggle="lookup" data-url="${base}/user/user/sysUserList" data-width="800" data-height="600">
            <input type="hidden" name="userNo" />
        </div>
        <div class="form-group">
            <label class="control-label x85">姓名：</label>
            <input type="text" name="realName" value="" placeholder="真实姓名" size="20" data-rule="required">
        </div>
        <div class="form-group">
            <label for="statusId" class="control-label x85">备注：</label>
            <textarea name="remark" cols="20" rows="3" data-toggle="autoheight" class="form-control autosize"></textarea>
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>
