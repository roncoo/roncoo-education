<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
    <form action="${base}/system/website/updateWebsite" data-toggle="validate" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${bean.id!}"/>
        <fieldset>
            <legend>站点信息</legend>
            <div class="form-group">
                <label class="control-label x120">站点标题：</label>
                <input type="text" name="websiteTitle" value="${bean.websiteTitle!}" placeholder="站点标题" size="50">
            </div>
            <div class="form-group">
                <label class="control-label x120">站点关键词：</label>
                <input type="text" name="websiteKeyword" value="${bean.websiteKeyword!}" placeholder="站点关键词" size="50">
            </div>
            <div class="form-group">
                <label class="control-label x120">站点描述：</label>
                <textarea data-toggle="autoheight" name="websiteDesc" cols="50">${bean.websiteDesc!}</textarea>
            </div>
            <div class="form-group">
                <label class="control-label x120">站点版权：</label>
                <input type="text" name="copyright" value="${bean.copyright!}" placeholder="站点版权" size="50">
            </div>
            <div class="form-group">
                <label class="control-label x120">站点微信：</label>
                <input name="weixinFile" type="file" />
                <img src="${bean.weixin!}" width="80">
            </div>
            <div class="form-group">
                <label class="control-label x120">站点微博：</label>
                <input name="weiboFile" type="file" />
                <img src="${bean.weibo!}" width="80">
            </div>
            <div class="form-group">
                <label class="control-label x120">网站logo：</label>
                <input name="logoImgFile" type="file" />
                <img src="${bean.logoImg!}" width="80">
            </div>
            <div class="form-group">
                <label class="control-label x120">网站Ico：</label>
                <input name="logoIcoFile" type="file" />
                <img src="${bean.logoIco!}" width="80">
            </div>
        </fieldset>  
        
        <fieldset>
            <legend>客服信息</legend>
            <div class="form-group">
                <label for="isShowService" class="control-label x85">客服设置：</label>
                <#list isShowServiceEnums as em>
                <input type="radio" id="isShowService${em.code}" name="isShowService" value="${em.code}" <#if bean.isShowService?? && bean.isShowService==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
                </#list>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label class="control-label x85">客服信息1：</label>
                <input type="text" name="service1" value="${bean.service1!}" placeholder="客服信息1" size="20">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<label class="control-label x85">客服信息2：</label>
                <input type="text" name="service2" value="${bean.service2!}" placeholder="客服信息2" size="20">
            </div>
        </fieldset> 
    
        <fieldset>
            <legend>统计信息</legend>
            <div class="form-group">
                <label for="isEnableStatistics" class="control-label x85">统计设置：</label>
                <#list isEnableStatisticsEnums as em>
                <input type="radio" id="isEnableStatistics${em.code}" name="isEnableStatistics" value="${em.code}" <#if bean.isEnableStatistics?? && bean.isEnableStatistics==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
                </#list>
            </div>
            <div class="form-group">
                <label class="control-label x85">统计代码：</label>
                <textarea data-toggle="autoheight" name="statisticsCode" cols="50">${bean.statisticsCode!}</textarea>
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