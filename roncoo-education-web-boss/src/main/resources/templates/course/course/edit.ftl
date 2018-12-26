<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/course/course/update" data-toggle="validate" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${bean.id}"/>
		<input type="hidden" name="introduceId" value="${bean.introduceId}"/>
		<table class="table-condensed" width="100%">
			<tr>
				<td colspan="3">
					<label class="control-label x85">课程封面：</label>
					<input type="file" id="actImg" name="advFile" />
					<span id="doc_span_pic"><img src="${bean.courseLogo!}" width="100"> <input type="hidden" name="courseImg" value="${bean.courseImg!}" /> </span>
				</td>
			</tr>
			<tr><td></td></tr>
			<tr>
				<td  colspan="3">
					<label class="control-label x85">课程名称：</label>
					<input name="courseName" size="80" value="${bean.courseName}" />
				</td>
			</tr>
			<tr><td></td></tr>
			<tr>
				<td style="width:350px">
					<label class="control-label x85">是否收费：</label>
					<#list isFreeEnums as em>
	                	<input type="radio" id="isFree" name="isFree" value="${em.code}" <#if bean.isFree?? && bean.isFree == em.code>checked</#if> data-toggle="icheck" data-label="${em.desc}" size="20">
	             	</#list>
				</td>
				<td style="width:350px">
					<label class="control-label x85">原&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</label>
					<input name="courseOriginal" value="${bean.courseOriginal}" data-toggle="spinner" data-min="0" data-step="1" size="10" /> 
				</td>
				<td>
					<label class="control-label x85">优&nbsp;惠&nbsp;价：</label>
					<input name="courseDiscount" value="${bean.courseDiscount}" data-toggle="spinner" data-min="0" data-step="1" size="10" /> 
				</td>
			</tr>
			<tr><td></td></tr>
			<tr>
				<td colspan="3">
					<label class="control-label x85">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序：</label>
					<input name="sort" value="${bean.sort}" data-toggle="spinner" data-min="0" data-step="1" size="10" />
				</td>
			</tr>
			<tr><td></td></tr>
			<tr>
				<td colspan="3">
					<label class="control-label x85">课程简介：</label>
					<br />
					<div style="margin-left: 90px; width:800px" id="courseEditEditor" ><p>${bean.introduce!}</p></div>
            		<textarea id="courseEditDesc" name="introduce" style="display:none" style="width:100%; height:200px;"></textarea>
				</td>
			</tr>
			<tr><td></td></tr>
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
<script type="text/javascript">
	initEdit('#courseEditEditor','${base}/upload');
</script>