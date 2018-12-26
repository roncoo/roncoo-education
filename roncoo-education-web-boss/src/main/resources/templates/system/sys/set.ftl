<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
    <form action="${base}/system/sys/updateSys" data-toggle="validate" method="post">
    	<input type="hidden" name="id" value="${bean.id!}"/>
        <fieldset>
            <legend>视频存储信息</legend>
            <div class="form-group">
                <label for="isShowService" class="control-label x150">视频存储平台：</label>
                <#list videoTypeEnums as em>
                <input type="radio" id="videoType${em.code}" class="videoType" name="videoType" value="${em.code}" <#if bean.videoType?? && bean.videoType==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
                </#list>
            </div>
            <br />
            <div class="form-group">
                <label class="control-label x150">useid：</label>
                <input type="text" name="polyvUseid" value="${bean.polyvUseid!}" placeholder="useid" size="20">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label class="control-label x120">secretkey：</label>
                <input type="text" name="polyvSecretkey" value="${bean.polyvSecretkey!}" placeholder="secretkey" size="20">
            </div>
            <br />
            <div class="form-group">
                <label class="control-label x150">readtoken：</label>
                <input type="text" name="polyvReadtoken" value="${bean.polyvReadtoken!}" placeholder="readtoken" size="57.5">
            </div>
            <br />
            <div class="form-group">
            	<label class="control-label x150">writetoken：</label>
            	<input type="text" name="polyvWritetoken" value="${bean.polyvWritetoken!}" placeholder="writetoken" size="57.5">
            </div>
        </fieldset>  
        
        <fieldset>
        <legend>文件存储信息</legend>
        	<div class="form-group">
                <label for="isShowService" class="control-label x150">文件存储类型：</label>
                <#list fileTypeEnums as em>
                <input type="radio" id="fileType${em.code}" name="fileType" value="${em.code}" <#if bean.fileType?? && bean.fileType==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
                </#list>
            </div>
            <br />
            <div class="form-group">
                <label class="control-label x150">accessKeyId：</label>
                <input type="text" name="aliyunAccessKeyId" value="${bean.aliyunAccessKeyId!}" placeholder="accessKeyId" size="20">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label class="control-label x120">ossBucket：</label>
                <input type="text" name="aliyunOssBucket" value="${bean.aliyunOssBucket!}" placeholder="accessKeySecret" size="20">
            </div>
            <br />
            <div class="form-group">
                <label class="control-label x150">oasVault：</label>
                <input type="text" name="aliyunOasVault" value="${bean.aliyunOasVault!}" placeholder="accessKeyId" size="20">
            </div>
            <br />
            <div class="form-group">
            	<label class="control-label x150">accessKeySecret：</label>
                <input type="text" name="aliyunAccessKeySecret" value="${bean.aliyunAccessKeySecret!}" placeholder="accessKeySecret" size="57.5">
            </div>
            <br />
            <div class="form-group">
                <label class="control-label x150">ossUrl：</label>
                <input type="text" name="aliyunOssUrl" value="${bean.aliyunOssUrl!}" placeholder="accessKeyId" size="57.5">
            </div>
            <br />
        </fieldset>
        
        <fieldset>
        <legend>支付通道信息</legend>
        	<div class="form-group">
                <label for="isShowService" class="control-label x150">支付通道：</label>
                <#list sysTypeEnums as em>
                <input type="radio" id="payType${em.code}" name="payType" value="${em.code}" <#if bean.payType?? && bean.payType==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
                </#list>
            </div>
            <br />
            <div class="form-group">
                <label class="control-label x150">payKey：</label>
                <input type="text" name="payKey" value="${bean.payKey!}" placeholder="roncooKey" size="57.5">
            </div>
            <br />
            <div class="form-group">
                <label class="control-label x150">paySecret：</label>
                <input type="text" name="paySecret" value="${bean.paySecret!}" placeholder="roncooSecret" size="57.5">
            </div>
            <br />
            <div class="form-group">
                <label class="control-label x150">payUrl：</label>
                <input type="text" name="payUrl" value="${bean.payUrl!}" placeholder="roncooKey" size="57.5">
            </div>
            <br />
            <div class="form-group">
                <label class="control-label x150">notifyUrl：</label>
                <input type="text" name="notifyUrl" value="${bean.notifyUrl!}" placeholder="roncooSecret" size="57.5">
            </div>
        </fieldset>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>