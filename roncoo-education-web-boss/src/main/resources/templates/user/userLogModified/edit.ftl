<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/user/userLogModified/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="${bean.gmtCreate!}" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">用户编号：</label>
            <input type="text" name="userNo" value="${bean.userNo!}" placeholder="用户编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">原手机号：</label>
            <input type="text" name="mobileOld" value="${bean.mobileOld!}" placeholder="原手机号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">新手机：</label>
            <input type="text" name="mobileNew" value="${bean.mobileNew!}" placeholder="新手机" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>