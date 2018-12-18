<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/system/website/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">状态(1有效, 0无效)：</label>
            <input type="text" name="statusId" value="" placeholder="状态(1有效, 0无效)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">站点标题：</label>
            <input type="text" name="websiteTitle" value="" placeholder="站点标题" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">站点关键词：</label>
            <input type="text" name="websiteKeyword" value="" placeholder="站点关键词" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">站点描述：</label>
            <input type="text" name="websiteDesc" value="" placeholder="站点描述" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否开启统计：</label>
            <input type="text" name="isEnableStatistics" value="" placeholder="是否开启统计" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">统计代码：</label>
            <input type="text" name="statisticsCode" value="" placeholder="统计代码" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否显示客服信息：</label>
            <input type="text" name="isShowService" value="" placeholder="是否显示客服信息" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>