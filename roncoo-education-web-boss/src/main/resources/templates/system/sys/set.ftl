<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
    <form action="${base}/system/sys/updateSys" data-toggle="validate" method="post">
    	<#if bean??>
    		<input type="hidden" name="id" value="${bean.id!}"/>
    	</#if>
	    <#if user.id == 1>
	        <fieldset>
	            <legend>视频存储信息</legend>
	            <div class="form-group">
	                <label for="isShowService" class="control-label x150">视频存储平台：</label>
	                <#if bean??>
		                <#list videoTypeEnums as em>
		                <input type="radio" id="videoType${em.code}" class="videoType" name="videoType" value="${em.code}" <#if bean.videoType?? && bean.videoType==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
		                </#list>
	                <#else>
		                <input type="radio" name="videoType" value="1" data-toggle="icheck" checked="checked" data-label="保利威视" size="20">
					    <input type="radio" name="videoType" value="2" data-toggle="icheck" data-label="七牛" size="20">
					    <input type="radio" name="videoType" value="3" data-toggle="icheck" data-label="本地" size="20" checked="checked">
	            	</#if>
	            </div>
	            <br />
	            <div class="form-group">
	                <label class="control-label x150">useid：</label>
	                <input type="text" name="polyvUseid" value="<#if bean??>${bean.polyvUseid!}</#if>" placeholder="useid" size="40">
	                <label class="control-label x150">secretkey：</label>
	                <input type="text" name="polyvSecretkey" value="<#if bean??>${bean.polyvSecretkey!}</#if>" placeholder="secretkey" size="40">
	            </div>
	            <br />
	            <div class="form-group">
	                <label class="control-label x150">readtoken：</label>
	                <input type="text" name="polyvReadtoken" value="<#if bean??>${bean.polyvReadtoken!}</#if>" placeholder="readtoken" size="40">
	                <label class="control-label x150">writetoken：</label>
	            	<input type="text" name="polyvWritetoken" value="<#if bean??>${bean.polyvWritetoken!}</#if>" placeholder="writetoken" size="40">
	            </div>
	        </fieldset>  
	        
	        <fieldset>
	        <legend>文件存储信息</legend>
	        	<div class="form-group">
	                <label for="isShowService" class="control-label x150">文件存储类型：</label>
	                <#if bean??>
	                	<#list fileTypeEnums as em>
		                	<input type="radio" id="fileType${em.code}" name="fileType" value="${em.code}" <#if bean.fileType?? && bean.fileType==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
		                </#list>
	                <#else>
		                <input type="radio" name="fileType" value="1" data-toggle="icheck" checked="checked" data-label="阿里云" size="20">
					    <input type="radio" name="fileType" value="2" data-toggle="icheck" data-label="七牛" size="20">
					    <input type="radio" name="fileType" value="3" data-toggle="icheck" data-label="本地" size="20" checked="checked">
	            	</#if>
	            </div>
	            <br />
	            <div class="form-group">
	                <label class="control-label x150">accessKeyId：</label>
	                <input type="text" name="aliyunAccessKeyId" value="<#if bean??>${bean.aliyunAccessKeyId!}</#if>" placeholder="accessKeyId" size="40">
	                <label class="control-label x150">accessKeySecret：</label>
	                <input type="text" name="aliyunAccessKeySecret" value="<#if bean??>${bean.aliyunAccessKeySecret!}</#if>" placeholder="accessKeySecret" size="40">
	            </div>
	            <br />
	            <div class="form-group">
	                <label class="control-label x150">ossUrl：</label>
	                <input type="text" name="aliyunOssUrl" value="<#if bean??>${bean.aliyunOssUrl!}</#if>" placeholder="accessKeyId" size="40">
	                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                <label class="control-label x120">ossBucket：</label>
	                <input type="text" name="aliyunOssBucket" value="<#if bean??>${bean.aliyunOssBucket!}</#if>" placeholder="accessKeySecret" size="40">
	            </div>
	            <br />
	            <div class="form-group">
	                <label class="control-label x150">smsCode：</label>
	                <input type="text" name="smsCode" value="<#if bean??>${bean.smsCode!}</#if>" placeholder="smsCode" size="40">
	                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                <label class="control-label x120">短信签名：</label>
	                <input type="text" name="signName" value="<#if bean??>${bean.signName!}</#if>" placeholder="signName" size="40">
	            </div>
	            <br />
	        </fieldset>
	        
	        <fieldset>
	        <legend>支付通道信息</legend>
	        	<div class="form-group">
	                <label for="isShowService" class="control-label x150">支付通道：</label>
	                <#if bean??>
	                	<#list sysTypeEnums as em>
		                	<input type="radio" id="payType${em.code}" name="payType" value="${em.code}" <#if bean.payType?? && bean.payType==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
		                </#list>
	                <#else>
		                <input type="radio" name="payType" value="1" data-toggle="icheck" checked="checked" data-label="龙果支付" size="20">
					    <input type="radio" name="payType" value="2" data-toggle="icheck" data-label="其他" size="20">
	            	</#if>
	            </div>
	            <br />
	            <div class="form-group">
	                <label class="control-label x150">payKey：</label>
	                <input type="text" name="payKey" value="<#if bean??>${bean.payKey!}</#if>" placeholder="roncooKey" size="40">
	                <label class="control-label x150">paySecret：</label>
	                <input type="text" name="paySecret" value="<#if bean??>${bean.paySecret!}</#if>" placeholder="roncooSecret" size="40">
	            </div>
	            <br />
	            <div class="form-group">
	            	<label class="control-label x150">支付请求：</label>
	                <input type="text" name="payUrl" value="<#if bean??>${bean.payUrl!}</#if>" placeholder="payUrl" size="40">
	                <label class="control-label x150">回调地址：</label>
	                <input type="text" name="notifyUrl" value="<#if bean??>${bean.notifyUrl!}</#if>" placeholder="notifyUrl" size="40">
	            </div>
	        </fieldset>
	    <#else>
	    	<fieldset>
	            <legend>视频存储信息</legend>
	            <div class="form-group">
	                <label for="isShowService" class="control-label x150">视频存储平台：</label>
	                <#if bean??>
		                <#list videoTypeEnums as em>
		                <input type="radio" id="videoType${em.code}" class="videoType" name="videoType" value="${em.code}" <#if bean.videoType?? && bean.videoType==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
		                </#list>
	                <#else>
		                <input type="radio" name="videoType" value="1" data-toggle="icheck" checked="checked" data-label="保利威视" size="20">
					    <input type="radio" name="videoType" value="2" data-toggle="icheck" data-label="七牛" size="20">
	            	</#if>
	            </div>
	            <br />
	            <div class="form-group">
	                <label class="control-label x150">useid：</label>
	                <input type="text" name="polyvUseid" value="********" placeholder="useid" size="40">
	                <label class="control-label x150">secretkey：</label>
	                <input type="text" name="polyvSecretkey" value="********" placeholder="secretkey" size="40">
	            </div>
	            <br />
	            <div class="form-group">
	                <label class="control-label x150">readtoken：</label>
	                <input type="text" name="polyvReadtoken" value="********" placeholder="readtoken" size="40">
	                <label class="control-label x150">writetoken：</label>
	            	<input type="text" name="polyvWritetoken" value="********" placeholder="writetoken" size="40">
	            </div>
	        </fieldset>  
	        
	        <fieldset>
	        <legend>文件存储信息</legend>
	        	<div class="form-group">
	                <label for="isShowService" class="control-label x150">文件存储类型：</label>
	                <#if bean??>
	                	<#list fileTypeEnums as em>
		                	<input type="radio" id="fileType${em.code}" name="fileType" value="${em.code}" <#if bean.fileType?? && bean.fileType==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
		                </#list>
	                <#else>
		                <input type="radio" name="fileType" value="1" data-toggle="icheck" checked="checked" data-label="阿里云" size="20">
					    <input type="radio" name="fileType" value="2" data-toggle="icheck" data-label="七牛" size="20">
	            	</#if>
	            </div>
	            <br />
	            <div class="form-group">
	                <label class="control-label x150">accessKeyId：</label>
	                <input type="text" name="aliyunAccessKeyId" value="********" placeholder="accessKeyId" size="40">
	                <label class="control-label x150">accessKeySecret：</label>
	                <input type="text" name="aliyunAccessKeySecret" value="********" placeholder="accessKeySecret" size="40">
	            </div>
	            <br />
	            <div class="form-group">
	            	<label class="control-label x150">ossUrl：</label>
	                <input type="text" name="aliyunOssUrl" value="********" placeholder="ossUrl" size="40">
	            	<label class="control-label x150">ossBucket：</label>
	                <input type="text" name="aliyunOssBucket" value="********" placeholder="ossBucket" size="40">
	            </div>
	            <br />
	            <div class="form-group">
	                <label class="control-label x150">smsCode：</label>
	                <input type="text" name="smsCode" value="********" placeholder="smsCode" size="40">
	                <label class="control-label x150">短信签名：</label>
	                <input type="text" name="signName" value="********" placeholder="signName" size="40">
	            </div>
	        </fieldset>
	        
	        <fieldset>
	        <legend>支付通道信息</legend>
	        	<div class="form-group">
	                <label for="isShowService" class="control-label x150">支付通道：</label>
	                <#if bean??>
	                	<#list sysTypeEnums as em>
		                	<input type="radio" id="payType${em.code}" name="payType" value="${em.code}" <#if bean.payType?? && bean.payType==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
		                </#list>
	                <#else>
		                <input type="radio" name="payType" value="1" data-toggle="icheck" checked="checked" data-label="龙果支付" size="20">
					    <input type="radio" name="payType" value="2" data-toggle="icheck" data-label="其他" size="20">
	            	</#if>
	            </div>
	            <br />
	            <div class="form-group">
	                <label class="control-label x150">payKey：</label>
	                <input type="text" name="payKey" value="********" placeholder="roncooKey" size="40">
	                <label class="control-label x150">paySecret：</label>
	                <input type="text" name="paySecret" value="********" placeholder="roncooSecret" size="40">
	            </div>
	            <br />
	            <div class="form-group">
	            	<label class="control-label x150">支付请求：</label>
	                <input type="text" name="payUrl" value="********" placeholder="payUrl" size="40">
	                <label class="control-label x150">回调地址：</label>
	                <input type="text" name="notifyUrl" value="********" placeholder="notifyUrl" size="40">
	            </div>
	        </fieldset>
    	</#if>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>