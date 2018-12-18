<div class="bjui-pageContent">
    <div class="form-group">
        <div class="col-lg-6">
            <label class="control-label">登录名：</label>${bean.loginName!}
        </div>
        <div class="col-lg-6">
            <label class="control-label">用户名：</label>${bean.nickName!}
        </div>
    </div>
    <br/>
    <br/>
    <div class="form-group">
        <div class="col-lg-6">
            <label class="control-label">邮箱：</label>${bean.email!}
        </div>
        <div class="col-lg-6">
            <label class="control-label">手机：</label>${bean.mobile!}
        </div>
    </div>
    <br/>
    <br/>
    <div class="form-group">
        <div class="col-lg-12">
            <label class="control-label">地址：</label>${bean.addr!}
        </div>
    </div>
    <br/>
    <br/>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">类型：</label><#list userTypeEnums as enumvo><#if bean.userType?? && bean.userType==enumvo.code>${enumvo.desc}</#if></#list>
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">状态：</label><#list statusIdEnums as enumvo><#if bean.statusId?? && bean.statusId==enumvo.code>${enumvo.desc}</#if></#list>
		</div>
	</div>
   
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
    </ul>
</div>