<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/system/websiteRecruit/update" data-toggle="validate" method="post">
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
            <label class="control-label x85">状态(1有效,0无效)：</label>
            <input type="text" name="statusId" value="${bean.statusId!}" placeholder="状态(1有效,0无效)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">招募分类(1讲师招募,2代理招募)：</label>
            <input type="text" name="recruitType" value="${bean.recruitType!}" placeholder="招募分类(1讲师招募,2代理招募)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">招募标题：</label>
            <input type="text" name="recruitTitle" value="${bean.recruitTitle!}" placeholder="招募标题" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">招募信息：</label>
            <input type="text" name="recruitInfo" value="${bean.recruitInfo!}" placeholder="招募信息" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">入驻协议：</label>
            <input type="text" name="entryAgreement" value="${bean.entryAgreement!}" placeholder="入驻协议" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>