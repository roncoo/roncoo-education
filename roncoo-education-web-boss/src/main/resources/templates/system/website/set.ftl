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
        </fieldset>  
        
        <fieldset>
            <legend>客服信息</legend>
            <div class="form-group">
                <label for="isShowService" class="control-label x85">客服设置：</label>
                <#list isShowServiceEnums as em>
                <input type="radio" id="isShowService${em.code}" name="isShowService" value="${em.code}" <#if bean.isShowService?? && bean.isShowService==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
                </#list>
            </div>
            <div class="form-group">
                <label class="control-label x85">客服信息1：</label>
                <input type="text" name="service1" value="${bean.service1!}" placeholder="客服信息1" size="20">
                <label class="control-label x85">客服信息2：</label>
                <input type="text" name="service2" value="${bean.service2!}" placeholder="客服信息2" size="20">
            </div>
            <div class="form-group">
                <label class="control-label x85">客服信息3：</label>
                <input type="text" name="service3" value="${bean.service3!}" placeholder="客服信息3" size="20">
                <label class="control-label x85">客服信息4：</label>
                <input type="text" name="service4" value="${bean.service4!}" placeholder="客服信息4" size="20">
            </div>
            <div class="form-group">
                <label class="control-label x85">客服信息5：</label>
                <input type="text" name="service5" value="${bean.service5!}" placeholder="客服信息5" size="20">
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
        
        <fieldset>
            <legend>扩展信息</legend>
            <div class="form-group">
                <label for="polyvStatus" class="control-label x120">保利威视设置：</label>
                <#list polyvStatusEnums as em>
                <input type="radio" id="polyvStatus${em.code}" name="polyvStatus" value="${em.code}" <#if bean.polyvStatus?? && bean.polyvStatus==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
                </#list>
                
                <label class="control-label x120">视频水印：</label>
                <input name="polyvLogoFile" type="file" />
                <img src="${bean.polyvLogo!}" width="150">
            </div>
            
            <div class="form-group">
                <label for="isWatermark" class="control-label x120">图片水印设置：</label>
                <#list isWatermarkEnums as em>
                <input type="radio" id="isWatermark${em.code}" name="isWatermark" value="${em.code}" <#if bean.isWatermark?? && bean.isWatermark==em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
                </#list>
            
                <label class="control-label x120">水印x,y轴：</label>
                <input type="text" name="picxy" value="${bean.picx!},${bean.picy!}" placeholder="水印x,y轴位置" size="20">
                
                <label class="control-label x120">水印图片：</label>
                <input name="picWatermarkFile" type="file" />
                <img src="${bean.picWatermark!}" width="150">
            </div>
            
            <div class="form-group">
                <label class="control-label x120">logoImg：</label>
                <input name="logoImgFile" type="file" />
                <img src="${bean.logoImg!}" width="80">
            </div>
            <div class="form-group">
                <label class="control-label x120">logoIco：</label>
                <input name="logoIcoFile" type="file" />
                <img src="${bean.logoIco!}" width="80">
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