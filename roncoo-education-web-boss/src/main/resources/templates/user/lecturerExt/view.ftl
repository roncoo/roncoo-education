<div class="bjui-pageContent">
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">创建时间：</label>${bean.gmtCreate?string('yyy-MM-dd HH:mm:ss')!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">修改时间：</label>${bean.gmtModified?string('yyy-MM-dd HH:mm:ss')!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">状态(1:正常，0:禁用)：</label>${bean.statusId!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">讲师用户编号：</label>${bean.lecturerUserNo!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">总收入：</label>${bean.totalIncome!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">已提金额：</label>${bean.historyMoney!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">可提余额：</label>${bean.enableBalances!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">冻结金额：</label>${bean.freezeBalances!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">银行卡号：</label>${bean.bankCardNo!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">银行名称：</label>${bean.bankName!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">银行支行名称：</label>${bean.bankBranchName!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">银行开户名：</label>${bean.bankUserName!}
		</div>
	</div>
    <div class="form-group">
		<div class="col-lg-6">
			<label class="control-label">银行身份证号：</label>${bean.bankIdCardNo!}
		</div>
	</div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
    </ul>
</div>