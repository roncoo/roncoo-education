<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/user/lecturerExt/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">状态(1:正常，0:禁用)：</label>
            <input type="text" name="statusId" value="" placeholder="状态(1:正常，0:禁用)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师用户编号：</label>
            <input type="text" name="lecturerUserNo" value="" placeholder="讲师用户编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">总收入：</label>
            <input type="text" name="totalIncome" value="" placeholder="总收入" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">已提金额：</label>
            <input type="text" name="historyMoney" value="" placeholder="已提金额" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">可提余额：</label>
            <input type="text" name="enableBalances" value="" placeholder="可提余额" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">冻结金额：</label>
            <input type="text" name="freezeBalances" value="" placeholder="冻结金额" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">银行卡号：</label>
            <input type="text" name="bankCardNo" value="" placeholder="银行卡号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">银行名称：</label>
            <input type="text" name="bankName" value="" placeholder="银行名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">银行支行名称：</label>
            <input type="text" name="bankBranchName" value="" placeholder="银行支行名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">银行开户名：</label>
            <input type="text" name="bankUserName" value="" placeholder="银行开户名" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">银行身份证号：</label>
            <input type="text" name="bankIdCardNo" value="" placeholder="银行身份证号" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>