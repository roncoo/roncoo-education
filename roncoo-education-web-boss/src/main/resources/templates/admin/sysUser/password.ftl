<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/admin/sysUserInfo/password" data-toggle="validate" method="post">
		<div class="form-group">
            <label for="j_pwschange_oldpassword" class="control-label x85">旧密码：</label>
            <input type="password" data-rule="required" name="oldPwd" id="j_pwschange_oldpassword" value="" placeholder="旧密码" size="20">
        </div>
        <div class="form-group">
            <label for="j_pwschange_newpassword" class="control-label x85">新密码：</label>
            <input type="password" data-rule="新密码:required" name="newPwd" id="j_pwschange_newpassword" value="" placeholder="新密码" size="20">
        </div>
        <div class="form-group">
            <label for="j_pwschange_secpassword" class="control-label x85">确认密码：</label>
            <input type="password" data-rule="required;match(pass)" name="rePwd" id="j_pwschange_secpassword" value="" placeholder="确认新密码" size="20">
        </div>
        
        <div class="form-group">
            <label for="j_pwschange_secpassword" class="control-label x85">&nbsp;</label>
            * 密码修改之后，系统要求用户必须重新登录
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>