<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/course/orderPay/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">订单号：</label>
            <input type="text" name="orderNo" value="" placeholder="订单号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">流水号：</label>
            <input type="text" name="serialNumber" value="" placeholder="流水号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">订单状态：1待支付，2成功支付，3支付失败，4已关闭，5已退款, 6订单解绑：</label>
            <input type="text" name="orderStatus" value="" placeholder="订单状态：1待支付，2成功支付，3支付失败，4已关闭，5已退款, 6订单解绑" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单：</label>
            <input type="text" name="payType" value="" placeholder="支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单" size="20">
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