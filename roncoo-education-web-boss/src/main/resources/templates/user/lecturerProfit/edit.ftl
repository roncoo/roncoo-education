<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/user/lecturerProfit/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="${bean.gmtCreate!}" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师用户编号：</label>
            <input type="text" name="lecturerUserNo" value="${bean.lecturerUserNo!}" placeholder="讲师用户编号" size="20">
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
		<div class="form-group">
            <label class="control-label x85">讲师收入：</label>
            <input type="text" name="lecturerProfit" value="${bean.lecturerProfit!}" placeholder="讲师收入" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">平台收入：</label>
            <input type="text" name="platformProfit" value="${bean.platformProfit!}" placeholder="平台收入" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">分润状态（1确认中，2成功，3失败）：</label>
            <input type="text" name="profitStatus" value="${bean.profitStatus!}" placeholder="分润状态（1确认中，2成功，3失败）" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>