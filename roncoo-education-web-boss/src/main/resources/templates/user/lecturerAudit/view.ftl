<div class="bjui-pageContent">
    <fieldset>
        <legend>讲师个人信息</legend>
        <div class="form-group">
            <div class="col-lg-4"><label>头像：</label><img src="${bean.headImgUrl!}" width="100px" height="100px"/></div>
        	<div class="col-lg-4"></div>
        </div>
       
        <div class="form-group">
        	<br/>
            <div class="col-lg-4"><label>讲师编号：</label>${bean.lecturerUserNo}</div>
            <div class="col-lg-4"><label>审核状态：</label><#list auditStatusEnums as em><#if bean.auditStatus?? && bean.auditStatus==em.code><span class="${em.color}">${em.desc}</span></#if></#list></div>
        </div>
        
        <div class="form-group">
        	<br/>
        	<br/>
            <div class="col-lg-4"><label>昵称：</label>${bean.lecturerName!}</div>
            <div class="col-lg-4"><label>状态：</label><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list></div>
        </div>
        <br/>
        <br/>
        <br/>
        <br/>
        <div class="form-group">
            <div class="col-lg-4"><label>手机号码：</label>${bean.lecturerMobile!}</div>
            <div class="col-lg-4"><label>邮箱：</label>${bean.lecturerEmail}</div>
            <div class="col-lg-4"><label>职位：</label>${bean.position!}</div>
        </div>
        <br/>
        <br/>
        <div class="form-group">
        	<div class="col-lg-4"><label>注册时间：</label>${bean.gmtCreate?string('yyyy-MM-dd HH:mm:ss')}</div>
            <div class="col-lg-4"><label>排序：</label>${bean.sort}</div>
        </div>
    </fieldset>    
    
   <fieldset>
        <legend>简介</legend>    
        <div class="form-group">
            <div class="col-lg-12">${bean.introduce!}</div>
        </div>
    </fieldset>    

   <fieldset>
        <legend>分成情况</legend>
        <div class="form-group">
            <div class="col-lg-4"><label>讲师分成：</label>${bean.lecturerProportion!*100}%</div>
            <div class="col-lg-4"><label>累计总收入：</label>${bean.lecturerExtVO.totalIncome!} 元</div>
        </div>
        <br/>
        <br/>
        <div class="form-group">
            <div class="col-lg-4"><label>已提现金额：</label>${bean.lecturerExtVO.historyMoney!} 元</div>
            <div class="col-lg-4"><label>可提现余额：</label>${bean.lecturerExtVO.enableBalances!} 元</div>
            <div class="col-lg-4"><label>冻结的金额：</label>${bean.lecturerExtVO.freezeBalances!} 元</div>
        </div>
        <br/>
        <br/>
        <div class="form-group">
            <div class="col-lg-4"><label>银行卡号：</label>${bean.lecturerExtVO.bankCardNo!}</div>
            <div class="col-lg-4"><label>银行名称：</label>${bean.lecturerExtVO.bankName!}</div>
            <div class="col-lg-4"><label>支行名称：</label>${bean.lecturerExtVO.bankBranchName!''}</div>
        </div>
        <br/>
        <br/>
        <div class="form-group">
            <div class="col-lg-4"><label>开户姓名：</label>${bean.lecturerExtVO.bankUserName!}</div>
            <div class="col-lg-4"><label>身份证号：</label>${bean.lecturerExtVO.bankIdCardNo!}</div>
        </div>
    </fieldset>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
    </ul>
</div>