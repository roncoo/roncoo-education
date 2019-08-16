<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/user/lecturerExt/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="${bean.gmtCreate!}" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="${bean.gmtModified!}" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">状态(1:正常，0:禁用)：</label>
            <input type="text" name="statusId" value="${bean.statusId!}" placeholder="状态(1:正常，0:禁用)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师用户编号：</label>
            <input type="text" name="lecturerUserNo" value="${bean.lecturerUserNo!}" placeholder="讲师用户编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">总收入：</label>
            <input type="text" name="totalIncome" value="${bean.totalIncome!}" placeholder="总收入" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">已提金额：</label>
            <input type="text" name="historyMoney" value="${bean.historyMoney!}" placeholder="已提金额" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">可提余额：</label>
            <input type="text" name="enableBalances" value="${bean.enableBalances!}" placeholder="可提余额" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">冻结金额：</label>
            <input type="text" name="freezeBalances" value="${bean.freezeBalances!}" placeholder="冻结金额" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">银行卡号：</label>
            <input type="text" name="bankCardNo" value="${bean.bankCardNo!}" placeholder="银行卡号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">银行名称：</label>
            <input type="text" name="bankName" value="${bean.bankName!}" placeholder="银行名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">银行支行名称：</label>
            <input type="text" name="bankBranchName" value="${bean.bankBranchName!}" placeholder="银行支行名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">银行开户名：</label>
            <input type="text" name="bankUserName" value="${bean.bankUserName!}" placeholder="银行开户名" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">银行身份证号：</label>
            <input type="text" name="bankIdCardNo" value="${bean.bankIdCardNo!}" placeholder="银行身份证号" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>