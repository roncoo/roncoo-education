<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/user/userExt/update" data-toggle="validate" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">用户头像：</label>
            <input type="file" name="headImgFile" />
        </div>
        <#if bean.headImgUrl??>
        <div class="form-group">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${bean.headImgUrl!}" width="100"> 
        </div>
        </#if>
        <br/>
        <div class="form-group">
            <label class="control-label x85">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label>
            <input type="text" name="nickname" value="${bean.nickname!}" placeholder="用户昵称" size="20">
        </div>
		<br/>
		<div class="form-group">
            <label class="control-label x85">用户手机：</label>
            <input type="text" name="mobile" value="${bean.mobile!}" placeholder="用户手机" size="20" disabled="disabled">
        </div>
        <br/>
        <div class="form-group">
            <label class="control-label x85">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
			<#list sexEnums as em>
    			<input type="radio" id="sex" name="sex" value="${em.code}" <#if bean.sex?? && bean.sex==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
    		</#list>
        </div>
        <br/>
        <div class="form-group">
            <label class="control-label x85">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</label>
            <input type="text" name="age" value="${bean.age!}" placeholder="年龄" size="20">
        </div>
        <br/>
        <div class="form-group">
            <label class="control-label x85">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
            <input type="text" name="remark" value="${bean.remark!}" placeholder="备注" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>