<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/course/orderInfo/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师用户编号：</label>
            <input type="text" name="lecturerUserNo" value="" placeholder="讲师用户编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师名称：</label>
            <input type="text" name="lecturerName" value="" placeholder="讲师名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">用户编号：</label>
            <input type="text" name="userNo" value="" placeholder="用户编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">用户注册时间：</label>
            <input type="text" name="registerTime" value="" placeholder="用户注册时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">订单号：</label>
            <input type="text" name="orderNo" value="" placeholder="订单号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课程ID：</label>
            <input type="text" name="courseId" value="" placeholder="课程ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课程名称：</label>
            <input type="text" name="courseName" value="" placeholder="课程名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">应付金额：</label>
            <input type="text" name="pricePayable" value="" placeholder="应付金额" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">优惠金额：</label>
            <input type="text" name="priceDiscount" value="" placeholder="优惠金额" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">实付金额：</label>
            <input type="text" name="pricePaid" value="" placeholder="实付金额" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">平台收入：</label>
            <input type="text" name="platformProfit" value="" placeholder="平台收入" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师收入：</label>
            <input type="text" name="lecturerProfit" value="" placeholder="讲师收入" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">交易类型：1线上支付，2线下支付：</label>
            <input type="text" name="tradeType" value="" placeholder="交易类型：1线上支付，2线下支付" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单：</label>
            <input type="text" name="payType" value="" placeholder="支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">购买渠道：1web，2pp，3微信，4手工绑定：</label>
            <input type="text" name="channelType" value="" placeholder="购买渠道：1web，2pp，3微信，4手工绑定" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">订单状态：1待支付，2成功支付，3支付失败，4已关闭，5已退款，6订单解绑：</label>
            <input type="text" name="orderStatus" value="" placeholder="订单状态：1待支付，2成功支付，3支付失败，4已关闭，5已退款，6订单解绑" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否显示给讲师(1是，0否)：</label>
            <input type="text" name="isShowLecturer" value="" placeholder="是否显示给讲师(1是，0否)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否显示给用户看(1是，0否)：</label>
            <input type="text" name="isShowUser" value="" placeholder="是否显示给用户看(1是，0否)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">客户备注：</label>
            <input type="text" name="remarkCus" value="" placeholder="客户备注" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">支付时间：</label>
            <input type="text" name="payTime" value="" placeholder="支付时间" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>