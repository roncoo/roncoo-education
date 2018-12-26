<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/user/lecturer/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<input type="hidden" name="lecturerUserNo" value="${bean.lecturerUserNo}"/>
		<input type="hidden" name="lecturerProportion" value="${bean.lecturerProportion*100}">
		<table class="table-condensed" width="100%">
        	<tr>
	            <td><label>一、${bean.lecturerName!''}</label></td>
	        </tr>
        	<tr>
        		<td><label class="control-label x85">讲师名称：</label><input name="lecturerName" value="${bean.lecturerName!''}"/ size="20"></td>
        		<td><label class="control-label x85">讲师职位：</label><input name="position" value="${bean.position!''}"/ size="20"></td>
        	</tr>
        	<tr>
                <td><label class="control-label x85">手机号码：</label><input name="lecturerMobile" disabled value="${bean.lecturerMobile!''}"/ size="20"></td>
                <td><label class="control-label x85">讲师邮箱：</label><input name="lecturerEmail" value="${bean.lecturerEmail!''}"/ size="20"></td>
            </tr>
        	<tr>
        		<td><label class="control-label x85">讲师排序：</label><input name="sort" value="${bean.sort}" data-toggle="spinner" data-min="0"  data-step="1" size="20" /></td>
        	</tr>
        	<tr>
        		<td colspan="2"><label class="control-label x85">讲师简介：</label><textarea name="introduce" rows="3" style="width:581px;">${bean.introduce!''}</textarea>
        	<tr/>
        	<tr>
	            <td><label>二、讲师分成及银行信息</label></td>
	        </tr>
	        <tr>
                <td><label class="control-label x85">银行名称：</label><input name="bankName" disabled value="${bean.lecturerExtVO.bankName!''}"/ size="20"></td>
                <td><label class="control-label x85">银行卡号：</label><input name="bankCardNo" disabled value="${bean.lecturerExtVO.bankCardNo!''}"/ size="20"></td>
            </tr>
        	<tr>
        		<td><label class="control-label x85">开户名称：</label><input name="bankUserName" disabled value="${bean.lecturerExtVO.bankUserName!''}"/ size="20"></td>
        		<td><label class="control-label x85">身份证号：</label><input name="bankIdCardNo" disabled value="${bean.lecturerExtVO.bankIdCardNo!''}"/ size="20"></td>
        	</tr>
        	<tr>
        		<td><label class="control-label x85">支行名称：</label><input name="bankBranchName" disabled value="${bean.lecturerExtVO.bankBranchName!''}"/ size="20"></td>
        	</tr>
        	<tr>
				<td colspan="3">
            		<span class="fa fa-info-circle red"> 注:修改立即生效，不用审核</span>
       			</td>
			</tr>
        </table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>