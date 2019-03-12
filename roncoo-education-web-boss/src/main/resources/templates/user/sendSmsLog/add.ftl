<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/user/sendSmsLog/send" data-toggle="validate" method="post">
		<br />
		<br />
		<div class="form-group">
            <label class="control-label x85">手机号：</label>
            <input type="text" name="mobile" value="" placeholder="手机号" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>