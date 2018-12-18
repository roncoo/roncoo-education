<div class="bjui-pageContent">
	<div class="form-group">
        <div class="col-lg-6">
            <label class="control-label">用户编号：</label>${bean.userNo!}
        </div>
        <div class="col-lg-6">
            <label class="control-label">注册时间：</label>
            ${bean.registerTime?string('yyyy-MM-dd HH:mm:ss')}
        </div>
    </div>
    <br/>
    <br/>
	<div class="form-group">
		<div class="col-lg-12">
			<label class="control-label">课程名称：</label>${bean.courseName!}
		</div>
	</div>
	<br/>
    <br/>
	<div class="form-group">
		<div class="col-lg-6">
        	<label class="control-label">讲师名称：</label>${bean.lecturerName!}
        </div>
        <div class="col-lg-6">
        	<label class="control-label">用户手机：</label>${bean.mobile!}
        </div>
	</div>
	<br/>
    <br/>
	<div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">订&nbsp;单&nbsp;号 ：</label>${bean.orderNo!}
		</div>
		<div class="col-lg-6">
			<label class="control-label">流&nbsp;水&nbsp;号 ：</label>${bean.serialNumber!}
		</div>
	</div>
	<br/>
    <br/>
	<div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">交易类型：</label>
			<#list tradeTypeEnums as em> <#if em.code == bean.tradeType><font color="blue">${em.desc!}</font></#if> </#list>
		</div>
		<div class="col-lg-6">
			<label class="control-label">支付时间：</label>
			<#if bean.payTime??>
				${bean.payTime?string('yyyy-MM-dd HH:mm:ss')}
			</#if>
		</div>
	</div>
	<br/>
    <br/>
    <div class="form-group">
        <div class="col-lg-12">
            <label class="control-label">客户备注：</label>${bean.remarkCus!}
        </div>
    </div>
    <br/>
    <br/>
    <div class="form-group">
        <div class="col-lg-12">
            <label class="control-label">后台备注：</label>${bean.remark!}
        </div>
    </div>
    <br/>
    <br/>
    <br/>
    <fieldset>
        <legend>金额分成</legend>
        <div class="form-group">
            <div class="col-lg-4">
               <label class="control-label">支付方式：</label>
               <#list payTypeEnums as em> <#if em.code == bean.payType><font color="blue">${em.desc!}</font></#if> </#list>
            </div>
            <div class="col-lg-4">
                <label class="control-label">购买渠道：</label>
            	<#list channelTypeEnums as em> <#if em.code == bean.channelType><font color="blue">${em.desc!}</font></#if> </#list>
            </div>
            <div class="col-lg-4">
               <label class="control-label">订单状态：</label>
               <#list orderStatusEnums as em> <#if em.code == bean.orderStatus><font color="blue">${em.desc!}</font></#if> </#list>
            </div>
        </div>
        <br/>
        <br/>
        <div class="form-group">
            <div class="col-lg-4">
                <label class="control-label">课程金额：</label>${bean.pricePayable?string('0.00')}
            </div>
            <div class="col-lg-4">
                <label class="control-label">订单金额 ：</label>${bean.pricePaid?string('0.00')}
            </div>
            <div class="col-lg-4">
                <label class="control-label">优惠金额 ：</label>${bean.priceDiscount?string('0.00')}
            </div>
        </div>
        <br/>
        <br/>
        <div class="form-group">
            <div class="col-lg-4">
                <label class="control-label">讲师收入：</label>${bean.lecturerProfit?string('0.00')}
            </div>
        </div>
        <br/>
        <br/>
        <div class="form-group">
            <div class="col-lg-12">
                <label class="control-label">平台收入：</label>${bean.platformProfit?string('0.00')} 【平台利润 = 订单金额 - 讲师收入】
            </div>
        </div>   
    </fieldset>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li>
			<button type="button" class="btn-close">取消</button>
		</li>
	</ul>
</div>