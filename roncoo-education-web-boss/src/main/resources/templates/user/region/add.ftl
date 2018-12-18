<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/user/region/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">父id：</label>
            <input type="text" name="parentId" value="" placeholder="父id" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">中心经度：</label>
            <input type="text" name="centerLng" value="" placeholder="中心经度" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">中心维度：</label>
            <input type="text" name="centerLat" value="" placeholder="中心维度" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">省Id：</label>
            <input type="text" name="provinceId" value="" placeholder="省Id" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">区域编码（国标）：</label>
            <input type="text" name="provinceCode" value="" placeholder="区域编码（国标）" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">省名称：</label>
            <input type="text" name="provinceName" value="" placeholder="省名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">市Id：</label>
            <input type="text" name="cityId" value="" placeholder="市Id" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">城市编码：</label>
            <input type="text" name="cityCode" value="" placeholder="城市编码" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">市名称：</label>
            <input type="text" name="cityName" value="" placeholder="市名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">区域名称：</label>
            <input type="text" name="regionName" value="" placeholder="区域名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">区名称：</label>
            <input type="text" name="districtName" value="" placeholder="区名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">全路径名称：</label>
            <input type="text" name="mergerName" value="" placeholder="全路径名称" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>