<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
    <form action="${base}/system/website/updateWebsite" data-toggle="validate" method="post" enctype="multipart/form-data">
        <#if bean??>
        <input type="hidden" name="id" value="${bean.id!}"/>
        </#if>
        <fieldset>
            <legend>站点信息</legend>
            <div class="form-group">
                <label class="control-label x120">站点标题：</label>
                <input type="text" name="websiteTitle" value="<#if bean??>${bean.websiteTitle!}</#if>" placeholder="站点标题" size="50">
            </div>
            <div class="form-group">
                <label class="control-label x120">站点关键词：</label>
                <input type="text" name="websiteKeyword" value="<#if bean??>${bean.websiteKeyword!}</#if>" placeholder="站点关键词" size="50">
            </div>
            <div class="form-group">
                <label class="control-label x120">站点描述：</label>
                <textarea data-toggle="autoheight" name="websiteDesc" cols="50"><#if bean??>${bean.websiteDesc!}</#if></textarea>
            </div>
            <div class="form-group">
                <label class="control-label x120">站点版权：</label>
                <input type="text" name="copyright" value="<#if bean??>${bean.copyright!}</#if>" placeholder="站点版权" size="50">
            </div>
            <div class="form-group">
                <label class="control-label x120">备案号：</label>
                <input type="text" name="icp" value="<#if bean??>${bean.icp!}</#if>" placeholder="备案号" size="50">
            </div>
            <div class="form-group">
                <label class="control-label x120">公安备案号：</label>
                <input type="text" name="prn" value="<#if bean??>${bean.prn!}</#if>" placeholder="公安备案号" size="50">
            </div>
            <div class="form-group">
                <label class="control-label x120">站点微信：</label>
                <input name="weixinFile" type="file" />
                <img src="<#if bean??>${bean.weixin!}</#if>" width="80">
            </div>
            <div class="form-group">
                <label class="control-label x120">小程序二维码：</label>
                <input name="weixinXcxFile" type="file" />
                <img src="<#if bean??>${bean.weixinXcx!}</#if>" width="80">
            </div>
            <div class="form-group">
                <label class="control-label x120">站点微博：</label>
                <input name="weiboFile" type="file" />
                <img src="<#if bean??>${bean.weibo!}</#if>" width="80">
            </div>
            <div class="form-group">
                <label class="control-label x120">网站logo：</label>
                <input name="logoImgFile" type="file" />
                <img src="<#if bean??>${bean.logoImg!}</#if>" width="80">
            </div>
            <div class="form-group">
                <label class="control-label x120">网站Ico：</label>
                <input name="logoIcoFile" type="file" />
                <img src="<#if bean??>${bean.logoIco!}</#if>" width="80">
            </div>
            <div class="form-group">
				<label class="control-label x120">用户协议：</label>
				<br /><br />
				<div style="margin-left: 55px; width:800px" id="websiteEditEditor">
	                <p><#if bean??>${bean.userAgreement!}</#if></p>
        		</div>
        		<textarea id="websiteEditDesc" style="display:none" name="userAgreement" style="width:100%; height:200px;"></textarea>
        	</div>
        </fieldset>  
        
        <fieldset>
            <legend>客服信息</legend>
            <div class="form-group">
                <label for="isShowService" class="control-label x85">客服设置：</label>
                <#if bean??>
	                <#list isShowServiceEnums as em>
	                	<input type="radio" id="isShowService${em.code}" name="isShowService" value="${em.code}" <#if bean.isShowService?? && bean.isShowService==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
	                </#list>
	            <#else>
	            	<input type="radio" name="isShowService" value="1" checked="checked" data-toggle="icheck" data-label="是" size="20">
				    <input type="radio" name="isShowService" value="2" data-toggle="icheck" data-label="否" size="20">
                </#if>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label class="control-label x85">客服信息1：</label>
                <input type="text" name="service1" value="<#if bean??>${bean.service1!}</#if>" placeholder="客服信息1" size="20">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<label class="control-label x85">客服信息2：</label>
                <input type="text" name="service2" value="<#if bean??>${bean.service2!}</#if>" placeholder="客服信息2" size="20">
            </div>
        </fieldset> 
    
        <fieldset>
            <legend>统计信息</legend>
            <div class="form-group">
                <label for="isEnableStatistics" class="control-label x85">统计设置：</label>
                <#if bean??>
	                <#list isEnableStatisticsEnums as em>
	                	<input type="radio" id="isEnableStatistics${em.code}" name="isEnableStatistics" value="${em.code}" <#if bean.isEnableStatistics?? && bean.isEnableStatistics==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
	                </#list>
                <#else>
                	<input type="radio" name="isEnableStatistics" value="1" data-toggle="icheck" checked="checked" data-label="开启" size="20">
				    <input type="radio" name="isEnableStatistics" value="0" data-toggle="icheck" data-label="关闭" size="20">
                </#if>
            </div>
            <div class="form-group">
                <label class="control-label x85">统计代码：</label>
                <textarea data-toggle="autoheight" name="statisticsCode" cols="50"><#if bean??>${bean.statisticsCode!}</#if></textarea>
            </div>
        </fieldset>
        
		<fieldset>
		<legend>讲师招募</legend>
		<div class="form-group">
			<label class="control-label x120">招募信息：</label>
			<br /><br />
			<div style="margin-left: 55px; width:800px" id="recruitInfoEditEditor">
                <p><#if bean??>${bean.recruitInfo!}</#if></p>
    		</div>
    		<textarea id="recruitInfoEditDesc" style="display:none" name="recruitInfo" style="width:100%; height:200px;"></textarea>
    	</div>
    	<div class="form-group">
			<label class="control-label x120">入驻协议：</label>
			<br /><br />
			<div style="margin-left: 55px; width:800px" id="entryAgreementEditEditor">
                <p><#if bean??>${bean.entryAgreement!}</#if></p>
    		</div>
    		<textarea id="entryAgreementEditDesc" style="display:none" name="entryAgreement" style="width:100%; height:200px;"></textarea>
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
<script>
initEdit('#websiteEditEditor','${base}/uploadPic');
initEdit('#recruitInfoEditEditor','${base}/uploadPic');
initEdit('#entryAgreementEditEditor','${base}/uploadPic');
</script>