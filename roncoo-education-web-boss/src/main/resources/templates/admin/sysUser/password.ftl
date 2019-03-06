<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/admin/sysUser/updateBypassword" data-toggle="validate" method="post">
		
		<input type="hidden" name="userNo" value="${userNo}"/>
		
      
        <div class="form-group">
            <label class="control-label x85">新密码：</label>
            <input type="password" name="mobilePsw" value="" placeholder="新密码" size="20">
        </div>
        <div class="form-group">
            <label class="control-label x85">确认密码：</label>
            <input type="password" name="rePwd" value="" placeholder="确认新密码" size="20">
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