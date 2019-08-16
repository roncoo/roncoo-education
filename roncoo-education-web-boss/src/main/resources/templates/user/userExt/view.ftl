<div class="bjui-pageContent">
	<fieldset>
        <legend>基础信息</legend>
        	<br/>
		    <div class="form-group">
				<div class="col-lg-6">
					<label class="control-label">用户编号：</label>${bean.userNo!}
				</div>
            	<div class="col-lg-6">
            		<label class="control-label">用户类型：</label><#list userTypeEnums as em><#if bean.userType?? && bean.userType==em.code><span class="${em.color}">${em.desc}</span></#if></#list>
        		</div>
			</div>
			<br/>
			<br/>
			<div class="form-group">
		        <div class="col-lg-6">
		            <label class="control-label">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</label>${bean.mobile!}
		        </div>
		        <div class="col-lg-6">
		            <label class="control-label">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label>${bean.nickname!}
		        </div>
		    </div>
		    <br/>
		    <br/>
		    <div class="form-group">
			    <div class="col-lg-6">
	                <label class="control-label">注册时间：</label>${bean.gmtCreate?string('yyy-MM-dd HH:mm:ss')!}
	            </div>
	            <div class="col-lg-6">
	            	<label>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list>
	            </div>
            </div>
            <br/>
	</fieldset>
	<br />
    <fieldset>
        <legend>其他信息</legend> 
        	<br/>
        	 <div class="form-group">
	            <div class="col-lg-12">
	                <label>用户头像：</label><img src="${bean.headImgUrl!}" width="100"/>
	            </div>
	        </div>
	        <#if bean.headImgUrl??>
	        <br />
	        <br />
	        <br />
	        <br />
	        <br />
	        <br />
	        </#if>
	        <br />
	        <br />
		    <div class="form-group">
		        <div class="col-lg-6">
		            <label class="control-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label><#list sexEnums as em><#if bean.sex?? && bean.sex==em.code>${em.desc}</#if></#list>
		        </div>
		        <div class="col-lg-6">
		            <label class="control-label">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</label>${bean.age!}
		        </div>
		    </div>
		    <br />
	        <br />
	        <div class="form-group">
	            <div class="col-lg-12">
	                <label class="control-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>${bean.remark!}
	            </div>
	        </div>
	</fieldset>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
    </ul>
</div>