<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/system/websiteRecruit/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">状态(1有效,0无效)：</label>
            <input type="text" name="statusId" value="" placeholder="状态(1有效,0无效)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">招募分类(1讲师招募,2代理招募)：</label>
            <input type="text" name="recruitType" value="" placeholder="招募分类(1讲师招募,2代理招募)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">招募标题：</label>
            <input type="text" name="recruitTitle" value="" placeholder="招募标题" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">招募信息：</label>
            <input type="text" name="recruitInfo" value="" placeholder="招募信息" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">入驻协议：</label>
            <input type="text" name="entryAgreement" value="" placeholder="入驻协议" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>