<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/user/sendSmsLog/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="${bean.gmtCreate!}" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">验证码：</label>
            <input type="text" name="smsCode" value="${bean.smsCode!}" placeholder="验证码" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否发送成功(1发送成功，0:发送失败)：</label>
            <input type="text" name="isSuccess" value="${bean.isSuccess!}" placeholder="是否发送成功(1发送成功，0:发送失败)" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>